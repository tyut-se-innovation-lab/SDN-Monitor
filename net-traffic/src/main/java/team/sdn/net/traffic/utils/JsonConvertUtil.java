package team.sdn.net.traffic.utils;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @author TokisakiKurumi
 * @className JsonConvertUtil
 * @date 2023/6/6
 * @description
 **/
public class JsonConvertUtil {
    public static DocumentBuilder builder;

    {
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static Document toXmlDoc(String jsonStr) {
        try {
            return builder.parse(jsonStr);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
