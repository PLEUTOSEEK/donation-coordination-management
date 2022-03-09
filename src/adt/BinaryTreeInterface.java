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

    public void add(U label, T data);

    // fing specific element within a list that within a specific node
    public void delData(U label, T data);

    // delete the specific node without care the list inside is empty or not
    // danger to use, use it only when neccessary, or else please use delData()
    public void deleteNode(U label);

    //clear all node, use it only when neccessary!
    public void clear();

    public Object getData(U label, T data);

    public Object getNode(U label);

    public void merge();

}
