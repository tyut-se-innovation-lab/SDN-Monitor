package publictest;

import org.junit.Test;
import team.sdn.enums.SFlowStatistic;
import team.sdn.sdnutils.NetflowUtils;
import team.sdn.util.HttpSender;

/**
 * @author Big-Bai
 **/
public class NetflowUtilsTest {
    @Test
    public void datasourceOfSwitchPortTest(){
        System.err.println(NetflowUtils.bytesPortOutputLastSecond("", SFlowStatistic.SUM));
        System.err.println(NetflowUtils.bytesPortOutputLastSecond("106", SFlowStatistic.AVG));
    }
}
