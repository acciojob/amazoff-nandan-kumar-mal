package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id = id;

        String dTime = deliveryTime;
        String[] timef = dTime.split(":");

        int hour = Integer.parseInt(timef[0]);
        int minute = Integer.parseInt(timef[1]);
        int  time = hour*60 + minute;

        this.deliveryTime = time;

    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
