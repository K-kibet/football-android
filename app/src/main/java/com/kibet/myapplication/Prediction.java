package com.kibet.myapplication;

public class Prediction {
    String countryName, leagueName, imageUrl, homeTeam, awayTeam, predictionResult, matchId;

    public Prediction(String countryName, String leagueName, String imageUrl, String homeTeam, String awayTeam, String predictionResult, String matchId) {
        this.countryName = countryName;
        this.leagueName = leagueName;
        this.imageUrl = imageUrl;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.predictionResult = predictionResult;
        this.matchId = matchId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getPredictionResult() {
        return predictionResult;
    }

    public void setPredictionResult(String predictionResult) {
        this.predictionResult = predictionResult;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}
