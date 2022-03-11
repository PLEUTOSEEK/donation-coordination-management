/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.RedBlackTree;
import com.bethecoder.ascii_table.ASCIITable;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class DoneeList implements Comparable<DoneeList> {

    private String doneeListID;
    private Donee donee;
    private Campaign campaign;
    private LocalDate dateJoin;
    private Timestamp dateModified;
    private String status;
    private static String lastDoneeListID;

    public DoneeList() {
    }

    public DoneeList(String doneeListID) {
        this.doneeListID = doneeListID;
    }

    public DoneeList(String doneeListID, Donee donee, Campaign campaign, LocalDate dateJoin, Timestamp dateModified, String status) {
        this.doneeListID = doneeListID;
        this.donee = donee;
        this.campaign = campaign;
        this.dateJoin = dateJoin;
        this.dateModified = dateModified;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoneeListID() {
        return doneeListID;
    }

    public void setDoneeListID(String doneeListID) {
        this.doneeListID = doneeListID;
    }

    public Donee getDonee() {
        return donee;
    }

    public void setDonee(Donee donee) {
        this.donee = donee;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public LocalDate getDateJoin() {
        return dateJoin;
    }

    public void setDateJoin(LocalDate dateJoin) {
        this.dateJoin = dateJoin;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    public static String getLastDoneeListID() {
        return lastDoneeListID;
    }

    public static void setLastDoneeListID(String lastDoneeListID) {
        DoneeList.lastDoneeListID = lastDoneeListID;
    }

    @Override
    public int compareTo(DoneeList o) {//ID
        if (this.doneeListID.compareTo(o.getDoneeListID()) < 0) {
            return -1;
        } else if (this.doneeListID.compareTo(o.getDoneeListID()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof DoneeList) {
            DoneeList other = (DoneeList) o;
            if (this.doneeListID == other.getDoneeListID()) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static String[] doneeListHeaders() {
        String[] campaignHeaders = {"Donee List ID", "Donee ID"};

        return campaignHeaders;
    }

    private String[] strArr() {
        return new String[]{doneeListID, donee.getDoneeID()};
    }

    private static String[][] doneeListRows(RedBlackTree<LocalDate, DoneeList> doneeListDB) {
        DoneeList[] doneeLists = doneeListDB.getAllArrayList();
        String[][] doneeListRows = new String[doneeLists.length][];
        for (int i = 0; i < doneeLists.length; i++) {
            doneeListRows[i] = doneeLists[i].strArr();
        }
        return doneeListRows;
    }

    public static void doneeListTable(RedBlackTree<LocalDate, DoneeList> doneeList) {
        String[] doneeListHeader = DoneeList.doneeListHeaders();
        String[][] doneeListData = DoneeList.doneeListRows(doneeList);

        ASCIITable.getInstance().printTable(doneeListHeader, doneeListData);
    }

    public String autoGenerateID() {
        String newCampaignID = "";
        int seq = 0;
        if (lastDoneeListID.equals("")) {
            newCampaignID = "DL1001";
        } else {
            seq = Integer.parseInt(lastDoneeListID.substring(2));
            seq += 1;

            newCampaignID = "DL" + seq;
        }

        lastDoneeListID = newCampaignID;

        return lastDoneeListID;
    }
}
