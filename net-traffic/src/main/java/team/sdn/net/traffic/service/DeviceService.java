package team.sdn.net.traffic.service;

import team.sdn.net.traffic.entity.Host;
import team.sdn.net.traffic.entity.Switch;

import java.util.List;

/**
 * @author TokisakiKurumi
 * @className DeviceService
 * @date 2023/5/31
 * @description 用于获取设备信息的接口类
 **/
public interface DeviceService {
    /**
     * 获取网络拓扑图
     * @return 拓扑图
     */
    String getTopology();

    /**
     * 获取主机信息
     * @return 主机信息
     */
    List<Host> getHost();

    /**
     * 获取交换机信息
     * @return 交换机信息
     */
    List<Switch> getSwitch();


}
