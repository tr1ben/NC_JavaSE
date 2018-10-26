package com.company.buildings;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Buildings {
    public static void outputBuilding (Building building, OutputStream out) throws IOException {
        DataOutputStream dOut = new DataOutputStream(out);
        int floorsCount = building.getFloorsCount();
        int spacesCount;
        dOut.write((floorsCount + " ").getBytes());
        for (int i = 0; i < floorsCount; i++) {
            spacesCount = building.getFloor(i).getSpacesCount();
            dOut.write((spacesCount + " ").getBytes());
            for (int j = 0; j < spacesCount; j++) {
                dOut.write((building.getFloor(i).getSpace(j).getRoomsCount() + " ").getBytes());
                dOut.write((building.getFloor(i).getSpace(j).getArea() + " ").getBytes());
            }
        }
        dOut.flush();
    }

    public static Building inputBuilding (InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String s = reader.readLine();
        String[] arr = s.split(" ");
        double[] a = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            a[i] = Double.parseDouble(arr[i]);
        }
        int roomsCount;
        double area;
        int pointer = 1;
        Floor[] floors = new Floor[(int) a[0]];
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[(int) a[pointer++]];
            for (int j = 0; j < spaces.length; j++) {
                roomsCount = (int) a[pointer++];
                area = a[pointer++];
                spaces[j] = new Flat(area, roomsCount);
            }
            floors[i] = new DwellingFloor(spaces);
        }
        return new Dwelling(floors);
    }

    public static void writeBuilding (Building building, Writer out) {
        PrintWriter dOut = new PrintWriter(out);
        int floorsCount = building.getFloorsCount();
        dOut.print(floorsCount);
        for (int i = 0; i < floorsCount; ++i) {
            Floor floor = building.getFloor(i);
            int spacesCount = floor.getSpacesCount();
            dOut.print(' ');
            dOut.print(spacesCount);
            for (int j = 0; j < spacesCount; ++j) {
                Space space = floor.getSpace(j);
                dOut.print(' ');
                dOut.print(space.getRoomsCount());
                dOut.print(' ');
                dOut.print(space.getArea());
            }
        }
        dOut.println();
        dOut.flush();
    }

    public static void writeBuildingFormat(Building building, Writer out) {
        PrintWriter dOut = new PrintWriter(out);
        int floorsCount = building.getFloorsCount();
        dOut.printf("%d", floorsCount);
        for (int i = 0; i < floorsCount; ++i) {
            Floor floor = building.getFloor(i);
            int spacesCount = floor.getSpacesCount();
            dOut.printf(" %d", spacesCount);
            for (int j = 0; j < spacesCount; ++j) {
                Space space = floor.getSpace(j);
                dOut.printf(Locale.ENGLISH, " %d %.1f", space.getRoomsCount(), space.getArea());
            }
        }
        dOut.printf("\n");
        dOut.println();
        dOut.flush();
    }

    public static Building readBuilding (Reader in) throws IOException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(in);
        streamTokenizer.nextToken();
        Floor[] floors = new Floor[(int) streamTokenizer.nval];
        for (int i = 0; i < floors.length; ++i) {
            streamTokenizer.nextToken();
            Space[] spaces = new Space[(int) streamTokenizer.nval];
            for (int j = 0; j < spaces.length; ++j) {
                streamTokenizer.nextToken();
                int roomsCount = (int) streamTokenizer.nval;
                streamTokenizer.nextToken();
                double area = streamTokenizer.nval;
                spaces[j] = new Flat(area, roomsCount);
            }
            floors[i] = new DwellingFloor(spaces);
        }
        return new Dwelling(floors);
    }

    public static Building readBuilding(Scanner sc) throws IOException {
        sc.useLocale(Locale.ENGLISH);
        int floorsCount = sc.nextInt();
        Floor[] floors = new Floor[floorsCount];
        for (int i = 0; i < floorsCount; ++i) {
            int spacesCount = sc.nextInt();
            Space[] spaces = new Space[spacesCount];
            for (int j = 0; j < spacesCount; ++j) {
                int roomsCount = sc.nextInt();
                double area = sc.nextDouble();
                spaces[j] = new Flat(area, roomsCount);
            }
            floors[i] = new DwellingFloor(spaces);
        }
        return new Dwelling(floors);
    }

}
