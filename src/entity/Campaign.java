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
import java.time.LocalTime;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class Campaign implements Comparable<Campaign> {

    private String campaignID;
    private String campaignName;
    private LocalDate campaignStartDate;
    private LocalTime campaignStartTime;
    private LocalDate campaignEndDate;
    private LocalTime campaignEndTime;
    private double targetAmount;
    private String campaignEmail;
    private String campaignMobileNo;
    private String campaignAddress;
    private String campaignBankNo;
    private String description;
    private String status;
    private Timestamp campaignRegisterDate;
    private Timestamp dateModified;
    private static String lastCampaignID = "";

    public Campaign() {
        this("", "", null, null, null, null, 0.0, "", "", "", "", "", "", null, null);
    }

    public Campaign(String campaignID) {
        this.campaignID = campaignID;
    }

    public Campaign(String campaignID, String campaignName, LocalDate campaignStartDate, LocalTime campaignStartTime, LocalDate campaignEndDate, LocalTime campaignEndTime, double targetAmount, String campaignEmail, String campaignMobileNo, String campagnAddress, String campaignBankNo, String description, String status, Timestamp campaignRegisterDate, Timestamp dateModified) {
        this.campaignID = campaignID;
        this.campaignName = campaignName;
        this.campaignStartDate = campaignStartDate;
        this.campaignStartTime = campaignStartTime;
        this.campaignEndDate = campaignEndDate;
        this.campaignEndTime = campaignEndTime;
        this.targetAmount = targetAmount;
        this.campaignEmail = campaignEmail;
        this.campaignMobileNo = campaignMobileNo;
        this.campaignAddress = campagnAddress;
        this.campaignBankNo = campaignBankNo;
        this.description = description;
        this.status = status;
        this.campaignRegisterDate = campaignRegisterDate;
        this.dateModified = dateModified;
    }

    public String getCampaignAddress() {
        return campaignAddress;
    }

    public void setCampaignAddress(String campaignAddress) {
        this.campaignAddress = campaignAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String getLastCampaignID() {
        return lastCampaignID;
    }

    public String getCampaignID() {
        return campaignID;
    }

    public void setCampaignID(String campaignID) {
        this.campaignID = campaignID;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public LocalDate getCampaignStartDate() {
        return campaignStartDate;
    }

    public void setCampaignStartDate(LocalDate campaignStartDate) {
        this.campaignStartDate = campaignStartDate;
    }

    public LocalTime getCampaignStartTime() {
        return campaignStartTime;
    }

    public void setCampaignStartTime(LocalTime campaignStartTime) {
        this.campaignStartTime = campaignStartTime;
    }

    public LocalDate getCampaignEndDate() {
        return campaignEndDate;
    }

    public void setCampaignEndDate(LocalDate campaignEndDate) {
        this.campaignEndDate = campaignEndDate;
    }

    public LocalTime getCampaignEndTime() {
        return campaignEndTime;
    }

    public void setCampaignEndTime(LocalTime campaignEndTime) {
        this.campaignEndTime = campaignEndTime;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public String getCampaignEmail() {
        return campaignEmail;
    }

    public void setCampaignEmail(String campaignEmail) {
        this.campaignEmail = campaignEmail;
    }

    public String getCampaignMobileNo() {
        return campaignMobileNo;
    }

    public void setCampaignMobileNo(String campaignMobileNo) {
        this.campaignMobileNo = campaignMobileNo;
    }

    public String getCampagnAddress() {
        return campaignAddress;
    }

    public void setCampagnAddress(String campagnAddress) {
        this.campaignAddress = campagnAddress;
    }

    public String getCampaignBankNo() {
        return campaignBankNo;
    }

    public void setCampaignBankNo(String campaignBankNo) {
        this.campaignBankNo = campaignBankNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCampaignRegisterDate() {
        return campaignRegisterDate;
    }

    public void setCampaignRegisterDate(Timestamp campaignRegisterDate) {
        this.campaignRegisterDate = campaignRegisterDate;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public int compareTo(Campaign o) {//ID
        if (this.campaignID.compareTo(o.campaignID) < 0) {
            return -1;
        } else if (this.campaignID.compareTo(o.campaignID) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Campaign) {
            Campaign other = (Campaign) o;
            if (this.campaignID == other.getCampaignID()) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static String[] campaignHeaders() {
        String[] campaignHeaders = {"Campaign ID", "Start Date", "End Date"};

        return campaignHeaders;
    }

    private String[] strArr() {
        return new String[]{campaignID, this.campaignStartDate.toString(), this.campaignEndDate.toString()};
    }

    private static String[][] campaignRows(RedBlackTree<LocalDate, Campaign> campaignList) {
        Campaign[] campaigns = campaignList.getAllArrayList();
        String[][] campaignRows = new String[campaignList.getLength()][];
        for (int i = 0; i < campaigns.length; i++) {
            campaignRows[i] = campaigns[i].strArr();
        }
        return campaignRows;
    }

    public static void campaignTable(RedBlackTree<LocalDate, Campaign> campaignList) {
        String[] header = Campaign.campaignHeaders();
        String[][] cmapaignData = Campaign.campaignRows(campaignList);

        ASCIITable.getInstance().printTable(header, cmapaignData);
    }

    public String autoGenerateID() {
        String newCampaignID = "";
        int seq = 0;
        if (lastCampaignID.equals("")) {
            newCampaignID = "C1001";
        } else {
            seq = Integer.parseInt(lastCampaignID.substring(1));
            seq += 1;

            newCampaignID = "C" + seq;
        }

        lastCampaignID = newCampaignID;

        return lastCampaignID;
    }

}
