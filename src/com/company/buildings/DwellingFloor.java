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

    /*
        Получение количества квартир на этаже
    */
    public int getFlatsCount() { return flats.length; }

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
    public Flat[] getFlats() {
        return flats;
    }

    /*
        Получение квартиры по номеру на этаже
    */
    public Flat getFlat(int num) {
        try { return flats[num]; }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        }
    }

    /*
        Изменение квартиры по номеру на этаже и ссылке на новую квартиру
    */
    public void changeFlat(int num, Flat newFlat) {
        try { flats[num] = newFlat; }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        }
    }

    /*
        Добавление новой квартиры по будущему номеру на этаже и ссылке на новую квартиру
    */
    public void addFlat(int num, Flat newFlat) {
        try {
            Flat[] newFlats = new Flat[flats.length + 1];
            int oldNum = 0;
            for (int i = 0; i < newFlats.length; i++) {
                if (num == i) newFlats[i] = newFlat;
                else {
                    newFlats[i] = flats[oldNum];
                    oldNum++;
                }
            }
            flats = newFlats;
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        }
    }

    /*
        Удаление квартиры по номеру на этаже
    */
    public void removeFlat(int num) {
        try {
            Flat[] newFlats = new Flat[flats.length - 1];
            int oldNum = 0;
            for (int i = 0; i < newFlats.length; i++) {
                if (num == i) oldNum++;
                newFlats[i] = flats[oldNum];
                oldNum++;
            }
            flats = newFlats;
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        }
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
