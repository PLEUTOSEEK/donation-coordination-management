/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author wjuny
 */
public class CircularLinkedQueue<T> implements QueueInterface<T> {
    private Node firstNode;
    private Node lastNode;

    public CircularLinkedQueue() {
        this.firstNode = null;
        this.lastNode = null;
    }

    public boolean enqueue(T newEntry) {
        Node newNode = new Node(newEntry, null);

        if (firstNode == null) {
            firstNode = newNode;
        } else if (checkExitsData(newEntry)) {
            return false;
        } else {
            lastNode.next = newNode;
        }

        lastNode = newNode;
        lastNode.next = firstNode;
        return true;
    }

    public T dequeue() {
        T front = null;
        if (firstNode == null) {
            return null;
        } else {
            if (firstNode == lastNode) {
                front = firstNode.data;
                firstNode = null;
                lastNode = null;
            } else {
                front = firstNode.data;
                firstNode = firstNode.next;
                lastNode.next = firstNode;
            }
            return front;
        }
    }

    public T getFront() {
        T front = null;
        if (firstNode == null) {
            return null;
        } else {
            front = firstNode.data;
        }
        return front;
    }

    public T getLast() {
        T last;

        if (firstNode.data == lastNode.data) {
            last = firstNode.data;
        } else {
            last = lastNode.data;
        }

        return last;
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
    }

    public boolean checkExitsData(T entry) {
        Node node = new Node(entry);
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
        chk = checkExitsData(node.data);
        if (chk == true) {
            while (node.next != firstNode) {
                if (node.data == oldEntry) {
                    node.data = newEntry;
                    return;
                }
                node = node.next;
                position++;
            }

            if (node.data == oldEntry) {
                node.data = newEntry;
                return;
            }
        }
        return;
    }

    public Iterator<T> getIterator() {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<T> {

        private Node currentNode;
        private boolean atStart;

        public LinkedQueueIterator() {
            if (!isEmpty()) {
                currentNode = firstNode;
                atStart = true;
            }
        }

        @Override
        public boolean hasNext() {
            if (isEmpty() || currentNode == firstNode && !atStart) {
                return false;
            }
            return true;
        }

        @Override
        public T next() {
            T data = currentNode.data;
            atStart = false;
            currentNode = currentNode.next;
            return data;
        }
    }

    public T[] get(CircularLinkedQueue q) {
        Node temp = q.firstNode;
        
        T[] array = (T[]) new Object[q.size()];
        
        for (int i = 0; i < q.size()-1; i++){
            array[i] = temp.next.data;
        }
        
        return array;
    }

    private class Node {

        private T data;
        private Node next;
        Node link;

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
