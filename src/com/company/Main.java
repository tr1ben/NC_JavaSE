package com.company;

public class Main {

    public static void main(String[] args) {
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
        System.out.println(firstDwelling.getGlobalArea());
    }
}