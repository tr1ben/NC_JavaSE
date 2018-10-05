package com.company.buildings;

public class OfficeFloor {

    public class Node {
        private Office head;
        private Node next;

        public Node(Office head) {
            this.head = head;
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

    private Node head;

    private Node getNode(int num) {
        Node node = head;
        for (int i = 1; i < num; i++) {
            node = node.getNext();
        }
        return node;
    }

    private void addNode(int num, Node newNode) {
        Node node = head;
        for (int i = 1; i < num-1; i++) {
            node = node.getNext();
            newNode.setNext(node.getNext());
            node.setNext(newNode);
        }
    }

    private void removeNode(int num) {
        Node node = head;
        for (int i = 1; i < num-1; i++) {
            node = node.getNext();
            node.setNext(node.getNext().getNext());
        }
    }

    public OfficeFloor(int officesCount) {
        head = new Node(new Office());
        Node node = head;
        for (int i = 1; i < officesCount; i++) {
            node.setNext(new Node(new Office()));
            node = node.getNext();
        }
        node.setNext(head);
    }

    public OfficeFloor(Office[] offices) {
        head = new Node(offices[0]);
        Node node = head;
        for (int i = 1; i < offices.length; i++) {
            node.setNext(new Node(offices[i]));
            node = node.getNext();
        }
        node.setNext(head);
    }

    public int getOfficesCount() {
        Node node = head;
        int officesCount = 1;
        while(node.hasNext() && node.getNext() != head) {
            node = node.getNext();
            officesCount++;
        }
        return officesCount;
    }

}
