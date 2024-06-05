package org.example.services;

import org.example.enums.*;
import org.example.models.*;
import org.junit.jupiter.api.*;

class ServicesTest {
    private final IServices service = new Services();

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
        WeaponModel weapon = new WeaponModel(Weapons.KNIFE);
        int score = 100;

        //When
        var result = service.canBuyWeapon(weapon, score);

        //Then
        Assertions.assertFalse(result);

    }

}