package com.example.mpl;

public class ModelUserTeams {
    String name,points;

    public ModelUserTeams(String name, String points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
