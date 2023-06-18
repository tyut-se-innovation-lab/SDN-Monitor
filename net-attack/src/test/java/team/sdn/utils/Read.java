package team.sdn.utils;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Read {

    @Test
    public void testReadFile(){
        ReadFile.read("D:\\Alone_0456\\project\\SDN-Monitor\\net-attack\\src\\main\\resources\\KDDTest+.txt");
        //System.out.println(ReadFile.calPercent(1, 4));
    }

}
