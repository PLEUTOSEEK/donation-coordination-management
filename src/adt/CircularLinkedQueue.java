/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author Wong Jun Yao
 */
public class CircularLinkedQueue<T extends Comparable<T>> implements QueueInterface<T> {

    private Node firstNode;
    private Node lastNode;
    private int length;

    public CircularLinkedQueue() {
        this.firstNode = null;
        this.lastNode = null;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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

    public boolean enqueue(T newEntry) {
        Node newNode = new Node((Comparable) newEntry, null);

        if (firstNode == null) {
            firstNode = newNode;
        } else if (checkExitsData(newEntry)) {
            return false;
        } else {
            lastNode.next = newNode;
        }

        lastNode = newNode;
        lastNode.next = firstNode;
        length++;
        return true;
    }

    public T dequeue() {
        T front = null;
        if (firstNode == null) {
            return null;
        } else {
            if (firstNode == lastNode) {
                front = (T) firstNode.data;
                firstNode = null;
                lastNode = null;
            } else {
                front = (T) firstNode.data;
                firstNode = firstNode.next;
                lastNode.next = firstNode;
            }
            length--;
            return front;
        }
    }

    public T getFront() {
        T front = null;
        if (firstNode == null) {
            return null;
        } else {
            front = (T) firstNode.data;
        }
        return front;
    }

    public T getEnd() {
        T end = null;
        if (lastNode == null) {
            return null;
        } else {
            end = (T) lastNode.data;
        }
        return end;
    }

    public boolean isEmpty() {
        boolean ans;
        if ((firstNode == null) && (lastNode == null)) {
            ans = true;
        } else {
            ans = false;
        }

        return ans;
    }

    public int size() {
        int size = 0;
        Node tempNode = firstNode;
        do {
            size++;
            tempNode = tempNode.next;
        } while (tempNode != firstNode);
        return size;
    }

    public void clear() {
        firstNode = null;
        lastNode = null;
        length = 0;
    }

    public boolean checkExitsData(T entry) {
        Node node = new Node((Comparable) entry);
        Node tempnode = firstNode;
        boolean exist = false;
        do {
            if (tempnode.data == node.data) {
                exist = true;
                break;
            }
            tempnode = tempnode.next;

        } while (tempnode != firstNode);
        return exist;
    }

    public void modify(T oldEntry, T newEntry) {
        int position = 0;
        boolean chk;
        Node node = firstNode;
        if (firstNode == null) {
            return;
        }
        chk = checkExitsData((T) node.data);
        if (chk == true) {
            while (node.next != firstNode) {
                if (node.data == oldEntry) {
                    node.data = (Comparable) newEntry;
                    return;
                }
                node = node.next;
                position++;
            }

            if (node.data == oldEntry) {
                node.data = (Comparable) newEntry;
                return;
            }
        }
        return;
    }

    public T[] toArray(T[] array) {
        Node current = this.firstNode;

        if (current != null) {

            int index = 0;
            while (current != null) {
                try {
                    array[index] = ((T) current.data);
                    current = current.getNext();
                    index++;
                } catch (Exception e) {
                    break;
                }
            }
            return array;
        } else {
            return null;
        }
    }

    public int indexOf(T element) {
        Node current = firstNode;
        int counter = 1;

        while (current != null) {

            if (element.equals(current.getData())) {
                return counter;
            }

            current = current.getNext();
            counter += 1;
        }

        return -1;
    }

    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    public T getAt(int givenPos) {
        //givenPos is start from 1
        if (givenPos >= 1 && givenPos <= length) {

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
}
