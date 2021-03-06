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
public interface SinglyLinkedListInterface<T> {

    public boolean add(T element);

    public boolean add(T newElement, int newPosition);

    public boolean remove(int inputPosition);

    public boolean contains(T element);

    public int indexOf(T element);

    public boolean replace(T element, int inputPosition);

    public void clear(T element);

    public boolean empty();

    public void print(T element);

    public T[] toArray(T[] array);

    public T getAt(int givenPos);

}
