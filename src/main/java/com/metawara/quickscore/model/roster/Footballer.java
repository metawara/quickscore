package com.metawara.quickscore.model.roster;

import java.util.Optional;

public class Footballer {
    private final int shirtNumber;
    private final String firstName;
    private final String lastName;
    private final String nickname;

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

    public Optional<String> getNickname() {
        return Optional.of(nickname);
    }

}
