package com.company.buildings;

public class OfficeBuilding {

    public class Node {
        private Node prev;
        private OfficeFloor head;
        private Node next;

        public Node(OfficeFloor head) {
            this.prev = this;
            this.head = head;
            this.next = this;
        }

        public Node(Node prev, OfficeFloor head, Node next) {
            this.prev = prev;
            this.head = head;
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public OfficeFloor getHead() {
            return head;
        }

        public Node getNext() {
            return next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public void setHead(OfficeFloor head) {
            this.head = head;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }

    private Node headNode;
    private int size = 1;

    private Node getNode(int num) {
        Node node = headNode;
        for (int i = 1; i < num; i++) node = node.getNext();
        return node;
    }

    private void addNode(Node newNode){
        Node lastNode = getNode(size);
        lastNode.setNext(newNode);
        newNode.setPrev(lastNode);
        newNode.setNext(headNode);
        headNode.setPrev(newNode);
        size++;
    }

    private void insertNode(int num, Node newNode) {
        Node node;
        if(num == 1) {
            node = headNode;
            getNode(size).setNext(newNode);
            newNode.setPrev(getNode(size));
            newNode.setNext(node);
            node.setPrev(newNode);
            headNode = newNode;
        } else {
            newNode.setNext(getNode(num));
            newNode.setPrev(getNode(num).getPrev());
            getNode(num).setPrev(newNode);
            getNode(num-1).setNext(newNode);
        }
        size++;
    }

    private void removeNode(int num) {
        if(num == 1) {
            headNode = headNode.getNext();
            getNode(size).setNext(headNode);
            headNode.setPrev(getNode(size));
        } else {
            getNode(num-1).setNext(getNode(num+1));
            getNode(num-1).getNext().setPrev(getNode(num-1));
        }
        size--;
    }

    public OfficeBuilding(int floorsCount, int[] officesCounts) {
        headNode = new Node(new OfficeFloor(officesCounts[0]));
        for (int i = 1; i < floorsCount; i++) addNode(new Node(new OfficeFloor(officesCounts[i])));
    }

    public OfficeBuilding(OfficeFloor[] officeFloors) {
        headNode = new Node(officeFloors[0]);
        for (int i = 1; i < officeFloors.length; i++) addNode(new Node(officeFloors[i]));
    }

    public int getFloorsCount() { return size; }
    
    public int getOfficesCount() {
        int officesCount = 0;
        for (int i = 0; i < size; i++) {
            officesCount += getNode(i+1).getHead().getOfficesCount();
        }
        return officesCount;
    }

    public double getArea() {
        double area = 0;
        for (int i = 0; i < size; i++) {
            area += getNode(i+1).getHead().getArea();
        }
        return area;
    }

    public int getRoomsCount() {
        int roomsCount = 0;
        for (int i = 0; i < size; i++) {
            roomsCount += getNode(i+1).getHead().getRoomsCount();
        }
        return roomsCount;
    }

    public OfficeFloor[] getOfficeFloors(){
        OfficeFloor[] officeFloors = new OfficeFloor[size];
        for (int i = 0; i < size; i++) {
            officeFloors[i] = getNode(i + 1).getHead();
        }
        return officeFloors;
    }

    public OfficeFloor getOfficeFloor(int num) {
        if(num < size) return getNode(num+1).getHead();
        else return null;
    }

    public void setOfficeFloor(int num, OfficeFloor newOfficeFloor) {
        getNode(num+1).setHead(newOfficeFloor);
    }

    public Office getOffice(int num) {
        if(num < size) {
            int counter = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < getOfficeFloor(i).getOfficesCount(); j++) {
                    if(counter == num) return getOfficeFloor(i).getOffice(j);
                    counter++;
                }
            }
        }
        return null;
    }

    public void setOffice(int num, Office newOffice) {
        if(num < size) {
            int counter = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < getOfficeFloor(i).getOfficesCount(); j++) {
                    if(counter == num) getOfficeFloor(i).setOffice(j, newOffice);
                    counter++;
                }
            }
        }
    }

    public void insertOffice(int num, Office newOffice) {
        if(num < size) {
            int counter = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < getOfficeFloor(i).getOfficesCount(); j++) {
                    if(counter == num) getOfficeFloor(i).insertOffice(j, newOffice);
                    counter++;
                }
            }
        }
    }

    public void removeOffice(int num) {
        if(num < size) {
            int counter = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < getOfficeFloor(i).getOfficesCount(); j++) {
                    if(counter == num) getOfficeFloor(i).removeOffice(j);
                    counter++;
                }
            }
        }
    }

    public Office getBestSpace() {
        Office bestSpace = getNode(0).getHead().getBestSpace();
        for (int i = 1; i < size; i++) {
            if(getNode(i+1).getHead().getBestSpace().getArea() > bestSpace.getArea()) bestSpace = getNode(i+1).getHead().getBestSpace();
        }
        return bestSpace;
    }

    //по убыванию площадей
    public Office[] getDescAreaSortedOfficeList() {
        Office[] offices = new Office[getOfficesCount()];
        for (int i = 0; i < offices.length; i++) {
            offices[i] = getOffice(i);
        }
        for (int out = offices.length - 1; out >= 1; out--){
            for (int in = 0; in < out; in++){
                if(offices[in].getArea() < offices[in + 1].getArea()) {
                    Office dummy = offices[in];
                    offices[in] = offices[in + 1];
                    offices[in + 1] = dummy;
                }
            }
        }
        return offices;
    }



}
