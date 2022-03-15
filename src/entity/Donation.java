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
    private LocalDate dateOfDonation;
    private Timestamp dateModified;
    private static String lastDonationID;

    public Donation() {
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

        String[] donorName = "Shiloh Almond,Haidar Potts,Anton Klein,Balraj Lang,Raya Penn,Cherry Swift,Raymond Worthington,Connah Parrish,Stacey Mathews,Corinne Monaghan,Reid Mccallum,Saqlain Stephens,Ray Whyte,Gertrude Mccabe,Jamaal Gould".split(",");
        String[] donorType = {"organization", "individual"};
        int[] gender = {77, 70};
        String[] ic = "980528-03-0521,1681005-04-1235,780225-06-1236,980623-05-5142,850412-02-5213,681228-05-5698,980523-25-6589,560925-05-5894,020615-02-5689,520628-06-8947,780928-04-5894,560924-05-8562,650498-05-5868,680825-06-5864,520225-07-2568,980506-02-4569".split(",");
        String[] registerNo = "05015-K,125432-N,457632-K,457632-K,457632-K,,125645-K,586425-N".split(",");
        String[] phoneNo = "012-8956682,03-20726766,011-12589562,012-5894562,012-9856483,03-33744119,016-5894562,019-5895632,03-91712260,011-12598632,013-5895235,03-78775873,012-8759862,013-5895235,03-79578362,013-2598625,011-12895625,012-0589532,03-21433192,018-5698215,017-8658612,03-69835698,03-56925813".split(",");
        String[] address = "A 532 Jln Cheras Batu 3 1/4,Bandar Mahkota Cheras,1 11 Lrg Tiara 1B Bandar Baru,2A-2-25 Lengkok Nipah,A 51 Lrg Meranti,37 Jln Manis 4 Taman Segar,12A Persiaran Wira Jaya Barat 44 Taman Jaya 1,B 649/1 Jln Batu 4 Wilayah Persekutuan, A 43 Jln Ss2/75 Ss2 Petaling Jaya,3402 Jln Ampang Hilir 2, Room 502 5Th Floor Wisma Daimam 64 Jalan Sulam Taman Sentosa Malays,238 Kawasan Perindustrian Ringan,4802 Jalan Bagan Luar,A 532 Jln Cheras Batu 3 1/4,Bandar Mahkota Cheras,1 11 Lrg Tiara 1B Bandar Baru,2A-2-25 Lengkok Nipah,A 51 Lrg Meranti,37 Jln Manis 4 Taman Segar,12A Persiaran Wira Jaya,Barat 44 Taman Jaya 1,B 649/1 Jln Batu 4 Wilayah Persekutuan, A 43 Jln Ss2/75 Ss2 Petaling Jaya,3402 Jln Ampang Hilir 2, Room 502 5Th Floor Wisma Daimam 64 Jalan Sulam Taman Sentosa Malaysia,238 Kawasan Perindustrian Ringan,4802 Jalan Bagan Luar,No. 94 A Lrg Samarinda 8 Kandis Permai,B 1 Jln Ss21/1A Ss21 Petaling Jaya,G9 Gerai Makan Pasar Jawa Jln Pasar Kaw 18,402 Jln Lama Batu 6 Wilayah Persekutuan,3 Wisma Berjaya Prudential Jln Abell Kuching,3 Central Park Commercial Centre 316 Jln Tun Ahmad Zaidi Adruce Central Park,30 Jln Mutiara Raya Taman Mutiara,No. 555 A Jln E3/5 Taman Ehsan Kepong,71 Jalan Setia Jaya Bukit Kapar Kapar,3 Jln Klln 3 Taman Koperatif Lln".split(",");

        for (int data = 0; data < 100; data++) {
            donor = new Donor();
            donor.setAccountID(autoGenerateID());
            donor.setName(faker.name().fullName());
            donor.setDonorType(donorType[faker.random().nextInt(0, donorType.length - 1)]);
            if (donorType.equals("organization")) {
                donor.setGender(' ');
                donor.setIc(registerNo[faker.random().nextInt(0, ic.length - 1)]);
            } else {
                donor.setGender((char) gender[faker.random().nextInt(0, 1)]);
                donor.setIc(ic[faker.random().nextInt(0, ic.length - 1)]);
            }
            donor.setEmail(faker.internet().emailAddress());
            donor.setPhoneNo(phoneNo[faker.random().nextInt(0, phoneNo.length - 1)]);
            donor.setAddress(address[faker.random().nextInt(0, address.length - 1)]);
            donor.setStatus("Active");
            dummyDonors.add(donor);
        }

        return dummyDonations;
    }*/
}
