package com.example.mpl;

public class ModelSelectPlayers {
    String pname,pteam,pcredits;

    public ModelSelectPlayers(String pname, String pteam, String pcredits) {
        this.pname = pname;
        this.pteam = pteam;
        this.pcredits = pcredits;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPteam() {
        return pteam;
    }

    public void setPteam(String pteam) {
        this.pteam = pteam;
    }

    public String getPcredits() {
        return pcredits;
    }

    public void setPcredits(String pcredits) {
        this.pcredits = pcredits;
    }
}
