package team.sdn.net.traffic.entity;

import lombok.*;

import java.util.List;

/**
 * @author TokisakiKurumi
 * @className Switch
 * @date 2023/6/6
 * @description 交换机信息类
 **/
@ToString
@AllArgsConstructor
@Setter
public class Switch {
    /**
     * 交换机ID
     */
    private String switchId;
    /**
     * 交换机端口集合
     */
    private List<Port> ports;
}

@AllArgsConstructor
@ToString
@NoArgsConstructor
class Port {
    /**
     * 端口名
     */
    private String portName;
    /**
     * 端口ID
     */
    private String portId;
    /**
     * 端口号
     */
    private String number;
    /**
     * 硬件地址
     */
    private String hardAddress;
}
