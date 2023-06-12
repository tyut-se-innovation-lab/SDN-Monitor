package publictest;

import org.junit.Test;
import team.sdn.sdnutils.NetflowUtils;
import team.sdn.util.HttpSender;

/**
 * @author Big-Bai
 **/
public class NetflowUtilsTest {
    @Test
    public void datasourceOfSwitchPortTest(){
//        System.err.println(NetflowUtils.datasourceOfSwitchPort("ALL"));
        System.err.println(NetflowUtils.bytesPortInputLastSecond("106"));
//        System.err.println(NetflowUtils.pktsPortInputLastSecond("100"));
//        System.err.println(NetflowUtils.bytesPortOutputLastSecond("100"));
//        System.err.println(NetflowUtils.pktsPortOutputLastSecond("100"));
//        System.err.println(NetflowUtils.portBandwidthUtilizationLastSecond("100"));

//        System.out.println(HttpSender.get("http://192.168.1.250:8008/metric/192.168.1.250/max:100.ifinoctets/json"));
//                                         http://192.168.1.250:8008/metric/192.168.1.250/max:100.ifinoctets/json
    }
}
