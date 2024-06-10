package org.example.services;

import org.example.models.*;

public interface IServices {
    boolean canBuyWeapon(WeaponModel weapon, int playerScore);

    boolean isPerfectNumber(int artifactCode);

    boolean isPrime(int artifact);

    boolean isTrap(int artifact);

    default boolean isSumDivBy3(int artifact) {
        int sum = 0;
        int num = artifact;

        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum % 3 == 0;
    }

    boolean myWinProbability(PlayerStatus opponent, PlayerStatus self);

    double getDistance(PlayerStatus opponent, PlayerStatus self);

    boolean opponentWins(PlayerStatus opponent, double distance, WeaponModel myWeapon);
}
