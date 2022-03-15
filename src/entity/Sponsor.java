/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.DoublyLinkedList;
import com.bethecoder.ascii_table.ASCIITable;
import com.github.javafaker.Faker;
import java.util.HashSet;
import java.util.Set;

public class Sponsor extends Account implements Comparable<Sponsor>, Cloneable {

    private String companyName;
    private static String lastSponsorID = "";

    public Sponsor() {
        this("", "", ' ', "", "", "", "", "", "");
    }

    public Sponsor(String accountID) {
        this.accountID = accountID;
    }

    public Sponsor(String accountID, String name, char gender, String ic, String email, String phoneNo, String address, String companyName, String status) {

        super(accountID, name, gender, ic, email, phoneNo, address, status);
        this.companyName = companyName;

    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public static String getLastSponsorID() {
        return lastSponsorID;
    }

    public static void setLastSponsorID(String lastSponsorID) {
        Sponsor.lastSponsorID = lastSponsorID;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + companyName;
    }

    @Override
    public int compareTo(Sponsor o) {//ID
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

        if (o instanceof Sponsor) {
            Sponsor other = (Sponsor) o;
            if (this.accountID.equalsIgnoreCase(other.getAccountID())) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static String[] sponsorHeaders() {
        String[] sponsorHeaders = {"Sponsor ID", "Sponsor Name", "IC", "Gender", "Phone No", "Company Name", "Company Email", "Company Address", "Status"};

        return sponsorHeaders;
    }

    private String[] strArr() {
        String gend = String.valueOf(gender);
        return new String[]{accountID, name, ic, gend, phoneNo, companyName, email, address, status};
    }

    private static String[][] sponsorRows(DoublyLinkedList<Sponsor> sponsorList) {
        Sponsor[] sponsors = new Sponsor[sponsorList.getLength()];
        sponsors = sponsorList.toArray(sponsors);
        String[][] sponsorRows = new String[sponsorList.getLength()][];
        for (int i = 0; i < sponsors.length; i++) {
            sponsorRows[i] = sponsors[i].strArr();
        }
        return sponsorRows;
    }

    public static void sponsorTable(DoublyLinkedList<Sponsor> sponsorList) {
        String[] header = Sponsor.sponsorHeaders();
        String[][] sponsorData = Sponsor.sponsorRows(sponsorList);

        ASCIITable.getInstance().printTable(header, sponsorData);
    }

    @Override
    public String autoGenerateID() {

        String newSponsorID = "";
        int seq = 0;
        if (lastSponsorID.equals("")) {
            newSponsorID = "S1001";
        } else {
            seq = Integer.parseInt(lastSponsorID.substring(1));
            seq += 1;

            newSponsorID = "S" + seq;
        }

        lastSponsorID = newSponsorID;

        return lastSponsorID;

    }

    public DoublyLinkedList<Sponsor> generateDummySponsor() {
        DoublyLinkedList<Sponsor> dummySponsor = new DoublyLinkedList<Sponsor>();

        Faker faker = new Faker();
        String[] sponsorIC = "1114152543,0904050315,0402005577,1004163133,0111147799,1101365780,0122554455,0302010441,0113224412,0730221541".split(",");
        int[] gender = {77, 70};
        String[] sponsorPhone = "0197682204,0143268085,0122323462,0164242625,0143268022,0146741265,0122072540,0195893047,0139285361,0198837324".split(",");
        String[] sponsorCompanyName = "Cox Unlimited,Rabbit Industries,Jolie and Co,Teach Teach Teach,Support Orphanage,Cameron Jolie Associates,Jolie of Malaysia,Kangaroo Support,Yeep & Associates,Happy TF Malaysia".split(",");
        String[] sponsorCompanyEmail = "cox@gmail.com,rabbit123@gmail.com,jolienco@gmail.com,teach@gmail.com,supportme22@gmail.com,cameronjolie@gmail.com,joliemalaysia@gmail.com,kangaroo@gmail.com,yeep@gmail.com,happytf@gmail.com".split("");
        String[] sponsorCompanyAddress = "No. 9M-06, Jalan Utama 3/7G,No. L-84-59, Jln Zaaba 9,No. 9Z-00, Jalan Bukit Tunku 9/9,Lot 9, Jln 4,No. 720, Jalan Bangsar 3/1F,5, Lorong Pahang 22Y,No. 19, Jln Damansara 8Q,816, Jalan Pudu 6/4,9N-43, Jalan Kedah 3/9V,19, Lorong Keliling 3/20".split(",");

        Sponsor sponsor = new Sponsor();

        for (int record = 0; record < 100; record++) {
            sponsor = new Sponsor();
            sponsor.setAccountID(autoGenerateID());
            sponsor.setName(faker.name().fullName());
            sponsor.setIc(sponsorIC[faker.number().numberBetween(0, sponsorIC.length - 1)]);
            sponsor.setGender((char) gender[faker.random().nextInt(0, gender.length - 1)]);
            sponsor.setPhoneNo(sponsorPhone[faker.number().numberBetween(0, sponsorPhone.length - 1)]);

            sponsor.setCompanyName(sponsorCompanyName[faker.number().numberBetween(0, sponsorCompanyName.length - 1)]);
            sponsor.setEmail(faker.internet().emailAddress());
            sponsor.setAddress(sponsorCompanyAddress[faker.number().numberBetween(0, sponsorCompanyAddress.length - 1)]);

            sponsor.setStatus("Active");
            dummySponsor.addLast(sponsor);
        }

        return dummySponsor;
    }

    @Override
    public Sponsor clone() throws CloneNotSupportedException {
        Sponsor cloned = (Sponsor) super.clone();
        return cloned;
    }

    public boolean isInActive() {
        return status.equalsIgnoreCase("Inactive");
    }

}
