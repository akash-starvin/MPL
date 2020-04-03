package com.example.mpl;

public class MBComments {
    String name,comment,time;

    public MBComments(String name, String comment, String time) {
        this.name = name;
        this.comment = comment;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
