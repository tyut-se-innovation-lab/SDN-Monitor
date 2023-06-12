package team.sdn.config;

/**
 * 流量相关地址
 * @author Big-Bai
 **/
public class SFlowAddress {
    /**
     * sflow地址
     */
    public static String SFLOW_ADDRESS = "http://192.168.1.250:8008";
    /**
     *0 + Agent + 1 + statistic + 2 + datasource + 3 + metric + 4
     */
    public static String[] METRIC = {"/metric/","/",":",".","/json"};
}
