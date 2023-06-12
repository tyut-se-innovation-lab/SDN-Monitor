package publictest;

import org.junit.Test;
import team.sdn.sdnutils.NetflowUtils;

/**
 * @author Big-Bai
 **/
public class NetflowUtilsTest {
    @Test
    public void datasourceOfSwitchPortTest(){
        System.err.println(NetflowUtils.datasourceOfSwitchPort("s1-eth1"));
        System.err.println(NetflowUtils.bytesPortInputLastSecond("100"));
        System.err.println(NetflowUtils.pktsPortInputLastSecond("100"));
        System.err.println(NetflowUtils.bytesPortOutputLastSecond("100"));
        System.err.println(NetflowUtils.pktsPortOutputLastSecond("100"));
        System.err.println(NetflowUtils.portBandwidthUtilizationLastSecond("100"));
    }
}
