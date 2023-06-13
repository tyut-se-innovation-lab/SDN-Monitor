package team.sdn.entiey.attack;

import team.sdn.entiey.Attack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class U2R extends Attack {
    private List<String> u2R;

    private final String[] NAME = {"buffer_overflow",
            "loadmodule", "perl", "psrootkit",
            "sqlattack", "xterm"};

    public U2R() {
        this.u2R = new ArrayList<>();
        Arrays.stream(NAME).forEach(name -> {
            this.u2R.add(name);
        });
    }

    public boolean isBelongR2L(String attack) {
        return this.u2R.contains(attack);
    }
}