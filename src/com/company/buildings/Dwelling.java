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

    /*
        Получение общего количества этажей дома
    */
    public int getFloorsCount() {
        return dwellingFloors.length;
    }

    /*
        Получение общего количества квартир дома
    */
    public int getFlatsCount() {
        int flatsCount = 0;
        for (DwellingFloor floor : dwellingFloors) flatsCount += floor.getFlatsCount();
        return flatsCount;
    }

    /*
        Получение общей площади квартир дома
    */
    public double getArea() {
        double area = 0;
        for (DwellingFloor floor : dwellingFloors) area += floor.getArea();
        return area;
    }

    /*
        Получение общего количества комнат дома
    */
    public int getRoomsCount() {
        int roomsCount = 0;
        for (DwellingFloor floor : dwellingFloors) roomsCount += floor.getRoomsCount();
        return roomsCount;
    }

    /*
        Получение массива этажей дома
    */
    public DwellingFloor[] getDwellingFloors() { return dwellingFloors; }

    /*
        Получение этажа по номеру в доме
    */
    public DwellingFloor getFloor(int num) {
        try { return dwellingFloors[num]; }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException();
        }
    }

    /*
        Изменение этажа по номеру в доме и ссылке на обновленный этаж
    */
    public void setFloor(int num, DwellingFloor newFloor) {
        try { dwellingFloors[num] = newFloor; }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new FloorIndexOutOfBoundsException();
        }
    }

    /*
        Получения квартиры по номеру в доме
    */
    public Flat getFlat(int flatNum) {
        if((flatNum < 0) || (flatNum >= getFlatsCount())) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        int num = 0;
        for (DwellingFloor floor : dwellingFloors) {
            for (int i = 0; i < floor.getFlatsCount(); i++) {
                if (num == flatNum) return floor.getFlat(i);
                num++;
            }
        }
        return null;
    }

    /*
        Изменение квартиры по номеру в доме и ссылке на новую квартиру
    */
    public void setFlat(int flatNum, Flat newFlat){
        if((flatNum < 0) || (flatNum >= getFlatsCount())) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        int num = 0;
        for (DwellingFloor floor : dwellingFloors) {
            for (int i = 0; i < floor.getFlatsCount(); i++) {
                if (num == flatNum) floor.setFlat(i, newFlat);
                num++;
            }
        }
    }

    /*
        Добавление квартиры по будущему номеру квартиры в доме и ссылке на новую квартиру
    */
    public void addFlat(int flatNum, Flat newFlat){
        if((flatNum < 0) || (flatNum > getFlatsCount())) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        int num = 0;
        for (DwellingFloor floor : dwellingFloors) {
            for (int i = 0; i < floor.getFlatsCount(); i++) {
                if (num == flatNum) floor.addFlat(i, newFlat);
                num++;
            }
        }
    }

    /*
        Удаление квартиры по номеру в доме
    */
    public void removeFlat(int flatNum) {
        if((flatNum < 0) || (flatNum >= getFlatsCount())) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        int num = 0;
        for (DwellingFloor floor : dwellingFloors) {
            for (int i = 0; i < floor.getFlatsCount(); i++) {
                if (num == flatNum) floor.removeFlat(i);
                num++;
            }
        }
    }

    /*
        Получение самой большой по площади квартиры дома
    */
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

    /*
        Получение отсортированного по убыванию площадей массива квартир
    */
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
