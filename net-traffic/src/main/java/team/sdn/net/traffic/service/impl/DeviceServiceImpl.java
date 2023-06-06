package team.sdn.net.traffic.service.impl;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import team.sdn.net.traffic.entity.Host;
import team.sdn.net.traffic.entity.Switch;
import team.sdn.net.traffic.service.DeviceService;
import team.sdn.net.traffic.utils.JsonConvertUtil;
import team.sdn.sdnutils.TopologyUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TokisakiKurumi
 * @className DeviceServiceImpl
 * @date 2023/6/2
 * @description DeviceService接口实现类
 **/
@Service
public class DeviceServiceImpl implements DeviceService {
    @Override
    public String getTopology() {
        return TopologyUtils.getTopology();
    }

    @Override
    public List<Host> getHost() {
        List<Host> hosts = new ArrayList<>();
        Document document = null;
        try {
            document = JsonConvertUtil.builder.parse(TopologyUtils.getTopology());
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        NodeList hostAddresses = document.getElementsByTagName("addresses");
        NodeList hostPoints = document.getElementsByTagName("attachment-points");
        for (int i = 0; i < hostAddresses.getLength(); i++) {
            Node address = hostAddresses.item(i);
            Node point = hostPoints.item(i);
            NodeList addressChildNodes = address.getChildNodes();
            NodeList pointChildNodes = point.getChildNodes();
            hosts.add(new Host(pointChildNodes.item(1).getTextContent()
                    ,addressChildNodes.item(1).getTextContent()
                    ,addressChildNodes.item(4).getTextContent()
                    ,pointChildNodes.item(1).getTextContent()
                    ,pointChildNodes.item(0).getTextContent()));
        }
        return hosts;
    }

    @Override
    public List<Switch> getSwitch() {
        List<Switch> switches = new ArrayList<>();
        Document document = null;
        try {
            document = JsonConvertUtil.builder.parse(TopologyUtils.getAllSwitch());
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        NodeList ids = document.getElementsByTagName("id");
        NodeList connectors = document.getElementsByTagName("node-connector");
        for (int i = 0; i < connectors.getLength(); i++) {

        }
        return null;
    }
}
