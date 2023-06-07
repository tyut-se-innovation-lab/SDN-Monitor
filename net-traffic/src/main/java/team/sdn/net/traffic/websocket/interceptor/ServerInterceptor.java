package team.sdn.net.traffic.websocket.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;
import java.util.Objects;

/**
 * @author TokisakiKurumi
 * @className ServerInterceptor
 * @date 2023/5/31
 * @description 拦截器
 **/
@Slf4j
public class ServerInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.info("Request from " + request.getRemoteAddress().getHostString() + "进入拦截器");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
            String method = serverHttpRequest.getServletRequest().getHeader("Sec-Websocket-Protocol");
            if (Objects.nonNull(method)) {
                response.getHeaders().add("Sec-Websocket-Protocol",method);
                attributes.put("method",method);
                return super.beforeHandshake(serverHttpRequest, response, wsHandler, attributes);
            }
            return false;
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        log.info("Request from " + request.getRemoteAddress().getHostString() + "通过拦截器");
    }
}
