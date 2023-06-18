package team.sdn.entiey.attack;

import lombok.Data;
import team.sdn.entiey.Attack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class R2L extends Attack {
    private List<String> r2L;

    private final String[] NAME = {"ftp_write", "guess_passwd",
            "httptunnel", "imap", "multihop",
            "named", "phf", "sendmail",
            "Snmpgetattack", "spy", "snmpguess",
            "warezclient", "warezmaster",
            "xlock", "xsnoop"};

    public R2L() {
        this.r2L = new ArrayList<>();
        Arrays.stream(NAME).forEach(name -> {
            this.r2L.add(name);
        });
    }

    public boolean isBelongU2R(String attack) {
        return this.r2L.contains(attack);
    }
}