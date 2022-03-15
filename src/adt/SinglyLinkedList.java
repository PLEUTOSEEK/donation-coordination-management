/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 *
 * @author Wong Phey Zhen
 */
public class SinglyLinkedList<T extends Comparable<T>> implements SinglyLinkedListInterface<T> {

    private Node firstNode;
    private Node tail;
    private int dataCount;

    public SinglyLinkedList() {
        this.firstNode = null;
        this.tail = null;
        this.dataCount = 0;
    }

    public Node getFirstNode() {
        return firstNode;
    }

    public Node getTail() {
        return tail;
    }

    public T getAt(int givenPos) {
        //givenPos is start from 1

        if (givenPos >= 1 && givenPos <= dataCount) {

            if (givenPos == 1) {

                return (T) this.firstNode.data;

            } else {

                Node current = this.firstNode;
                for (int i = 2; i <= givenPos; i++) {
                    current = current.getNext();
                }

                return (T) current.data;

            }

        }

        return null;
    }

    public int getDataCount() {
        return dataCount;
    }

    @Override
    public boolean add(T element) {
        Node newNode = new Node(element);

        if (empty()) {
            firstNode = newNode;
        } else {
            Node currNode = firstNode;
            while (currNode.next != null) {
                currNode = currNode.next;
            }
            currNode.next = newNode;
        }

        dataCount++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newElement) {
        boolean addNode = true;

        if ((newPosition >= 1) && (newPosition <= dataCount + 1)) {
            Node newNode = new Node(newElement);

            if (empty() || (newPosition == 1)) {
                newNode.next = firstNode;
                firstNode = newNode;
            } else {
                Node previousNode = firstNode;
                for (int i = 1; i < newPosition - 1; ++i) {
                    previousNode = previousNode.next;
                }

                newNode.next = previousNode.next;
                previousNode.next = newNode;
            }

            dataCount++;
        } else {
            addNode = false;
        }

        return addNode;
    }

    @Override
    public boolean remove(int inputPosition) {
        Node previous = firstNode;
        dataCount = 1;

        if ((inputPosition >= 1) && (inputPosition <= dataCount)) {

            if (inputPosition == 1) {
                firstNode = firstNode.next;
            } else {
                Node prev = firstNode;
                for (int i = 1; i < inputPosition - 1; ++i) {
                    prev = prev.next;
                }
                Node curr = prev.next;
                prev.next = curr.next;
                return true;
            }

            dataCount--;
        }

        return false;
    }

    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    public int indexOf(T element) {
        Node current = this.firstNode;
        int counter = 1;

        while (current != null) {

            if (element.equals(current.data)) {
                return counter;
            }

            current = current.getNext();
            counter += 1;
        }

        return -1;
    }

    @Override
    public final void clear(T element) {
        firstNode = null;
        dataCount = 0;
    }

    @Override
    public boolean empty() {
        boolean result;
        result = dataCount == 0;
        return result;
    }

    @Override
    public void print(T element) {
        Node curr = firstNode;

        while (curr != null) {
            System.out.print("[" + curr.getData() + "], ");
            curr = curr.getNext();
        }
        System.out.println("");
    }

    public void printSpec(T element) {
        Node curr = firstNode;

        while (curr != null) {
            if (element.equals(curr.data)) {
                System.out.print("[" + curr.getData() + "], ");
            }
        }
        System.out.println("");
    }

    public T[] toArray(T[] array) {
        Node curr = this.firstNode;

        if (curr != null) {

            int num = 0;

            while (curr != null) {
                try {
                    array[num] = ((T) curr.data);
                    curr = curr.getNext();
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

    @Override
    public boolean replace(T element, int inputPosition) {
        Node currentNode = getFirstNode();
        int counter = 1;
        if (this.firstNode == null) {
            return false;
        }

        if ((inputPosition >= 1) && (inputPosition <= dataCount)) {
            Node newNode = new Node(element);

            if (inputPosition == 1) {
                newNode.next = this.firstNode.getNext();
                this.firstNode = newNode;
                return true;
            } else {

                while (counter != inputPosition - 1) {
                    currentNode = currentNode.getNext();
                    counter++;
                }
                newNode.setNext(currentNode.getNext());
                currentNode.setNext(newNode);
                return true;
            }
        }
        return false;

    }

    private class Node<T extends Comparable<T>> {

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

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }

}
