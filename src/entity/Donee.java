/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.DoublyLinkedList;
import com.bethecoder.ascii_table.ASCIITable;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class Donee implements Comparable<Donee> {

    private String doneeID;
    private String doneeName;

    public Donee() {
    }

    public Donee(String doneeID) {
        this.doneeID = doneeID;
    }

    public String getDoneeID() {
        return doneeID;
    }

    public void setDoneeID(String doneeID) {
        this.doneeID = doneeID;
    }

    public String getDoneeName() {
        return doneeName;
    }

    public void setDoneeName(String doneeName) {
        this.doneeName = doneeName;
    }

    @Override
    public int compareTo(Donee o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static String[] doneeHeaders() {
        String[] doneeRows = {"Donee ID", "Donee Name"};

        return doneeRows;
    }

    private String[] strArr() {
        return new String[]{doneeID, doneeName};
    }

    //change the list and apply toArray method.
    private static String[][] doneeRows(DoublyLinkedList<Donee> doneeList) {
        Donee[] donees = doneeList.toArray();
        String[][] doneeRows = new String[doneeList.getLength()][];
        for (int i = 0; i < donees.length; i++) {
            doneeRows[i] = donees[i].strArr();
        }
        return doneeRows;
    }

    public static void doneeTable(DoublyLinkedList<Donee> doneeList) {
        String[] header = Donee.doneeHeaders();
        String[][] doneeData = Donee.doneeRows(doneeList);

        ASCIITable.getInstance().printTable(header, doneeData);
    }

}
