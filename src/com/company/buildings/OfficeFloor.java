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

    public OfficeFloor(Office[] offices) {
        headNode = new Node(offices[0]);
        for (int i = 1; i < offices.length; i++) addNode(new Node(offices[i]));
    }

    /*
        Получение количества офисов на этаже
    */
    public int getOfficesCount() { return size; }

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
    public Office[] getOffices() {
        Office[] offices = new Office[size];
        for (int i = 0; i < size; i++) {
            offices[i] = getNode(i+1).getHead();
        }
        return offices;
    }

    /*
        Получение офиса по номеру на этаже
    */
    public Office getOffice(int num) {
        if(num < size) return getNode(num+1).getHead();
        else return null;
    }

    /*
        Изменение офиса по номеру на этаже и ссылке на обновленный офис
    */
    public void setOffice(int num, Office newOffice) {
        getNode(num+1).setHead(newOffice);
    }

    /*
        Добавление нового офиса на этаже по будущему номеру офиса
    */
    public void insertOffice(int num, Office office) {
        insertNode(num+1, new Node(office));
    }

    /*
        Удаление офиса по номеру на этаже
    */
    public void removeOffice(int num) {
        removeNode(num+1);
    }

    /*
        Получение самого большого по площади офиса этажа
    */
    public Office getBestSpace() {
        Office bestSpace = getNode(1).getHead();
        for (int i = 2; i < size; i++) {
            if(getNode(i).getHead().getArea() > bestSpace.getArea()) bestSpace = getNode(i).getHead();
        }
        return bestSpace;
    }

}
