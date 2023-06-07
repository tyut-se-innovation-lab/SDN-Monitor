package team.sdn.net.traffic.websocket.handler;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import team.sdn.net.traffic.service.TrafficService;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author TokisakiKurumi
 * @className TrafficHandler
 * @date 2023/5/31
 * @description 关于网络流量信息的处理类
 **/
@Component
@Slf4j
public class TrafficHandler implements WebSocketHandler {

    /**
     * 连接数
     */
    private static final AtomicInteger LINK_COUNT = new AtomicInteger(0);

    /**
     * 网络流量服务类
     */
    @Autowired
    private TrafficService service;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        int count = LINK_COUNT.incrementAndGet();
        log.info("sessionID:" + session.getId() + "加入连接");
        log.info("已有" + count + "个连接");
        session.sendMessage(new TextMessage("Traffic服务端已将你加入连接"));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(String.valueOf(message.getPayload()));
        String method = (String) session.getAttributes().get("method");
        Object[] params = jsonObject.values().toArray();
        Class[] classes = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            classes[i] = String.class;
        }
        Method serviceMethod = service.getClass().getDeclaredMethod(method,classes);
        for (int i = 0;i<params.length;i++) {
            if ("".equals(params[i])) {
                params[i] = null;
            }
        }
        Object result = serviceMethod.invoke(service, params);

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("SessionID:" + session.getId() + "发生异常(" + exception.toString() + ")");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        LINK_COUNT.decrementAndGet();
        log.info("SessionID:" + session.getId() + "退出连接");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private void memoryThread(Object result) {

    }
}
