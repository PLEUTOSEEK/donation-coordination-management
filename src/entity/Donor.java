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
public class Donor implements Comparable<Donor> {

    private String donorID;
    private String donorName;

    public Donor() {
    }

    public Donor(String donorID) {
        this.donorID = donorID;
    }

    public String getDonorID() {
        return donorID;
    }

    public void setDonorID(String donorID) {
        this.donorID = donorID;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    @Override
    public int compareTo(Donor o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static String[] donorHeaders() {
        String[] donorHeaders = {"Donor ID", "Donor Name"};

        return donorHeaders;
    }

    private String[] strArr() {
        return new String[]{donorID, donorName};
    }

    //change the list and apply toArray method.
    private static String[][] donorRows(DoublyLinkedList<Donor> donorList) {
        Donor[] donors = donorList.toArray();
        String[][] donorRows = new String[donorList.getLength()][];
        for (int i = 0; i < donors.length; i++) {
            donorRows[i] = donors[i].strArr();
        }
        return donorRows;
    }

    public static void donorTable(DoublyLinkedList<Donor> donorList) {
        String[] header = Donor.donorHeaders();
        String[][] donorData = Donor.donorRows(donorList);

        ASCIITable.getInstance().printTable(header, donorData);
    }

}
