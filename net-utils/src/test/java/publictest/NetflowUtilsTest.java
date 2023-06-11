package publictest;

import org.junit.Test;
import team.sdn.sdnutils.NetflowUtils;

/**
 * @author Big-Bai
 **/
public class NetflowUtilsTest {
    @Test
    public void datasourceOfSwitchPortTest(){
        System.out.println(NetflowUtils.datasourceOfSwitchPort("s1-eth1"));
    }
    @Test
    public void getBytesTest(){
        System.out.println(NetflowUtils.bytesPortInputLastSecond("ALL"));
    }
}
