/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 *
 * @author Tee Zhuo Xuan
 */
//U = node, T = list
public interface BinaryTreeInterface<U, T> {

    public boolean addData(U label, T data);

    public boolean updateData(U label, T data);

    public boolean delData(U label, T data);

    public boolean deleteNode(U label);

    public boolean clear();

    public T get(T data);

    public ListInterface<T> getMin();

    public ListInterface<T> getMax();

    public T[] getAllListInArray(T[] array);

    public boolean containsData(U label, T data);

    public boolean containsNode(U label);

    public boolean isEmpty();

}
