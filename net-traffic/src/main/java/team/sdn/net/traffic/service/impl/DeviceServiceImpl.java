package team.sdn.net.traffic.service.impl;

import cn.hutool.json.XML;
import com.alibaba.fastjson2.JSONObject;
import org.json.JSONException;
import org.json.XML;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import team.sdn.domain.Host;
import team.sdn.domain.Switch;
import team.sdn.domain.Topology;
import team.sdn.net.traffic.service.DeviceService;
import team.sdn.net.traffic.utils.JsonConvertUtil;
import team.sdn.sdnutils.TopologyUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public List<Host> getHost(String hostId) {
        List<Host> hosts = new ArrayList<>();
        Document document = JsonConvertUtil.builder.parse(TopologyUtils.getAllHost());
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
    public List<Switch> getSwitch(String switchId) {
        if (Objects.nonNull(switchId)) {
            List<Switch> switches = new ArrayList<>();
            switches.add(TopologyUtils.getSwitchById(switchId));
            return switches;
        }
        return TopologyUtils.getAllSwitch();
    }
}
