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
public class SponsorList implements Comparable<SponsorList> {

    private String sponsorListID;
    private Sponsor sponsor;
    private Campaign campaign;
    private LocalDate dateJoin;
    private Timestamp dateModified;
    private static String lastSponsorListID;

    public SponsorList() {
    }

    public SponsorList(String sponsorListID) {
        this.sponsorListID = sponsorListID;
    }

    public SponsorList(String sponsorListID, Sponsor sponsor, Campaign campaign, LocalDate dateJoin, Timestamp dateModified) {
        this.sponsorListID = sponsorListID;
        this.sponsor = sponsor;
        this.campaign = campaign;
        this.dateJoin = dateJoin;
        this.dateModified = dateModified;
    }

    public String getSponsorListID() {
        return sponsorListID;
    }

    public void setSponsorListID(String sponsorListID) {
        this.sponsorListID = sponsorListID;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
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

    public static String getLastSponsorListID() {
        return lastSponsorListID;
    }

    public static void setLastSponsorListID(String lastSponsorListID) {
        SponsorList.lastSponsorListID = lastSponsorListID;
    }

    @Override
    public int compareTo(SponsorList o) {//ID
        if (this.sponsorListID.compareTo(o.getSponsorListID()) < 0) {
            return -1;
        } else if (this.sponsorListID.compareTo(o.getSponsorListID()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof SponsorList) {
            SponsorList other = (SponsorList) o;
            if (this.sponsorListID == other.getSponsorListID()) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static String[] sponsorListHeaders() {
        String[] campaignHeaders = {"Sponsor List ID", "Sponsor ID"};

        return campaignHeaders;
    }

    private String[] strArr() {
        return new String[]{sponsorListID, sponsor.getSponsorID()};
    }

    private static String[][] sponsorListRows(RedBlackTree<LocalDate, SponsorList> sponsorListDB) {
        SponsorList[] sponsorList = sponsorListDB.getAllArrayList();
        String[][] sponsorListRows = new String[sponsorList.length][];
        for (int i = 0; i < sponsorList.length; i++) {
            sponsorListRows[i] = sponsorList[i].strArr();
        }
        return sponsorListRows;
    }

    public static void sponsorListTable(RedBlackTree<LocalDate, SponsorList> donorDB) {
        String[] sponsorListHeader = SponsorList.sponsorListHeaders();
        String[][] sponsorListData = SponsorList.sponsorListRows(donorDB);

        ASCIITable.getInstance().printTable(sponsorListHeader, sponsorListData);
    }

    public String autoGenerateID() {
        String newCampaignID = "";
        int seq = 0;
        if (lastSponsorListID.equals("")) {
            newCampaignID = "SL1001";
        } else {
            seq = Integer.parseInt(lastSponsorListID.substring(2));
            seq += 1;

            newCampaignID = "SL" + seq;
        }

        lastSponsorListID = newCampaignID;

        return lastSponsorListID;
    }

}
