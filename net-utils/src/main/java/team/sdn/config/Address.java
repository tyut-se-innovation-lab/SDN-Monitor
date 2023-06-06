package team.sdn.config;

/**
 * 各种地址
 * @author Big-Bai
 **/
public class Address {
    /**
     * opendaylight控制器地址
     */
    public static String ODL_ADDRESS = "http://192.168.1.250:8181";

    /**
     * 查询流表<br/>
     * GET<br/>
     * o+nodeId+1+tableId+2+flowId
     */
    public static String[] QUERY_FLOW_TABLE_GET_NODE = {"/restconf/operational/opendaylight-inventory:nodes/node/","/flow-node-inventory:table/","/flow/"};

    /**
     * 获取拓扑图
     * GET
     */
    public static String TOPOLOGY = "/restconf/operational/network-topology:network-topology";

    /**
     * 某个节点的信息<br/>
     * GET
     * 拼接交换机id
     */
    public static String NODE_INFO="/restconf/operational/opendaylight-inventory:nodes/node/";

    /**
     * 所有交换机信息
     * GET
     */
    public static String NODES = "/restconf/operational/opendaylight-inventory:nodes";

    /**
     * 查询某个端口信息<br/>
     * 0 + 交换机id + 1 + 交换机端口id
     * GET
     */
    public static String[] NODE_CONNECTOR = {"/restconf/operational/opendaylight-inventory:nodes/node/","/opendaylight-inventory:node-connector/"} ;


    public static String[] ONE_FLOW = {"/restconf/operational/opendaylight-inventory:nodes/node/","/flow-node-inventory:table/0/flow/"};


}
