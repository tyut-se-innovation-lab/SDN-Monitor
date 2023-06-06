package team.sdn.sdnutils;

import team.sdn.config.Address;
import team.sdn.util.HttpSender;

/**
 * 拓扑（拓扑图、主机、交换机等信息）相关工具
 * @author Big-Bai
 **/
public class TopologyUtils {
    //拓扑
    /**
     * 获取网络拓扑
     * @return 拓扑类
     */
    public static String getTopology(){
        return  HttpSender.get(Address.ODL_ADDRESS+Address.TOPOLOGY,"json");
    }

    //交换机
    /**
     * 获取所有交换机信息
     * @return 所有交换机信息
     */
    public static String getAllSwitch(){
        return HttpSender.get(Address.NODES);
    }

    /**
     * 根据id获取某个交换机的信息
     * @param switchId 交换机的id
     * @return 交换机信息
     */
    public static String getSwitchById(String switchId){
        return HttpSender.get(Address.NODE_INFO+switchId);
    }


}
