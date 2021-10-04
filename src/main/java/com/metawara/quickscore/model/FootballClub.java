package com.metawara.quickscore.model;

public class FootballClub {
    private int id;
    private String name;
    private double chanceOfWinnning;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getChanceOfWinnning() {
        return chanceOfWinnning;
    }

    public void setChanceOfWinnning(double chanceOfWinnning) {
        this.chanceOfWinnning = chanceOfWinnning;
    }
}