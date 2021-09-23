package com.example.shopapp.entity;

import java.sql.Timestamp;

public class Order extends Entity{

    private User user;
    private State state;
    private int totalPrice;
    private Timestamp timestamp;

//    public Order(User user, State state, int totalPrice, Timestamp timestamp){
//        su
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
