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
        PlayerStatus self = new PlayerStatus(service);

        //When
        self.setHealth(50);
        self.setScore(200);
        opp.setScore(-90);
        opp.setHealth(150);

        var result = service.myWinProbability(opp, self);

        //Then
        Assertions.assertTrue(result);
    }

    @Test
    void myWinProbability_ShouldReturnFalseIfHigherOpponent() {
        //Given
        PlayerStatus opp = new PlayerStatus(service);
        PlayerStatus self = new PlayerStatus(service);

        //When
        self.setScore(90);
        self.setHealth(50);
        opp.setScore(190);
        opp.setHealth(250);

        var result = service.myWinProbability(opp, self);

        //Then
        Assertions.assertFalse(result);
    }


    @Test
    void getDistanceBetweenSamePoints_ShouldReturn0() {

        //Given
        PlayerStatus opp = new PlayerStatus(service);
        PlayerStatus self = new PlayerStatus(service);

        //When
        self.setPositionX(0);
        self.setPositionY(0);
        opp.setPositionX(0);
        opp.setPositionY(0);

        var result = service.getDistance(opp, self);

        //then
        Assertions.assertEquals(0, result, "distance is 0");
    }

    @Test
    void getDistanceBetweenSamePoints_ShouldReturn5() {

        //Given
        PlayerStatus opp = new PlayerStatus(service);
        PlayerStatus self = new PlayerStatus(service);

        //When
        self.setPositionX(1);
        self.setPositionY(2);
        opp.setPositionX(4);
        opp.setPositionY(6);

        var result = service.getDistance(opp, self);

        //then
        Assertions.assertEquals(5, result, "distance is 5");
    }

    @Test
    void getDistanceBetweenSamePoints_ShouldReturn5WithNegativeValues() {

        //Given
        PlayerStatus opp = new PlayerStatus(service);
        PlayerStatus self = new PlayerStatus(service);

        //When
        self.setPositionX(-1);
        self.setPositionY(-2);
        opp.setPositionX(-4);
        opp.setPositionY(-6);

        var result = service.getDistance(opp, self);

        //then
        Assertions.assertEquals(5, result, "distance is 5");
    }

    @Test
    void opponentWins_ShouldReturnTrueWhenOpponentSniperDistanceGreaterThan1000() {

        //Given
        PlayerStatus opp = new PlayerStatus(service);
        PlayerStatus self = new PlayerStatus(service);
        WeaponModel oppWeapon;
        WeaponModel myWeapon;
        double distance;

        //When
        oppWeapon = new WeaponModel(Weapons.SNIPER);
        myWeapon = new WeaponModel(Weapons.KALASHNIKOV);
        opp.setScore(250000000);
        self.setScore(50000);
        opp.setWeaponInHand(oppWeapon);
        self.setWeaponInHand(myWeapon);
        distance = 10001;

        //Then
        var result = service.opponentWins(opp, distance, myWeapon);
        Assertions.assertTrue(result);
    }

    @Test
    void opponentWins_ShouldReturnTrueWhenOpponentKalashDistanceSmallerThan1000() {

        //Given
        PlayerStatus opp = new PlayerStatus(service);
        PlayerStatus self = new PlayerStatus(service);
        WeaponModel oppWeapon;
        WeaponModel myWeapon;
        double distance;

        //When
        oppWeapon = new WeaponModel(Weapons.KALASHNIKOV);
        myWeapon = new WeaponModel(Weapons.SNIPER);
        opp.setScore(250000000);
        self.setScore(50000);
        opp.setWeaponInHand(oppWeapon);
        self.setWeaponInHand(myWeapon);
        distance = 999;

        //Then
        var result = service.opponentWins(opp, distance, myWeapon);
        Assertions.assertTrue(result);
    }

    @Test
    void opponentWins_ShouldReturnTrueWhenOpponentKnifeVSMyKakash() {

        //Given
        PlayerStatus opp = new PlayerStatus(service);
        PlayerStatus self = new PlayerStatus(service);
        WeaponModel oppWeapon;
        WeaponModel myWeapon;
        double distance;

        //When
        oppWeapon = new WeaponModel(Weapons.KNIFE);
        myWeapon = new WeaponModel(Weapons.KALASHNIKOV);
        opp.setScore(250000000);
        self.setScore(50000);
        opp.setWeaponInHand(oppWeapon);
        self.setWeaponInHand(myWeapon);
        distance = 999;

        //Then
        var result = service.opponentWins(opp, distance, myWeapon);
        Assertions.assertFalse(result);
    }
}