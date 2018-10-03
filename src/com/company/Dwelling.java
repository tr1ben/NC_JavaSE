package com.company;

public class Dwelling {
    private DwellingFloor[] dwellingFloors;

    public Dwelling(int floorsCount, int[] flatsCount) {
        dwellingFloors = new DwellingFloor[floorsCount];
        for (int i = 0; i < dwellingFloors.length; i++) dwellingFloors[i] = new DwellingFloor(flatsCount[i]);
    }

    public Dwelling(DwellingFloor[] dwellingFloors) {
        this.dwellingFloors = dwellingFloors;
    }

    public int getFloorsCount() {
        return dwellingFloors.length;
    }

    public int getFlatsCount() {
        int flatsCount = 0;
        for (DwellingFloor floor : dwellingFloors) flatsCount += floor.getFlatsCount();
        return flatsCount;
    }

    public double getGlobalArea() {
        double area = 0;
        for (DwellingFloor floor : dwellingFloors) area += floor.getGlobalArea();
        return area;
    }

    public int getRoomsCount() {
        int roomsCount = 0;
        for (DwellingFloor floor : dwellingFloors) roomsCount += floor.getGlobalRoomsCount();
        return roomsCount;
    }

    public DwellingFloor[] getDwellingFloors() {
        return dwellingFloors;
    }

    public DwellingFloor getFloor(int num) {
        return dwellingFloors[num];
    }

    public void changeFloor(int num, DwellingFloor newFloor) {
        dwellingFloors[num] = newFloor;
    }

    public Flat getFlat(int flatNum) {
        int num = 0;
        for (DwellingFloor floor : dwellingFloors) {
            for (int i = 0; i < floor.getFlatsCount(); i++) {
                if(num == flatNum) return floor.getFlat(i);
            }
        }
        return null;
    }

    public void changeFlat(int flatNum, Flat newFlat){
        int num = 0;
        for (DwellingFloor floor : dwellingFloors) {
            for (int i = 0; i < floor.getFlatsCount(); i++) {
                if(num == flatNum) {
                    floor.getFlat(i).setArea(newFlat.getArea());
                    floor.getFlat(i).setRoomsCount(newFlat.getRoomsCount());
                }
            }
        }
    }
    

}
