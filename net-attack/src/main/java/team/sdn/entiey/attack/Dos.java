package team.sdn.entiey.attack;

import team.sdn.entiey.Attack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dos extends Attack {
    private List<String> dos;

    private final String[] NAME = {"apache2", "back",
            "land", "neptune", "mailbomb",
            "pod", "processtable", "smurf",
            "teardrop", "udpstorm", "worm"};

    public Dos() {
        this.dos = new ArrayList<>();
        Arrays.stream(NAME).forEach(name -> {
            this.dos.add(name);
        });
    }

    public boolean isBelongDos(String attack) {
        return this.dos.contains(attack);
    }
}
