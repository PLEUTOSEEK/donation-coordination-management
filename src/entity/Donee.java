/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.CircularLinkedQueue;
import com.bethecoder.ascii_table.ASCIITable;
import com.github.javafaker.Faker;

/**
 *
 * @author junyao
 */
public class Donee extends Account implements Comparable<Donee>, Cloneable {

    private String requestIssue, status;
    private double requestAmount;
    private double requestOriAmount;
    private String bankType, bankAcc;
    //private List<Donation> donations;
    private static String lastDoneeID = "";

    public Donee() {
        this("", "", "", ' ', "", "", "", "", 0.0, "", "");
    }

    public Donee(String accountID, String name, String ic, char gender, String email, String phoneNo, String address, String requestIssue, double requestAmount, String bankType, String bankAcc) {
        super(accountID, name, gender, ic, email, phoneNo, address);
        this.accountID = lastDoneeID;
        this.requestIssue = requestIssue;
        this.requestAmount = requestAmount;
        this.bankType = bankType;
        this.bankAcc = bankAcc;
    }

    public Donee(String doneeID) {
        this.accountID = doneeID;
    }

    public void setRequestIssue(String requestIssue) {
        this.requestIssue = requestIssue;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRequestAmount(double requestAmount) {
        this.requestAmount = requestAmount;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

    public static String getLastDoneeID() {
        return lastDoneeID;
    }

    public static void setLastDoneeID(String lastDoneeID) {
        Donee.lastDoneeID = lastDoneeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static void setLastId(String lastId) {
        Account.lastId = lastId;
    }

    public String getRequestIssue() {
        return requestIssue;
    }

    public String getStatus() {
        return status;
    }

    public double getRequestAmount() {
        return requestAmount;
    }

    public String getBankType() {
        return bankType;
    }

    public String getBankAcc() {
        return bankAcc;
    }

    @Override

    public int compareTo(Donee o) {//ID
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

        if (o instanceof Donee) {
            Donee other = (Donee) o;
            if (this.accountID.equals(other.getAccountID())) {
                return true;
            } else {
                return false;
            }
        }

        return false;

    }

    private static String[] doneeHeaders() {
        String[] doneeRows = {"Donee ID", "Donee Name", "IC", "Gender", "Email", "Phone", "Address", "Request Issue", "Request Amount", "Bank Type", "Bank Account", "Status"};

        return doneeRows;
    }

    private String[] strArr() {
        String gend = String.valueOf(gender);
        String amt = Double.toString(requestAmount);
        return new String[]{accountID, name, ic, gend, email, phoneNo, address, requestIssue, amt, bankType, bankAcc, status};

    }

    //change the list and apply toArray method.
    private static String[][] doneeRows(CircularLinkedQueue<Donee> doneeList) {
        Donee[] donees = new Donee[doneeList.size()];
        donees = doneeList.toArray(donees);
        String[][] doneeRows = new String[doneeList.size()][];
        for (int i = 0; i < donees.length; i++) {
            doneeRows[i] = donees[i].strArr();
        }
        return doneeRows;
    }

    public static void doneeTable(CircularLinkedQueue<Donee> doneeList) {
        String[] header = Donee.doneeHeaders();
        String[][] doneeData = Donee.doneeRows(doneeList);

        ASCIITable.getInstance().printTable(header, doneeData);
    }

    public String autoGenerateID1(CircularLinkedQueue<Donee> d) {

//        if (d.isEmpty() == true) {
//            newDoneeID = "DE1001";
//        } else {
//            newDoneeID = d.getEnd().getLastDoneeID();
//            n = Integer.parseInt(newDoneeID.substring(2));
//            n++;
//            newDoneeID = "DE" + n;
//        }
        return lastDoneeID;
    }

    public CircularLinkedQueue<Donee> generateDummyDonee() {
        CircularLinkedQueue<Donee> dummyDonee = new CircularLinkedQueue<Donee>();
        Faker faker = new Faker();
        String[] doneeIC = "1114152543,0904050315,0402005577,1004163133,0111147799,1101365780,0122554455,0302010441,0113224412,0730221541".split(",");
        int[] gender = {77, 70};
        String[] doneeEmail = "Uzair@gmail.com,Keaton@gmail.com,Kajal@Salas.com,Susie@gmail.com,Sullivan@Bean.com,Ralph@Yates.com,Ashlyn@Cooke.com,Habib@gmail.com,JeffReese@gmail.com,Corben@Donald.com".split(",");
        String[] doneePhone = "0197682204,0143268085,0122323462,0164242625,0143268022,0146741265,0122072540,0195893047,0139285361,0198837324".split(",");
        String[] doneeAddress = "No. 9M-36, Jalan Utama 3/7G,No. L-84-59, Jln Zaaba 9,No. 9Z-04, Jalan Bukit Tunku 7/9,Lot 9, Jln 4,No. 720, Jalan Bangsar 3/69F,5, Lorong Pahang 22Y,No. 19, Jln Damansara 8Q,816, Jalan Pudu 6/4,9N-43, Jalan Kedah 3/9V,19, Lorong Keliling 3/20".split(",");
        String[] doneeRequestIssue = "Request for nursing home,Request for orphanage,Request for flood,Request for earthquake,Request for famine,Request for the food bank,Request for daughter's leukemia,Request for cancer medical expenses,Request for supplies, food, and education to African children,Request for leukemia patients".split(",");
        String[] doneeRequestAmount = "2000.00,1500.00,3200.00,2300.00,1000.00,2000.00,3000.00,1500.00,2500.00,3500.00".split(",");
        String[] doneeBankType = {"Public bank", "Maybank"};

        Donee donee = new Donee();

        for (int record = 0; record < 100; record++) {
            String bankNo = (int) faker.number().randomDouble(0, 1000, 9000) + " "
                    + (int) faker.number().randomDouble(0, 1000, 9000) + " "
                    + (int) faker.number().randomDouble(0, 10, 99);

            donee = new Donee();

            donee.setAccountID(autoGenerateID());
            donee.setName(faker.name().fullName());
            donee.setIc(doneeIC[faker.number().numberBetween(0, doneeIC.length - 1)]);
            donee.setGender((char) gender[faker.random().nextInt(0, gender.length - 1)]);
            donee.setEmail(faker.internet().emailAddress());
            donee.setPhoneNo(doneePhone[faker.number().numberBetween(0, doneePhone.length - 1)]);
            donee.setAddress(doneeAddress[faker.number().numberBetween(0, doneeAddress.length - 1)]);
            donee.setRequestIssue(doneeRequestIssue[faker.number().numberBetween(0, doneeRequestIssue.length - 1)]);
            donee.setRequestAmount(faker.number().randomDouble(2, 1000, 8000));
            donee.setBankType(doneeBankType[faker.number().numberBetween(0, doneeBankType.length - 1)]);
            donee.setBankAcc(bankNo);
            donee.setStatus("Active");
            dummyDonee.enqueue(donee);
        }
        return dummyDonee;
    }

    @Override
    public String autoGenerateID() {
        String newDoneeID = "";
        int n;

        if (lastDoneeID.equals("")) {
            newDoneeID = "DE1001";
        } else {

            n = Integer.parseInt(lastDoneeID.substring(2));
            n += 1;
            newDoneeID = "DE" + n;
        }

        lastDoneeID = newDoneeID;

        return lastDoneeID;
    }
    
        @Override
    public Donee clone() throws CloneNotSupportedException {
        Donee cloned = (Donee) super.clone();
        return cloned;
    }
}
