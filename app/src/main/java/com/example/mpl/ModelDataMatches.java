package com.example.mpl;

public class ModelDataMatches {
    String t1, t2, img1, img2, mname,status, date;

    public ModelDataMatches(String t1, String t2, String img1, String img2, String mname, String status,String date) {
        this.t1 = t1;
        this.t2 = t2;
        this.img1 = img1;
        this.img2 = img2;
        this.mname = mname;
        this.status = status;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
