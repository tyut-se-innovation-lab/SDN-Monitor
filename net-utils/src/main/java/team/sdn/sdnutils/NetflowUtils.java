package team.sdn.sdnutils;

/**
 * 流量相关工具
 * @author Big-Bai
 **/
public class NetflowUtils {
    /**
     * 获取某个端口的datasource编号
     * @param switchPortId 端口id
     * @return datasource编号
     */
    public static String datasourceOfSwitchPort(String switchPortId){
        return null;
    }
    /**
     * 获取某个端口过去一秒输入的字节数
     * @param datasource 端口编号
     * @return 请求返回值
     */
    public static String bytesPortInputLastSecond(String datasource){
        return null;
    }

    /**
     * 获取某个端口过去一秒输入的包数
     * @param datasource 端口编号
     * @return 请求返回值
     */
    public static String pktsPortInputLastSecond(String datasource){
        return null;
    }
    /**
     * 获取某个端口过去一秒输出的字节数
     * @param datasource 端口编号
     * @return 请求返回值
     */
    public static String bytesPortOutputLastSecond(String datasource){
        return null;
    }
    /**
     * 获取某个端口过去一秒输出的包数
     * @param datasource 端口编号
     * @return 请求返回值
     */
    public static String pktsPortOutputLastSecond(String datasource){
        return null;
    }

    /**
     * 获取某端口带宽利用率(发送字节数占总带宽的百分比)
     * @param datasource 端口编号
     * @return 请求返回值
     */
    public static String portBandwidthUtilizationLastSecond(String datasource){
        return null;
    }




}
