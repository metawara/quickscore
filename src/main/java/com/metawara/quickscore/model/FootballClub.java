package com.metawara.quickscore.model;

public class FootballClub {
    private final String name;
    private final double baseWinningChance;
    private final double positiveWCVariance;
    private final double negativeWCVariance;

    public FootballClub(String name, double baseWinningChance, double positiveWCVariance, double negativeWCVariance) {
        this.name = name;
        this.baseWinningChance = baseWinningChance;
        this.positiveWCVariance = positiveWCVariance;
        this.negativeWCVariance = negativeWCVariance;
    }

    public FootballClub(String name, double baseWinningChance) {
        this(name, baseWinningChance, 0, 0);
    }

    public String getName() {
        return name;
    }

    public double getBaseWinningChance() {
        return baseWinningChance;
    }

    public double getPositiveWCVariance() {
        return positiveWCVariance;
    }

    public double getNegativeWCVariance() {
        return negativeWCVariance;
    }

    @Override
    public String toString() {
        return "FootballClub{" +
                "name='" + name + '\'' +
                ", baseWinningChance=" + baseWinningChance +
                ", positiveWCVariance=" + positiveWCVariance +
                ", negativeWCVariance=" + negativeWCVariance +
                '}';
    }
}