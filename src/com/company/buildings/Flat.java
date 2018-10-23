package com.company.buildings;

public class Flat {
    private double area;
    private int roomsCount;

    private static final double defaultArea = 50;
    private static final int defaultRoomsCount = 2;

    public Flat() {
        area = defaultArea;
        roomsCount = defaultRoomsCount;
    }

    public Flat(double area) {
        this();
        this.area = area;
    }

    public Flat(double area, int roomsCount) {
        this.area = area;
        this.roomsCount = roomsCount;
    }

    /*
        Получение площади квартиры
    */
    public double getArea() {
        return area;
    }

    /*
        Получение количества комнат в квартире
     */
    public int getRoomsCount() {
        return roomsCount;
    }

    /*
        Изменение площади квартиры
    */
    public void setArea(double area) {
        this.area = area;
    }

    /*
        Изменение количества комнат в квартире
    */
    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }
}
