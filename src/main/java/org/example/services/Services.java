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

        for (int i = 1; i <= artifactCode / 2; i++) {
            if (artifactCode % i == 0) {
                sum += i;
            }
        }

        return artifactCode != 0 && sum == artifactCode;
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
    public boolean myWinProbability(PlayerStatus opponent, PlayerStatus self) {
        double opponentWinProb = (3 * opponent.getHealth() + (double) opponent.getScore() / 1000) / 4;
        double myWinProb = (3 * self.getHealth() + self.getScore() / (double) 1000) / 4;

        return myWinProb >= opponentWinProb;
    }

    @Override
    public double getDistance(PlayerStatus opponent, PlayerStatus self) {
        double ac = Math.abs(opponent.getPositionX() - self.getPositionX());
        double cb = Math.abs(opponent.getPositionY() - self.getPositionY());
        return Math.hypot(ac, cb);
    }

    @Override
    public boolean opponentWins(PlayerStatus opponent, double distance, WeaponModel myWeapon) {
        opponent.getWeaponInHand().setCombatValue(distance);
        myWeapon.setCombatValue(distance);

        return opponent.getWeaponInHand().getCombatValue() > myWeapon.getCombatValue();
    }
}
