package com.company.buildings;

import java.io.Serializable;
import java.util.Arrays;

public class Dwelling implements Building, Serializable {
    private Floor[] dwellingFloors;

    public Dwelling(int floorsCount, int ... flatsCount) {
        dwellingFloors = new DwellingFloor[floorsCount];
        for (int i = 0; i < dwellingFloors.length; i++) dwellingFloors[i] = new DwellingFloor(flatsCount[i]);
    }

    public Dwelling(Floor ... dwellingFloors) { this.dwellingFloors = dwellingFloors; }

    /*
        Получение общего количества этажей дома
    */
    public int getFloorsCount() { return dwellingFloors.length; }

    /*
        Получение общего количества квартир дома
    */
    public int getSpacesCount() {
        int flatsCount = 0;
        for (Floor floor : dwellingFloors) flatsCount += floor.getSpacesCount();
        return flatsCount;
    }

    /*
        Получение общей площади квартир дома
    */
    public double getArea() {
        double area = 0;
        for (Floor floor : dwellingFloors) area += floor.getArea();
        return area;
    }

    /*
        Получение общего количества комнат дома
    */
    public int getRoomsCount() {
        int roomsCount = 0;
        for (Floor floor : dwellingFloors) roomsCount += floor.getRoomsCount();
        return roomsCount;
    }

    /*
        Получение массива этажей дома
    */
    public Floor[] getFloors() { return dwellingFloors; }

    /*
        Получение этажа по номеру в доме
    */
    public Floor getFloor(int num) {
        try { return dwellingFloors[num]; }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException();
        }
    }

    /*
        Изменение этажа по номеру в доме и ссылке на обновленный этаж
    */
    public void setFloor(int num, Floor newFloor) {
        try { dwellingFloors[num] = (DwellingFloor) newFloor; }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException();
        }
    }

    /*
        Получения квартиры по номеру в доме
    */
    public Space getSpace(int spaceNum) {
        if((spaceNum < 0) || (spaceNum >= getSpacesCount())) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        int num = 0;
        for (Floor floor : dwellingFloors) {
            for (int i = 0; i < floor.getSpacesCount(); i++) {
                if (num == spaceNum) return (Flat) floor.getSpace(i);
                num++;
            }
        }
        return null;
    }

    /*
        Изменение квартиры по номеру в доме и ссылке на новую квартиру
    */
    public void setSpace(int spaceNum, Space newSpace){
        if((spaceNum < 0) || (spaceNum >= getSpacesCount())) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        int num = 0;
        for (Floor floor : dwellingFloors) {
            for (int i = 0; i < floor.getSpacesCount(); i++) {
                if (num == spaceNum) floor.setSpace(i, newSpace);
                num++;
            }
        }
    }

    /*
        Добавление квартиры по будущему номеру квартиры в доме и ссылке на новую квартиру
    */
    public void addSpace(int spaceNum, Space newSpace){
        if((spaceNum < 0) || (spaceNum > getSpacesCount())) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        int num = 0;
        for (Floor floor : dwellingFloors) {
            for (int i = 0; i < floor.getSpacesCount(); i++) {
                if (num == spaceNum) floor.addSpace(i, newSpace);
                num++;
            }
        }
    }

    /*
        Удаление квартиры по номеру в доме
    */
    public void removeSpace(int spaceNum) {
        if((spaceNum < 0) || (spaceNum >= getSpacesCount())) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        int num = 0;
        for (Floor floor : dwellingFloors) {
            for (int i = 0; i < floor.getSpacesCount(); i++) {
                if (num == spaceNum) floor.removeSpace(i);
                num++;
            }
        }
    }

    /*
        Получение самой большой по площади квартиры дома
    */
    public Space getBestSpace() {
        Space bestFlat = getFloor(0).getBestSpace();
        for (Floor floor : dwellingFloors) {
            if(floor.getBestSpace().getArea() > bestFlat.getArea()) bestFlat = floor.getBestSpace();
        }
        return bestFlat;
    }

    /*
        Получение отсортированного по убыванию площадей массива квартир
    */
    public Space[] getDescAreaSortedSpaceMassive() {
        Flat[] flats = new Flat[getSpacesCount()];
        for (int i = 0; i < flats.length; i++) {
            flats[i] = (Flat) getSpace(i);
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

    @Override
    public String toString() {
        StringBuffer dwellingString = new StringBuffer();
        dwellingString.append("Dwelling (" + dwellingFloors.length);
        for(Floor floor : dwellingFloors) dwellingString.append(", " + floor);
        dwellingString.append(")");
        return dwellingString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dwelling dwelling = (Dwelling) o;
        return Arrays.equals(dwellingFloors, dwelling.dwellingFloors);
    }

    @Override
    public int hashCode() {
        int hash = dwellingFloors.length;
        for (int i = 0; i < dwellingFloors.length; i++) {
            hash ^= dwellingFloors[i].hashCode();
        }
        return hash;
    }
}
