package team.sdn.net.traffic.service;

import org.springframework.web.socket.WebSocketSession;

/**
 * @author TokisakiKurumi
 * @className TrafficService
 * @date 2023/5/31
 * @description 用于获取网络流量的接口
 **/
public interface TrafficService {
    /**
     * 获取瞬时字节速率
     * @return
     */
    void getInstantByteRate();

    /**
     * 获取瞬时包速率
     * @return
     */
    void getInstantPackRate();

    /**
     * 存储定时任务
     */
    void storageTimingTask();

    /**
     * 展示全局字节速率
     * @param session WebSocketSession
     */
    void getAllByteRate(WebSocketSession session);

    /**
     * 展示全局包速率
     * @param session WebSocketSession
     */
    void getAllPackRate(WebSocketSession session);

    /**
     * 获取端口字节速率
     * @param port 端口
     * @param session WebSocketSession
     */
    void getPortByteRate(WebSocketSession session,String port);


    /**
     * 获取端口包速率
     * @param port 端口
     * @param session WebSocketSession
     */
    void getPortPackRate(WebSocketSession session,String port);

    /**
     * 获取端口输入字节速率
     * @param port 端口
     * @param session WebSocketSession
     */
    void getPortInputByteRate(WebSocketSession session,String port);

    /**
     * 获取端口输入包速率
     * @param port 端口
     * @param session WebSocketSession
     */
    void getPortInputPackRate(WebSocketSession session,String port);

    /**
     * 获取端口输出包速率
     * @param port 端口
     * @param session WebSocketSession
     */
    void getPortOutputPackRate(WebSocketSession session,String port);

    /**
     * 获取端口输出字节速率
     * @param port 端口
     * @param session WebSocketSession
     */
    void getPortOutputByteRate(WebSocketSession session,String port);

    /**
     * 获取过去48小时的字节速率
     * @param session WebSocketSession
     */
    void getPastByteRate(WebSocketSession session);

    /**
     * 获取端口带宽占比
     * @param session WebSocketSession
     * @param port 端口
     */
    void getPortBandWidthProportion(WebSocketSession session,String port);

    /**
     * 获取全局带宽占比
     * @param session WebSocketSession
     */
    void getAllBandWidthProportion(WebSocketSession session);

}
