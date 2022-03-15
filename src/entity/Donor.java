/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.SinglyLinkedList;
import com.bethecoder.ascii_table.ASCIITable;
import com.github.javafaker.Faker;

/**
 *
 * @author Wong Phey Zhen
 */
public class Donor extends Account implements Comparable<Donor>, Cloneable {

    private String donorType;

    private static String lastDonorID = "";

    public Donor() {
    }

    public Donor(String accountID) {
        this.accountID = accountID;
    }

    public Donor(String accountID, String name, String donorType, char gender, String ic, String email, String phoneNo, String address, String status) {
        //String name, char gender, String ic, String email, String phoneNo, String address,
        super(accountID, name, gender, ic, email, phoneNo, address, status);
        this.donorType = donorType;
    }

    public String getDonorType() {
        return donorType;
    }

    public void setDonorType(String donorType) {
        this.donorType = donorType;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String getLastDonorID() {
        return lastDonorID;
    }

    public static void setLastDonorID(String lastDonorID) {
        Donor.lastDonorID = lastDonorID;
    }

    @Override
    public int compareTo(Donor o) {//ID
        if (this.accountID.compareTo(o.accountID) < 0) {
            return -1;
        } else if (this.accountID.compareTo(o.accountID) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Donor) {
            Donor other = (Donor) o;
            if (this.accountID.equalsIgnoreCase(other.getAccountID())) {
                return true;
            } else {
                return false;
            }
        }

        return false;

    }

    private static String[] donorHeaders() {
        String[] donorHeaders = {"Donor ID", "Donor Name", "Donor Type", "IC/ Company Register No", "Gender", "Email Address", "Phone Number", "Address", "Status"};

        return donorHeaders;
    }

    private String[] strArr() {
        String g = String.valueOf(gender);
        return new String[]{accountID, name, donorType, ic, g, email, phoneNo, address, status};
    }

    //change the list and apply toArray method.
    private static String[][] donorRows(SinglyLinkedList<Donor> donorList) {
        Donor[] donees = new Donor[donorList.getDataCount()];
        donees = donorList.toArray(donees);
        String[][] donorRows = new String[donorList.getDataCount()][];

        for (int i = 0; i < donees.length; i++) {
            donorRows[i] = donees[i].strArr();
        }
        return donorRows;
    }

    public static void donorTable(SinglyLinkedList<Donor> donorList) {
        String[] header = Donor.donorHeaders();
        String[][] donorData = Donor.donorRows(donorList);

        ASCIITable.getInstance().printTable(header, donorData);
    }

    @Override
    public String autoGenerateID() {
        String newDonorID = "";
        int seq = 0;
        if (lastDonorID.equals("")) {
            newDonorID = "DO1001";
        } else {
            seq = Integer.parseInt(lastDonorID.substring(2));
            seq += 1;

            newDonorID = "DO" + seq;
        }
        lastDonorID = newDonorID;

        return lastDonorID;
    }

    public SinglyLinkedList<Donor> generateDummyDonor() {
        SinglyLinkedList<Donor> dummyDonors = new SinglyLinkedList< Donor>();
        Faker faker = new Faker();
        Donor donor = new Donor();

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

        return dummyDonors;
    }

    @Override
    public Donor clone() throws CloneNotSupportedException {
        Donor cloned = (Donor) super.clone();
        return cloned;
    }

    public boolean isInActive() {
        return status.equalsIgnoreCase("Inactive");
    }

}
