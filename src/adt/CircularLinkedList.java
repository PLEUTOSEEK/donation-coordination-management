/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 *
 * @author Looi Jia Toong
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
        if (position >= 1 && position <= length) {

            if (position == 1) {
                getFirstNode();
            } else if (position == this.length) {
                getLastNode();
            } else {
                Node currentNode = null;
                for (int i = 2; i <= position; i++) {
                    currentNode = currentNode.next;
                }

                return currentNode;
            }

        }
        return false;
    }

    @Override
    public boolean removeFirstNode() {
        if (isEmpty() == false) {
            lastNode.next = firstNode.next;
            firstNode = firstNode.next;
        }
        length--;
        return true;
    }

    @Override
    public boolean removeLastNode() {
        removeAnyNode(length - 1);
        return true;
    }

    @Override
    public boolean removeAnyNode(int position) {
        if (position >= 1 && position <= length) {

            if (position == 1) {
                return removeFirstNode();
            } else if (position == this.length) {
                return removeLastNode();
            } else {
                Node currentNode = null;
                for (int i = 2; i <= position; i++) {
                    currentNode = currentNode.next;
                }

                //currentNode.next = currentNode.next.next()
                //previousNode.next = currentNode

                return true;
            }

        }
        return true;
    }

    @Override
    public boolean replaceFirstNode(T element) {
        Node newNode = new Node(element);
        if (isEmpty() == true) {
            firstNode = newNode;
            lastNode.next = newNode;
        } else {
            newNode.next = firstNode.next;
            firstNode = newNode;
            lastNode.next = newNode;
        }

        return true;
    }

    @Override
    public boolean replaceLastNode(T element) {
        Node newNode = new Node(element);
        if (isEmpty() == true) {
            firstNode = newNode;
            newNode.next = lastNode;
            lastNode.next = newNode;
        } else {
            newNode.next = lastNode.next;
            lastNode = newNode;
        }

        return true;
    }

    @Override
    public boolean replaceAnyNode(T element, int position) {
        if (position >= 1 && position <= length) {

            if (position == 1) {
                return replaceFirstNode(element);
            } else if (position == this.length) {
                return replaceLastNode(element);
            } else {
                Node newNode = new Node(element);
                Node currentNode = null;
                for (int i = 2; i <= position; i++) {
                    currentNode = currentNode.next;
                }

                newNode.next = currentNode.next;
                currentNode = newNode;

                return true;
            }

        }
        return false;
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
