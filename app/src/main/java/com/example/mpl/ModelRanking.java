package com.example.mpl;

public class ModelRanking {

    String rank,uname,total,phone;

    public ModelRanking(String rank, String uname, String total) {
        this.rank = rank;
        this.uname = uname;
        this.total = total;
    }

    public ModelRanking(String rank, String uname, String total, String phone) {
        this.rank = rank;
        this.uname = uname;
        this.total = total;
        this.phone = phone;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
