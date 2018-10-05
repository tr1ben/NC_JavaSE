package com.company.buildings;

public class Office {
    private double area;
    private int roomsCount;

    private static final double DEFAULT_AREA = 250;
    private static final int DEFAULT_ROOMS_COUNT = 1;

    public Office() {
        area = DEFAULT_AREA;
        roomsCount = DEFAULT_ROOMS_COUNT;
    }

    public Office(double area) {
        this();
        this.area = area;
    }

    public Office(double area, int roomsCount) {
        this.area = area;
        this.roomsCount = roomsCount;
    }

    public double getArea() {
        return area;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }
}
