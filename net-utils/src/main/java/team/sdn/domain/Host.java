package team.sdn.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 主机信息
 * @author Big-Bai
 **/
@Data
public class Host{
    /**
     * 节点id<br/>
     * 格式 host:xx:xx:xx:xx:xx:xx
     */
    private String nodeId;

    /**
     * mac地址<br/>
     * 格式 xx:xx:xx:xx:xx:xx
     */
    private String mac;

    /**
     * ip地址
     */
    private String ip;

    /**
     *初次记录的时间
     */
    private String firstSeen;

    /**
     *最后一次记录的时间
     */
    private String lastSeen;
}
