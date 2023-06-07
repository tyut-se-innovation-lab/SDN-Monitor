package team.sdn.net.traffic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author TokisakiKurumi
 * @className Port
 * @date 2023/6/7
 * @description 交换机端口类
 **/
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
public class Port {
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
