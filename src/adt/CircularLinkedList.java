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

        if (firstNode == null) {
            firstNode = newNode;
            lastNode = newNode;
            lastNode.next = firstNode;
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

        if (firstNode == null) {
            firstNode = newNode;
            lastNode = newNode;
            lastNode.next = firstNode;
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

                Node currentNode = firstNode;
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
        Node currentNode = firstNode;
        int length = 0;
        if (firstNode != null) {
            do {
                currentNode = currentNode.next;
                length++;
            } while (currentNode != firstNode);
        }
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
        if (firstNode == null) {
            return null;
        }
        return firstNode.data;
    }

    @Override
    public Object getLastNode() {
        if (isEmpty()) {
            return null;
        }
        return lastNode.data;
    }

    @Override
    public Object getAnyNode(int position) {
        if (position >= 1 && position <= length) {

            if (position == 1) {
                getFirstNode();
            } else if (position == this.length) {
                getLastNode();
            } else {
                Node currentNode = firstNode;
                for (int i = 2; i <= position; i++) {
                    currentNode = currentNode.next;
                }

                return currentNode.data;
            }
        }
        return null;
    }

    @Override
    public boolean removeFirstNode() {
        if (!isEmpty()) {
            if (firstNode != lastNode) {
                firstNode = firstNode.next;
                lastNode.next = firstNode;
            } else {
                firstNode = null;
                lastNode = null;
            }
        }
        length--;
        return true;
    }

    @Override
    public boolean removeLastNode() {
        if (!isEmpty()) {
            Node currentNode = firstNode;
            while (currentNode.next != lastNode) {
                currentNode = currentNode.next;
            }
            lastNode = currentNode;
            lastNode.next = firstNode;
        }
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
                Node currentNode = firstNode;
                for (int i = 2; i <= position; i++) {
                    currentNode = currentNode.next;
                }
                Node dltNode = currentNode.next;
                currentNode.next = dltNode.next;
                return true;
            }

        }
        return true;
    }

    @Override
    public boolean replaceFirstNode(T element) {
        Node newNode = new Node(element);

        if (!isEmpty()) {
            newNode.next = firstNode.next;
            firstNode = newNode;
            lastNode.next = newNode;
        }

        return true;
    }

    @Override
    public boolean replaceLastNode(T element) {
        Node newNode = new Node(element);
        if (!isEmpty()) {
            newNode.next = firstNode;
            lastNode = newNode;
            return true;
        }
        return false;
    }

    @Override
    public boolean replaceAnyNode(T element, int position
    ) {
        if (position >= 1 && position <= length) {

            if (position == 1) {
                return replaceFirstNode(element);
            } else if (position == this.length) {
                return replaceLastNode(element);
            } else {
                Node newNode = new Node(element);
                Node currentNode = firstNode;
                for (int i = 2; i <= position; i++) {
                    currentNode = currentNode.next;
                }

                newNode.next = currentNode.next;
                newNode = currentNode;

                return true;
            }

        }
        return false;
    }

    public T[] toArray(T[] array) {
        Node currentNode = firstNode;

        if (currentNode != null) {

            int num = 0;

            while (currentNode != null) {
                try {
                    array[num] = ((T) currentNode.data);
                    currentNode = currentNode.next;
                    num++;
                } catch (Exception e) {
                    break;
                }
            }
            return array;
        } else {
            return null;
        }
    }

    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    public int indexOf(T element) {
        Node currentNode = firstNode;
        int counter = 1;

        while (currentNode != null) {

            if (currentNode.equals(element)) {
                return counter;
            }

            currentNode = currentNode.next;
            counter += 1;
        }

        return -1;
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
