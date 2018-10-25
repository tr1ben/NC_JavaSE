package com.company.buildings;

public class Main {

    public static void main(String[] args) {
        Flat first = new Flat();
        Flat second = new Flat(60);
        Flat third = new Flat(50, 3);
        Flat fourth = new Flat(40);
        Flat fifth = new Flat(30, 1);
        DwellingFloor firstFloor = new DwellingFloor(new Flat[]{first, second});
        DwellingFloor secondFloor = new DwellingFloor(3);
        secondFloor.addSpace(100, new Flat());
        DwellingFloor thirdFloor = new DwellingFloor(new Flat[]{third, fourth, fifth});
        Dwelling firstDwelling = new Dwelling(new DwellingFloor[]{firstFloor, secondFloor, thirdFloor});
        Dwelling secondDwelling = new Dwelling(3, new int[]{2,3,2});
        System.out.println(firstDwelling.getArea());
        Flat[] SortedList = (Flat[]) firstDwelling.getDescAreaSortedSpaceMassive();
        for (Flat flat : SortedList) System.out.println(flat.getArea());

        Office fo = new Office();
        Office so = new Office(60);
        Office qo = new Office(90);
        System.out.println("Fo = " + fo.getArea());
        System.out.println("So = " + so.getArea());
        OfficeFloor fFloor = new OfficeFloor(new Office[]{fo, so, fo, so, so, fo});
        System.out.println("Офисов: " + fFloor.getSpacesCount());
        System.out.println("Area: " + fFloor.getArea());
        System.out.println("Комнат: " + fFloor.getRoomsCount());
        /*fFloor.printOffices();
        fFloor.removeOffice(4);
        System.out.println("=====================");
        fFloor.printOffices();
        fFloor.removeOffice(0);
        System.out.println("=====================");
        fFloor.printOffices();
        fFloor.insertOffice(2, qo);
        System.out.println("=====================");
        fFloor.printOffices();
        Office[] offf = fFloor.getOffices();
        for (int i = 0; i < offf.length; i++) {
            System.out.println("offf" + i + " = " + offf[i].getArea());
        }
        System.out.println("BEST = " + fFloor.getBestSpace().getArea());*/

        OfficeFloor sFloor = new OfficeFloor(new Office[]{qo, fo, so});
        OfficeFloor tFloor = new OfficeFloor(new Office[]{so, so, fo});
        OfficeBuilding fBuilding = new OfficeBuilding(new OfficeFloor[]{fFloor, sFloor, tFloor});
        System.out.println("AREA = " + fBuilding.getArea());
        System.out.println("RoomsCount = " + fBuilding.getRoomsCount());
        OfficeFloor[] oFloors = (OfficeFloor[]) fBuilding.getFloors();
        for (int i = 0; i < oFloors.length; i++) {
            System.out.println(oFloors[i].getArea());
        }
        System.out.println("Сортировка:");
        Office[] offices = (Office[]) fBuilding.getDescAreaSortedSpaceMassive();
        for (int i = 0; i < offices.length; i++) {
            System.out.println(offices[i].getArea());
        }
    }
}
