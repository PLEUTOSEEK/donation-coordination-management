/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.CircularLinkedQueue;
import adt.DoublyLinkedList;
import com.bethecoder.ascii_table.ASCIITable;
import com.github.javafaker.Faker;

/**
 *
 * @author junyao
 */
public class Donee extends Account implements Comparable<Donee> {

    private String doneeID = "";
    private String requestIssue, status;
    private double requestAmount;
    private String bankType, bankAcc;
    //private List<Donation> donations;
    private String lastDoneeID = "";

    public Donee() {
        this("", "", ' ', "", "", "", "", 0.0, "", "");
    }

    public Donee(String name, String ic, char gender, String email, String phoneNo, String address, String requestIssue, double requestAmount, String bankType, String bankAcc) {
        super(name, gender, ic, email, phoneNo, address);
        this.requestIssue = requestIssue;
        this.requestAmount = requestAmount;
        this.bankType = bankType;
        this.bankAcc = bankAcc;
    }

    public Donee(String doneeID) {
        this.doneeID = doneeID;
    }

    public String getDoneeID() {
        return doneeID;
    }

    public void setDoneeID(String doneeID) {
        this.doneeID = doneeID;
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

    public String getLastDoneeID() {
        return lastDoneeID;
    }

    public void setLastDoneeID(String lastDoneeID) {
        this.lastDoneeID = lastDoneeID;
    }
    

    @Override
    public int compareTo(Donee o) {
        if (this.doneeID.compareTo(o.doneeID) < 0) {
            return -1;
        } else if (this.doneeID.compareTo(o.doneeID) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    private static String[] doneeHeaders() {
        String[] doneeRows = {"Donee ID", "Donee Name", "IC", "Gender", "Email", "Phone", "Address", "Request Issue", "Request Amount", "Bank Type", "Bank Account"};

        return doneeRows;
    }

    private String[] strArr() {
        return new String[]{doneeID, name};
    }

    //change the list and apply toArray method.
    private static String[][] doneeRows(DoublyLinkedList<Donee> doneeList) {
        Donee[] donees = doneeList.toArray();
        String[][] doneeRows = new String[doneeList.getLength()][];
        for (int i = 0; i < donees.length; i++) {
            doneeRows[i] = donees[i].strArr();
        }
        return doneeRows;
    }

    public static void doneeTable(DoublyLinkedList<Donee> doneeList) {
        String[] header = Donee.doneeHeaders();
        String[][] doneeData = Donee.doneeRows(doneeList);

        ASCIITable.getInstance().printTable(header, doneeData);
    }

<<<<<<< HEAD
    public String autoGenerateID() {
        String newDoneeID = "";
        int n = 0;

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

    public CircularLinkedQueue<Donee> generateDummyDonee() {
        Faker faker = new Faker();

        String[] doneeName = "Uzair Hassan,Keaton Bruce,Kajal Salas,Susie Wilkinson,Sullivan Bean,Ralph Yates,Ashlyn Cooke,Habib Cross,Jeff Reese,Corben Donald".split(",");
        String[] doneeIC = "1114152543,0904050315,0402005577,1004163133,0111147799,1101365780,0122554455,0302010441,0113224412,0730221541".split(",");
        String[] doneeGender = "M,M,M,F,F,M,F,F,M,M".split(",");
        //String[] doneeEmail = "Uzair@gmail.com,Keaton@gmail.com,Kajal@Salas.com,Susie@gmail.com,Sullivan@Bean.com,Ralph@Yates.com,Ashlyn@Cooke.com,Habib@gmail.com,JeffReese@gmail.com,Corben@Donald.com".split(",");
        String[] doneePhone = "0197682204,0143268085,0122323462,0164242625,0143268022,0146741265,0122072540,0195893047,0139285361,0198837324".split(",");
        String[] doneeAddress = "No. 9M-36, Jalan Utama 3/7G,No. L-84-59, Jln Zaaba 9,No. 9Z-04, Jalan Bukit Tunku 7/9,Lot 9, Jln 4,No. 720, Jalan Bangsar 3/69F,5, Lorong Pahang 22Y,No. 19, Jln Damansara 8Q,816, Jalan Pudu 6/4,9N-43, Jalan Kedah 3/9V,19, Lorong Keliling 3/20".split(",");
        String[] doneeRequestIssue = "Request for nursing home,Request for orphanage,Request for flood,Request for earthquake,Request for famine,Request for the food bank,Request for daughter's leukemia,Request for cancer medical expenses,Request for supplies, food, and education to African children,Request for leukemia patients".split(",");
        String[] doneeRequestAmount = "2000.00,1500.00,3200.00,2300.00,1000.00,2000.00,3000.00,1500.00,2500.00,3500.00".split(",");
        String[] doneeBankType = "Public bank,Maybank,Ambank,Maybank,Public bank,Public bank,Maybank,Public bank,Ambank,Maybank".split(",");
        String[] doneeBankAcc = "6685046056,155050504789,987458963,155151510679,6374581257,6854782213,150514248513,6457865510,987561489,155458520100".split(",");

        Donee donee = new Donee();

        for (int record = 0; record < 40; record++) {

            donee = new Donee();

            donee.setDoneeID(autoGenerateID());
            donee.setName(doneeName[record]);
            donee.setIc(doneeIC[record]);
            //donee.setGender(doneeGender.[record]);
            donee.setEmail(faker.internet().emailAddress());
            donee.setPhoneNo(doneePhone[record]);
            donee.setAddress(doneeAddress[record]);
            donee.setRequestIssue(doneeRequestIssue[record]);
            //donee.setRequestAmount(doneeRequestAmount[record]);
            donee.setBankType(doneeBankType[record]);
            donee.setBankAcc(doneeBankAcc[record]);
            donee.setStatus("Active");

            System.out.println(donee.getDoneeID().toString());
        }

        return null;
=======
    @Override
    public String autoGenerateID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DoublyLinkedList<Donee> generateDummyDonee() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> 56cf93c235a512cff46df21730b521e5d9ef9776
    }

}
