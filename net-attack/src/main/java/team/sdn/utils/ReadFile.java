package team.sdn.utils;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ReadFile {
    private static final int PARAMETER_NUM = 43;
    private static Map<String, AtomicInteger>[] record;
    private static String[] paramName;
    static {
        paramName = new String[]{" ","Duration","Protocol Type","Service","Flag","Src Bytes",
            "Dst Bytes","Land","Wrong Fragment","Urgent","Hot",
            "Num Failed Logins","Logged In","Num Compromised","Root Shell","Su Attempted",
            "Num Root","Num File Creations","Num Shells","Num Access Files","Num Outbound Cmds",
            "Is Hot Logins","Is Guest Login","Count","Srv Count","Serror Rate",
            "Srv Serror Rate","Rerror Rate","Srv Rerror Rate","Same Srv Rate","Diff Srv Rate",
            "Srv Diff Host Rate","Dst Host Count","Dst Host Srv Count","Dst Host Same Srv Rate","Dst Host Diff Srv Rate",
            "Dst Host Same Src Port Rate","Dst Host Srv Diff Host Rate","Dst Host Serror Rate","Dst Host Srv Serror Rate","Dst Host Rerror Rate",
            "Dst Host Srv Rerror Rate","Class","Difficulty Level"};

        record = new Map[PARAMETER_NUM];
        for (int i = 0; i < PARAMETER_NUM; i++) {
            record[i] = new HashMap<>();
        }
    }

    public static void read(String filePath) {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        String fileName = "D:\\Alone_0456\\project\\SDN-Monitor\\net-attack\\data\\";

        try {
            fileInputStream = new FileInputStream(filePath);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String context = "";
            int allsum = 0;
            while ((context = bufferedReader.readLine()) != null) {
                allsum++;
                String[] split = context.split(",");
                for (int i = 0; i < split.length; i++) {
                    if (!record[i].containsKey(split[i])) {
                        record[i].put(split[i], new AtomicInteger(0));
                    }
                    record[i].get(split[i]).incrementAndGet();
                }
            }
            final double sum = allsum;
            AtomicInteger num = new AtomicInteger(0);
            Arrays.stream(record).collect(Collectors.toList()).forEach(map -> {
                File file = new File(fileName + num.incrementAndGet() + "-" + paramName[num.intValue()] + ".txt");
                BufferedWriter writer;
                try {
                    file.createNewFile();
                    writer = new BufferedWriter(new FileWriter(file));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                map.entrySet().stream()
                        .sorted((a,b) -> b.getValue().intValue() - a.getValue().intValue())
                        .forEach(entry ->{
                            try {
                                String a = String.format("%-15s -> %06.3f%%",entry.getKey() ,(entry.getValue().doubleValue() / sum) * 100);
                                writer.write(a);
                                writer.newLine();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Map<String, Double> getProType(String context, int index) {
        HashMap<String, Double> stringDoubleHashMap = new HashMap<>();
        String[] split = context.split(",");

        return stringDoubleHashMap;
    }
}
