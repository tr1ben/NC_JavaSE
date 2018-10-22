package com.company.buildings;

public class OfficeFloor {

    public class Node {
        private Office head;
        private Node next;

        public Node(Office head) {
            this.head = head;
            this.next = this;
        }

        public Node(Office head, Node next) {
            this.head = head;
            this.next = next;
        }

        public Office getHead() {
            return head;
        }

        public Node getNext() {
            return next;
        }

        public void setHead(Office head) {
            this.head = head;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public boolean hasNext() {
            return this.next != null && this.next != this;
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
        newNode.setNext(headNode);
        size++;
    }

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

    public OfficeFloor(Office[] offices) {
        headNode = new Node(offices[0]);
        for (int i = 1; i < offices.length; i++) addNode(new Node(offices[i]));
    }

    public int getOfficesCount() {
        return size;
    }

    public double getArea() {
        Node node = headNode;
        double area = 0;
        for (int i = 0; i < size; i++) {
            area += node.getHead().getArea();
            node = node.getNext();
        }
        return area;
    }

    public int getRoomsCount() {
        Node node = headNode;
        int roomsCount = 0;
        for (int i = 0; i < size; i++) {
            roomsCount += node.getHead().getRoomsCount();
            node = node.getNext();
        }
        return roomsCount;
    }

    public Office[] getOffices() {
        Office[] offices = new Office[size];
        for (int i = 0; i < size; i++) {
            offices[i] = getNode(i+1).getHead();
        }
        return offices;
    }

    public Office getOffice(int num) {
        if(num < size) return getNode(num+1).getHead();
        else return null;
    }

    public void setOffice(int num, Office newOffice) {
        getNode(num+1).setHead(newOffice);
    }

    public void insertOffice(int num, Office office) {
        insertNode(num+1, new Node(office));
    }

    public void removeOffice(int num) {
        removeNode(num+1);
    }

    public Office getBestSpace() {
        Office bestSpace = getNode(1).getHead();
        for (int i = 2; i < size; i++) {
            if(getNode(i).getHead().getArea() > bestSpace.getArea()) bestSpace = getNode(i).getHead();
        }
        return bestSpace;
    }

    public void printOffices() {
        Node node = headNode;
        for (int i = 0; i < size; i++) {
            System.out.println(node.getHead().getArea());
            node = node.getNext();
        }
    }

}
