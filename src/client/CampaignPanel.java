/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.DoublyLinkedList;
import adt.RedBlackTree;
import entity.Campaign;
import entity.Donee;
import entity.DoneeList;
import entity.Donor;
import entity.DonorList;
import entity.Sponsor;
import entity.SponsorList;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class CampaignPanel implements Panel {

    public void controlPanel(RedBlackTree<LocalDate, Campaign> campaignList, DoublyLinkedList<Sponsor> sponsorDB, RedBlackTree<LocalDate, SponsorList> sponsorListDB, DoublyLinkedList<Donee> doneeDB, RedBlackTree<LocalDate, DoneeList> doneeListDB, DoublyLinkedList<Donor> donorDB, RedBlackTree<LocalDate, DonorList> donorListDB) {
        Scanner input = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println(menu());
            option = input.nextInt();

            switch (option) {
                case 1:
                    add(campaignList, sponsorDB, sponsorListDB, doneeDB, doneeListDB, donorDB, donorListDB);
                    break;
                case 2:
                    Campaign.campaignTable(campaignList);
                    break;
                case 3:
                    search();
                    break;
                case 4:
                    delete(campaignList);
                    break;
                case 5:
                    update(campaignList);
                    break;
                case 6:
                    System.out.println("Return to previous Page...");
                    break;
            }

        } while (option != 6);
    }

    @Override
    public void controlPanel() {

    }

    @Override
    public String menu() {
        StringBuilder menu = new StringBuilder();

        menu.append("1. Add new campaign \n");
        menu.append("2. Display campaign \n");
        menu.append("3. Search campaign \n");
        menu.append("4. Deactive campaign \n");
        menu.append("5. Update campaign \n");
        menu.append("6. Exit \n");

        return menu.toString();
    }

    public void add(RedBlackTree<LocalDate, Campaign> campaignList, DoublyLinkedList<Sponsor> sponsorDB, RedBlackTree<LocalDate, SponsorList> sponsorListDB, DoublyLinkedList<Donee> doneeDB, RedBlackTree<LocalDate, DoneeList> doneeListDB, DoublyLinkedList<Donor> donorDB, RedBlackTree<LocalDate, DonorList> donorListDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        Campaign campaign = new Campaign();
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("H:mm:ss");

        do {
            // ID need to auto generate

            System.out.print("Enter the campaign name: ");
            campaign.setCampaignName(input.nextLine());
            System.out.print("Enter the campaign start date [dd. MMM. yyyy]: ");
            campaign.setCampaignStartDate(LocalDate.parse(input.nextLine(), dtfDate));
            System.out.print("Enter the campaign start time [H:mm:ss]: ");
            campaign.setCampaignStartTime(LocalTime.parse(input.nextLine(), dtfTime));
            System.out.print("Enter the campaign end date [dd. MMM. yyyy]: ");
            campaign.setCampaignEndDate(LocalDate.parse(input.nextLine(), dtfDate));
            System.out.print("Enter the campaign end time [H:mm:ss]: ");
            campaign.setCampaignEndTime(LocalTime.parse(input.nextLine(), dtfTime));
            System.out.print("Enter the target amount: ");
            campaign.setTargetAmount(input.nextDouble());
            System.out.print("Enter the campaign email: ");
            campaign.setCampaignEmail(input.nextLine());
            System.out.print("Enter the campaign mobile no: ");
            campaign.setCampaignMobileNo(input.nextLine());
            System.out.print("Enter the campaign campaign address: ");
            campaign.setCampagnAddress(input.nextLine());
            System.out.print("Enter the campaign bank no: ");
            campaign.setCampaignBankNo(input.nextLine());
            System.out.print("Enter the campaign description: ");
            campaign.setDescription(input.nextLine());
            campaign.setCampaignRegisterDate(new Timestamp(System.currentTimeMillis()));
            campaign.setDateModified(new Timestamp(System.currentTimeMillis()));
            campaign.setStatus("Active");
            //

            addSponsor(campaign, sponsorDB, sponsorListDB);
            addDonee(campaign, doneeDB, doneeListDB);
            addDonor(campaign, donorDB, donorListDB);

            campaign.setCampaignID(campaign.autoGenerateID());

            System.out.println("Confirm add campaign ? (Y/N)");
            confirmation = input.nextLine();

            if (confirmation.toUpperCase().equals("Y")) {
                campaignList.addData(campaign.getCampaignStartDate(), campaign);
            }

            System.out.println(confirmation.toUpperCase().equals("Y") ? "Added campaign successfully" : "Add campaign abort");

            System.out.println("Continue add campaign ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "Return to previous page..." : "");

        } while (option.toUpperCase().equals("Y"));
    }

    public void add(Campaign campaign) {

    }

    public void addSponsor(Campaign campaign, DoublyLinkedList<Sponsor> sponsorDB, RedBlackTree<LocalDate, SponsorList> sponsorListDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String sponsorID = "";
        SponsorList sponsorList = new SponsorList();
        DoublyLinkedList<SponsorList> sponsorListList = new DoublyLinkedList<>();
        boolean haveRecord = false;

        do {

            Sponsor.sponsorTable(sponsorDB);

            System.out.println("Enter sponsor ID: ");
            sponsorID = input.nextLine();

            if (sponsorDB.contains(new Sponsor(sponsorID)) == true) {

                System.out.println("Confirm add sponsor ? (Y/N)");
                confirmation = input.nextLine();
                sponsorList.setSponsor(sponsorDB.getAt(sponsorDB.indexOf(new Sponsor(sponsorID))));
                sponsorList.setCampaign(campaign);
                sponsorList.setDateJoin(LocalDate.now());
                sponsorList.setDateModified(new Timestamp(System.currentTimeMillis()));
                sponsorList.setSponsorListID(sponsorList.autoGenerateID());

                if (confirmation.toUpperCase().equals("Y")) {
                    sponsorListDB.addData(sponsorList.getDateJoin(), sponsorList);
                    haveRecord = true;
                }

                System.out.println(confirmation.toUpperCase().equals("Y") ? "Added sponsor successfully" : "Add sponsor abort");
            } else {
                System.out.println("Sponsor ID not found, add sponsor abort");
            }

            System.out.println("Continue add sponsor to this campaign ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");

            if (haveRecord == false) {
                System.out.println("At least one sponsor needed for a campaign...");
                option = "N";
            }

        } while (option.toUpperCase().equals("Y"));

    }

    public void addDonee(Campaign campaign, DoublyLinkedList<Donee> doneeDB, RedBlackTree<LocalDate, DoneeList> doneeListDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String doneeID = "";
        DoneeList doneeList = new DoneeList();
        boolean haveRecord = false;
        do {

            Donee.doneeTable(doneeDB);

            System.out.println("Enter donee ID: ");
            doneeID = input.nextLine();

            if (doneeDB.contains(new Donee(doneeID)) == true) {
                System.out.println("Confirm add donee ? (Y/N)");
                confirmation = input.nextLine();

                doneeList.setDonee(doneeDB.getAt(doneeDB.indexOf(new Donee(doneeID))));
                doneeList.setCampaign(campaign);
                doneeList.setDateJoin(LocalDate.now());
                doneeList.setDateModified(new Timestamp(System.currentTimeMillis()));
                doneeList.setDoneeListID(doneeList.autoGenerateID());

                if (confirmation.toUpperCase().equals("Y")) {
                    doneeListDB.addData(doneeList.getDateJoin(), doneeList);
                    haveRecord = true;
                }

                System.out.println(confirmation.toUpperCase().equals("Y") ? "Added donee successfully" : "Add donee abort");
            } else {
                System.out.println("Donee ID not found, add sponsor abort");
            }

            System.out.println("Continue add donee to this campaign ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");

            if (haveRecord == false) {
                System.out.println("At least one donee needed for a campaign...");
                option = "N";
            }

        } while (option.toUpperCase().equals("Y"));

    }

    public void addDonor(Campaign campaign, DoublyLinkedList<Donor> donorDB, RedBlackTree<LocalDate, DonorList> donorListDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String donorID = "";
        DonorList donorList = new DonorList();
        boolean haveRecord = false;
        do {

            Donor.donorTable(donorDB);

            System.out.println("Enter donor ID: ");
            donorID = input.nextLine();

            if (donorDB.contains(new Donor(donorID)) == true) {
                System.out.println("Confirm add donor ? (Y/N)");
                confirmation = input.nextLine();
                donorList.setDonor(donorDB.getAt(donorDB.indexOf(new Donor(donorID))));
                donorList.setCampaign(campaign);
                donorList.setDateJoin(LocalDate.now());
                donorList.setDateModified(new Timestamp(System.currentTimeMillis()));
                donorList.setDonorListID(donorList.autoGenerateID());

                if (confirmation.toUpperCase().equals("Y")) {
                    donorListDB.addData(donorList.getDateJoin(), donorList);
                    haveRecord = true;
                }

                System.out.println(confirmation.toUpperCase().equals("Y") ? "Added donor successfully" : "Add donor abort");
            } else {
                System.out.println("Donor ID not found, add donor abort");
            }

            System.out.println("Continue add donor to this campaign ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");

            if (haveRecord == false) {
                System.out.println("At least one donor needed for a campaign...");
                option = "N";
            }
        } while (option.toUpperCase().equals("Y"));
    }

    public String campaignUpdateMenu() {
        StringBuilder menu = new StringBuilder();

        menu.append("1. Campaign Name\n");
        menu.append("2. Campaign Start Date\n");
        menu.append("3. Campaign Start Time\n");
        menu.append("4. Campaign End Date\n");
        menu.append("5. Campaign End Time\n");
        menu.append("6. Target Amount\n");
        menu.append("7. Campaign Email\n");
        menu.append("8. Campaign Mobile No\n");
        menu.append("9. Campaign Address\n");
        menu.append("10. Campaign Bank No\n");
        menu.append("11. Campaign Description\n");

        return menu.toString();

    }

    public void update(RedBlackTree<LocalDate, Campaign> campaignList) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String campaignID = "";
        String indexSelected = "";
        Campaign campaign = new Campaign();
        LocalDate oriStartDate = null;
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("H:mm:ss");

        do {
            Campaign.campaignTable(campaignList);

            System.out.println("Enter campaign ID: ");
            campaignID = input.nextLine();

            if (campaignList.getAllList().contains(new Campaign(campaignID)) == true) {
                DoublyLinkedList<Campaign> campaigns = campaignList.getAllList();
                campaign = campaigns.getAt(campaigns.indexOf(new Campaign(campaignID)));
                oriStartDate = campaign.getCampaignStartDate();
                boolean validIndex = true;
                do {
                    System.out.println(campaignUpdateMenu());
                    validIndex = true;
                    System.out.println("Enter index of option that want to update, if multiple index leave space at between [1 5 6]: ");
                    indexSelected = input.nextLine();

                    String[] splitIndex = indexSelected.split("\\s+");
                    int[] splitIndexInt = new int[splitIndex.length];

                    for (int i = 0; i < splitIndex.length; i++) {
                        try {
                            splitIndexInt[i] = Integer.valueOf(splitIndex[i]);
                        } catch (Exception e) {
                            validIndex = false;
                            break;
                        }
                    }

                    if (validIndex == true) {
                        for (int i = 0; i < splitIndexInt.length; i++) {
                            switch (splitIndexInt[i]) {
                                case 1:
                                    System.out.print("Enter the new campaign name: ");
                                    campaign.setCampaignName(input.nextLine());
                                    break;
                                case 2:
                                    System.out.print("Enter the new campaign start date [dd. MMM. yyyy]: ");
                                    campaign.setCampaignStartDate(LocalDate.parse(input.nextLine(), dtfDate));
                                    break;
                                case 3:
                                    System.out.print("Enter the new campaign start time [H:mm:ss]: ");
                                    campaign.setCampaignStartTime(LocalTime.parse(input.nextLine(), dtfTime));
                                    break;
                                case 4:
                                    System.out.print("Enter the new campaign end date [dd. MMM. yyyy]: ");
                                    campaign.setCampaignEndDate(LocalDate.parse(input.nextLine(), dtfDate));
                                    break;
                                case 5:
                                    System.out.print("Enter the new campaign end time [H:mm:ss]: ");
                                    campaign.setCampaignEndTime(LocalTime.parse(input.nextLine(), dtfTime));
                                    break;
                                case 6:
                                    System.out.print("Enter the new target amount: ");
                                    campaign.setTargetAmount(input.nextDouble());
                                    break;
                                case 7:
                                    System.out.print("Enter the new campaign email: ");
                                    campaign.setCampaignEmail(input.nextLine());
                                    break;
                                case 8:
                                    System.out.print("Enter the new campaign mobile no: ");
                                    campaign.setCampaignMobileNo(input.nextLine());
                                    break;
                                case 9:

                                    System.out.print("Enter the new campaign campaign address: ");
                                    campaign.setCampagnAddress(input.nextLine());
                                    break;
                                case 10:
                                    System.out.print("Enter the new campaign bank no: ");
                                    campaign.setCampaignBankNo(input.nextLine());
                                    break;
                                case 11:
                                    System.out.print("Enter the new campaign description: ");
                                    campaign.setDescription(input.nextLine());
                                    break;
                                default:
                                    System.out.println("Index " + splitIndexInt[i] + "out of bound!");
                            }
                        }

                        if (splitIndexInt.length != 0) {
                            System.out.println("Confirm update campaign ? (Y/N)");
                            confirmation = input.nextLine();

                            if (confirmation.toUpperCase().equals("Y")) {
                                campaign.setDateModified(new Timestamp(System.currentTimeMillis()));
                                if (oriStartDate != campaign.getCampaignStartDate()) {
                                    campaignList.delData(oriStartDate, campaign);
                                    campaignList.addData(campaign.getCampaignStartDate(), campaign);
                                } else {
                                    campaignList.updateData(campaign.getCampaignStartDate(), campaign);
                                }
                            }

                            System.out.println(confirmation.toUpperCase().equals("Y") ? "Update campaign successfully" : "Update campaign abort");
                        } else {
                            System.out.println("No data selected to be update...");
                        }
                    }

                } while (validIndex == false);
            } else {
                System.out.println("Campaign ID not found, update campaign abort");
            }

            System.out.println("Continue update campaign ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");
        } while (option.toUpperCase().equals("Y"));
    }

    public void delete(RedBlackTree<LocalDate, Campaign> campaignList) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String campaignID = "";

        do {
            Campaign.campaignTable(campaignList);

            System.out.println("Enter campaign ID: ");
            campaignID = input.nextLine();
            DoublyLinkedList<Campaign> campaigns = campaignList.getAllList();
            if (campaigns.contains(new Campaign(campaignID)) == true) {
                System.out.println("Confirm deactive campaign ? (Y/N)");
                confirmation = input.nextLine();

                if (confirmation.toUpperCase().equals("Y")) {
                    Campaign campaign = campaigns.getAt(campaigns.indexOf(new Campaign(campaignID)));
                    campaign.setStatus("Inactive");
                    campaign.setDateModified(new Timestamp(System.currentTimeMillis()));
                    campaignList.updateData(campaign.getCampaignStartDate(), campaign);
                }
            } else {
                System.out.println("Campaign ID not found, update campaign abort");
            }
            System.out.println("Continue deactive campaign ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");
        } while (option.toUpperCase().equals("Y"));

    }

    @Override
    public void search() {
    }

    @Override
    public void exit() {
    }

    public void display(DoublyLinkedList<Campaign> sponsorList) {

    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
