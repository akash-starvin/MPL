package com.example.mpl;

public class MBFeedback {

    String Name,Feedback,Rating,Phone;

    public MBFeedback(String name, String feedback, String rating, String phone) {
        Name = name;
        Feedback = feedback;
        Rating = rating;
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
