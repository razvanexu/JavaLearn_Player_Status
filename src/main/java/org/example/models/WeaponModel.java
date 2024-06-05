package org.example.models;

import org.example.enums.*;

public class WeaponModel {
    private String name;
    private int cost;
    private int combatValue;

    public WeaponModel(Weapons weapon) {
        this.name = weapon.toString();
        setCostAndValue(weapon);
    }

    private void setCostAndValue(Weapons name) {
        switch (name) {
            case FIST:
                this.cost = 0;
                this.combatValue = 1;
                break;
            case KNIFE:
                this.cost = 1000;
                this.combatValue = 2;
                break;
            case SNIPER:
                this.cost = 10_000;
                this.combatValue = getCombatValue();
                break;
            case KALASHNIKOV:
                this.cost = 20_000;
                this.combatValue = getCombatValue();
                break;
        }
    }

    public int getCombatValue() {
        return combatValue;
    }

    public void setCombatValue(double distance) {
        if (distance > 1000) {
            this.combatValue = 4;
        }
        this.combatValue = 3;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public void setName(Weapons weapon) {
        this.name = weapon.toString();
    }
}
