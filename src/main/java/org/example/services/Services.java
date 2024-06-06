package org.example.services;

import org.example.models.*;

public class Services implements IServices {

    public Services() {
        //empty constructor
    }

    @Override
    public boolean canBuyWeapon(WeaponModel weapon, int playerScore) {

        return playerScore >= weapon.getCost();
    }

    @Override
    public boolean isPerfectNumber(int artifactCode) {
        long sum = 0;
        boolean isPerfect = false;

        for (int i = 1; i <= artifactCode / 2; i++) {
            if (artifactCode % i == 0) {
                sum += i;
            }
        }

        if (artifactCode == 0) {
            return isPerfect;
        } else if (sum == artifactCode) {
            isPerfect = true;
        }
        return isPerfect;
    }

    @Override
    public boolean isPrime(int artifact) {
        if (artifact <= 1) {
            return false;
        }

        for (int i = 2; i < artifact; i++) {
            if (artifact % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isTrap(int artifact) {
        return isSumDivBy3(artifact) && artifact % 2 == 0;
    }

    @Override
    public boolean myWinProbability(PlayerStatus opponent, int health, int score) {
        var opponentWinProb = (3 * opponent.getHealth() + opponent.getScore() / 1000) / 4;
        var myWinProb = (3 * health + score / 1000) / 4;

        return myWinProb >= opponentWinProb;
    }

    @Override
    public double getDistance(PlayerStatus opponent, double posX, double posY) {
        double ac = Math.abs(opponent.getPositionX() - posX);
        double cb = Math.abs(opponent.getPositionY() - posY);
        return Math.hypot(ac, cb);
    }

    @Override
    public boolean winDuel(PlayerStatus opponent, double distance, WeaponModel myWeapon) {
        opponent.getWeaponInHand().setCombatValue(distance);
        myWeapon.setCombatValue(distance);

        return opponent.getWeaponInHand().getCombatValue() > myWeapon.getCombatValue();
    }
}
