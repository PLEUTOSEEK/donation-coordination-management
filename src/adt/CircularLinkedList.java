/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 *
 * @author JiaToong
 */
public class CircularLinkedList<T> implements CircularLinkedListInterface<T> {

    private Node firstNode, lastNode;
    private int length;

    public CircularLinkedList() {
        this.firstNode = null;
        this.lastNode = null;
    }

    @Override
    public boolean addLastNode(T element) {
        Node newNode = new Node(element);

        if (isEmpty() == true) {
            firstNode = newNode;
        } else {
            lastNode.next = newNode;
            lastNode = newNode;
            newNode.next = firstNode;
        }

        length++;
        return true;
    }

    @Override
    public boolean addFirstNode(T element) {
        Node newNode = new Node(element);

        if (isEmpty() == true) {
            firstNode = newNode;
        } else {
            newNode.next = firstNode;
            lastNode.next = newNode;
            firstNode = newNode;
        }

        length++;
        return true;
    }

    @Override
    public boolean addAtAnyNode(T element, int position) {
        Node newNode = new Node(element);
        if (position >= 1 && position <= length + 1) {

            if (position == 1) {
                return addFirstNode(element);
            } else if (position == this.length + 1) {
                return addLastNode(element);
            } else {

                Node currentNode = null;
                for (int i = 2; i <= position; i++) {
                    currentNode = currentNode.next;
                }

                newNode.next = currentNode.next;
                currentNode.next = newNode;
            }
            length++;
        }
        return true;
    }

    @Override
    public int countNodes() {
        return length;
    }

    @Override
    public boolean searchNode(T element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        if (firstNode == null && lastNode == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object getFirstNode() {
        return firstNode;
    }

    @Override
    public Object getLastNode() {
        return lastNode;
    }

    @Override
    public Object getAnyNode(int position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeFirstNode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeLastNode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAnyNode(int position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean replaceFirstNode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean replaceLastNode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean replaceAnyNode(int position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    private class Node {

        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}
