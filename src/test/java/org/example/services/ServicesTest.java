package org.example.services;

import org.example.enums.*;
import org.example.models.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.stream.*;

class ServicesTest {
    private final IServices service = new Services();

    public static Stream<Arguments> provideArgsForScoreLessThanCost() {
        return Stream.of(
            Arguments.of(992, Weapons.KNIFE),
            Arguments.of(9999, Weapons.SNIPER),
            Arguments.of(1999, Weapons.KALASHNIKOV)
        );
    }

    public static Stream<Arguments> provideArgsForScoreEqualOrMoreThanCost() {
        return Stream.of(
            Arguments.of(1001, Weapons.KNIFE),
            Arguments.of(10_000, Weapons.SNIPER),
            Arguments.of(20_543, Weapons.KALASHNIKOV)
        );
    }


    @ParameterizedTest
    @MethodSource("provideArgsForScoreLessThanCost")
    void canBuyWeaponTest_ShouldReturnFalseWhenScoreIsLessThanCost(int cost, Weapons type) {

        //Given
        WeaponModel weapon = new WeaponModel(type);

        //When
        var result = service.canBuyWeapon(weapon, cost);

        //Then
        Assertions.assertFalse(result);

    }

    @ParameterizedTest
    @MethodSource("provideArgsForScoreEqualOrMoreThanCost")
    void canBuyWeaponTest_ShouldReturnTrueWhenScoreIsEqualOrBiggerThanCost() {

        //Given
        WeaponModel weapon = new WeaponModel(Weapons.FIST);
        int score = 100_000;

        //When
        var result = service.canBuyWeapon(weapon, score);

        //Then
        Assertions.assertTrue(result);

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 6, 28, 496, 8128})
    void isPerfectNumberTest_ShouldReturnTrueWhenPerfectNumber(int number) {

        //Given

        //When

        var result = service.isPerfectNumber(number);

        //Then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 8, 26, 486, 8138})
    void isPerfectNumberTest_ShouldReturnFalseWhenNotPerfectNumber(int number) {

        //Given

        //When

        var result = service.isPerfectNumber(number);

        //Then
        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7})
    void isPrime_ShouldReturnTrueWhenPrime(int nr) {
        //Given

        //When
        var result = service.isPrime(nr);

        //Then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 9, 15})
    void isPrime_ShouldReturnFalseWhenNotPrime(int nr) {
        //Given

        //When
        var result = service.isPrime(nr);

        //Then
        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {3574, 11, 10, 14})
    void isSumDivBy3_ShouldReturnFalseWhenSumNumbersIsNotDivBy3(int number) {
        //Given

        //When
        var result = service.isSumDivBy3(number);

        //Then
        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 18, 36, 15})
    void isSumDivBy3_ShouldReturnFalseWhenSumNumbersIsDivBy3(int number) {
        //Given

        //When
        var result = service.isSumDivBy3(number);

        //Then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 18, 36, 42})
    void isTrap_ShouldReturnTrueWhenNrIsSumDivBy3AndIsEven(int number) {
        //Given

        //When
        var result = service.isTrap(number);

        //Then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 16, 37, 44})
    void isTrap_ShouldReturnFalseWhenNrIsNotSumDivBy3OrIsEven(int number) {
        //Given

        //When
        var result = service.isTrap(number);

        //Then
        Assertions.assertFalse(result);
    }

    @Test
    void myWinProbability_ShouldReturnTrueIfLowerOpponent() {
        //Given
        PlayerStatus opp = new PlayerStatus(service);
        int oppHealth, oppScore, myHealth, myScore;

        //When
        myHealth = 50;
        myScore = 200;
        oppHealth = -90;
        oppScore = 150;
        opp.setScore(oppScore);
        opp.setHealth(oppHealth);

        var result = service.myWinProbability(opp, myHealth, myScore);

        //Then
        Assertions.assertTrue(result);
    }

    
}