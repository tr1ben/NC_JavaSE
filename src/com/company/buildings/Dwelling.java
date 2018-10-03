package com.company.buildings;

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
                num++;
            }
        }
        return null;
    }

    public void changeFlat(int flatNum, Flat newFlat){
        int num = 0;
        for (DwellingFloor floor : dwellingFloors) {
            for (int i = 0; i < floor.getFlatsCount(); i++) {
                if(num == flatNum) floor.changeFlat(i, newFlat);
                num++;
            }
        }
    }

    public void addFlat(int flatNum, Flat newFlat){
        int num = 0;
        for (DwellingFloor floor : dwellingFloors) {
            for (int i = 0; i < floor.getFlatsCount(); i++) {
                if(num == flatNum) floor.addFlat(i, newFlat);
                num++;
            }
        }
    }

    public void removeFlat(int flatNum) {
        int num = 0;
        for (DwellingFloor floor : dwellingFloors) {
            for (int i = 0; i < floor.getFlatsCount(); i++) {
                if(num == flatNum) floor.removeFlat(i);
                num++;
            }
        }
    }

    public Flat getBestSpace() {
        double bestSpace = 0;
        Flat bestFlat = null;
        for (DwellingFloor floor : dwellingFloors) {
            if(floor.getBestSpace().getArea() > bestSpace) {
                bestSpace = floor.getBestSpace().getArea();
                bestFlat = floor.getBestSpace();
            }
        }
        return bestFlat;
    }

    //метод получения отсортированного по убыванию площадей массива квартир
    public Flat[] getDescAreaSortedFlatList() {
        Flat[] flats = new Flat[getFlatsCount()];
        for (int i = 0; i < flats.length; i++) {
            flats[i] = getFlat(i);
        }
        for (int out = flats.length - 1; out >= 1; out--){
            for (int in = 0; in < out; in++){
                if(flats[in].getArea() < flats[in + 1].getArea()) {
                    Flat dummy = flats[in];
                    flats[in] = flats[in + 1];
                    flats[in + 1] = dummy;
                }
            }
        }
        return flats;
    }

}
