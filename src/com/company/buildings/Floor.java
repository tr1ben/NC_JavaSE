package com.company.buildings;

public interface Floor {
    int getSpacesCount();
    double getArea();
    int getRoomsCount();
    Space[] getSpaces();
    Space getSpace(int num);
    void setSpace(int num, Space newSpace);
    void addSpace(int num, Space newSpace);
    void removeSpace(int num);
    Space getBestSpace();
}
