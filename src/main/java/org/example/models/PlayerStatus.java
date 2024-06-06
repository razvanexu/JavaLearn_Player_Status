package org.example.models;

import org.example.enums.*;
import org.example.services.*;

import static org.example.enums.Weapons.*;

public class PlayerStatus {
    private static final String GAME_NAME = "UNREAL";
    private final IServices service;
    private String nickname;
    private int score;
    private int lives;
    private int health = 100;
    private WeaponModel weaponInHand;
    private double positionX;
    private double positionY;

    public PlayerStatus(IServices service) {
        this.service = service;
    }

    public boolean shouldAttackOpponent(PlayerStatus opponent) {
        if (opponent.getWeaponInHand() == weaponInHand) {
            return service.myWinProbability(opponent, health, score);
        } else {
            var distance = service.getDistance(opponent, positionX, positionY);
            return service.winDuel(opponent, distance, weaponInHand);
        }
    }

    public WeaponModel getWeaponInHand() {
        return weaponInHand;
    }

    public void setWeaponInHand(WeaponModel weapon, Weapons weaponType) {
        weaponInHand = new WeaponModel(FIST);
        if (service.canBuyWeapon(weapon, score)) {
            weaponInHand.setName(weaponType);
            this.score -= weapon.getCost();
        }
    }

    public String getGameName() {
        return GAME_NAME;
    }

    public void initPlayer(String nickname) {
        this.nickname = nickname;
    }

    public void initPlayer(String nickname, int lives) {
        this.nickname = nickname;
        this.lives = lives;
    }

    public void initPlayer(String nickname, int lives, int score) {
        this.nickname = nickname;
        this.lives = lives;
        this.score = score;
    }

    public String getNickname() {
        return nickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives += lives;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health += health;
        if (this.health < 0) {
            lives--;
            this.health = 100;
        }
        if (this.health + health > 100) {
            this.health = 100;
        }
    }

    public double getPositionX() {
        return positionX;
    }

    public void movePlayer(double newPosX, double newPosY) {
        positionX += newPosX;
        positionY += newPosY;
    }

    public double getPositionY() {
        return positionY;
    }

    public void findArtifactCode(int artifactCode) {
        if (service.isPerfectNumber(artifactCode)) {
            setScore(5000);
            setLives(1);
            setHealth(100);
        } else if (service.isPrime(artifactCode)) {
            setScore(1000);
            setLives(2);
            setHealth(25);
        } else if (service.isTrap(artifactCode)) {
            setScore(-3000);
            setHealth(-25);
        } else {
            score += artifactCode;
        }
    }
}
