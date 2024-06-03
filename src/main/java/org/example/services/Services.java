package org.example.services;

import org.example.enums.Weapons;
import org.example.models.PlayerStatus;
import org.example.models.WeaponModel;

import static org.example.enums.Weapons.*;

public class Services {

    public static int updateScoreService(WeaponModel weapon, int playerScore) {
        return playerScore - weapon.getCost();
    }

    public static boolean weaponValidatorService(Weapons weapon, int playerScore) {

        boolean isValid = false;

        if (playerScore >= 1000 && playerScore < 20_000 && weapon == fist ||
                playerScore >= 1000 && playerScore < 10_000 && weapon == knife) {
            isValid = true;
        } else if (playerScore >= 1000 && playerScore < 30_000 && weapon == fist ||
                playerScore >= 1000 && playerScore < 30_000 && weapon == knife ||
                playerScore >= 1000 && playerScore < 30_000 && weapon == sniper) {
            isValid = true;
        } else if (playerScore >= 1000 && weapon == fist ||
                playerScore >= 1000 && weapon == knife ||
                playerScore >= 1000 && weapon == sniper ||
                playerScore >= 1000 && weapon == kalashnikov) {
            isValid = true;
        }
        return isValid;
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
