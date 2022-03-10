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

    // fing specific element within a list that within a specific node
    public boolean delData(U label, T data);

    public boolean clearAt(U label);

    //clear all node, use it only when neccessary!
    public boolean clear();

    public Object getData(U label, T data);

    public Object getMin();

    public Object getMax();

    public boolean containsData(U label, T data);

    public boolean containsNode(U label);

    public boolean isEmpty();

    public T[] getAllArrayList();
}
