package team.sdn.entiey.attack;

import team.sdn.entiey.Attack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Probe extends Attack {
    private List<String> probe;

    private final String[] NAME = {"ipsweep", "mscan",
            "nmap", "portsweep", "saint",
            "satan"};

    public Probe() {
        this.probe = new ArrayList<>();
        Arrays.stream(NAME).forEach(name -> {
            this.probe.add(name);
        });
    }

    public boolean isBelongProbe(String attack) {
        return this.probe.contains(attack);
    }
}
