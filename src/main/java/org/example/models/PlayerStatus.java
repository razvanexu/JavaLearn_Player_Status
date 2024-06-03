package org.example.models;

import org.example.enums.Weapons;
import org.example.services.Services;

import static org.example.enums.Weapons.fist;
import static org.example.services.Services.*;

public class PlayerStatus {
    private String nickname;
    private int score;
    private int lives;
    private int health = 100;
    private WeaponModel weaponInHand;
    private double positionX;
    private double positionY;
    private String gameName;

    public boolean shouldAttackOpponent(PlayerStatus opponent) {
        if (opponent.getWeaponInHand() == weaponInHand) {
            return winProbability(opponent, health, score);
        } else {
            var distance = getDistance(opponent, positionX, positionY);
            return winDuel(opponent, distance, weaponInHand);
        }
    }

    public WeaponModel getWeaponInHand() {
        return weaponInHand;
    }

    public void setWeaponInHand(Weapons weapon) {
        if (!weaponValidatorService(weapon, this.score)) {
            weaponInHand.setName(fist);
        } else {
            weaponInHand.setName(weapon);
        }
    }

    public String getGameName() {
        return gameName;
    }

    private void setGameName(String gameName) {
        this.gameName = gameName;
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
        this.score = Services.updateScoreService(weaponInHand, score);
    }

    public int getLives() {
        return lives;
    }

    private void setLives(int lives) {
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health -= health;
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
        if (isPerfectNumber(artifactCode)) {
            score += 5000;
            lives++;
            health = 100;
        } else if (isPrime(artifactCode)) {
            score += 1000;
            lives += 2;
            health += 25;
        } else if (isTrap(artifactCode)) {
            score -= 3000;
            health -= 25;
        } else {
            score += artifactCode;
        }
    }
}
