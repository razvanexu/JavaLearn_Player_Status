package org.example.services;

import org.example.models.*;

public class Services {

    private Services() {
    }

    public static int updateScoreService(WeaponModel weapon, int playerScore) {
        return playerScore - weapon.getCost();
    }

    public static boolean canBuyWeapon(WeaponModel weapon, int playerScore) {

        return playerScore >= weapon.getCost();
    }

    public static boolean isPerfectNumber(int artifactCode) {
        long sum = 0;
        boolean isPerfect = false;

        for (int i = 1; i <= artifactCode / 2; i++) {
            if (artifactCode % i == 0) {
                sum += i;
            }
        }
        if (sum == artifactCode) {
            isPerfect = true;
        }
        return isPerfect;
    }

    public static boolean isPrime(int artifact) {
        if (artifact <= 1) {
            return false;
        }

        for (int i = 2; i < Math.sqrt(artifact); i++) {
            if (artifact % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTrap(int artifact) {
        return isSumDivBy3(artifact) && isEven(artifact);
    }

    private static boolean isSumDivBy3(int artifact) {
        int sum = 0;
        int num = artifact;

        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum % 3 == 0;
    }

    private static boolean isEven(int artifact) {
        return artifact % 2 == 0;
    }

    public static boolean winProbability(PlayerStatus opponent, int health, int score) {
        var opponentWinProb = (3 * opponent.getHealth() + opponent.getScore() / 1000) / 4;
        var myWinProb = (3 * health + score / 1000) / 4;

        return myWinProb > opponentWinProb;
    }

    public static double getDistance(PlayerStatus opponent, double posX, double posY) {
        double ac = Math.abs(opponent.getPositionX() - posX);
        double cb = Math.abs(opponent.getPositionY() - posY);
        return Math.hypot(ac, cb);
    }

    public static boolean winDuel(PlayerStatus opponent, double distance, WeaponModel myWeapon) {
        opponent.getWeaponInHand().setCombatValue(distance);
        myWeapon.setCombatValue(distance);

        return opponent.getWeaponInHand().getCombatValue() > myWeapon.getCombatValue();
    }
}
