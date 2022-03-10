/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.DoublyLinkedList;
import adt.RedBlackTree;
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
    private DoublyLinkedList<Sponsor> sponsorList;
    private DoublyLinkedList<Donee> doneeList;
    private DoublyLinkedList<Donor> donorList;
    private DoublyLinkedList<Donation> donationList;
    private LocalDate campaignStartDate;
    private LocalTime campaignStartTime;
    private LocalDate campaignEndDate;
    private LocalTime campaignEndTime;
    private double targetAmount;
    private String campaignEmail;
    private String campaignMobileNo;
    private String campagnAddress;
    private String campaignBankNo;
    private String description;
    private Timestamp campaignRegisterDate;
    private Timestamp dateModified;
    private static String lastCampaignID = "";

    public Campaign() {
        this("", "", null, null, null, null, null, null, null, null, 0.0, "", "", "", "", "", null, null);
    }

    public Campaign(String campaignID, String campaignName, DoublyLinkedList<Sponsor> sponsorList, DoublyLinkedList<Donee> doneeList, DoublyLinkedList<Donor> donorList, DoublyLinkedList<Donation> donationList, LocalDate campaignStartDate, LocalTime campaignStartTime, LocalDate campaignEndDate, LocalTime campaignEndTime, double targetAmount, String campaignEmail, String campaignMobileNo, String campagnAddress, String campaignBankNo, String description, Timestamp campaignRegisterDate, Timestamp dateModified) {
        this.campaignID = campaignID;
        this.campaignName = campaignName;
        this.sponsorList = sponsorList;
        this.doneeList = doneeList;
        this.donorList = donorList;
        this.donationList = donationList;
        this.campaignStartDate = campaignStartDate;
        this.campaignStartTime = campaignStartTime;
        this.campaignEndDate = campaignEndDate;
        this.campaignEndTime = campaignEndTime;
        this.targetAmount = targetAmount;
        this.campaignEmail = campaignEmail;
        this.campaignMobileNo = campaignMobileNo;
        this.campagnAddress = campagnAddress;
        this.campaignBankNo = campaignBankNo;
        this.description = description;
        this.campaignRegisterDate = campaignRegisterDate;
        this.dateModified = dateModified;
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

    public DoublyLinkedList<Sponsor> getSponsorList() {
        return sponsorList;
    }

    public void setSponsorList(DoublyLinkedList<Sponsor> sponsorList) {
        this.sponsorList = sponsorList;
    }

    public DoublyLinkedList<Donee> getDoneeList() {
        return doneeList;
    }

    public void setDoneeList(DoublyLinkedList<Donee> doneeList) {
        this.doneeList = doneeList;
    }

    public DoublyLinkedList<Donor> getDonorList() {
        return donorList;
    }

    public void setDonorList(DoublyLinkedList<Donor> donorList) {
        this.donorList = donorList;
    }

    public DoublyLinkedList<Donation> getDonationList() {
        return donationList;
    }

    public void setDonationList(DoublyLinkedList<Donation> donationList) {
        this.donationList = donationList;
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
        return campagnAddress;
    }

    public void setCampagnAddress(String campagnAddress) {
        this.campagnAddress = campagnAddress;
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
    public int compareTo(Campaign o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static String[] campaignHeaders() {
        String[] campaignHeaders = {"Campaign ID", "Start Date", "End Date"};

        return campaignHeaders;
    }

    private String[] strArr() {
        return new String[]{campaignID, this.campaignStartDate.toString(), this.campaignEndDate.toString()};
    }

    private static String[][] campaignRows(RedBlackTree<LocalDate, Campaign> campaignList) {
        Campaign[] campaigns = campaignList.getAllList();
        String[][] campaignRows = new String[campaignList.getLength()][];
        for (int i = 0; i < campaigns.length; i++) {
            campaignRows[i] = campaigns[i].strArr();
        }
        return campaignRows;
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
