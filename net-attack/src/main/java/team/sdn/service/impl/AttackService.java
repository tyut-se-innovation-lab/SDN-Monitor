package team.sdn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.sdn.entiey.Attack;
import team.sdn.entiey.Param;
import team.sdn.entiey.attack.*;
import team.sdn.net.traffic.entity.Host;
import team.sdn.net.traffic.service.DeviceService;
import team.sdn.service.IAttackService;

import java.util.Map;

@Service
public class AttackService implements IAttackService {

    @Autowired
    private DeviceService deviceService;

    private final String URL = "http:192.168.1.250:";

    @Override
    public Map<Host, Attack> getAttackOfHost() {
        return null;
    }

    @Override
    public Map<Attack, Double> getAttackOfNetwork() {
        return null;
    }

    public String getResult(Param param) {
        return "";
    }

    public Attack isAttack(String attack) {
        Dos dos = new Dos();
        Normal normal = new Normal();
        Probe probe = new Probe();
        R2L r2L = new R2L();
        U2R u2R = new U2R();

        if (dos.isBelongDos(attack)) {
            return dos;
        } else if (normal.isBelongNormal(attack)) {
            return normal;
        } else if (probe.isBelongProbe(attack)) {
            return probe;
        } else if (r2L.isBelongU2R(attack)) {
            return r2L;
        } else if (u2R.isBelongR2L(attack)) {
            return u2R;
        }
        return normal;
    }
}
