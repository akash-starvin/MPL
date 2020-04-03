package com.example.mpl;

public class ModelAllTeams {
    String teamname,teamlogo,ownername;

    public ModelAllTeams(String teamname, String teamlogo, String ownername) {
        this.teamname = teamname;
        this.teamlogo = teamlogo;
        this.ownername = ownername;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getTeamlogo() {
        return teamlogo;
    }

    public void setTeamlogo(String teamlogo) {
        this.teamlogo = teamlogo;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }
}
