package team.sdn.sdnutils;

import com.alibaba.fastjson2.JSONObject;
import team.sdn.config.SFlowAddress;
import team.sdn.enums.SFlowStatistic;
import team.sdn.util.HttpSender;

import java.util.HashMap;
import java.util.Map;

/**
 * 流量相关工具
 *
 * @author Big-Bai
 **/
public class NetflowUtils {
    public static String AGENT = "192.168.1.250";
    private static Map<String, String> datasourcePort;

    /**
     * 获取某个端口的datasource编号<br/>
     * 若无返回条件则返回null
     *
     * @return datasource编号
     */
    public static String datasourceOfSwitchPort(String switchPortId) {
        if (datasourcePort == null) {
            Map<String, String> switchDatasource = new HashMap<>();
            JSONObject handle = JSONObject.parse(HttpSender.get(SFlowAddress.SFLOW_ADDRESS + SFlowAddress.METRIC[0] + AGENT + SFlowAddress.METRIC[4]));
            datasourcePort = new HashMap<>();
            for (Map.Entry<String, Object> entry : handle.entrySet()) {
                if (entry.getKey().contains("ifname")) {
                    datasourcePort.put((String) entry.getValue(), entry.getKey().split("\\.")[0]);
                }
            }
        }
        return datasourcePort.get(switchPortId);

    }

    /**
     * 获取某个端口过去一秒输入的字节数<br/>
     * 该方法默认使用max
     *
     * @param datasource 端口编号
     * @return 请求返回值
     */
    public static String bytesPortInputLastSecond(String datasource) {
        return bytesPortInputLastSecond(datasource, SFlowStatistic.MAX);
    }

    /**
     * 获取某个端口过去一秒输入的字节数<br/>
     * 该方法使用自行传入的统计状态
     *
     * @param datasource 端口编号
     * @param statistic  统计状态
     * @return 请求返回值
     */
    public static String bytesPortInputLastSecond(String datasource, SFlowStatistic statistic) {
        return send(datasource, statistic, "ifinoctets");
    }


    /**
     * 获取某个端口过去一秒输入的包数<br/>
     * 该方法默认使用max统计状态
     *
     * @param datasource 端口编号
     * @return 请求返回值
     */
    public static String pktsPortInputLastSecond(String datasource) {
        return pktsPortInputLastSecond(datasource, SFlowStatistic.MAX);
    }

    /**
     * 获取某个端口过去一秒输入的包数<br/>
     * 该方法使用传入的统计状态
     *
     * @param datasource 端口编号
     * @param statistic  统计状态
     * @return 请求返回值
     */
    public static String pktsPortInputLastSecond(String datasource, SFlowStatistic statistic) {
        return send(datasource, statistic, "ifinpkts");
    }

    /**
     * 获取某个端口过去一秒输出的字节数
     *
     * @param datasource 端口编号
     * @return 请求返回值
     */
    public static String bytesPortOutputLastSecond(String datasource) {
        return bytesPortOutputLastSecond(datasource,SFlowStatistic.MAX);
    }

    /**
     * 获取某个端口过去一秒输出的字节数<br/>
     * 该方法使用传入的统计状态
     *
     * @param datasource 端口编号
     * @param statistic  统计状态
     * @return 请求返回值
     */
    public static String bytesPortOutputLastSecond(String datasource,SFlowStatistic statistic){
        return send(datasource,statistic,"ifoutoctets");
    }
    /**
     * 获取某个端口过去一秒输出的包数
     *
     * @param datasource 端口编号
     * @return 请求返回值
     */
    public static String pktsPortOutputLastSecond(String datasource) {
        return pktsPortOutputLastSecond(datasource,SFlowStatistic.MAX);
    }

    /**
     * 获取某个端口过去一秒输出的包数<br/>
     * 该方法使用传入的统计状态
     *
     * @param datasource 端口编号
     * @param statistic  统计状态
     * @return 请求返回值
     */
    public static String pktsPortOutputLastSecond(String datasource,SFlowStatistic statistic) {
        return send(datasource,statistic,"ifoutpkts");
    }
    /**
     * 获取某端口带宽利用率(发送字节数占总带宽的百分比)<br/>
     * 该方法默认使用msx统计状态
     * @param datasource 端口编号
     * @return 请求返回值
     */
    public static String portBandwidthUtilizationLastSecond(String datasource) {
        return portBandwidthUtilizationLastSecond(datasource,SFlowStatistic.MAX);
    }
    /**
     * 获取某端口带宽利用率(发送字节数占总带宽的百分比)<br/>
     * 该方法使用传入的统计状态
     *
     * @param datasource 端口编号
     * @param statistic  统计状态
     * @return 请求返回值
     */
    public static String portBandwidthUtilizationLastSecond(String datasource,SFlowStatistic statistic) {
        return send(datasource,statistic,"ifoututilization");
    }

    private static String send(String datasource, SFlowStatistic statistic, String metric) {
        return HttpSender.get(SFlowAddress.SFLOW_ADDRESS + SFlowAddress.METRIC[0] + AGENT
                + SFlowAddress.METRIC[1] + statistic
                + SFlowAddress.METRIC[2] + datasource
                + SFlowAddress.METRIC[3] + metric
                + SFlowAddress.METRIC[4]);
    }


}
