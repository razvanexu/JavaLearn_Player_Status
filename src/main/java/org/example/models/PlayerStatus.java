package org.example.models;

import org.example.enums.*;
import org.example.services.*;

public class PlayerStatus {
    private static final String GAME_NAME = "UNREAL";
    private final IServices service;
    private String nickname;
    private int score = 0;
    private int lives = 3;
    private int health;
    private WeaponModel weaponInHand;
    private double positionX;
    private double positionY;

    public PlayerStatus(IServices service) {
        this.service = service;
    }

    public boolean shouldAttackOpponent(PlayerStatus opponent, PlayerStatus self) {
        if (opponent.getWeaponInHand() == self.getWeaponInHand()) {
            return service.myWinProbability(opponent, self);
        } else {
            var distance = service.getDistance(opponent, self);
            return service.opponentWins(opponent, distance, weaponInHand);
        }
    }

    public WeaponModel getWeaponInHand() {
        return weaponInHand;
    }

    public boolean setWeaponInHand(WeaponModel weapon) {
        weaponInHand = new WeaponModel(Weapons.FIST);
        if (service.canBuyWeapon(weapon, score)) {
            weaponInHand = weapon;
            this.score -= weapon.getCost();
            return true;
        }
        return false;
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
        if (this.health <= 0) {
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

    public void setPositionX(double posX) {
        positionX = posX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public void movePlayer(double newPosX, double newPosY) {
        positionX += newPosX;
        positionY += newPosY;
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
            if (score < 0) {
                setScore(0);
            }
            setHealth(-25);
        } else {
            score += artifactCode;
        }
    }
}
