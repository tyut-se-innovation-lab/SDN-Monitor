package publictest;

import org.junit.Test;
import team.sdn.enums.SFlowStatistic;
import team.sdn.sdnutils.NetflowUtils;
import team.sdn.util.HttpSender;

/**
 * @author Big-Bai
 **/
public class NetflowUtilsTest {
    private static final String TEST_AGENT = "27";
    private static final String TEST_AGENT2 = "";
    @Test
    public void datasourceOfSwitchPortTest(){
        System.err.println(NetflowUtils.bytesPortOutputLastSecond(TEST_AGENT2, SFlowStatistic.SUM));
        System.err.println(NetflowUtils.bytesPortOutputLastSecond(TEST_AGENT, SFlowStatistic.AVG));
        System.out.println(NetflowUtils.bytesPortInputLastSecond("106", SFlowStatistic.AVG));
        System.out.println(NetflowUtils.bytesPortInputLastSecond("106", SFlowStatistic.AVG));
        System.out.println(NetflowUtils.pktsPortInputLastSecond("106", SFlowStatistic.AVG));
        System.out.println(NetflowUtils.pktsPortOutputLastSecond("106", SFlowStatistic.AVG));
        System.out.println(NetflowUtils.portBandwidthUtilizationLastSecond("106", SFlowStatistic.AVG));
        System.err.println("_____________________________________________________________");
        System.out.println(NetflowUtils.bytesPortInputLastSecond("", SFlowStatistic.SUM));
        System.out.println(NetflowUtils.bytesPortInputLastSecond("", SFlowStatistic.SUM));
        System.out.println(NetflowUtils.pktsPortInputLastSecond("", SFlowStatistic.SUM));
        System.out.println(NetflowUtils.pktsPortOutputLastSecond("", SFlowStatistic.SUM));
        System.out.println(NetflowUtils.portBandwidthUtilizationLastSecond("", SFlowStatistic.SUM));
        System.err.println("---------------------------------------------------------------");
        System.err.println(NetflowUtils.ifInUtilization(TEST_AGENT,SFlowStatistic.SUM));
        System.err.println(NetflowUtils.ifInUtilization("",SFlowStatistic.SUM));
        System.err.println("---------------------------------------------------------------");
        System.err.println(NetflowUtils.ifOutUtilization(TEST_AGENT,SFlowStatistic.SUM));
        System.err.println(NetflowUtils.ifOutUtilization("",SFlowStatistic.SUM));
    }
}
