/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 * @author Wong Jun Yao
 */
import java.util.Iterator;

public interface QueueInterface<T> {

    public boolean enqueue(T newEntry);

    public T dequeue();

    public T getFront();

    public boolean isEmpty();

    public int size();

    public void clear();

    public boolean checkExitsData(T entry);

    public void modify(T oldEntry, T newEntry);

    public T[] toArray(T[] array);

    public int indexOf(T element);

    public boolean contains(T element);
}
