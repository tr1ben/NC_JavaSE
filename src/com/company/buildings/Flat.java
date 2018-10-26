package com.company.buildings;

import java.io.Serializable;

public class Flat implements Space, Serializable {
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
        if(area <= 0) throw new InvalidSpaceAreaException();
        this.area = area;
    }

    public Flat(double area, int roomsCount) {
        if(area <= 0) throw new InvalidSpaceAreaException();
        if(roomsCount <= 0) throw new InvalidRoomsCountException();
        this.area = area;
        this.roomsCount = roomsCount;
    }

    /*
        Получение площади квартиры
    */
    public double getArea() { return area; }

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

    @Override
    public String toString() {
        return "Flat (" + roomsCount + ", " + area + ")";
    }
}
