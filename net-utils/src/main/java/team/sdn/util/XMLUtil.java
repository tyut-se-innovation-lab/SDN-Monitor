package team.sdn.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import team.sdn.config.Address;
import team.sdn.exception.NetException;

import java.util.List;

/**
 * @author Big-Bai
 **/
public class XMLUtil {
    private static SAXReader saxReader = new SAXReader();
    public static Document getDocument(String xmlString){
        try {
            return saxReader.read(xmlString);
        }catch (DocumentException e){
            throw new NetException("XML格式错误",e);
        }
    }


    public static String getTextByElementStringPath(Element element, String ... path){
        for(int pathPointer = 0;pathPointer<path.length;pathPointer++){
            List<Element> elements = element.elements(path[pathPointer]);
            for(Element e:elements){
                if(e.getName().equals(path[pathPointer+1])){
                    element = e;
                    break;
                }
            }
        }
        return element.getName();
    }

    public static List<Element> getElementsByPath(Element element,String ... path){
        for(int pathPointer = 0;pathPointer<path.length;pathPointer++){
            List<Element> elements = element.elements(path[pathPointer]);
            for(Element e:elements){
                if(e.getName().equals(path[pathPointer+1])){
                    element = e;
                    break;
                }
            }
        }
        return element.elements();
    }


}
