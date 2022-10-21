package com.blackJack;

public class Player extends Personne {
    private double playerBalance;
    public Player() {
        super();
        playerBalance = 5000;
    }

    public double getPlayerBalance() {
        return playerBalance;
    }

    public void setPlayerBalance(double playerBalance) {
        this.playerBalance = playerBalance;
    }
}
