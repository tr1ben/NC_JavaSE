package com.company.buildings;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Flat first = new Flat();
        Flat second = new Flat(60);
        Flat third = new Flat(50, 3);
        Flat fourth = new Flat(40);
        Flat fifth = new Flat(30, 1);
        DwellingFloor firstFloor = new DwellingFloor(new Flat[]{first, second});
        DwellingFloor secondFloor = new DwellingFloor(3);
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

        OfficeFloor sFloor = new OfficeFloor(new Office[]{qo, fo, so});
        OfficeFloor tFloor = new OfficeFloor(new Office[]{so, so, fo});
        OfficeBuilding fBuilding = new OfficeBuilding(new OfficeFloor[]{fFloor, sFloor, tFloor});
        OutputStream of = new FileOutputStream(new File("sadd.txt"));
        Buildings.outputBuilding(fBuilding, of);
        InputStream in = new FileInputStream(new File("sadd.txt"));
        Dwelling dw = (Dwelling) Buildings.inputBuilding(in);
        int ex = 0;
        for (Floor fl : dw.getFloors()) {
            System.out.println("Этаж " + ex++);
            for(Space s : fl.getSpaces()){
                System.out.println(s.getRoomsCount() + " " + s.getArea());
            }
        }
        OfficeFloor[] oFloors = (OfficeFloor[]) fBuilding.getFloors();
        System.out.println("Сортировка:");
        Office[] offices = (Office[]) fBuilding.getDescAreaSortedSpaceMassive();
        for (int i = 0; i < offices.length; i++) {
            System.out.println(offices[i].getArea());
        }

        /**
         * Проверка сериализации
         */
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("out.bin"));
            out.writeObject(dw);
            out.close();
        } catch (IOException e) {
            System.out.println("Serialization error");
        }

        /**
         * Проверка десериализации
         */
        Dwelling dw1 = null;
        try {
            ObjectInputStream oIn = new ObjectInputStream(new FileInputStream("out.bin"));
            dw1 = (Dwelling) oIn.readObject();
            in.close();
        } catch (IOException e) {
            System.out.println("Serialization error");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
        System.out.println("Area: " + dw.getArea());
        System.out.println("Area: " + dw.getArea());
        System.out.println(dw.getSpace(0));
        System.out.println(dw.getFloor(0));
        System.out.println(dw);
    }
}
