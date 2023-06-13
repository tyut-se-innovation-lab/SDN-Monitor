package team.sdn.service;

import team.sdn.entiey.Attack;
import team.sdn.net.traffic.entity.Host;

import java.util.Map;

public interface IAttackService {
    /**
     * 得到每个主机属于的攻击种类
     *
     * @return
     */
    Map<Host, Attack> getAttackOfHost();

    /**
     * 得到整个网络中各个攻击的百分比
     *
     * @return
     */
    Map<Attack, Double> getAttackOfNetwork();
}
