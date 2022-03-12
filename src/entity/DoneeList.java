/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.DoublyLinkedList;
import adt.RedBlackTree;
import com.bethecoder.ascii_table.ASCIITable;
import com.github.javafaker.Faker;
import io.github.benas.randombeans.randomizers.range.LocalDateTimeRangeRandomizer;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class DoneeList implements Comparable<DoneeList> {

    private String doneeListID;
    private Donee donee;
    private Campaign campaign;
    private LocalDate dateJoin;
    private Timestamp dateModified;
    private String status;
    private static String lastDoneeListID = "";

    public DoneeList() {
    }

    public DoneeList(String doneeListID) {
        this.doneeListID = doneeListID;
    }

    public DoneeList(String doneeListID, Donee donee, Campaign campaign, LocalDate dateJoin, Timestamp dateModified, String status) {
        this.doneeListID = doneeListID;
        this.donee = donee;
        this.campaign = campaign;
        this.dateJoin = dateJoin;
        this.dateModified = dateModified;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoneeListID() {
        return doneeListID;
    }

    public void setDoneeListID(String doneeListID) {
        this.doneeListID = doneeListID;
    }

    public Donee getDonee() {
        return donee;
    }

    public void setDonee(Donee donee) {
        this.donee = donee;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public LocalDate getDateJoin() {
        return dateJoin;
    }

    public void setDateJoin(LocalDate dateJoin) {
        this.dateJoin = dateJoin;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    public static String getLastDoneeListID() {
        return lastDoneeListID;
    }

    public static void setLastDoneeListID(String lastDoneeListID) {
        DoneeList.lastDoneeListID = lastDoneeListID;
    }

    @Override
    public int compareTo(DoneeList o) {//ID
        if (this.doneeListID.compareTo(o.getDoneeListID()) < 0) {
            return -1;
        } else if (this.doneeListID.compareTo(o.getDoneeListID()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof DoneeList) {
            DoneeList other = (DoneeList) o;
            if (this.doneeListID == other.getDoneeListID()) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static String[] doneeListHeaders() {
        String[] campaignHeaders = {"Donee List ID", "Donee ID"};

        return campaignHeaders;
    }

    private String[] strArr() {
        return new String[]{doneeListID, donee.getAccountID()};
    }

    private static String[][] doneeListRows(RedBlackTree<LocalDate, DoneeList> doneeListDB) {
        DoneeList[] doneeLists = new DoneeList[doneeListDB.getLength()];
        doneeLists = doneeListDB.getAllArrayList(doneeLists);
        String[][] doneeListRows = new String[doneeLists.length][];
        for (int i = 0; i < doneeLists.length; i++) {
            doneeListRows[i] = doneeLists[i].strArr();
        }
        return doneeListRows;
    }

    public static void doneeListTable(RedBlackTree<LocalDate, DoneeList> doneeList) {
        String[] doneeListHeader = DoneeList.doneeListHeaders();
        String[][] doneeListData = DoneeList.doneeListRows(doneeList);

        ASCIITable.getInstance().printTable(doneeListHeader, doneeListData);
    }

    public String autoGenerateID() {
        String newCampaignID = "";
        int seq = 0;
        if (lastDoneeListID.equals("")) {
            newCampaignID = "DL1001";
        } else {
            seq = Integer.parseInt(lastDoneeListID.substring(2));
            seq += 1;

            newCampaignID = "DL" + seq;
        }

        lastDoneeListID = newCampaignID;

        return lastDoneeListID;
    }

    public RedBlackTree<LocalDate, DoneeList> generateDummyDoneeList(RedBlackTree<LocalDate, Campaign> campaignDB, DoublyLinkedList<Donee> doneeDB) {

        RedBlackTree<LocalDate, DoneeList> dummyDoneeList = new RedBlackTree<>();
        //<editor-fold defaultstate="collapsed" desc="fake data generator tools">
        Faker faker = new Faker();
        LocalDateTimeRangeRandomizer randomLDTR;
        LocalDateTime randomLDT;
        LocalDateTime minTime = LocalDateTime.of(2018, Month.JANUARY, 1, 00, 00, 00);
        LocalDateTime maxTime = LocalDateTime.of(2021, Month.DECEMBER, 31, 23, 59, 59);
        randomLDTR = LocalDateTimeRangeRandomizer.aNewLocalDateTimeRangeRandomizer(minTime, maxTime);
        //</editor-fold>
        int counter = 1;
        DoublyLinkedList<Campaign> campaigns = campaignDB.getAllList();

        DoneeList doneeList = new DoneeList();

        while (counter <= campaigns.getLength()) {

            Campaign campaign = campaigns.getAt(counter);
            int randomTtl = faker.number().numberBetween(1, 3);

            for (int record = 0; record < randomTtl; record++) {
                DoneeList[] doneeListArr = new DoneeList[dummyDoneeList.getLength()];
                doneeListArr = dummyDoneeList.getAllArrayList(doneeListArr);
                Donee donee = doneeDB.getAt(faker.number().numberBetween(1, doneeDB.getLength()));

                doneeList = new DoneeList();
                doneeList.setDoneeListID(autoGenerateID());
                doneeList.setCampaign(campaign);
                //<editor-fold defaultstate="collapsed" desc="sponsor check constraint">
                if (doneeListArr != null) {

                    for (int i = 0; i < doneeListArr.length; i++) {
                        donee = doneeDB.getAt(faker.number().numberBetween(1, doneeDB.getLength()));
                        if (doneeListArr[i].getCampaign().equals(campaign) && doneeListArr[i].getDonee().equals(donee)) {
                            donee = doneeDB.getAt(faker.number().numberBetween(1, doneeDB.getLength()));
                            i = 0;
                        }
                    }
                }

                //</editor-fold>
                doneeList.setDonee(donee);
                doneeList.setStatus("Active");
                doneeList.setDateJoin(campaign.getCampaignRegisterDate().plusDays(faker.number().numberBetween(4, 14)));
                doneeList.setDateModified(Timestamp.valueOf(doneeList.dateJoin.plusDays(faker.number().numberBetween(0, 6)).atStartOfDay()));
                dummyDoneeList.addData(doneeList.getDateJoin(), doneeList);
            }
            counter++;
        }

        return dummyDoneeList;
    }
}
