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
import java.time.LocalTime;
import java.time.Month;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class SponsorItem implements Comparable<SponsorItem> {

    private String sponsoredID;
    private DemandList demandList;
    private Funds funds;
    private int quantity;
    private LocalDate dateDonate;
    private Timestamp dateModified;
    private String status;
    private static String lastSponsoredID = "";

    public SponsorItem() {

    }

    public SponsorItem(String sponsoredID, DemandList demandList, Funds funds, int quantity, LocalDate dateDonate, Timestamp dateModified, String status) {
        this.sponsoredID = sponsoredID;
        this.demandList = demandList;
        this.funds = funds;
        this.quantity = quantity;
        this.dateDonate = dateDonate;
        this.dateModified = dateModified;
        this.status = status;
    }

    public static String getLastSponsoredID() {
        return lastSponsoredID;
    }

    public static void setLastSponsoredID(String lastSponsoredID) {
        SponsorItem.lastSponsoredID = lastSponsoredID;
    }

    public String getSponsoredID() {
        return sponsoredID;
    }

    public void setSponsoredID(String sponsoredID) {
        this.sponsoredID = sponsoredID;
    }

    public DemandList getDemandList() {
        return demandList;
    }

    public void setDemandList(DemandList demandList) {
        this.demandList = demandList;
    }

    public Funds getFunds() {
        return funds;
    }

    public void setFunds(Funds funds) {
        this.funds = funds;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDateDonate() {
        return dateDonate;
    }

    public void setDateDonate(LocalDate dateDonate) {
        this.dateDonate = dateDonate;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(SponsorItem o) {
        if (this.sponsoredID.compareTo(o.sponsoredID) < 0) {
            return -1;
        } else if (this.sponsoredID.compareTo(o.sponsoredID) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof SponsorItem) {
            SponsorItem other = (SponsorItem) o;
            if (this.sponsoredID.equalsIgnoreCase(other.getSponsoredID())) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static String[] sponsorItemHeaders() {
        String[] sponsorItemHeaders = {"Sponsored Item ID", "Demand List ID", "Fund ID", "Quantity", "Date Donate", "Date Modified", "Status"};

        return sponsorItemHeaders;
    }

    private String[] strArr() {
        String quan = String.valueOf(quantity);
        return new String[]{sponsoredID, demandList.getDemandListID(), funds.getFundsID(), quan, this.dateDonate.toString(), this.dateModified.toString(), status};
    }

    //change the list and apply toArray method.
    private static String[][] sponsorItemRows(DoublyLinkedList<SponsorItem> sponsorItemList) {
        SponsorItem[] sponsorItem = new SponsorItem[sponsorItemList.getLength()];
        sponsorItem = sponsorItemList.toArray(sponsorItem);
        String[][] sponsorItemRow = new String[sponsorItemList.getLength()][];

        for (int i = 0; i < sponsorItem.length; i++) {
            sponsorItemRow[i] = sponsorItem[i].strArr();
        }
        return sponsorItemRow;
    }

    public static void sponsorItemTable(DoublyLinkedList<SponsorItem> sponsorItemDB) {
        String[] header = SponsorItem.sponsorItemHeaders();
        String[][] sponsorItemData = SponsorItem.sponsorItemRows(sponsorItemDB);

        ASCIITable.getInstance().printTable(header, sponsorItemData);

    }

    public String autoGenerateID() {
        String newSponsoredID = "";
        int seq = 0;
        if (lastSponsoredID.equals("")) {
            newSponsoredID = "SI1001";
        } else {
            seq = Integer.parseInt(lastSponsoredID.substring(2));
            seq += 1;

            newSponsoredID = "SI" + seq;
        }

        lastSponsoredID = newSponsoredID;

        return lastSponsoredID;
    }

    public DoublyLinkedList<SponsorItem> generateDummySponsorItem(RedBlackTree<LocalDate, DemandList> demandListDB, DoublyLinkedList<Funds> fundsDB) {
        DoublyLinkedList<SponsorItem> dummySponsorItem = new DoublyLinkedList<SponsorItem>();
        Faker faker = new Faker();
        LocalDateTimeRangeRandomizer randomLDTR;
        LocalDateTime randomLDT;
        LocalDateTime minTime = LocalDateTime.of(2018, Month.JANUARY, 1, 00, 00, 00);
        LocalDateTime maxTime = LocalDateTime.of(2021, Month.DECEMBER, 31, 23, 59, 59);
        randomLDTR = LocalDateTimeRangeRandomizer.aNewLocalDateTimeRangeRandomizer(minTime, maxTime);

        DoublyLinkedList<DemandList> demandList = (DoublyLinkedList<DemandList>) demandListDB.getAllList();
        DoublyLinkedList<Funds> funds = fundsDB;

        SponsorItem sponsorItem = new SponsorItem();

        for (int data = 1; data <= demandList.getLength(); data++) {

            LocalDate startDate = randomLDTR.getRandomValue().toLocalDate();
            LocalDate endDate = startDate.plusDays(faker.number().numberBetween(1, 9));
            LocalTime startTime = randomLDTR.getRandomValue().toLocalTime();
            LocalTime endTime = startTime.plusHours(faker.number().numberBetween(1, 4));
            LocalDate dateDonate = startDate.minusDays(faker.number().numberBetween(15, 30));
            Timestamp dateModified = new Timestamp(dateDonate.plusDays(faker.number().numberBetween(4, 14)).toEpochDay());

            sponsorItem = new SponsorItem();

            DemandList demandListIndividual = demandList.getAt(data);
            Funds fundsIndividual = funds.getAt(data);

            sponsorItem.setSponsoredID(autoGenerateID());
            sponsorItem.setDemandList(demandListIndividual);
            sponsorItem.setFunds(fundsIndividual);
            //sponsorItem.setSponsor(sponsorIndividual);

            int qtyDeduct = faker.number().numberBetween(1, demandListIndividual.getQuantity());
            demandListIndividual.setQuantity(demandListIndividual.getQuantity() - qtyDeduct);

            sponsorItem.setQuantity(qtyDeduct);
            sponsorItem.setDateDonate(dateDonate);
            sponsorItem.setDateModified(dateModified);
            sponsorItem.setStatus("Active");

            dummySponsorItem.addLast(sponsorItem);

        }
        return dummySponsorItem;
    }

    @Override
    public SponsorItem clone() throws CloneNotSupportedException {
        SponsorItem cloned = (SponsorItem) super.clone();
        return cloned;
    }

    public boolean isInActive() {
        return status.equalsIgnoreCase("Inactive");
    }

}
