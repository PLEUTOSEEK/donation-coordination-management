/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.CircularLinkedList;
import com.bethecoder.ascii_table.ASCIITable;
import com.github.javafaker.Faker;
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
    private String status;
    private LocalDate dateOfDonation;
    private Timestamp dateModified;
    private static String lastDonationID;

    public Donation() {
    }

    public Donation(String donationID, Donor donor, Donee donee, double totalAmount, String description, String status, LocalDate dateOfDonation, Timestamp dateModified) {
        this.donationID = donationID;
        this.donor = donor;
        this.donee = donee;
        this.totalAmount = totalAmount;
        this.description = description;
        this.status = status;
        this.dateOfDonation = dateOfDonation;
        this.dateModified = dateModified;
    }

    public Donation(String donationID, Campaign campaign, Donor donor, double totalAmount, String description, String status, LocalDate dateOfDonation, Timestamp dateModified) {
        this.donationID = donationID;
        this.campaign = campaign;
        this.donor = donor;
        this.totalAmount = totalAmount;
        this.description = description;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public boolean equals(Object o) {
        if (o instanceof Donation) {
            Donation other = (Donation) o;
            if (this.donationID.equalsIgnoreCase(other.getDonationID())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Donation o) {
        if (this.donationID.compareTo(o.donationID) < 0) {
            return -1;
        } else if (this.donationID.compareTo(o.donationID) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public String autoGenerateID() {
        String newDonationID = "";
        int seq = 0;
        if (lastDonationID.equals("")) {
            newDonationID = "DON1001";
        } else {
            seq = Integer.parseInt(lastDonationID.substring(2));
            seq += 1;

            newDonationID = "DON" + seq;
        }
        lastDonationID = newDonationID;

        return lastDonationID;
    }

    private static String[] donationHeaders() {
        String[] campaignHeaders = {"Donation ID", "Donor ID", "Campaign ID", "Donee ID", "Total Amount", "Description", "Date of Donation", "Date Modified"};

        return campaignHeaders;
    }

    private String[] strArr() {
        return new String[]{donationID, donor.getAccountID(), campaign.getCampaignID(), donee.getAccountID(), String.valueOf(totalAmount), description, dateOfDonation.toString(), dateModified.toLocalDateTime().toString()};
    }

    /*private static String[][] donationRows(CircularLinkedList<Donation> donationDB) {
        Donation[] donations = new Donation[donationDB.getAllList().getLength()];
        donations = donationDB.getAllArrayList(donations);
        String[][] donationRows = new String[donations.length][];
        for (int i = 0; i < donations.length; i++) {
            donationRows[i] = donations[i].strArr();
        }
        return donationRows;
    }

    public static void donationTable(CircularLinkedList<Donation> donationDB) {
        String[] donationHeader = Donation.donationHeaders();
        String[][] donationData = Donation.donationRows(donationDB);

        ASCIITable.getInstance().printTable(donationHeader, donationData);
    }

    public CircularLinkedList<Donation> generateDummyDonation() {
        CircularLinkedList<Donation> dummyDonations = new CircularLinkedList<Donation>();
        Faker faker = new Faker();
        Donation donation = new Donation();

        String[] totalAmount = "Shiloh Almond,Haidar Potts,Anton Klein,Balraj Lang,Raya Penn,Cherry Swift,Raymond Worthington,Connah Parrish,Stacey Mathews,Corinne Monaghan,Reid Mccallum,Saqlain Stephens,Ray Whyte,Gertrude Mccabe,Jamaal Gould".split(",");
        String[] description = {"organization", "individual"};
        int[] dateOfDonation = {77, 70};

        for (int data = 0; data < 100; data++) {
            donation = new Donation();
            donation.setDonationID(autoGenerateID());
            ...
            donation.setStatus("Active");
            dummyDonors.add(donor);
        }

        return dummyDonations;
    }*/
}
