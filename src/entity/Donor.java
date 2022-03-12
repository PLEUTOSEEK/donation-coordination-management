/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.SinglyLinkedList;
import com.bethecoder.ascii_table.ASCIITable;
import com.github.javafaker.Faker;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Wong Phey Zhen
 */
public class Donor extends Account implements Comparable<Donor> {

    
    private String donorType;
    private String status;
    private static String lastDonorID = "";

    public Donor() {
    }
    
    public Donor(String lastDonorID) {
        this.lastDonorID = lastDonorID;
    }

    public Donor(String name , String donorType, char gender, String ic, String email, String phoneNo, String address) {
        //String name, char gender, String ic, String email, String phoneNo, String address,
        super(name, gender, ic, email, phoneNo, address);
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

    
    @Override
    public int compareTo(Donor o) {
        if(this.accountID.compareTo(o.accountID) < 0){
           return -1;
       }else if (this.accountID.compareTo(o.accountID) > 0){
           return 1;
       } else{
           return 0;
       }
    }

    private static String[] donorHeaders() {
        String[] donorHeaders = {"Donor ID", "Donor Name","Donor Type", "IC/ Company Register No","Email Address","Phone Number","Address"};

        return donorHeaders;
    }

    private String[] strArr() {
        return new String[]{accountID, name, donorType, ic, email, phoneNo, address };
    }

    //change the list and apply toArray method.
    private static String[][] donorRows(SinglyLinkedList<Donor> donorList) {
        Donor[] donors = donorList.toArray();
        String[][] donorRows = new String[donorList.getDataCount()][];
        for (int i = 0; i < donors.length; i++) {
            donorRows[i] = donors[i].strArr();
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
       if(lastDonorID.equals("")){
           newDonorID = "DO1001";
       }else{
           seq = Integer.parseInt(lastDonorID.substring(2));
           seq += 1;
           
           newDonorID = "DO" +seq;
       }
       lastDonorID = newDonorID;
       
       return lastDonorID;
    }

    public SinglyLinkedList<Donor> generateDummyDonor() {
        Faker faker = new Faker();
        Donor donor = new Donor();
         
        String[] donorName = "Shiloh Almond,Haidar Potts,Anton Klein,Balraj Lang,Raya Penn,Cherry Swift,Raymond Worthington,Connah Parrish,Stacey Mathews,Corinne Monaghan,Reid Mccallum,Saqlain Stephens,Ray Whyte,Gertrude Mccabe,Jamaal Gould".split(","); 
        String[] donorType = "individual,organization,individual,individual,individual,organization,individual,individual,organization,individual,individual,organization,individual,individual,individual".split(",");
        String[] gender = "'M',' ','M','F','F',' ','M','F','F','F','M',' ','M','M',' ','M','M','M',' ','F','M',' ',' '".split(",");
        String[] ic = "980528-03-0521,105015-K,681005-04-1235,780225-06-1236,980623-05-5142,125432-N,850412-02-5213,681228-05-5698,980523-25-6589,560925-05-5894,020615-02-5689,457632-K,520628-06-8947,780928-04-5894,457632-K,560924-05-8562,650498-05-5868,680825-06-5864,457632-K,520225-07-2568,980506-02-4569,125645-K,586425-N".split(",");
        String[] email= "shilohA@gmail.com,y&a@gmailcom,haidar@yahoo.com,anton97@gmail.com,BalrajL@gmail.com,handOC@gmail.com,raya@yahoo.com,charry67@gmail.com,marcy@gamil.com,raymond@gmail.com,cp@gmail.com,sukahati@gmail.com,stacey43@yahoo.com,corinne@gmail.com,borneoChildAidSociety@gmail.com,reid@gmail.com,saqlain@gamil.com,ray@yahoo.com,ncsom@gamil.com,mccba@gmail.com,jamaal@gmail.com,josephHome@gmail.com,joyHouse.com@gmail.com".split(",");
        String[] phoneNo = "012-8956682,03-20726766,011-12589562,012-5894562,012-9856483,03-33744119,016-5894562,019-5895632,03-91712260,011-12598632,013-5895235,03-78775873,012-8759862,013-5895235,03-79578362,013-2598625,011-12895625,012-0589532,03-21433192,018-5698215,017-8658612,03-69835698,03-56925813".split(",");
        String[] address = "A 532 Jln Cheras Batu 3 1/4,Bandar Mahkota Cheras,1 11 Lrg Tiara 1B Bandar Baru,2A-2-25 Lengkok Nipah,A 51 Lrg Meranti,37 Jln Manis 4 Taman Segar,12A Persiaran Wira Jaya Barat 44 Taman Jaya 1,B 649/1 Jln Batu 4 Wilayah Persekutuan, A 43 Jln Ss2/75 Ss2 Petaling Jaya,3402 Jln Ampang Hilir 2, Room 502 5Th Floor Wisma Daimam 64 Jalan Sulam Taman Sentosa Malays,238 Kawasan Perindustrian Ringan,4802 Jalan Bagan Luar,A 532 Jln Cheras Batu 3 1/4,Bandar Mahkota Cheras,1 11 Lrg Tiara 1B Bandar Baru,2A-2-25 Lengkok Nipah,A 51 Lrg Meranti,37 Jln Manis 4 Taman Segar,12A Persiaran Wira Jaya,Barat 44 Taman Jaya 1,B 649/1 Jln Batu 4 Wilayah Persekutuan, A 43 Jln Ss2/75 Ss2 Petaling Jaya,3402 Jln Ampang Hilir 2, Room 502 5Th Floor Wisma Daimam 64 Jalan Sulam Taman Sentosa Malaysia,238 Kawasan Perindustrian Ringan,4802 Jalan Bagan Luar,No. 94 A Lrg Samarinda 8 Kandis Permai,B 1 Jln Ss21/1A Ss21 Petaling Jaya,G9 Gerai Makan Pasar Jawa Jln Pasar Kaw 18,402 Jln Lama Batu 6 Wilayah Persekutuan,3 Wisma Berjaya Prudential Jln Abell Kuching,3 Central Park Commercial Centre 316 Jln Tun Ahmad Zaidi Adruce Central Park,30 Jln Mutiara Raya Taman Mutiara,No. 555 A Jln E3/5 Taman Ehsan Kepong,71 Jalan Setia Jaya Bukit Kapar Kapar,3 Jln Klln 3 Taman Koperatif Lln".split(",");
        String[] status = "active,active,active,active,active,acactive,active,active,active,active,active,active,active,active,active,active,active,active,active,active,active,active,active".split(",");
        
        for (int data = 0; data < 40; data++){
            donor = new Donor();
            donor.setAccountID(autoGenerateID());
            donor.setName(donorName[data]);
            donor.setDonorType(donorType[data]);
            donor.setGender(gender[data].charAt(0));
            donor.setIc(ic[data]);
            donor.setEmail(email[data]);
            donor.setPhoneNo(phoneNo[data]);
            donor.setAddress(address[data]);
               
            System.out.println(donor.getAccountID().toString());
        }        
        return null;
    }

}
