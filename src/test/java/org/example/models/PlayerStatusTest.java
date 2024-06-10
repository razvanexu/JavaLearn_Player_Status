package org.example.models;

import org.example.enums.*;
import org.example.services.*;
import org.junit.jupiter.api.*;

class PlayerStatusTest {
    private final IServices service = new Services();

    @Test
    void shouldAttackOpponent_ShouldReturnFalseIfOppWinsSameWeapon() {

        //Given
        PlayerStatus opp = new PlayerStatus(service);
        PlayerStatus self = new PlayerStatus(service);
        WeaponModel weapon = new WeaponModel(Weapons.SNIPER);

        //When
        self.setScore(10);
        self.setHealth(-50);
        opp.setScore(190);
        opp.setHealth(100);
        opp.setWeaponInHand(weapon);
        self.setWeaponInHand(weapon);
        var result = self.shouldAttackOpponent(opp, self);

        //Then
        Assertions.assertFalse(result);
    }

    @Test
    void shouldAttackOpponent_ShouldReturnTrueIfOppLoosesSameWeapon() {

        //Given
        PlayerStatus opp = new PlayerStatus(service);
        PlayerStatus self = new PlayerStatus(service);
        WeaponModel weapon = new WeaponModel(Weapons.KALASHNIKOV);

        //When
        self.setScore(290);
        self.setHealth(50);
        opp.setScore(190);
        opp.setHealth(-50);
        opp.setWeaponInHand(weapon);
        self.setWeaponInHand(weapon);
        var result = self.shouldAttackOpponent(opp, self);

        //Then
        Assertions.assertTrue(result);
    }

    @Test
    void findArtifactCode_ShouldReturnPerfectNumberScore() {

        //Given
        int artifact;
        PlayerStatus player = new PlayerStatus(service);

        //When
        artifact = 6;
        player.findArtifactCode(artifact);
        player.setHealth(100);

        //Then
        Assertions.assertAll(
            () -> Assertions.assertEquals(5000, player.getScore()),
            () -> Assertions.assertEquals(4, player.getLives()),
            () -> Assertions.assertEquals(100, player.getHealth())

        );
    }

    @Test
    void findArtifactCode_ShouldReturnPrimeNumberScore() {

        //Given
        int artifact;
        PlayerStatus player = new PlayerStatus(service);

        //When
        artifact = 7;
        player.findArtifactCode(artifact);
        player.setHealth(100);

        //Then
        Assertions.assertAll(
            () -> Assertions.assertEquals(1000, player.getScore()),
            () -> Assertions.assertEquals(5, player.getLives()),
            () -> Assertions.assertEquals(100, player.getHealth())

        );
    }

    @Test
    void findArtifactCode_ShouldReturnTrapScore() {

        //Given
        int artifact;
        PlayerStatus player = new PlayerStatus(service);

        //When
        artifact = 18;
        player.findArtifactCode(artifact);
        player.setHealth(25);

        //Then
        Assertions.assertAll(
            () -> Assertions.assertEquals(0, player.getScore()),
            () -> Assertions.assertEquals(100, player.getHealth()),
            () -> Assertions.assertEquals(2, player.getLives())

        );
    }
}