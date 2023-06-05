package team.sdn.sdnutils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.dom4j.Document;
import org.dom4j.Element;
import team.sdn.config.Address;
import team.sdn.domain.Host;
import team.sdn.domain.Link;
import team.sdn.domain.Switch;
import team.sdn.domain.Topology;
import team.sdn.util.HttpSender;
import team.sdn.util.XMLUtil;

import java.util.ArrayList;
import java.util.List;

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
        return  HttpSender.get(Address.ODL_ADDRESS+Address.TOPOLOGY);
    }
//    @Deprecated
//    public static Topology getTopologyDeprecated(){
//        Document document = XMLUtil.getDocument(HttpSender.get(Address.ODL_ADDRESS+Address.TOPOLOGY));
//        Topology topology = new Topology();
//        List<Host> hosts = new ArrayList<>();
//        List<Switch> switches = new ArrayList<>();
//        List<Link> links = new ArrayList<>();
//        Element root = document.getRootElement();
//
//        List<Element> elements = XMLUtil.getElementsByPath(root,"network-topology","topology","node");
//        List<Element> hostElements = new ArrayList<>();
//        for(Element e:elements){
//            String name = XMLUtil.getTextByElementStringPath(e,"node","node-id");
//            if(name.contains(":")){
//                Host host = new Host();
//                host.setIp(XMLUtil.getTextByElementStringPath(e,"node","addresses","ip"));
//                host.setMac(XMLUtil.getTextByElementStringPath(e,"node","addresses","mac"));
//                host.setNodeId(XMLUtil.getTextByElementStringPath(e,"node","node-id"));
//                host.setFirstSeen(XMLUtil.getTextByElementStringPath(e,"node","addresses","first-seen"));
//                host.setLastSeen(XMLUtil.getTextByElementStringPath(e,"node","addresses","last-seen"));
//                hosts.add(host);
//            }else {
//                Switch sw = new Switch();
//                sw.setNodeId(XMLUtil.getTextByElementStringPath(e,"node","node-id"));
//                sw.setTerminations(new ArrayList<>());
//                List<Element> terminations = XMLUtil.getElementsByPath(e,"node","termination-point");
//                for(Element ee:terminations){
//                    sw.getTerminations().add(XMLUtil.getTextByElementStringPath(ee,"termination-point","tp-id"));
//                }
//                switches.add(sw);
//            }
//        }
//        List<Element> linkElements = XMLUtil.getElementsByPath(root,"network-topology","topology","link");
//        for(Element e:linkElements) {
//            Link link = new Link();
//            link.setPoint1(XMLUtil.getTextByElementStringPath(e,"link","source","source-node"));
//            link.setPoint1Tp(XMLUtil.getTextByElementStringPath(e,"link","source","source-tp"));
//            link.setPoint2(XMLUtil.getTextByElementStringPath(e,"link","destination","dest-node"));
//            link.setPoint2Tp(XMLUtil.getTextByElementStringPath(e,"link","destination","dest-tp"));
//            links.add(link);
//        }
//        topology.setTopologyId(XMLUtil.getTextByElementStringPath(root,"network-topology", "topology", "topology-id"));
//        return topology;
//    }

    //主机
    /**
     * 获取所有主主机信息
     * @return 所有主机信息
     */
    public static List<Host> getAllHost(){
        return null;
    }

    /**
     * 根据id获取某一主机当前信息
     * @param hostId 主机id
     * @return 主机信息
     */
    public static Host getHostById(String hostId){
        return null;
    }

    //交换机
    /**
     * 获取所有交换机信息
     * @return 所有交换机信息
     */
    public static List<Switch> getAllSwitch(){
        return null;
    }

    /**
     * 根据id获取某个交换机的信息
     * @param switchId 交换机的id
     * @return 交换机信息
     */
    public static Switch getSwitchById(String switchId){
        return null;
    }


}
