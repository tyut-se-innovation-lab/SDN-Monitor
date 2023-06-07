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
     * @param tableId 流表id
     * @param flow 流表内容
     * @return 是否下发成功
     */
    public static boolean addFlowForSwitch(String switchId,String tableId,OpenFlow flow){
        return HttpSender.post(Address.ODL_ADDRESS+Address.ADD_FLOW[0]+switchId+Address.ADD_FLOW[1]+tableId,flow);
    }

    /**
     * 获取某个交换器的某个流表的所有流表项
     * @param switchId 交换机id
     * @param tableId 流表id
     * @return 流表们
     */
    public static String getAllFlowsOfTable(String switchId,String tableId){
        return HttpSender.get(Address.ODL_ADDRESS+Address.ONE_FLOW[0]+switchId+Address.ONE_FLOW[1]+tableId);
    }

    /**
     * 删除某个交换机的某个流表项
     * @param switchId 交换机id
     * @param tableId 流表id
     * @param flowId 流表项id
     * @return 是否成功删除
     */
    public static boolean deleteFlowTable(String switchId,String tableId,String flowId){
        return HttpSender.delete(Address.ODL_ADDRESS+Address.DELETE_FLOW[0]+switchId+Address.DELETE_FLOW[1]+tableId+Address.DELETE_FLOW[2]+flowId);
    }


    /**
     * 修改某个交换机的某个流表项内容
     * @param switchId 交换机id
     * @param tableId 流表id
     * @param flowId 流表项id
     * @return 是否成功删除
     */
    public static boolean modifyFlowTable(String switchId,String tableId,String flowId,OpenFlow openFlow){
        return HttpSender.put(Address.ODL_ADDRESS+Address.MODIFY_FLOW[0]+switchId+Address.MODIFY_FLOW[1]+tableId+Address.MODIFY_FLOW[2]+flowId,openFlow);
    }
}
