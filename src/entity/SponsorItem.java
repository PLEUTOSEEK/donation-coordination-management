/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class SponsorItem implements Comparable<SponsorItem> {

    private String sponsoredID;
    private DemandList demandList;
    private Funds funds;
    private Sponsor sponsor;
    private Double quantity;
    private Timestamp dateDonate;
    private String description;
    private Timestamp dateModified;
    private static String lastSponsoredID = "";

    public SponsorItem() {

    }

    public SponsorItem(String sponsoredID, DemandList demandList, Funds funds, Sponsor sponsor, Double quantity, Timestamp dateDonate, String description, Timestamp dateModified) {
        this.sponsoredID = sponsoredID;
        this.demandList = demandList;
        this.funds = funds;
        this.sponsor = sponsor;
        this.quantity = quantity;
        this.dateDonate = dateDonate;
        this.description = description;
        this.dateModified = dateModified;
    }

    public static String getLastSponsoredID() {
        return lastSponsoredID;
    }

    public static void setLastSponsoredID(String lastSponsoredID) {
        SponsorItem.lastSponsoredID = lastSponsoredID;
    }

    public String getSponsoredID() {
        return sponsoredID;
    }

    public void setSponsoredID(String sponsoredID) {
        this.sponsoredID = sponsoredID;
    }

    public DemandList getDemandList() {
        return demandList;
    }

    public void setDemandList(DemandList demandList) {
        this.demandList = demandList;
    }

    public Funds getFunds() {
        return funds;
    }

    public void setFunds(Funds funds) {
        this.funds = funds;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Timestamp getDateDonate() {
        return dateDonate;
    }

    public void setDateDonate(Timestamp dateDonate) {
        this.dateDonate = dateDonate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public int compareTo(SponsorItem o) {
        if (this.sponsoredID.compareTo(o.sponsoredID) < 0) {
            return -1;
        } else if (this.sponsoredID.compareTo(o.sponsoredID) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof SponsorItem) {
            SponsorItem other = (SponsorItem) o;
            if (this.sponsoredID == other.getSponsoredID()) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public String autoGenerateID() {
        String newSponsoredID = "";
        int seq = 0;
        if (lastSponsoredID.equals("")) {
            newSponsoredID = "SI1001";
        } else {
            seq = Integer.parseInt(lastSponsoredID.substring(2));
            seq += 1;

            newSponsoredID = "SI" + seq;
        }

        lastSponsoredID = newSponsoredID;

        return lastSponsoredID;
    }

}
