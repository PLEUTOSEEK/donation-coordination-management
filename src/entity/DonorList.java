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
public class DonorList implements Comparable<DonorList> {

    private String donorListID;
    private Donor donor;
    private Campaign campaign;
    private LocalDate dateJoin;
    private Timestamp dateModified;
    private String status;
    private static String lastDonorListID;

    public DonorList() {
    }

    public DonorList(String donorListID) {
        this.donorListID = donorListID;
    }

    public DonorList(String donorListID, Donor donor, Campaign campaign, LocalDate dateJoin, Timestamp dateModified, String status) {
        this.donorListID = donorListID;
        this.donor = donor;
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

    public String getDonorListID() {
        return donorListID;
    }

    public void setDonorListID(String donorListID) {
        this.donorListID = donorListID;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
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

    public static String getLastDonorListID() {
        return lastDonorListID;
    }

    @Override
    public int compareTo(DonorList o) {//ID
        if (this.donorListID.compareTo(o.getDonorListID()) < 0) {
            return -1;
        } else if (this.donorListID.compareTo(o.getDonorListID()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof DonorList) {
            DonorList other = (DonorList) o;
            if (this.donorListID == other.getDonorListID()) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static String[] donorListHeaders() {
        String[] campaignHeaders = {"Donor List ID", "Donor ID"};

        return campaignHeaders;
    }

    private String[] strArr() {
        return new String[]{donorListID, donor.getDonorID()};
    }

    private static String[][] donorListRows(RedBlackTree<LocalDate, DonorList> donorListDB) {
        DonorList[] donorList = donorListDB.getAllArrayList();
        String[][] donorListRows = new String[donorList.length][];
        for (int i = 0; i < donorList.length; i++) {
            donorListRows[i] = donorList[i].strArr();
        }
        return donorListRows;
    }

    public static void donorListTable(RedBlackTree<LocalDate, DonorList> donorDB) {
        String[] doneeListHeader = DonorList.donorListHeaders();
        String[][] doneeListData = DonorList.donorListRows(donorDB);

        ASCIITable.getInstance().printTable(doneeListHeader, doneeListData);
    }

    public String autoGenerateID() {
        String newCampaignID = "";
        int seq = 0;
        if (lastDonorListID.equals("")) {
            newCampaignID = "DOL1001";
        } else {
            seq = Integer.parseInt(lastDonorListID.substring(3));
            seq += 1;

            newCampaignID = "DOL" + seq;
        }

        lastDonorListID = newCampaignID;

        return lastDonorListID;
    }

    public RedBlackTree<LocalDate, DonorList> generateDummyDonorList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public RedBlackTree<LocalDate, DemandList> generateDummyDemandList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
