import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.sdn.net.traffic.entity.Traffic;
import team.sdn.net.traffic.mapper.TrafficMapper;

import java.util.List;

/**
 * @author TokisakiKurumi
 * @className TrafficMapperTest
 * @date 2023/6/11
 * @description
 **/
@SpringBootTest(classes = {com.net.RuoYiApplication.class})
public class TrafficMapperTest {
    @Autowired
    public TrafficMapper mapper;

    @Test
    public void testMapper() {
        List<Traffic> traffic = mapper.getTraffic("2023-6-13 10:53:59", "");
        System.out.println(traffic);
    }

}
