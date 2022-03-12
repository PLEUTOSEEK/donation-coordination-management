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
public class Sponsor extends Account implements Comparable<Sponsor> {

    private String sponsorID;
    private String sponsorName;

    public Sponsor() {

    }

    public Sponsor(String sponsorID) {
        this.sponsorID = sponsorID;
    }

    public String getSponsorID() {
        return sponsorID;
    }

    public void setSponsorID(String sponsorID) {
        this.sponsorID = sponsorID;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    @Override
    public int compareTo(Sponsor o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static String[] sponsorHeaders() {
        String[] sponsorHeaders = {"Sponsor ID", "Sponsor Name"};

        return sponsorHeaders;
    }

    private String[] strArr() {
        return new String[]{sponsorID, sponsorName};
    }

    private static String[][] sponsorRows(DoublyLinkedList<Sponsor> sponsorList) {
        Sponsor[] sponsors = sponsorList.toArray();
        String[][] sponsorRows = new String[sponsorList.getLength()][];
        for (int i = 0; i < sponsors.length; i++) {
            sponsorRows[i] = sponsors[i].strArr();
        }
        return sponsorRows;
    }

    public static void sponsorTable(DoublyLinkedList<Sponsor> sponsorList) {
        String[] header = Sponsor.sponsorHeaders();
        String[][] sponsorData = Sponsor.sponsorRows(sponsorList);

        ASCIITable.getInstance().printTable(header, sponsorData);
    }

    @Override
    public String autoGenerateID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DoublyLinkedList<Sponsor> generateDummySponsor() {
        return null;
    }

}
