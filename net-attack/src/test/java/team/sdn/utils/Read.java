package team.sdn.utils;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Read {

    @Test
    public void testReadFile(){
        ReadFile.saveParam();
        //System.out.println(ReadFile.calPercent(1, 4));
    }

}
