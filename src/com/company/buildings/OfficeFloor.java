package com.company.buildings;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class OfficeFloor implements Floor, Serializable {

    public class Node {
        private Space head;
        private Node next;

        public Node(Space head) {
            this.head = head;
            this.next = this;
        }

        public Node(Space head, Node next) {
            this.head = head;
            this.next = next;
        }

        public Space getHead() {
            return head;
        }

        public Node getNext() {
            return next;
        }

        public void setHead(Space head) {
            this.head = head;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }

    private Node headNode;
    private int size = 1;

    /*
        Получение узла по номеру
    */
    private Node getNode(int num) {
        Node node = headNode;
        for (int i = 1; i < num; i++) node = node.getNext();
        return node;
    }

    /*
        Добавление узла в конец
    */
    private void addNode(Node newNode){
        Node lastNode = getNode(size);
        lastNode.setNext(newNode);
        newNode.setNext(headNode);
        size++;
    }

    /*
        Вставка (добавление) узла по номеру
    */
    private void insertNode(int num, Node newNode) {
        Node node;
        if(num == 1) {
            node = headNode;
            getNode(size).setNext(newNode);
            newNode.setNext(node);
            headNode = newNode;
        } else {
            newNode.setNext(getNode(num));
            getNode(num-1).setNext(newNode);
        }
        size++;
    }

    /*
        Удаление узла по номеру
    */
    private void removeNode(int num) {
        if(num == 1) {
            headNode = headNode.getNext();
            getNode(size).setNext(headNode);
        } else getNode(num-1).setNext(getNode(num+1));
        size--;
    }

    public OfficeFloor(int officesCount) {
        headNode = new Node(new Office());
        for (int i = 1; i < officesCount; i++) addNode(new Node(new Office()));
    }

    public OfficeFloor(Space ... offices) {
        headNode = new Node(offices[0]);
        for (int i = 1; i < offices.length; i++) addNode(new Node(offices[i]));
    }

    /*
        Получение количества офисов на этаже
    */
    public int getSpacesCount() { return size; }

    /*
        Получение общей площади помещений этажа
    */
    public double getArea() {
        Node node = headNode;
        double area = 0;
        for (int i = 0; i < size; i++) {
            area += node.getHead().getArea();
            node = node.getNext();
        }
        return area;
    }

    /*
        Получение общего количества комнат этажа
    */
    public int getRoomsCount() {
        Node node = headNode;
        int roomsCount = 0;
        for (int i = 0; i < size; i++) {
            roomsCount += node.getHead().getRoomsCount();
            node = node.getNext();
        }
        return roomsCount;
    }

    /*
        Получение массива офисов этажа
    */
    public Space[] getSpaces() {
        Space[] offices = new Office[size];
        for (int i = 0; i < size; i++) {
            offices[i] = getNode(i+1).getHead();
        }
        return offices;
    }

    /*
        Получение офиса по номеру на этаже
    */
    public Space getSpace(int num) {
        if((num < 0) || (num >= size)) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        return getNode(num+1).getHead();
    }

    /*
        Изменение офиса по номеру на этаже и ссылке на обновленный офис
    */
    public void setSpace(int num, Space newSpace) {
        if((num < 0) || (num >= size)) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        getNode(num+1).setHead((Office)newSpace);
    }

    /*
        Добавление нового офиса на этаже по будущему номеру офиса
    */
    public void addSpace(int num, Space space) {
        if((num < 0) || (num > size)) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        insertNode(num+1, new Node((Office)space));
    }

    /*
        Удаление офиса по номеру на этаже
    */
    public void removeSpace(int num) {
        if((num < 0) || (num >= size)) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        removeNode(num+1);
    }

    /*
        Получение самого большого по площади офиса этажа
    */
    public Space getBestSpace() {
        Space bestSpace = getNode(1).getHead();
        for (int i = 2; i < size; i++) {
            if(getNode(i).getHead().getArea() > bestSpace.getArea()) bestSpace = getNode(i).getHead();
        }
        return bestSpace;
    }

    @Override
    public String toString() {
        StringBuffer floorString = new StringBuffer();
        floorString.append("OfficeFloor (" + size);
        for(Space office : getSpaces()) floorString.append(", " + office);
        floorString.append(")");
        return floorString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeFloor that = (OfficeFloor) o;
        return size == that.size && Arrays.equals(this.getSpaces(), that.getSpaces());
    }

    @Override
    public int hashCode() {
        int hash = this.getSpaces().length;
        for (int i = 0; i < this.getSpaces().length; i++) {
            hash ^= this.getSpace(i).hashCode();
        }
        return hash;
    }
}
