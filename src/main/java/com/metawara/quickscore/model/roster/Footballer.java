package com.metawara.quickscore.model.roster;

import java.util.Optional;

public class Footballer {
    private final int shirtNumber;
    private final String firstName;
    private final String lastName;
    private final String nickname;

    private int goals = 0;
    private int assists = 0;
    private int yellowCards = 0;
    private int redCards = 0;

    public Footballer(int shirtNumber, String firstName, String lastName, String nickname) {
        this.shirtNumber = shirtNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getShortFirstName() {
        return firstName.charAt(0) + ".";
    }

    public String getLastName() {
        return lastName;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    public Optional<String> getNickname() {
        return Optional.of(nickname);
    }

}
