package com.company.buildings;

public class DwellingFloor implements Floor {
    private Flat[] flats;

    public DwellingFloor(Flat[] flats) {
        this.flats = flats;
    }

    public DwellingFloor(int flatsCount) {
        flats = new Flat[flatsCount];
        for (int i = 0; i < flatsCount; i++) flats[i] = new Flat();
    }

    /*
        Получение количества квартир на этаже
    */
    public int getSpacesCount() { return flats.length; }

    /*
        Получение общей площади квартир этажа
    */
    public double getArea() {
        double area = 0;
        for (Flat flat : flats) area += flat.getArea();
        return area;
    }

    /*
        Получение общего количества комнат этажа
    */
    public int getRoomsCount() {
        int roomsCount = 0;
        for (Flat flat : flats) roomsCount += flat.getRoomsCount();
        return roomsCount;
    }

    /*
        Получение массива квартир этажа
    */
    public Space[] getSpaces() {
        return flats;
    }

    /*
        Получение квартиры по номеру на этаже
    */
    public Space getSpace(int num) {
        try { return flats[num]; }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        }
    }

    /*
        Изменение квартиры по номеру на этаже и ссылке на новую квартиру
    */
    public void setSpace(int num, Space newSpace) {
        try { flats[num] = (Flat)newSpace; }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        }
    }

    /*
        Добавление новой квартиры по будущему номеру на этаже и ссылке на новую квартиру
    */
    public void addSpace(int num, Space newSpace) {
        if((num < 0) || (num > flats.length)) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        Flat[] newFlats = new Flat[flats.length + 1];
        int oldNum = 0;
        for (int i = 0; i < newFlats.length; i++) {
            if (num == i) newFlats[i] = (Flat) newSpace;
            else {
                newFlats[i] = flats[oldNum];
                oldNum++;
            }
        }
        flats = newFlats;
    }

    /*
        Удаление квартиры по номеру на этаже
    */
    public void removeSpace(int num) {
        if((num < 0) || (num >= flats.length)) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        Flat[] newFlats = new Flat[flats.length - 1];
        int oldNum = 0;
        for (int i = 0; i < newFlats.length; i++) {
            if (num == i) oldNum++;
            newFlats[i] = flats[oldNum];
            oldNum++;
        }
        flats = newFlats;
    }

    /*
        Получение самой большой по площади квартиры этажа
    */
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
