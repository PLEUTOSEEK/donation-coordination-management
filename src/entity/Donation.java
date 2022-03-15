/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 *
 * @author JiaToong
 */
public class Donation implements Comparable<Donation> {

    private String donationID;
    private Campaign campaign;
    private Donor donor;
    private Donee donee;
    private double totalAmount;
    private String description;
    private LocalDate dateOfDonation;
    private Timestamp dateModified;
    private static String lastDonationID;
    
    public Donation(){
    }

    public Donation(String donationID, Donor donor, Donee donee, double totalAmount, String description, LocalDate dateOfDonation, Timestamp dateModified) {
        this.donationID = donationID;
        this.donor = donor;
        this.donee = donee;
        this.totalAmount = totalAmount;
        this.description = description;
        this.dateOfDonation = dateOfDonation;
        this.dateModified = dateModified;
    }

    public Donation(String donationID, Campaign campaign, Donor donor, double totalAmount, String description, LocalDate dateOfDonation, Timestamp dateModified) {
        this.donationID = donationID;
        this.campaign = campaign;
        this.donor = donor;
        this.totalAmount = totalAmount;
        this.description = description;
        this.dateOfDonation = dateOfDonation;
        this.dateModified = dateModified;
    }

    public String getDonationID() {
        return donationID;
    }

    public void setDonationID(String donationID) {
        this.donationID = donationID;
    }

    public static String getLastDonationID() {
        return lastDonationID;
    }

    public static void setLastDonationID(String lastDonationID) {
        Donation.lastDonationID = lastDonationID;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Donee getDonee() {
        return donee;
    }

    public void setDonee(Donee donee) {
        this.donee = donee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateOfDonation() {
        return dateOfDonation;
    }

    public void setDateOfDonation(LocalDate dateOfDonation) {
        this.dateOfDonation = dateOfDonation;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

  
    
    
    @Override
    public int compareTo(Donation o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
