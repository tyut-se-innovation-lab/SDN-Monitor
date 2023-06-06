package team.sdn.net.traffic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author TokisakiKurumi
 * @className Host
 * @date 2023/6/6
 * @description
 **/
@Data
@ToString
@AllArgsConstructor
public class Host {
    /**
     * 主机ID
     */
    private String hostId;
    /**
     * MAC地址
     */
    private String mac;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 源端口ID
     */
    private String tpId;
    /**
     * 目的端口ID
     */
    private String apId;

}
