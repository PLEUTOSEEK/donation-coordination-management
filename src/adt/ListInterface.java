/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 *
 * @author Angelina Oon
 */
public interface ListInterface<T> {

    public boolean isEmpty();

    public boolean addFirst(T element);

    public boolean addLast(T element);

    public boolean addUnique(T element);

    public boolean addAt(T element, int givenPos);

    public boolean delFirst();

    public boolean delLast();

    public boolean delAt(int givenPos);

    public Object getFirst();

    public Object getLast();

    public Object getAt(int givenPos);

    public boolean replaceFirst(T element);

    public boolean replaceLast(T element);

    public boolean replaceAt(T element, int givenPos);

    public boolean joinFirst(ListInterface otherList);

    public boolean joinLast(ListInterface otherList);

    public boolean joinAt(ListInterface otherList, int givenPos);

    public int indexOf(T element);

    public boolean contains(T element);

    public void printForward();

    public void printBackward();

    public void clear();

}
