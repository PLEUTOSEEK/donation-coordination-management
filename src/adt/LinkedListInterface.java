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
public interface LinkedListInterface<T> {

    boolean addLastNode(T element);

    boolean addFirstNode(T element);

    boolean addAtAnyNode(T element, int position);

    int countNodes();

    boolean searchNode(T element);

    boolean isEmpty();

    Object getFirstNode();

    Object getLastNode();

    Object getAnyNode(int position);

    boolean removeFirstNode();

    boolean removeLastNode();

    boolean removeAnyNode(int position);

    boolean replaceFirstNode();

    boolean replaceLastNode();

    boolean replaceAnyNode(int position);
}
