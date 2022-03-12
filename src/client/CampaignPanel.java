/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.DoublyLinkedList;
import adt.RedBlackTree;
import entity.Campaign;
import entity.DemandList;
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

    public void controlPanel(RedBlackTree<LocalDate, Campaign> campaignDB,
            DoublyLinkedList<Sponsor> sponsorDB,
            RedBlackTree<LocalDate, SponsorList> sponsorListDB,
            DoublyLinkedList<Donee> doneeDB, RedBlackTree<LocalDate, DoneeList> doneeListDB, DoublyLinkedList<Donor> donorDB,
            RedBlackTree<LocalDate, DonorList> donorListDB,
            RedBlackTree<LocalDate, DemandList> demandListDB) {

        Scanner input = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println(menu());
            System.out.println("Option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    add(campaignDB, sponsorDB, sponsorListDB, doneeDB, doneeListDB, donorDB, donorListDB);
                    break;
                case 2:
                    Campaign.campaignTable(campaignDB);
                    break;
                case 3:
                    search();
                    break;
                case 4:
                    delete(campaignDB);
                    break;
                case 5:
                    update(campaignDB);
                    break;
                case 6:
                    supportListPanel(campaignDB, demandListDB);
                    break;
                case 7:
                    System.out.println("Return to previous Page...");
                    break;
                default:
                    System.out.println("Index not correct...");
            }

        } while (option != 7);
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
        menu.append("6. Campaign support list maintenance");
        menu.append("7. Exit \n");

        return menu.toString();
    }

    public String supportListMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("1. Sponsor List \n");
        menu.append("2. Donee List \n");
        menu.append("3. Donor List \n");
        menu.append("4. Demand List \n");
        menu.append("5. Exit \n");
        return menu.toString();
    }

    public void supportListPanel(RedBlackTree<LocalDate, Campaign> campaignDB, RedBlackTree<LocalDate, DemandList> demandListDB) {
        Scanner input = new Scanner(System.in);
        int option = 0;

        SponsorListPanel sponsorListPanel = new SponsorListPanel();
        DoneeListPanel doneeListPanel = new DoneeListPanel();
        DonorListPanel donorListPanel = new DonorListPanel();
        DemandListPanel demandListPanel = new DemandListPanel();

        do {
            System.out.println(menu());
            option = input.nextInt();

            switch (option) {
                case 1:
                    sponsorListPanel.controlPanel();
                    break;
                case 2:
                    doneeListPanel.controlPanel();
                    break;
                case 3:
                    donorListPanel.controlPanel();
                    break;
                case 4:
                    demandListPanel.controlPanel(campaignDB, demandListDB);
                    break;
                case 5:
                    System.out.println("Return to previous Page...");
                    break;
                default:
                    System.out.println("Index not correct...");
            }

        } while (option != 4);
    }

    public void add(RedBlackTree<LocalDate, Campaign> campaignDB, DoublyLinkedList<Sponsor> sponsorDB, RedBlackTree<LocalDate, SponsorList> sponsorListDB, DoublyLinkedList<Donee> doneeDB, RedBlackTree<LocalDate, DoneeList> doneeListDB, DoublyLinkedList<Donor> donorDB, RedBlackTree<LocalDate, DonorList> donorListDB) {
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
            System.out.print("Enter the campaign register date: ");
            campaign.setCampaignRegisterDate(LocalDate.parse(input.nextLine(), dtfDate));
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
                campaignDB.addData(campaign.getCampaignStartDate(), campaign);
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

    public void update(RedBlackTree<LocalDate, Campaign> campaignDB) {
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
            Campaign.campaignTable(campaignDB);

            System.out.println("Enter campaign ID: ");
            campaignID = input.nextLine();

            if (campaignDB.getAllList().contains(new Campaign(campaignID)) == true) {
                DoublyLinkedList<Campaign> campaigns = campaignDB.getAllList();
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
                        boolean hasUpdateSomething = false;
                        for (int i = 0; i < splitIndexInt.length; i++) {
                            switch (splitIndexInt[i]) {
                                case 1:
                                    System.out.print("Enter the new campaign name: ");
                                    campaign.setCampaignName(input.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 2:
                                    System.out.print("Enter the new campaign start date [dd. MMM. yyyy]: ");
                                    campaign.setCampaignStartDate(LocalDate.parse(input.nextLine(), dtfDate));
                                    hasUpdateSomething = true;
                                    break;
                                case 3:
                                    System.out.print("Enter the new campaign start time [H:mm:ss]: ");
                                    campaign.setCampaignStartTime(LocalTime.parse(input.nextLine(), dtfTime));
                                    hasUpdateSomething = true;
                                    break;
                                case 4:
                                    System.out.print("Enter the new campaign end date [dd. MMM. yyyy]: ");
                                    campaign.setCampaignEndDate(LocalDate.parse(input.nextLine(), dtfDate));
                                    hasUpdateSomething = true;
                                    break;
                                case 5:
                                    System.out.print("Enter the new campaign end time [H:mm:ss]: ");
                                    campaign.setCampaignEndTime(LocalTime.parse(input.nextLine(), dtfTime));
                                    hasUpdateSomething = true;
                                    break;
                                case 6:
                                    System.out.print("Enter the new target amount: ");
                                    campaign.setTargetAmount(input.nextDouble());
                                    hasUpdateSomething = true;
                                    break;
                                case 7:
                                    System.out.print("Enter the new campaign email: ");
                                    campaign.setCampaignEmail(input.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 8:
                                    System.out.print("Enter the new campaign mobile no: ");
                                    campaign.setCampaignMobileNo(input.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 9:

                                    System.out.print("Enter the new campaign campaign address: ");
                                    campaign.setCampagnAddress(input.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 10:
                                    System.out.print("Enter the new campaign bank no: ");
                                    campaign.setCampaignBankNo(input.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 11:
                                    System.out.print("Enter the new campaign description: ");
                                    campaign.setDescription(input.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                default:
                                    System.out.println("Index " + splitIndexInt[i] + "out of bound!");
                            }
                        }

                        if (hasUpdateSomething == true) {
                            System.out.println("Confirm update campaign ? (Y/N)");
                            confirmation = input.nextLine();

                            if (confirmation.toUpperCase().equals("Y")) {
                                campaign.setDateModified(new Timestamp(System.currentTimeMillis()));
                                if (oriStartDate != campaign.getCampaignStartDate()) {
                                    campaignDB.delData(oriStartDate, campaign);
                                    campaignDB.addData(campaign.getCampaignStartDate(), campaign);
                                } else {
                                    campaignDB.updateData(campaign.getCampaignStartDate(), campaign);
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

    public void delete(RedBlackTree<LocalDate, Campaign> campaignDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String campaignID = "";

        do {
            Campaign.campaignTable(campaignDB);

            System.out.println("Enter campaign ID: ");
            campaignID = input.nextLine();
            DoublyLinkedList<Campaign> campaigns = campaignDB.getAllList();
            if (campaigns.contains(new Campaign(campaignID)) == true) {
                System.out.println("Confirm deactive campaign ? (Y/N)");
                confirmation = input.nextLine();

                if (confirmation.toUpperCase().equals("Y")) {
                    Campaign campaign = campaigns.getAt(campaigns.indexOf(new Campaign(campaignID)));
                    campaign.setStatus("Inactive");
                    campaign.setDateModified(new Timestamp(System.currentTimeMillis()));
                    campaignDB.updateData(campaign.getCampaignStartDate(), campaign);
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
