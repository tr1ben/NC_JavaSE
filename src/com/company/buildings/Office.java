package com.company.buildings;

import java.io.Serializable;
import java.util.Objects;

public class Office implements Space, Serializable {
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
        if(area <= 0) throw new InvalidSpaceAreaException();
        this.area = area;
    }

    public Office(double area, int roomsCount) {
        if(area <= 0) throw new InvalidSpaceAreaException();
        if(roomsCount <= 0) throw new InvalidRoomsCountException();
        this.area = area;
        this.roomsCount = roomsCount;
    }

    /*
        Получение площади офиса
    */
    public double getArea() {
        return area;
    }

    /*
        Получение количества комнат в офисе
    */
    public int getRoomsCount() {
        return roomsCount;
    }

    /*
        Изменение площади офиса
    */
    public void setArea(double area) {
        this.area = area;
    }

    /*
        Изменение количества комнат в офисе
    */
    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    @Override
    public String toString() {
        return "Office (" + roomsCount + ", " + area + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return Double.compare(office.area, area) == 0 &&
                roomsCount == office.roomsCount;
    }

    @Override
    public int hashCode() {
        int hash = roomsCount;
        long l = Double.doubleToLongBits(area);
        hash ^= (int)(l >> 32) ^ (int)(l & 0xFFFFFFFF);
        return hash;
    }
}
