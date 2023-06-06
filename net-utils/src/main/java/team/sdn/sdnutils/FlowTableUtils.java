package team.sdn.sdnutils;

import team.sdn.config.Address;
import team.sdn.domain.OpenFlow;
import team.sdn.util.HttpSender;

import java.util.List;

/**
 * 流表相关工具
 * @author Big-Bai
 **/
public class FlowTableUtils {

    /**
     * 为某个交换机下发流表
     * @param switchId 交换机id
     * @param flow 流表内容
     * @return 是否下发成功
     */
    public static boolean addFlowForSwitch(String switchId,OpenFlow flow){
        return true;
    }

    /**
     * 获取某个交换器的某个流表的所有流表项
     * @param switchId 交换机id
     * @return 流表们
     */
    public static String getAllFlowsOfTable(String switchId,String tableId){
        return HttpSender.get(Address.ONE_FLOW[0]+switchId+Address.ONE_FLOW[1]+tableId);
    }

    /**
     * 删除某个交换机的某个流表
     * @param switchId 交换机id
     * @param tableId 流表id
     * @return 是否成功删除
     */
    public static boolean deleteFlowTable(String switchId,String tableId){
        return true;
    }


    /**
     * 修改某个交换机的某个流表内容
     * @param switchId 交换机id
     * @param tableId 流表id
     * @return 是否成功删除
     */
    public static boolean modifyFlowTable(String switchId,String tableId){
        return false;
    }
}
