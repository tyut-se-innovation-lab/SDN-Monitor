package team.sdn.net.traffic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TokisakiKurumi
 * @className traffic
 * @date 2023/6/11
 * @description 流量信息类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Traffic {
    /**
     * 来源的客户端
     */
    private String agent = null;
    /**
     * 流量速率
     */
    private String metricValue = null;
    /**
     * 获取时间
     */
    private String lastUpdate = null;

    /**
     *流量方向
     */
    private String direction = null;
}
