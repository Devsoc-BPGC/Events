package com.macbitsgoa.events.LeaderBoard;

public class LeaderBoardModel {
   private String Name;
   private int points;
   private int Position;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getPoints() {
        return points;
    }

    public LeaderBoardModel(String name, int points, int position) {
        Name = name;
        this.points = points;
        Position = position;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }
}
