package com.macbitsgoa.events.LeaderBoard;

public class LeaderBoardModel {
   private String name;
   private int totalpoints;
   private int Position;


    public String getName() {
        return name;
    }


    public void setName(String name){
        this.name=name;
    }


    public int getTotalpoints() {
        return totalpoints;
    }
    public void setTotalpoints(int totalpoints){
        this.totalpoints=totalpoints;
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }
    public LeaderBoardModel(){

    }

    public LeaderBoardModel(String name, int totalpoints, int position) {
        this.name = name;
        this.totalpoints = totalpoints;

        //Position = position;

    }


}
