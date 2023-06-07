package team.sdn.net.traffic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import team.sdn.net.traffic.entity.Host;
import team.sdn.net.traffic.entity.Port;
import team.sdn.net.traffic.entity.Switch;
import team.sdn.net.traffic.service.DeviceService;
import team.sdn.net.traffic.utils.JsonConvertUtil;
import team.sdn.sdnutils.TopologyUtils;


import javax.xml.parsers.DocumentBuilder;
import java.io.IOException;
import java.io.StringReader;
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
    @Autowired
    private DocumentBuilder builder;

    @Override
    public String getTopology() {
        return TopologyUtils.getTopology();
    }

    @Override
    public List<Host> getHost() {
        List<Host> hosts = new ArrayList<>();
        try {
            Document document = builder.parse(new InputSource(new StringReader(TopologyUtils.getTopologyXML())));
            NodeList hostAddresses = document.getElementsByTagName("addresses");
            NodeList hostPoints = document.getElementsByTagName("attachment-points");
            for (int i = 0; i < hostAddresses.getLength(); i++) {
                Node address = hostAddresses.item(i);
                System.out.println(address);
                Node point = hostPoints.item(i);
                NodeList addressChildNodes = address.getChildNodes();
                NodeList pointChildNodes = point.getChildNodes();
                hosts.add(new Host(pointChildNodes.item(1).getTextContent()
                        , addressChildNodes.item(1).getTextContent()
                        , addressChildNodes.item(4).getTextContent()
                        , pointChildNodes.item(1).getTextContent()
                        , pointChildNodes.item(0).getTextContent()));
            }
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return hosts;
    }

    @Override
    public List<Switch> getSwitch() {
        List<Switch> switches = new ArrayList<>();
        Document document = null;
        try {
            document = builder.parse(new InputSource(new StringReader(TopologyUtils.getAllSwitch())));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        NodeList nodes = document.getElementsByTagName("node");
        for (int i = 0; i < nodes.getLength(); i++) {
            Switch newSwitch = new Switch();
            List<Port> ports = new ArrayList<>();
             newSwitch.setSwitchId(nodes.item(i).getChildNodes().item(0).getTextContent());
            Element node = (Element) nodes.item(i);
            NodeList connectors = node.getElementsByTagName("node-connector");
            for (int j = 0; j < connectors.getLength(); j++) {
                Node connector = connectors.item(j);
                ports.add(new Port(connector.getChildNodes().item(3).getTextContent()
                        ,connector.getChildNodes().item(0).getTextContent()
                        ,connector.getChildNodes().item(8).getTextContent()
                        ,connector.getChildNodes().item(11).getTextContent()));
            }
            newSwitch.setPorts(ports);
            switches.add(newSwitch);
        }
        /*NodeList ids = document.getElementsByTagName("id");
        NodeList connectors = document.getElementsByTagName("node-connector");
        for (int i = 0; i < connectors.getLength(); i++) {
            Switch newSwitch = new Switch();
            newSwitch.setSwitchId(ids.item(i).getTextContent());
            NodeList childNodes = connectors.item(i).getChildNodes();
            newSwitch.setPorts(new ArrayList<>());
            for (int j = 0; j < childNodes.getLength(); j++) {
                newSwitch.getPorts().add(new Port(childNodes.item(3).getTextContent()
                        ,childNodes.item(0).getTextContent()
                        ,childNodes.item(8).getTextContent()
                        ,childNodes.item(11).getTextContent()));
            }
            switches.add(newSwitch);
        }*/
        return switches;
    }
}
