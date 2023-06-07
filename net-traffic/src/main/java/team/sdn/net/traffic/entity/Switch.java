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
@Data
@NoArgsConstructor
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
