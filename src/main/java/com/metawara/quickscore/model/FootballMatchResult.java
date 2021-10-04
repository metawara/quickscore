package com.metawara.quickscore.model;

public class FootballMatchResult {
    private FootballClub winner;
    private FootballClub loser;

    public FootballMatchResult() {
    }

    public FootballMatchResult(FootballClub winner, FootballClub loser) {
        this.winner = winner;
        this.loser = loser;
    }

    public FootballClub getWinner() {
        return winner;
    }

    public void setWinner(FootballClub winner) {
        this.winner = winner;
    }

    public FootballClub getLoser() {
        return loser;
    }

    public void setLoser(FootballClub loser) {
        this.loser = loser;
    }
}
