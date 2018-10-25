package com.company.buildings;

public interface Building {
    int getFloorsCount();
    int getSpacesCount();
    double getArea();
    int getRoomsCount();
    Floor[] getFloors();
    Floor getFloor(int num);
    void setFloor(int num, Floor newFloor);
    Space getSpace(int SpaceNum);
    void setSpace(int SpaceNum, Space newSpace);
    void addSpace(int SpaceNum, Space newSpace);
    void removeSpace(int SpaceNum);
    Space getBestSpace();
    Space[] getDescAreaSortedSpaceMassive();
}
