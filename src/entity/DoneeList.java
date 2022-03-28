/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.CircularLinkedQueue;
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
public class DoneeList implements Comparable<DoneeList>, Cloneable {

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
            if (this.doneeListID.equalsIgnoreCase(other.getDoneeListID())) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static String[] doneeListHeaders() {
        String[] doneeListHeaders = {"Donee List ID",
            "Donee ID",
            "Donee Name",
            "Donee Email",
            "Donee Phone No",
            "Campaign ID",
            "Campaign Name",
            "Campaign Status",
            "Donee Date Join",
            "Status",
            "Date Modified"};

        return doneeListHeaders;
    }

    private String[] strArr() {
        return new String[]{doneeListID,
            donee.accountID,
            donee.name,
            donee.email,
            donee.phoneNo,
            campaign.getCampaignID(),
            campaign.getCampaignName(),
            campaign.getStatus(),
            dateJoin.toString(),
            status,
            dateModified.toLocalDateTime().toString()};
    }

    private static String[][] doneeListRows(RedBlackTree<LocalDate, DoneeList> doneeListDB) {
        DoneeList[] doneeLists = new DoneeList[doneeListDB.getAllList().getLength()];
        doneeLists = doneeListDB.getAllListInArray(doneeLists);
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

    public RedBlackTree<LocalDate, DoneeList> generateDummyDoneeList(
            RedBlackTree<LocalDate, Campaign> campaignDB,
            CircularLinkedQueue<Donee> doneeDB,
            DoublyLinkedList<Donee> doneeInHelpDB) {

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
        DoublyLinkedList<Campaign> campaigns = (DoublyLinkedList<Campaign>) campaignDB.getAllList();

        DoneeList doneeList = new DoneeList();

        while (counter <= campaigns.getLength()) {

            Campaign campaign = campaigns.getAt(counter);
            int randomTtl = faker.number().numberBetween(1, 3);

            for (int record = 0; record < randomTtl; record++) {

                DoneeList[] doneeListArr = new DoneeList[dummyDoneeList.getAllList().getLength()];
                doneeListArr = dummyDoneeList.getAllListInArray(doneeListArr);
                Donee donee = doneeDB.dequeue();
                doneeInHelpDB.addLast(donee);
                doneeList = new DoneeList();
                doneeList.setDoneeListID(autoGenerateID());
                doneeList.setCampaign(campaign);
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

    @Override
    public DoneeList clone() throws CloneNotSupportedException {
        DoneeList cloned = (DoneeList) super.clone();
        return cloned;
    }

}
