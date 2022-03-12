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
 * @author pz
 */
public class Donor extends Account implements Comparable<Donor> {

    private String donorName;

    public Donor() {
    }

    public Donor(String donorID) {
        this.accountID = donorID;
    }

    public Donor(String donorID, String donorName) {
        this.accountID = donorID;
        this.donorName = donorName;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    @Override
    public int compareTo(Donor o) {//ID
        if (this.accountID.compareTo(o.accountID) < 0) {
            return -1;
        } else if (this.accountID.compareTo(o.accountID) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Donor) {
            Donor other = (Donor) o;
            if (this.accountID == other.getAccountID()) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static String[] donorHeaders() {
        String[] donorHeaders = {"Donor ID", "Donor Name"};

        return donorHeaders;
    }

    private String[] strArr() {
        return new String[]{accountID, donorName};
    }

    //change the list and apply toArray method.
    private static String[][] donorRows(DoublyLinkedList<Donor> donorList) {
        Donor[] donors = new Donor[donorList.getLength()];
        donors = donorList.toArray(donors);
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

    @Override
    public String autoGenerateID() {
        return null;
    }

    public DoublyLinkedList<Donor> generateDummyDonor() {
        return null;
    }

}
