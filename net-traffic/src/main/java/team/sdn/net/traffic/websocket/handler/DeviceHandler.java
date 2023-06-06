package team.sdn.net.traffic.websocket.handler;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import team.sdn.net.traffic.service.DeviceService;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author TokisakiKurumi
 * @className DeviceHandler
 * @date 2023/5/31
 * @description 关于设备信息的处理类
 **/
@Component
@Slf4j
public class DeviceHandler implements WebSocketHandler {
    /**
     * 连接数
     */
    private static final AtomicInteger LINK_COUNT = new AtomicInteger(0);

    /**
     * 设备信息服务类
     */
    @Autowired
    private DeviceService service;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        int count = LINK_COUNT.incrementAndGet();
        log.info("SessionID:" + session.getId() + "加入连接");
        log.info("已有" + count + "个连接");
        session.sendMessage(new TextMessage("Device服务端已将你加入连接"));
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
        sendMsg(session,result);
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

    /**
     * 发送JSON消息给客户端
     * @param session 与客户端的连接
     * @param t 发送的消息
     * @param <T> 泛型
     * @throws IOException IO异常
     */
    public <T> void sendMsg(WebSocketSession session,T t) throws IOException {
        session.sendMessage(new TextMessage(JSONObject.toJSONString(t)));
    }

}
