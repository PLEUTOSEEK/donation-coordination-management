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
public interface CircularLinkedListInterface<T> {

    boolean addLastNode(T element);
    boolean addFirstNode(T element);
    boolean addAtAnyNode(T element, int position);

    Object getFirstNode();
    Object getLastNode();
    Object getAnyNode(int position);

    boolean removeFirstNode();
    boolean removeLastNode();
    boolean removeAnyNode(int position);

    boolean replaceFirstNode(T element);
    boolean replaceLastNode(T element);
    boolean replaceAnyNode(T element, int position);

    int countNodes();
    boolean isEmpty();    
    T[] toArray(T[] array);
    boolean contains(T element);
    int indexOf(T element);
}