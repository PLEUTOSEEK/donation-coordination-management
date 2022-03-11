/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.RedBlackTree;
import com.bethecoder.ascii_table.ASCIITable;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class DemandList implements Comparable<DemandList> {

    private String demandListID;
    private Campaign campaign;
    private String demandName;
    private int quantity;
    private double pricePerUnit;
    private String description;
    private int orgiQty;
    private LocalDate dateRegister;
    private Timestamp dateModified;
    private static String lastDemandID;

    public DemandList() {
    }

    public DemandList(String demandListID) {
        this.demandListID = demandListID;
    }

    public String getDemandListID() {
        return demandListID;
    }

    public void setDemandListID(String demandListID) {
        this.demandListID = demandListID;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrgiQty() {
        return orgiQty;
    }

    public void setOrgiQty(int orgiQty) {
        this.orgiQty = orgiQty;
    }

    public LocalDate getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDate dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public int compareTo(DemandList o) {//ID
        if (this.demandListID.compareTo(o.demandListID) < 0) {
            return -1;
        } else if (this.demandListID.compareTo(o.demandListID) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof DemandList) {
            DemandList other = (DemandList) o;
            if (this.demandListID == other.getDemandListID()) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static String[] demandHeaders() {
        String[] campaignHeaders = {"Demand List ID", "Demand Name", "Quantity"};

        return campaignHeaders;
    }

    private String[] strArr() {
        return new String[]{demandListID, demandName, String.valueOf(quantity)};
    }

    private static String[][] demandRows(RedBlackTree<LocalDate, DemandList> demandListDB) {
        DemandList[] demandLists = demandListDB.getAllArrayList();
        String[][] demandRows = new String[demandLists.length][];
        for (int i = 0; i < demandLists.length; i++) {
            demandRows[i] = demandLists[i].strArr();
        }
        return demandRows;
    }

    public static void demandTable(RedBlackTree<LocalDate, DemandList> demandListDB) {
        String[] demandHeader = DemandList.demandHeaders();
        String[][] demandData = DemandList.demandRows(demandListDB);

        ASCIITable.getInstance().printTable(demandHeader, demandData);
    }

    public String autoGenerateID() {
        String newCampaignID = "";
        int seq = 0;
        if (lastDemandID.equals("")) {
            newCampaignID = "DM1001";
        } else {
            seq = Integer.parseInt(lastDemandID.substring(2));
            seq += 1;

            newCampaignID = "DM" + seq;
        }

        lastDemandID = newCampaignID;

        return lastDemandID;
    }

}
