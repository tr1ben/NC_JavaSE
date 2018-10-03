package com.company.buildings;

public class DwellingFloor {
    private Flat[] flats;

    public DwellingFloor(Flat[] flats) {
        this.flats = flats;
    }

    public DwellingFloor(int flatsCount) {
        flats = new Flat[flatsCount];
        for (int i = 0; i < flatsCount; i++) flats[i] = new Flat();
    }

    public int getFlatsCount() {
    return flats.length;
    }

    public double getGlobalArea() {
        double area = 0;
        for (Flat flat : flats) area += flat.getArea();
        return area;
    }

    public int getGlobalRoomsCount() {
        int roomsCount = 0;
        for (Flat flat : flats) roomsCount += flat.getRoomsCount();
        return roomsCount;
    }

    public Flat[] getFlats() {
        return flats;
    }

    public Flat getFlat(int num) {
        return flats[num];
    }

    public void changeFlat(int num, Flat newFlat) {
        flats[num] = newFlat;
    }

    public void addFlat(int num, Flat newFlat) {
        Flat[] newFlats = new Flat[flats.length+1];
        int oldNum = 0;
        for (int i = 0; i < newFlats.length; i++) {
            if(num == i) newFlats[i] = newFlat;
            else {
                newFlats[i] = flats[oldNum];
                oldNum++;
            }
        }
        flats = newFlats;
    }

    public void removeFlat(int num) {
        Flat[] newFlats = new Flat[flats.length-1];
        int oldNum = 0;
        for (int i = 0; i < newFlats.length; i++) {
            if(num == i) oldNum++;
            newFlats[i] = flats[oldNum];
            oldNum++;
        }
        flats = newFlats;
    }

    public Flat getBestSpace() {
        double bestSpace = 0;
        Flat bestFlat = null;
        for (Flat flat : flats) {
            if(flat.getArea() > bestSpace) {
                bestSpace = flat.getArea();
                bestFlat = flat;
            }
        }
        return bestFlat;
    }
}
