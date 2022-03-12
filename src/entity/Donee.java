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
 * @author junyao
 */
public class Donee extends Account implements Comparable<Donee> {

    private String doneeName;

    public Donee() {
    }

    public Donee(String doneeID) {
        this.accountID = doneeID;
    }

    public Donee(String doneeID, String doneeName) {
        this.accountID = doneeID;
        this.doneeName = doneeName;
    }

    public String getDoneeName() {
        return doneeName;
    }

    public void setDoneeName(String doneeName) {
        this.doneeName = doneeName;
    }

    @Override
    public int compareTo(Donee o) {//ID
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

    private static String[] doneeHeaders() {
        String[] doneeRows = {"Donee ID", "Donee Name"};

        return doneeRows;
    }

    private String[] strArr() {
        return new String[]{accountID, doneeName};
    }

    //change the list and apply toArray method.
    private static String[][] doneeRows(DoublyLinkedList<Donee> doneeList) {
        Donee[] donees = new Donee[doneeList.getLength()];
        donees = doneeList.toArray(donees);
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

    @Override
    public String autoGenerateID() {
        return null;
    }

    public DoublyLinkedList<Donee> generateDummyDonee() {
        return null;
    }

}
