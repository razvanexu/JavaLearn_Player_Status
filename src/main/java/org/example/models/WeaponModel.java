package org.example.models;

import org.example.enums.*;

public class WeaponModel {
    private Weapons weaponName;
    private int cost;
    private int combatValue;

    public WeaponModel(Weapons weapon) {
        this.weaponName = weapon;
        setCostAndValue();
    }

    private void setCostAndValue() {
        switch (weaponName) {
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
        if (weaponName == Weapons.SNIPER) {
            if (distance > 1000) {
                combatValue = 4;
            } else {
                combatValue = 3;
            }
        } else if (weaponName == Weapons.KALASHNIKOV && distance <= 1000) {
            combatValue = 4;
        } else {
            combatValue = 3;
        }

    }

    public int getCost() {
        return cost;
    }

    public Weapons getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(Weapons weapon) {
        this.weaponName = weapon;
    }
}
