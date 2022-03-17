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
 * @author Angelina Oon
 */
public class Funds implements Comparable<Funds> {
    
    private String fundsID;
    private SponsorItem sponsorItem;
    private Sponsor sponsor;
    private Double totalAmount;
    private Double originalTotalAmount;
    private LocalDate datePay;
    private Timestamp dateModified;
    private String status;
    private static String lastFundsID = "";
    
    public Funds() {
    }
    
    public Funds(String fundsID, SponsorItem sponsorItem, Sponsor sponsor, Double totalAmount, Double originalTotalAmount, LocalDate datePay, Timestamp dateModified, String status) {
        this.fundsID = fundsID;
        this.sponsorItem = sponsorItem;
        this.sponsor = sponsor;
        this.totalAmount = totalAmount;
        this.originalTotalAmount = originalTotalAmount;
        this.datePay = datePay;
        this.dateModified = dateModified;
        this.status = status;
    }
    
    public String getFundsID() {
        return fundsID;
    }
    
    public void setFundsID(String fundsID) {
        this.fundsID = fundsID;
    }
    
    public SponsorItem getSponsorItem() {
        return sponsorItem;
    }
    
    public void setSponsorItem(SponsorItem sponsorItem) {
        this.sponsorItem = sponsorItem;
    }
    
    public Sponsor getSponsor() {
        return sponsor;
    }
    
    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }
    
    public Double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public Double getOriginalTotalAmount() {
        return originalTotalAmount;
    }
    
    public void setOriginalTotalAmount(Double originalTotalAmount) {
        this.originalTotalAmount = originalTotalAmount;
    }
    
    public LocalDate getDatePay() {
        return datePay;
    }
    
    public void setDatePay(LocalDate datePay) {
        this.datePay = datePay;
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
    
    public static String getLastFundsID() {
        return lastFundsID;
    }
    
    public static void setLastFundsID(String lastFundsID) {
        Funds.lastFundsID = lastFundsID;
    }
    
    @Override
    public int compareTo(Funds o) {//ID
        if (this.fundsID.compareTo(o.fundsID) < 0) {
            return -1;
        } else if (this.fundsID.compareTo(o.fundsID) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
    
    @Override
    public boolean equals(Object o) {
        
        if (o instanceof Funds) {
            Funds other = (Funds) o;
            if (this.fundsID.equalsIgnoreCase(other.getFundsID())) {
                return true;
            } else {
                return false;
            }
        }
        
        return false;
    }
    
    private static String[] fundsHeaders() {
        String[] fundsHeaders = {"Fund ID", "Sponsor Item ID", "Sponsor ID", "Total Amount", "Original Total Amount", "Date Pay", "Date Modified", "Status"};
        
        return fundsHeaders;
    }
    
    private String[] strArr() {
        String total = String.valueOf(totalAmount);
        String oriTotal = String.valueOf(originalTotalAmount);
        
        return new String[]{fundsID, sponsorItem.getSponsoredID(), sponsor.getAccountID(), total, oriTotal, this.datePay.toString(), this.dateModified.toString(), status};
    }
    
    private static String[][] fundsRows(DoublyLinkedList<Funds> fundsList) {
        Funds[] funds = new Funds[fundsList.getLength()];
        funds = fundsList.toArray(funds);
        String[][] fundsRows = new String[fundsList.getLength()][];
        for (int i = 0; i < funds.length; i++) {
            fundsRows[i] = funds[i].strArr();
        }
        return fundsRows;
    }
    
    public static void sponsorTable(DoublyLinkedList<Funds> fundsList) {
        String[] header = Funds.fundsHeaders();
        String[][] fundsData = Funds.fundsRows(fundsList);
        
        ASCIITable.getInstance().printTable(header, fundsData);
    }
    
    public String autoGenerateID() {
        String newFundsID = "";
        int seq = 0;
        if (lastFundsID.equals("")) {
            newFundsID = "F1001";
        } else {
            seq = Integer.parseInt(lastFundsID.substring(1));
            seq += 1;
            
            newFundsID = "F" + seq;
        }
        
        lastFundsID = newFundsID;
        
        return lastFundsID;
    }
    
    public DoublyLinkedList<Funds> generateDummyFunds(RedBlackTree<LocalDate, DemandList> demandListDB) {
        DoublyLinkedList<Funds> dummyFunds = new DoublyLinkedList<Funds>();
        Faker faker = new Faker();
        LocalDateTimeRangeRandomizer randomLDTR;
        LocalDateTime randomLDT;
        LocalDateTime minTime = LocalDateTime.of(2018, Month.JANUARY, 1, 00, 00, 00);
        LocalDateTime maxTime = LocalDateTime.of(2021, Month.DECEMBER, 31, 23, 59, 59);
        randomLDTR = LocalDateTimeRangeRandomizer.aNewLocalDateTimeRangeRandomizer(minTime, maxTime);

        //DoublyLinkedList<SponsorItem> sponsorItem = sponsorItemList.getAllList();
        Funds funds = new Funds();
        
        for (int record = 1; record <= 100; record++) {
            LocalDate startDate = randomLDTR.getRandomValue().toLocalDate();
            LocalDate endDate = startDate.plusDays(faker.number().numberBetween(1, 9));
            LocalTime startTime = randomLDTR.getRandomValue().toLocalTime();
            LocalTime endTime = startTime.plusHours(faker.number().numberBetween(1, 4));
            LocalDate datePay = startDate.minusDays(faker.number().numberBetween(15, 30));
            Timestamp dateModified = new Timestamp(datePay.plusDays(faker.number().numberBetween(4, 14)).toEpochDay());
            
            funds = new Funds();
            //DemandList demandListIndividual = demandList.getAt(record);
            //funds.setSponsorItem(demandListIndividual);
            funds.setFundsID(autoGenerateID());
            funds.setTotalAmount(faker.number().randomDouble(2, 1000, 10000));
            funds.setOriginalTotalAmount(faker.number().randomDouble(2, 1000, 10000));
            funds.setDatePay(datePay);
            funds.setDateModified(dateModified);
            funds.setStatus("Active");
            
            dummyFunds.addLast(funds);
            
        }
        
        return dummyFunds;
        
    }
    
    public boolean isInActive() {
        return status.equalsIgnoreCase("Inactive");
    }
    
}
