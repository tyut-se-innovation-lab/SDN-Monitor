package team.sdn.net.traffic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @author TokisakiKurumi
 * @className DocumentBuilderConfig
 * @date 2023/6/6
 * @description 配置DocumentBuilder类
 **/
@Configuration
public class DocumentBuilderConfig {
    @Bean
    public DocumentBuilder documentBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            return factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
