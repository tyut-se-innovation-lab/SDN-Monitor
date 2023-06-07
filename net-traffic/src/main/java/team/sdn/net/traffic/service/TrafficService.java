package team.sdn.net.traffic.service;

/**
 * @author TokisakiKurumi
 * @className TrafficService
 * @date 2023/5/31
 * @description 用于获取网络流量的接口
 **/
public interface TrafficService {

    String getAveByteRate();

    String getInstantByteRate();

    String getAvePackRate();

    String getInstantPackRate();


}
