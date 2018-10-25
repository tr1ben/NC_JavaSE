package com.company.buildings;

public class OfficeBuilding implements Building {

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

    /*
        Получение общего количества этажей здания
    */
    public int getFloorsCount() { return size; }

    /*
        Получение общего количества офисов здания
    */
    public int getSpacesCount() {
        int officesCount = 0;
        for (int i = 0; i < size; i++) {
            officesCount += getNode(i+1).getHead().getSpacesCount();
        }
        return officesCount;
    }

    /*
        Получение общей площади помещений здания
    */
    public double getArea() {
        double area = 0;
        for (int i = 0; i < size; i++) {
            area += getNode(i+1).getHead().getArea();
        }
        return area;
    }

    /*
        Получение общего количества комнат здания
    */
    public int getRoomsCount() {
        int roomsCount = 0;
        for (int i = 0; i < size; i++) {
            roomsCount += getNode(i+1).getHead().getRoomsCount();
        }
        return roomsCount;
    }

    /*
        Получение массива этажей офисного здания
    */
    public Floor[] getFloors(){
        OfficeFloor[] officeFloors = new OfficeFloor[size];
        for (int i = 0; i < size; i++) {
            officeFloors[i] = getNode(i + 1).getHead();
        }
        return officeFloors;
    }

    /*
        Получение этажа по номеру в здании
    */
    public Floor getFloor(int num) {
        if((num < 0) || (num >= size)) throw new FloorIndexOutOfBoundsException("FloorIndexOutOfBoundsException");
        return getNode(num+1).getHead();
    }

    /*
        Изменение этажа по номеру в здании и ссылке на обновленный этаж
    */
    public void setFloor(int num, Floor newFloor) {
        if((num < 0) || (num >= size)) throw new SpaceIndexOutOfBoundsException("FloorIndexOutOfBoundsException");
        getNode(num+1).setHead((OfficeFloor) newFloor);
    }

    /*
        Получение офиса по номеру в офисном здании
    */
    public Space getSpace(int num) {
        if((num < 0) || (num >= getSpacesCount())) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        int counter = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < getFloor(i).getSpacesCount(); j++) {
                if(counter == num) return (Office) getFloor(i).getSpace(j);
                counter++;
            }
        }
        return null;
    }

    /*
        Изменение объекта офиса по номеру в доме и ссылке на новый офис
    */
    public void setSpace(int num, Space newSpace) {
        if((num < 0) || (num >= getSpacesCount())) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        int counter = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < getFloor(i).getSpacesCount(); j++) {
                if(counter == num) getFloor(i).setSpace(j, newSpace);
                counter++;
            }
        }
    }

    /*
        Добавление офиса в здание по номеру офиса в здании и ссылке на офис
    */
    public void addSpace(int num, Space newSpace) {
        if((num < 0) || (num > getSpacesCount())) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        int counter = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < getFloor(i).getSpacesCount(); j++) {
                if(counter == num) getFloor(i).addSpace(j, newSpace);
                counter++;
            }
        }
    }

    /*
        Удаление офиса по номеру в здании
    */
    public void removeSpace(int num) {
        if((num < 0) || (num >= getSpacesCount())) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        int counter = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < getFloor(i).getSpacesCount(); j++) {
                if(counter == num) getFloor(i).removeSpace(j);
                counter++;
            }
        }
    }

    /*
        Получение самого большого по площади офиса здания
    */
    public Space getBestSpace() {
        Office bestSpace = getNode(0).getHead().getBestSpace();
        for (int i = 1; i < size; i++) {
            if(getNode(i+1).getHead().getBestSpace().getArea() > bestSpace.getArea()) bestSpace = getNode(i+1).getHead().getBestSpace();
        }
        return bestSpace;
    }
    /*
        Получение отсортированного по убыванию площадей массива офисов
    */
    public Space[] getDescAreaSortedSpaceMassive() {
        Office[] offices = new Office[getSpacesCount()];
        for (int i = 0; i < offices.length; i++) {
            offices[i] = (Office) getSpace(i);
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
