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
public class SponsorList implements Comparable<SponsorList>, Cloneable {

    private String sponsorListID;
    private Sponsor sponsor;
    private Campaign campaign;
    private LocalDate dateJoin;
    private Timestamp dateModified;
    private String status;
    private static String lastSponsorListID = "";

    public SponsorList() {
    }

    public SponsorList(String sponsorListID) {
        this.sponsorListID = sponsorListID;
    }

    public SponsorList(String sponsorListID, Sponsor sponsor, Campaign campaign, LocalDate dateJoin, Timestamp dateModified, String status) {
        this.sponsorListID = sponsorListID;
        this.sponsor = sponsor;
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

    public String getSponsorListID() {
        return sponsorListID;
    }

    public void setSponsorListID(String sponsorListID) {
        this.sponsorListID = sponsorListID;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
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

    public static String getLastSponsorListID() {
        return lastSponsorListID;
    }

    public static void setLastSponsorListID(String lastSponsorListID) {
        SponsorList.lastSponsorListID = lastSponsorListID;
    }

    @Override
    public int compareTo(SponsorList o) {//ID
        if (this.sponsorListID.compareTo(o.getSponsorListID()) < 0) {
            return -1;
        } else if (this.sponsorListID.compareTo(o.getSponsorListID()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof SponsorList) {
            SponsorList other = (SponsorList) o;
            if (this.sponsorListID.equalsIgnoreCase(other.getSponsorListID())) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static String[] sponsorListHeaders() {
        String[] campaignHeaders = {"Sponsor List ID", "Sponsor ID", "Sponsor Name", "Sponsor Email", "Sponsor Phone No", "Campaign ID", "Campaign Name", "Campaign Status", "Sponsor Date Join", "Status", "Date Modified"};

        return campaignHeaders;
    }

    private String[] strArr() {
        return new String[]{sponsorListID, sponsor.accountID, sponsor.name, sponsor.email, sponsor.phoneNo, campaign.getCampaignID(), campaign.getCampaignName(), campaign.getStatus(), dateJoin.toString(), status, dateModified.toLocalDateTime().toString()};
    }

    private static String[][] sponsorListRows(RedBlackTree<LocalDate, SponsorList> sponsorListDB) {
        SponsorList[] sponsorList = new SponsorList[sponsorListDB.getAllList().getLength()];
        sponsorList = sponsorListDB.getAllListInArray(sponsorList);
        String[][] sponsorListRows = new String[sponsorList.length][];
        for (int i = 0; i < sponsorList.length; i++) {
            sponsorListRows[i] = sponsorList[i].strArr();
        }
        return sponsorListRows;
    }

    public static void sponsorListTable(RedBlackTree<LocalDate, SponsorList> donorDB) {
        String[] sponsorListHeader = SponsorList.sponsorListHeaders();
        String[][] sponsorListData = SponsorList.sponsorListRows(donorDB);

        ASCIITable.getInstance().printTable(sponsorListHeader, sponsorListData);
    }

    public String autoGenerateID() {
        String newCampaignID = "";
        int seq = 0;
        if (lastSponsorListID.equals("")) {
            newCampaignID = "SL1001";
        } else {
            seq = Integer.parseInt(lastSponsorListID.substring(2));
            seq += 1;

            newCampaignID = "SL" + seq;
        }

        lastSponsorListID = newCampaignID;

        return lastSponsorListID;
    }

    public RedBlackTree<LocalDate, SponsorList> generateDummySponsorList(
            RedBlackTree<LocalDate, Campaign> campaignDB,
            DoublyLinkedList<Sponsor> sponsorDB) {

        RedBlackTree<LocalDate, SponsorList> dummySponsorList = new RedBlackTree<>();
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

        SponsorList sponsorList = new SponsorList();

        while (counter <= campaigns.getLength()) {

            Campaign campaign = campaigns.getAt(counter);
            int randomTtl = faker.number().numberBetween(1, 3);
            for (int record = 0; record < randomTtl; record++) {
                SponsorList[] sponsorListArr = new SponsorList[dummySponsorList.getAllList().getLength()];
                sponsorListArr = dummySponsorList.getAllListInArray(sponsorListArr);
                Sponsor sponsor = sponsorDB.getAt(faker.number().numberBetween(1, sponsorDB.getLength()));

                sponsorList = new SponsorList();
                sponsorList.setSponsorListID(autoGenerateID());
                sponsorList.setCampaign(campaign);
                //<editor-fold defaultstate="collapsed" desc="sponsor check constraint">

                if (sponsorListArr != null) {

                    for (int i = 0; i < sponsorListArr.length; i++) {
                        sponsor = sponsorDB.getAt(faker.number().numberBetween(1, sponsorDB.getLength()));
                        if (sponsorListArr[i].getCampaign().equals(campaign) && sponsorListArr[i].getSponsor().equals(sponsor)) {
                            sponsor = sponsorDB.getAt(faker.number().numberBetween(1, sponsorDB.getLength()));
                            i = 0;
                        }
                    }
                }

                //</editor-fold>
                sponsorList.setSponsor(sponsor);
                sponsorList.setStatus("Active");
                sponsorList.setDateJoin(campaign.getCampaignRegisterDate().plusDays(faker.number().numberBetween(4, 14)));
                sponsorList.setDateModified(Timestamp.valueOf(sponsorList.dateJoin.plusDays(faker.number().numberBetween(0, 6)).atStartOfDay()));
                dummySponsorList.addData(sponsorList.getDateJoin(), sponsorList);

            }
            counter++;
        }

        return dummySponsorList;
    }

    @Override
    public SponsorList clone() throws CloneNotSupportedException {
        SponsorList cloned = (SponsorList) super.clone();
        return cloned;
    }
}
