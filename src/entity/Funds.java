/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author Angelina Oon
 */
public class Funds implements Comparable<Funds> {

    private String fundsID;
    private SponsorItem sponsorItem;
    private Sponsor sponsor;
    private Double totalAmount;
    private Double originalTotalAmount;
    private Timestamp datePay;
    private Timestamp dateModified;
    private static String lastFundsID = "";

    public Funds() {
    }

    public Funds(String fundsID, SponsorItem sponsorItem, Sponsor sponsor, Double totalAmount, Double originalTotalAmount, Timestamp datePay, Timestamp dateModified) {
        this.fundsID = fundsID;
        this.sponsorItem = sponsorItem;
        this.sponsor = sponsor;
        this.totalAmount = totalAmount;
        this.originalTotalAmount = originalTotalAmount;
        this.datePay = datePay;
        this.dateModified = dateModified;
    }

    public String getFundsID() {
        return fundsID;
    }

    public void setFundsID(String fundsID) {
        this.fundsID = fundsID;
    }

    public SponsorItem getSponsorItem() {
        return sponsorItem;
    }

    public void setSponsorItem(SponsorItem sponsorItem) {
        this.sponsorItem = sponsorItem;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getOriginalTotalAmount() {
        return originalTotalAmount;
    }

    public void setOriginalTotalAmount(Double originalTotalAmount) {
        this.originalTotalAmount = originalTotalAmount;
    }

    public Timestamp getDatePay() {
        return datePay;
    }

    public void setDatePay(Timestamp datePay) {
        this.datePay = datePay;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public int compareTo(Funds o) {//ID
        if (this.fundsID.compareTo(o.fundsID) < 0) {
            return -1;
        } else if (this.fundsID.compareTo(o.fundsID) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Funds) {
            Funds other = (Funds) o;
            if (this.fundsID == other.getFundsID()) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public String autoGenerateID() {
        String newFundsID = "";
        int seq = 0;
        if (lastFundsID.equals("")) {
            newFundsID = "F1001";
        } else {
            seq = Integer.parseInt(lastFundsID.substring(1));
            seq += 1;

            newFundsID = "F" + seq;
        }

        lastFundsID = newFundsID;

        return lastFundsID;
    }

}
