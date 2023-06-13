package team.sdn.entiey.attack;

import team.sdn.entiey.Attack;

public class Normal extends Attack {
    private String normal;

    public Normal() {
        this.normal = "normal";
    }

    public boolean isBelongNormal(String attack){
        return this.normal.equals(attack);
    }
}
