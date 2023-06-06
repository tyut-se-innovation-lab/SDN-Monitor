package team.sdn.sdnutils;

import team.sdn.domain.OpenFlow;

import java.util.List;

/**
 * 流表相关工具
 * @author Big-Bai
 **/
public class FlowTableUtils {
    /**
     * 批量下发流表
     * @param flow 流表
     * @return 是否下发成功
     */
    public static Boolean addFlowsBatch(OpenFlow flow){
        return null;
    }

    /**
     * 为某个交换机下发流表
     * @param switchId 交换机id
     * @param flow 流表内容
     * @return 是否下发成功
     */
    public static Boolean addFlowForSwitch(String switchId,OpenFlow flow){
        return null;
    }

    /**
     * 获取所有的流表
     * @return 所有的流表
     */
    public static String getAllFlowTables(){
        return null;
    }

    /**
     * 获取某个交换器的流表
     * @param switchId 交换机id
     * @return 流表们
     */
    public static String getAllTablesOfSwitch(String switchId){
        return null;
    }

    /**
     * 删除某个交换机的某个流表
     * @param switchId 交换机id
     * @param tableId 流表id
     * @return 是否成功删除
     */
    public static Boolean deleteFlowTable(String switchId,String tableId){
        return null;
    }
    /**
     * 删除某个交换机的所有流表
     * @param switchId 交换机id
     * @return 是否成功删除
     */
    public static Boolean deleteAllTableOfSwitch(String switchId){
        return null;
    }

    /**
     * 修改某个交换机的某个流表内容
     * @param switchId 交换机id
     * @param tableId 流表id
     * @return 是否成功删除
     */
    public static Boolean modifyFlowTable(String switchId,String tableId){
        return null;
    }
}
