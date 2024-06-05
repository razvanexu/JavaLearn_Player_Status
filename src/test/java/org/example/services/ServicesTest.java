package org.example.services;

import org.example.enums.*;
import org.example.models.*;
import org.junit.jupiter.api.*;

class ServicesTest {

    @org.junit.jupiter.api.Test
    void updateScoreService() {

        //Given
        int gave = 3;
        int exp = 5;
        //When

        //Then
        Assertions.assertEquals(gave, exp);
    }


    @org.junit.jupiter.api.Test
    void canBuyWeaponTest_ShouldReturnFalse() {

        //Given
        PlayerStatus player = new PlayerStatus();
        WeaponModel weapon = new WeaponModel(Weapons.KNIFE);

        //When

        player.setWeaponInHand(weapon, Weapons.SNIPER);
        //Then


    }


}