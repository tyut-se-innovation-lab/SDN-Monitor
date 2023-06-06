package team.sdn.domain;

import lombok.Data;

/**
 * 链路 无向
 * @author Big-Bai
 **/
@Data
public class Link {
    /**
     * 端点1id
     */
    private String point1;

    /**
     * 端点1的端口
     */
    private String point1Tp;

    /**
     * 端点2id
     */
    private String point2;

    /**
     * 端点2端口
     */
    private String point2Tp;

}
