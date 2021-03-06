/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.DoublyLinkedList;
import adt.RedBlackTree;
import adt.SinglyLinkedList;
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
public class DonorList implements Comparable<DonorList>, Cloneable {

    private String donorListID;
    private Donor donor;
    private Campaign campaign;
    private LocalDate dateJoin;
    private Timestamp dateModified;
    private String status;
    private static String lastDonorListID = "";

    public DonorList() {
    }

    public DonorList(String donorListID) {
        this.donorListID = donorListID;
    }

    public DonorList(String donorListID, Donor donor, Campaign campaign, LocalDate dateJoin, Timestamp dateModified, String status) {
        this.donorListID = donorListID;
        this.donor = donor;
        this.campaign = campaign;
        this.dateJoin = dateJoin;
        this.dateModified = dateModified;
        this.status = status;
    }

    public static void setLastDonorListID(String lastDonorListID) {
        DonorList.lastDonorListID = lastDonorListID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDonorListID() {
        return donorListID;
    }

    public void setDonorListID(String donorListID) {
        this.donorListID = donorListID;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
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

    public static String getLastDonorListID() {
        return lastDonorListID;
    }

    @Override
    public int compareTo(DonorList o) {//ID
        if (this.donorListID.compareTo(o.getDonorListID()) < 0) {
            return -1;
        } else if (this.donorListID.compareTo(o.getDonorListID()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof DonorList) {
            DonorList other = (DonorList) o;
            if (this.donorListID.equalsIgnoreCase(other.getDonorListID())) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public void copy(DonorList copyDonorList) {
        donorListID = copyDonorList.donorListID;
        donor = copyDonorList.donor;
        campaign = copyDonorList.campaign;
        dateJoin = copyDonorList.dateJoin;
        dateModified = copyDonorList.dateModified;
        status = copyDonorList.status;
    }

    private static String[] donorListHeaders() {
        String[] donotListHeaders = {"Donor List ID",
            "Donor ID",
            "Donor Name",
            "Donor Email",
            "Donor Phone No",
            "Campaign ID",
            "Campaign Name",
            "Campaign Status",
            "Donor Date Join",
            "Status",
            "Date Modified"};

        return donotListHeaders;
    }

    private String[] strArr() {

        return new String[]{donorListID,
            donor.accountID,
            donor.name,
            donor.email,
            donor.phoneNo,
            campaign.getCampaignID(),
            campaign.getCampaignName(),
            campaign.getStatus(),
            dateJoin.toString(),
            status,
            dateModified.toLocalDateTime().toString()};
    }

    private static String[][] donorListRows(RedBlackTree<LocalDate, DonorList> donorListDB) {
        DonorList[] donorList = new DonorList[donorListDB.getAllList().getLength()];
        donorList = donorListDB.getAllListInArray(donorList);
        String[][] donorListRows = new String[donorList.length][];
        for (int i = 0; i < donorList.length; i++) {
            donorListRows[i] = donorList[i].strArr();
        }
        return donorListRows;
    }

    public static void donorListTable(RedBlackTree<LocalDate, DonorList> donorDB) {
        String[] donorListHeader = DonorList.donorListHeaders();
        String[][] donorListData = DonorList.donorListRows(donorDB);

        ASCIITable.getInstance().printTable(donorListHeader, donorListData);
    }

    public String autoGenerateID() {
        String newCampaignID = "";
        int seq = 0;
        if (lastDonorListID.equals("")) {
            newCampaignID = "DOL1001";
        } else {
            seq = Integer.parseInt(lastDonorListID.substring(3));
            seq += 1;

            newCampaignID = "DOL" + seq;
        }

        lastDonorListID = newCampaignID;

        return lastDonorListID;
    }

    public RedBlackTree<LocalDate, DonorList> generateDummyDonorList(
            RedBlackTree<LocalDate, Campaign> campaignDB,
            SinglyLinkedList<Donor> donorDB) {

        RedBlackTree<LocalDate, DonorList> dummyDonorList = new RedBlackTree<>();
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

        DonorList donorList = new DonorList();

        while (counter <= campaigns.getLength()) {

            Campaign campaign = campaigns.getAt(counter);
            int randomTtl = faker.number().numberBetween(1, 3);

            for (int record = 0; record < randomTtl; record++) {
                DonorList[] donorListArr = new DonorList[dummyDonorList.getAllList().getLength()];
                donorListArr = dummyDonorList.getAllListInArray(donorListArr);
                Donor donor = donorDB.getAt(faker.number().numberBetween(1, donorDB.getDataCount()));

                donorList = new DonorList();
                donorList.setDonorListID(autoGenerateID());
                donorList.setCampaign(campaign);
                //<editor-fold defaultstate="collapsed" desc="sponsor check constraint">
                if (donorListArr != null) {

                    for (int i = 0; i < donorListArr.length; i++) {
                        donor = donorDB.getAt(faker.number().numberBetween(1, donorDB.getDataCount()));
                        if (donorListArr[i].getCampaign().equals(campaign) && donorListArr[i].getDonor().equals(donor)) {
                            donor = donorDB.getAt(faker.number().numberBetween(1, donorDB.getDataCount()));
                            i = 0;
                        }
                    }
                }

                //</editor-fold>
                donorList.setDonor(donor);
                donorList.setStatus("Active");
                donorList.setDateJoin(campaign.getCampaignRegisterDate().plusDays(faker.number().numberBetween(4, 14)));
                donorList.setDateModified(Timestamp.valueOf(donorList.dateJoin.plusDays(faker.number().numberBetween(0, 6)).atStartOfDay()));
                dummyDonorList.addData(donorList.getDateJoin(), donorList);
            }
            counter++;
        }
        return dummyDonorList;
    }

    @Override
    public DonorList clone() throws CloneNotSupportedException {
        DonorList cloned = (DonorList) super.clone();
        return cloned;
    }

}
