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
//        System.err.println(NetflowUtils.bytesPortOutputLastSecond("", SFlowStatistic.SUM));
//        System.err.println(NetflowUtils.bytesPortOutputLastSecond("106", SFlowStatistic.AVG));
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
    }
}
