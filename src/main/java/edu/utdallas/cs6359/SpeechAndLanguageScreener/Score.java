package edu.utdallas.cs6359.SpeechAndLanguageScreener;

public class Score {
    private int points_earned;
    private int points_possible;
    private double percentage;

    private void calcAndSetPercentage() {
        if(points_possible == 0){
            percentage = 0;
        } else {
            percentage = (points_earned / points_possible) * 100;
        }
    }

    public Score(int points_earned, int points_possible){
        this.points_earned = points_earned;
        this.points_possible = points_possible;
        calcAndSetPercentage();
    }

    public int getPoints_earned() {
        return points_earned;
    }

    public void setPoints_earned(int points_earned) {
        this.points_earned = points_earned;
        calcAndSetPercentage();
    }

    public int getPoints_possible() {
        return points_possible;
    }

    public void setPoints_possible(int points_possible) {
        this.points_possible = points_possible;
        calcAndSetPercentage();
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void addScore(Score score){
        points_earned += score.points_earned;
        points_possible += score.points_possible;
        calcAndSetPercentage();
    }

}
