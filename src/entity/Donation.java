/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.CircularLinkedList;
import adt.CircularLinkedQueue;
import adt.RedBlackTree;
import adt.SinglyLinkedList;
import com.bethecoder.ascii_table.ASCIITable;
import com.github.javafaker.Faker;
import io.github.benas.randombeans.randomizers.range.LocalDateTimeRangeRandomizer;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 *
 * @author Looi Jia Toong
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

    public Donation(String donationID) {
        this.donationID = donationID;
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
            seq = Integer.parseInt(lastDonationID.substring(3));
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

    private static String[][] donationRows(CircularLinkedList<Donation> donationDB) {
        Donation[] donations = new Donation[donationDB.countNodes()];
        donations = donationDB.toArray(donations);
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

    public CircularLinkedList<Donation> generateDummyDonation(SinglyLinkedList<Donor> donorDB, CircularLinkedQueue<Donee> doneeDB, RedBlackTree<LocalDate, Campaign> campaignDB) {
        CircularLinkedList<Donation> dummyDonations = new CircularLinkedList<Donation>();
        Faker faker = new Faker();
        Donation donation = new Donation();
        LocalDateTimeRangeRandomizer randomTimeRange;
        LocalDateTime randomTime;
        LocalDateTime minTime = LocalDateTime.of(2017, Month.JANUARY, 1, 00, 00, 00);
        LocalDateTime maxTime = LocalDateTime.of(2021, Month.DECEMBER, 31, 23, 59, 59);
        randomTimeRange = LocalDateTimeRangeRandomizer.aNewLocalDateTimeRangeRandomizer(minTime, maxTime);

        String[] description = "Heaven Kitties,Heaven Bunnies,Heaven Pigeons,Heaven Volunteers,Heaven Pinks,Heaven Pink Jackets,Heaven Pink Legs,Heaven United,Heaven Athletic,Pink Kitties,Pink Bunnies,Pink Pigeons,Pink Volunteers,Lively Kitties,Lively Bunnies,Lively Pigeons,Lively Volunteers,Donation Kitties,Donation Bunnies,Donation Pigeons,Donation Volunteers,Generous Kitties,Generous Bunnies,Generous Pigeons,Generous Volunteers".split(",");
        
        Donor[] donors = new Donor[donorDB.getDataCount()];
        for (int i = 0; i < donorDB.getDataCount(); i++) {
            donors[i] = donorDB.getAt(i);
        }

        Donee[] donees = new Donee[doneeDB.getLength()];
        for (int i = 0; i < doneeDB.getLength(); i++) {
            donees[i] = doneeDB.getAt(i);
        }

        Campaign[] campaigns = new Campaign[campaignDB.getAllList().getLength()];
        campaigns = campaignDB.getAllListInArray(campaigns);

        for (int data = 0; data < 30; data++) {
            LocalDate dateOfDonation = randomTimeRange.getRandomValue().toLocalDate();
            Timestamp dateModified = new Timestamp(dateOfDonation.plusDays(faker.number().numberBetween(0, 3)).toEpochDay());

            donation = new Donation();
            donation.setDonationID(autoGenerateID());
            donation.setDonor(donors[faker.number().numberBetween(0, donorDB.getDataCount())]);
            donation.setDonee(donees[faker.number().numberBetween(0, doneeDB.getLength())]);
            donation.setCampaign(campaigns[faker.number().numberBetween(0, campaignDB.getLength())]);
            donation.setTotalAmount(faker.number().randomDouble(2, 10, 10000));
            donation.setDescription(description[(int) faker.number().randomDigit()]);
            donation.setDateOfDonation(dateOfDonation);
            donation.setStatus("Active");
            donation.setDateModified(dateModified);
        }

        return dummyDonations;
    }

    @Override
    public Donation clone() throws CloneNotSupportedException {
        Donation cloned = (Donation) super.clone();
        return cloned;
    }
    
    
}
