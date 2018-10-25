package com.company.buildings;

public class PlacementExchanger {
    public static boolean checkSpaces(Space space1, Space space2) { return ((space1.getArea() == space2.getArea()) && (space1.getRoomsCount() == space2.getRoomsCount())); }
    public static boolean checkFloors(Floor floor1, Floor floor2) { return ((floor1.getArea() == floor2.getArea()) && (floor1.getSpacesCount() == floor2.getSpacesCount())); }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException {
        if(!checkSpaces(floor1.getSpace(index1),floor2.getSpace(index2))) throw new InexchangeableSpacesException();
        Space temp = floor1.getSpace(index1);
        floor1.setSpace(index1, floor2.getSpace(index2));
        floor2.setSpace(index2, temp);
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException {
        if(!checkFloors(building1.getFloor(index1),building2.getFloor(index2))) throw new InexchangeableFloorsException();
        Floor temp = building1.getFloor(index1);
        building1.setFloor(index1, building2.getFloor(index2));
        building2.setFloor(index2, temp);
    }
}
