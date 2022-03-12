/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.DoublyLinkedList;
import com.bethecoder.ascii_table.ASCIITable;

public class Sponsor extends Account implements Comparable<Sponsor> {

    private String companyName;
    private static String lastSponsorID = "";

    public Sponsor() {
        this("", ' ', "", "", "", "", "");
    }

    public Sponsor(String name, char gender, String ic, String email, String phoneNo, String address, String companyName) {

        super(name, gender, ic, email, phoneNo, address);
        this.companyName = companyName;

    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + companyName;
    }

    @Override
    public int compareTo(Sponsor o) {

        if (accountID.compareTo(o.accountID) < 0) {
            return -1;
        } else if (this.accountID.compareTo(o.accountID) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    private static String[] sponsorHeaders() {
        String[] sponsorHeaders = {"Sponsor ID", "Sponsor Name", "IC", "Phone No", "Company Name", "Company Email", "Company Address"};

        return sponsorHeaders;
    }

    private String[] strArr() {
        return new String[]{accountID, name, ic, email, phoneNo, address, companyName};
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
        String newSponsorID = "";
        int seq = 0;
        if (lastSponsorID.equals("")) {
            newSponsorID = "S1001";
        } else {
            seq = Integer.parseInt(lastSponsorID.substring(1));
            seq += 1;

            newSponsorID = "S" + seq;
        }

        lastSponsorID = newSponsorID;

        return lastSponsorID;
    }

}
