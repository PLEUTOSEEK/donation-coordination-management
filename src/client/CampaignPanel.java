/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.CircularLinkedQueue;
import adt.DoublyLinkedList;
import adt.RedBlackTree;
import adt.SinglyLinkedList;
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
import utils.CampaignPredicates;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class CampaignPanel implements Panel {

    public void controlPanel(RedBlackTree<LocalDate, Campaign> campaignDB,
            DoublyLinkedList<Sponsor> sponsorDB,
            RedBlackTree<LocalDate, SponsorList> sponsorListDB,
            CircularLinkedQueue<Donee> doneeDB,
            DoublyLinkedList<Donee> doneeInHelpDB,
            RedBlackTree<LocalDate, DoneeList> doneeListDB,
            SinglyLinkedList<Donor> donorDB,
            RedBlackTree<LocalDate, DonorList> donorListDB,
            RedBlackTree<LocalDate, DemandList> demandListDB) throws CloneNotSupportedException {

        Scanner input = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println(menu());
            System.out.print("Option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    add(campaignDB, sponsorDB, sponsorListDB, doneeDB, doneeInHelpDB, doneeListDB, donorDB, donorListDB);
                    break;
                case 2:
                    Campaign.campaignTable(campaignDB);
                    break;
                case 3:
                    search(campaignDB);
                    break;
                case 4:
                    delete(campaignDB, doneeDB, doneeInHelpDB, doneeListDB);
                    break;
                case 5:
                    update(campaignDB);
                    break;
                case 6:
                    supportListPanel(campaignDB, sponsorDB, sponsorListDB, doneeDB, doneeInHelpDB, doneeListDB, donorDB, donorListDB, demandListDB);
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
        System.out.println();
        menu.append("1. Add new campaign \n");
        menu.append("2. Display campaign \n");
        menu.append("3. Search campaign \n");
        menu.append("4. Deactive campaign \n");
        menu.append("5. Update campaign \n");
        menu.append("6. Campaign support list maintenance \n");
        menu.append("7. Exit \n");
        return menu.toString();
    }

    public String supportListMenu() {
        StringBuilder menu = new StringBuilder();
        System.out.println();
        menu.append("1. Sponsor List \n");
        menu.append("2. Donee List \n");
        menu.append("3. Donor List \n");
        menu.append("4. Demand List \n");
        menu.append("5. Exit \n");
        return menu.toString();
    }

    public void supportListPanel(RedBlackTree<LocalDate, Campaign> campaignDB,
            DoublyLinkedList<Sponsor> sponsorDB,
            RedBlackTree<LocalDate, SponsorList> sponsorListDB,
            CircularLinkedQueue<Donee> doneeDB,
            DoublyLinkedList<Donee> doneeInHelpDB,
            RedBlackTree<LocalDate, DoneeList> doneeListDB,
            SinglyLinkedList<Donor> donorDB,
            RedBlackTree<LocalDate, DonorList> donorListDB,
            RedBlackTree<LocalDate, DemandList> demandListDB) throws CloneNotSupportedException {

        Scanner input = new Scanner(System.in);
        int option = 0;

        SponsorListPanel sponsorListPanel = new SponsorListPanel();
        DoneeListPanel doneeListPanel = new DoneeListPanel();
        DonorListPanel donorListPanel = new DonorListPanel();
        DemandListPanel demandListPanel = new DemandListPanel();

        do {
            System.out.println(supportListMenu());
            System.out.print("Option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    sponsorListPanel.controlPanel(campaignDB, sponsorDB, sponsorListDB);
                    break;
                case 2:
                    doneeListPanel.controlPanel(campaignDB, doneeDB, doneeInHelpDB, doneeListDB);
                    break;
                case 3:
                    donorListPanel.controlPanel(campaignDB, donorDB, donorListDB);
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

        } while (option != 5);
    }

    public void add(RedBlackTree<LocalDate, Campaign> campaignDB,
            DoublyLinkedList<Sponsor> sponsorDB,
            RedBlackTree<LocalDate, SponsorList> sponsorListDB,
            CircularLinkedQueue<Donee> doneeDB,
            DoublyLinkedList<Donee> doneeInHelpDB,
            RedBlackTree<LocalDate, DoneeList> doneeListDB,
            SinglyLinkedList<Donor> donorDB,
            RedBlackTree<LocalDate, DonorList> donorListDB) {

        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        Campaign campaign = new Campaign();
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("H:mm:ss");
        String oriCampLastID = "";
        String oriDoneeListLastID = "";
        String oriDonorListLastID = "";
        String oriSponsorListLastID = "";

        if (doneeDB.getFront() != null) {

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
                input.nextLine();
                System.out.print("Enter the campaign email: ");
                campaign.setCampaignEmail(input.nextLine());
                System.out.print("Enter the campaign mobile no: ");
                campaign.setCampaignMobileNo(input.nextLine());
                System.out.print("Enter the campaign address: ");
                campaign.setCampagnAddress(input.nextLine());
                System.out.print("Enter the campaign bank no: ");
                campaign.setCampaignBankNo(input.nextLine());
                System.out.print("Enter the campaign description: ");
                campaign.setDescription(input.nextLine());
                System.out.print("Enter the campaign register date [dd. MMM. yyyy]: ");
                campaign.setCampaignRegisterDate(LocalDate.parse(input.nextLine(), dtfDate));

                campaign.setDateModified(new Timestamp(System.currentTimeMillis()));
                campaign.setStatus("Active");
                oriCampLastID = Campaign.getLastCampaignID();
                oriDoneeListLastID = DoneeList.getLastDoneeListID();
                oriDonorListLastID = DonorList.getLastDonorListID();
                oriSponsorListLastID = SponsorList.getLastSponsorListID();
                campaign.setCampaignID(campaign.autoGenerateID());
                //
                DoublyLinkedList<SponsorList> memoSponsors = addSponsor(campaign, sponsorDB, sponsorListDB);
                DoublyLinkedList<DoneeList> memoDonees = addDonee(campaign, doneeDB, doneeInHelpDB, doneeListDB, campaignDB);
                DoublyLinkedList<DonorList> memoDonors = addDonor(campaign, donorDB, donorListDB);

                System.out.print("Confirm add campaign ? (Y/N) ");
                confirmation = input.nextLine();

                if (confirmation.toUpperCase().equals("Y")) {
                    for (int i = 1; i <= memoSponsors.getLength(); i++) { //memory
                        sponsorListDB.addData(memoSponsors.getAt(i).getDateJoin(), memoSponsors.getAt(i));
                    }

                    for (int i = 1; i <= memoDonees.getLength(); i++) {
                        doneeInHelpDB.addLast(doneeDB.dequeue());
                        doneeListDB.addData(memoDonees.getAt(i).getDateJoin(), memoDonees.getAt(i));
                    }

                    for (int i = 1; i <= memoDonors.getLength(); i++) {
                        donorListDB.addData(memoDonors.getAt(i).getDateJoin(), memoDonors.getAt(i));
                    }

                    campaignDB.addData(campaign.getCampaignStartDate(), campaign);
                } else {
                    Campaign.setLastCampaignID(oriCampLastID);
                    DoneeList.setLastDoneeListID(oriDoneeListLastID);
                    DonorList.setLastDonorListID(oriDonorListLastID);
                    SponsorList.setLastSponsorListID(oriSponsorListLastID);
                }

                System.out.println(confirmation.toUpperCase().equals("Y") ? "Added campaign successfully" : "Add campaign abort");

                System.out.print("Continue add campaign ? (Y/N) ");
                option = input.nextLine();

                System.out.println(confirmation.toUpperCase().equals("Y") ? "Return to previous page..." : "");

            } while (option.toUpperCase().equals("Y"));
        } else {
            System.out.println("No donee need help, no need campaign");
        }
    }

    public DoublyLinkedList<SponsorList> addSponsor(Campaign campaign, DoublyLinkedList<Sponsor> sponsorDB, RedBlackTree<LocalDate, SponsorList> sponsorListDB) {
        DoublyLinkedList<SponsorList> memoSponsorList = new DoublyLinkedList<SponsorList>();
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String accountID = "";
        String lastSponsorID = "";
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        Sponsor sponsor = new Sponsor();
        SponsorList sponsorList = new SponsorList();
        boolean haveRecord = false;
        boolean hasSponsor = true;
        do {
            do {
                sponsorList = new SponsorList();
                hasSponsor = true;
                System.out.println();
                Sponsor.sponsorTable(sponsorDB);
                System.out.print("Enter sponsor ID: ");
                lastSponsorID = input.nextLine();

                if (sponsorDB.contains(new Sponsor(lastSponsorID))) {
                    SponsorList[] sponsorListArr = new SponsorList[memoSponsorList.getLength()];
                    sponsorListArr = memoSponsorList.toArray(sponsorListArr);

                    if (sponsorListArr != null) {
                        for (int i = 0; i < sponsorListArr.length; i++) {
                            if (sponsorListArr[i].getCampaign().equals(campaign) && sponsorListArr[i].getSponsor().equals(new Sponsor(lastSponsorID))) {

                                hasSponsor = false;
                                break;
                            }
                        }
                    }

                    if (hasSponsor == false) {
                        System.out.println("Sponsor ID exist in the campaign already, try again the other sponsor");
                    }
                } else {
                    hasSponsor = false;
                    System.out.println("Sponsor ID not found, try again");
                }
            } while (hasSponsor == false);

            sponsor = sponsorDB.getAt(sponsorDB.indexOf(new Sponsor(lastSponsorID)));//retrieve

            if (sponsor.isInActive() == false) {
                sponsorList.setCampaign(campaign);
                sponsorList.setSponsor(sponsor);
                System.out.print("Enter date join [dd. MMM. yyyy]: ");
                sponsorList.setDateJoin(LocalDate.parse(input.nextLine(), dtfDate));
                sponsorList.setDateModified(new Timestamp(System.currentTimeMillis()));
                sponsorList.setStatus("Active");
                sponsorList.setSponsorListID(sponsorList.autoGenerateID());

                System.out.print("Confirm add sponsor to this campaign ? (Y/N) ");
                confirmation = input.nextLine();

                if (confirmation.toUpperCase().equals("Y")) {
                    memoSponsorList.addLast(sponsorList);
                    haveRecord = true;
                }

                System.out.println(confirmation.toUpperCase().equals("Y") ? "Added sponsor successfully" : "Add sponsor abort");
            } else {
                System.out.println("Sponsor is in inactive status, please try again the other sponsor...");

            }

            if (haveRecord != true) {
                System.out.println("At least one sponsor require...");
                option = "Y";
            } else {
                System.out.print("Continue add sponsor to this campaign ? (Y/N) ");
                option = input.nextLine();
            }

        } while (option.trim().toUpperCase().equals("Y"));

        return memoSponsorList;

    }

    public DoublyLinkedList<DoneeList> addDonee(Campaign campaign, CircularLinkedQueue<Donee> doneeDB, DoublyLinkedList<Donee> doneeInHelpDB, RedBlackTree<LocalDate, DoneeList> doneeListDB, RedBlackTree<LocalDate, Campaign> campaignDB) {
        DoublyLinkedList<DoneeList> memoDonees = new DoublyLinkedList<>();
        CircularLinkedQueue<Donee> memoDoneeDB = new CircularLinkedQueue<>();
        Donee[] memoDoneeDBArr = new Donee[doneeDB.getLength()];
        memoDoneeDBArr = doneeDB.toArray(memoDoneeDBArr);
        for (int i = 0; i < memoDoneeDBArr.length; i++) {
            memoDoneeDB.enqueue(memoDoneeDBArr[i]);
        }

        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String doneeID = "";
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        DoneeList doneeList = new DoneeList();
        boolean haveRecord = false;
        do {
            doneeList = new DoneeList();
            Donee.doneeTable(memoDoneeDB);

            if (doneeDB.getFront() != null) {
                System.out.print("Enter date join [dd. MMM. yyyy]: ");
                doneeList.setDateJoin(LocalDate.parse(input.nextLine(), dtfDate));

                System.out.print("Confirm add donee ? (Y/N) ");

                confirmation = input.nextLine();

                if (confirmation.toUpperCase().equals("Y")) {
                    Donee donee = new Donee();
                    donee = memoDoneeDB.dequeue();
                    doneeList.setDonee(donee);
                    doneeList.setCampaign(campaign);
                    doneeList.setDateModified(new Timestamp(System.currentTimeMillis()));
                    doneeList.setStatus("Active");
                    doneeList.setDoneeListID(doneeList.autoGenerateID());
                    System.out.println();
                    memoDonees.addLast(doneeList);
                    haveRecord = true;
                }

                System.out.println(confirmation.toUpperCase().equals("Y") ? "Added donee successfully" : "Add donee abort");

            } else {
                System.out.println("No donee need help, add donee abort");
            }

            System.out.print("Continue add donee to this campaign ? (Y/N) ");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");

            if (haveRecord == false) {
                System.out.println("At least one donee needed for a campaign...");
                option = "Y";
            }

        } while (option.toUpperCase().equals("Y"));

        return memoDonees;
    }

    public DoublyLinkedList<DonorList> addDonor(Campaign campaign, SinglyLinkedList<Donor> donorDB, RedBlackTree<LocalDate, DonorList> donorListDB) {
        DoublyLinkedList<DonorList> memoDonors = new DoublyLinkedList<>();
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String donorID = "";
        DonorList donorList = new DonorList();
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        boolean haveRecord = false;
        boolean hasDonor = true;
        do {

            do {
                donorList = new DonorList();
                hasDonor = true;
                Donor.donorTable(donorDB);
                System.out.print("Enter donor ID: ");
                donorID = input.nextLine();

                if (donorDB.contains(new Donor(donorID))) {
                    DonorList[] donorListArr = new DonorList[memoDonors.getLength()];
                    donorListArr = memoDonors.toArray(donorListArr);
                    if (donorListArr != null) {
                        for (int i = 0; i < donorListArr.length; i++) {
                            if (donorListArr[i].getCampaign().equals(campaign) && donorListArr[i].getDonor().equals(new Donor(donorID))) {
                                hasDonor = false;
                                break;
                            }
                        }
                    }

                    if (hasDonor == false) {
                        System.out.println("donor ID exist in the campaign already, try again the other donor");
                    }
                } else {
                    hasDonor = false;
                    System.out.println("donor ID not found, try again");
                }
            } while (hasDonor == false);

            Donor donor = donorDB.getAt(donorDB.indexOf(new Donor(donorID)));
            if (donor.isInActive() == false) {

                donorList.setCampaign(campaign);
                donorList.setDonor(donor);
                System.out.print("Enter date join [dd. MMM. yyyy]: ");
                donorList.setDateJoin(LocalDate.parse(input.nextLine(), dtfDate));
                donorList.setDateModified(new Timestamp(System.currentTimeMillis()));
                donorList.setStatus("Active");
                donorList.setDonorListID(donorList.autoGenerateID());

                System.out.print("Confirm add donor to this campaign ? (Y/N) ");
                confirmation = input.nextLine();

                if (confirmation.toUpperCase().equals("Y")) {
                    memoDonors.addLast(donorList);
                    haveRecord = true;
                }

                System.out.println(confirmation.toUpperCase().equals("Y") ? "Added donor successfully" : "Add donor abort");
            } else {
                System.out.println("Donor is in inactive status, please try again the other donor...");
            }

            if (haveRecord != true) {
                System.out.println("At least one donor require...");
                option = "Y";
            } else {
                System.out.print("Continue add donor to this campaign ? (Y/N) ");
                option = input.nextLine();
            }

        } while (option.toUpperCase().equals("Y"));

        return memoDonors;
    }

    public String campaignUpdateMenu() {
        StringBuilder menu = new StringBuilder();
        System.out.println();
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

    public void update(RedBlackTree<LocalDate, Campaign> campaignDB) throws CloneNotSupportedException {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String campaignID = "";
        String indexSelected = "";
        Campaign memoCampaign = new Campaign();
        Campaign campaign = new Campaign();
        LocalDate oriStartDate = null;
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("H:mm:ss");

        do {
            Campaign.campaignTable(campaignDB);

            System.out.print("Enter campaign ID: ");
            campaignID = input.nextLine();

            if (campaignDB.contains(new Campaign(campaignID)) == true) {
                DoublyLinkedList<Campaign> campaigns = (DoublyLinkedList<Campaign>) campaignDB.getAllList();
                memoCampaign = new Campaign();
                campaign = new Campaign();
                campaign = campaignDB.get(new Campaign(campaignID));
                memoCampaign = campaign.clone();

                if (memoCampaign.isPermanentDelete() == false) {
                    oriStartDate = memoCampaign.getCampaignStartDate();

                    boolean validIndex = true;
                    do {
                        System.out.println(campaignUpdateMenu());
                        validIndex = true;
                        System.out.print("Enter index of option that want to update, if multiple index leave space at between [1 5 6]: ");
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
                                        memoCampaign.setCampaignName(input.nextLine());
                                        hasUpdateSomething = true;
                                        break;
                                    case 2:
                                        System.out.print("Enter the new campaign start date [dd. MMM. yyyy]: ");
                                        memoCampaign.setCampaignStartDate(LocalDate.parse(input.nextLine(), dtfDate));
                                        hasUpdateSomething = true;
                                        break;
                                    case 3:
                                        System.out.print("Enter the new campaign start time [H:mm:ss]: ");
                                        memoCampaign.setCampaignStartTime(LocalTime.parse(input.nextLine(), dtfTime));
                                        hasUpdateSomething = true;
                                        break;
                                    case 4:
                                        System.out.print("Enter the new campaign end date [dd. MMM. yyyy]: ");
                                        memoCampaign.setCampaignEndDate(LocalDate.parse(input.nextLine(), dtfDate));
                                        hasUpdateSomething = true;
                                        break;
                                    case 5:
                                        System.out.print("Enter the new campaign end time [H:mm:ss]: ");
                                        memoCampaign.setCampaignEndTime(LocalTime.parse(input.nextLine(), dtfTime));
                                        hasUpdateSomething = true;
                                        break;
                                    case 6:
                                        System.out.print("Enter the new target amount: ");
                                        memoCampaign.setTargetAmount(input.nextDouble());
                                        hasUpdateSomething = true;
                                        break;
                                    case 7:
                                        System.out.print("Enter the new campaign email: ");
                                        memoCampaign.setCampaignEmail(input.nextLine());
                                        hasUpdateSomething = true;
                                        break;
                                    case 8:
                                        System.out.print("Enter the new campaign mobile no: ");
                                        memoCampaign.setCampaignMobileNo(input.nextLine());
                                        hasUpdateSomething = true;
                                        break;
                                    case 9:

                                        System.out.print("Enter the new campaign address: ");
                                        memoCampaign.setCampagnAddress(input.nextLine());
                                        hasUpdateSomething = true;
                                        break;
                                    case 10:
                                        System.out.print("Enter the new campaign bank no: ");
                                        memoCampaign.setCampaignBankNo(input.nextLine());
                                        hasUpdateSomething = true;
                                        break;
                                    case 11:
                                        System.out.print("Enter the new campaign description: ");
                                        memoCampaign.setDescription(input.nextLine());
                                        hasUpdateSomething = true;
                                        break;
                                    default:
                                        System.out.println("Index " + splitIndexInt[i] + "out of bound!");
                                }
                            }

                            if (hasUpdateSomething == true) {
                                System.out.print("Confirm update campaign ? (Y/N) ");
                                confirmation = input.nextLine();

                                if (confirmation.toUpperCase().equals("Y")) {
                                    memoCampaign.setDateModified(new Timestamp(System.currentTimeMillis()));
                                    campaign.copy(memoCampaign);
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
                    System.out.println("Campaign with permanent inactive status unable to make modification.");
                }
            } else {
                System.out.println("Campaign ID not found, update campaign abort");
            }

            System.out.print("Continue update campaign ? (Y/N) ");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");
        } while (option.toUpperCase().equals("Y"));
    }

    public void delete(RedBlackTree<LocalDate, Campaign> campaignDB,
            CircularLinkedQueue<Donee> doneeDB,
            DoublyLinkedList<Donee> doneeInHelpDB,
            RedBlackTree<LocalDate, DoneeList> doneeListDB) {

        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String campaignID = "";
        String[] status = {"Active", "Inactive", "Permanent Inactive"};
        int statusSelect;
        do {
            Campaign.campaignTable(campaignDB);

            System.out.print("Enter campaign ID: ");
            campaignID = input.nextLine();
            DoublyLinkedList<Campaign> campaigns = (DoublyLinkedList<Campaign>) campaignDB.getAllList();
            if (campaigns.contains(new Campaign(campaignID)) == true) {
                Campaign campaign = campaigns.getAt(campaigns.indexOf(new Campaign(campaignID)));

                if (campaign.isPermanentDelete() == false) {

                    StringBuilder statusMenu = new StringBuilder();
                    for (int i = 0; i < status.length; i++) {
                        statusMenu.append((i + 1) + ". " + status[i] + "\n");
                    }

                    System.out.println(statusMenu.toString());
                    System.out.print("Option: ");
                    statusSelect = input.nextInt();

                    input.nextLine();

                    System.out.print("Confirm " + status[statusSelect - 1] + " campaign ? (Y/N) ");
                    confirmation = input.nextLine();

                    if (confirmation.toUpperCase().equals("Y")) {
                        campaign.setStatus(status[statusSelect - 1]);
                        campaign.setDateModified(new Timestamp(System.currentTimeMillis()));
                        campaignDB.updateData(campaign.getCampaignStartDate(), campaign);

                        if (status[statusSelect - 1].toUpperCase().equals("PERMANENT INACTIVE")) {
                            DoneeList[] doneeListArr = new DoneeList[doneeListDB.getAllList().getLength()];
                            doneeListArr = doneeListDB.getAllListInArray(doneeListArr);

                            for (int i = 0; i < doneeListArr.length; i++) {
                                if (doneeListArr[i].getCampaign().equals(campaign)) {
                                    doneeInHelpDB.delAt(doneeInHelpDB.indexOf(doneeListArr[i].getDonee()));
                                    doneeDB.enqueue(doneeListArr[i].getDonee());
                                    doneeListDB.delData(doneeListArr[i].getDateJoin(), doneeListArr[i]);
                                }
                            }
                        }
                    }
                    System.out.println(confirmation.toUpperCase().equals("Y") ? "Campaign " + status[statusSelect - 1] + " successfully" : status[statusSelect - 1] + " campaign abort");

                } else {
                    System.out.println("Campaign with permanent inactive status unable to make modification.");
                }
            } else {
                System.out.println("Campaign ID not found, update campaign abort");
            }
            System.out.print("Continue change campaign's status ? (Y/N) ");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");
        } while (option.toUpperCase().equals("Y"));

    }

    @Override
    public void search() {
    }

    public void search(RedBlackTree<LocalDate, Campaign> campaignDB) {
        Campaign[] campaignArray = new Campaign[campaignDB.getAllList().getLength()];
        campaignArray = campaignDB.getAllListInArray(campaignArray);
        RedBlackTree<LocalDate, Campaign> listForPrint = new RedBlackTree<>();
        Campaign[] arrListForPrint = null;

        arrListForPrint = CampaignPredicates.ControlPanel(campaignArray);

        if (arrListForPrint != null && arrListForPrint.length != 0) {
            for (Campaign arrListForPrint1 : arrListForPrint) {
                listForPrint.addData(arrListForPrint1.getCampaignStartDate(), arrListForPrint1);
            }
            Campaign.campaignTable(listForPrint);
        } else {
            System.out.println("No Record Found...");
        }
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
