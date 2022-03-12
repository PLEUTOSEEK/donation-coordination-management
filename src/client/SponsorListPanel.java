/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.DoublyLinkedList;
import adt.RedBlackTree;
import entity.Campaign;
import entity.Sponsor;
import entity.SponsorList;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Tee Zhuo Xuan
 */
class SponsorListPanel implements Panel {

    public void controlPanel(
            RedBlackTree<LocalDate, Campaign> campaignDB,
            DoublyLinkedList<Sponsor> sponsorDB,
            RedBlackTree<LocalDate, SponsorList> sponsorListDB
    ) {

        Scanner input = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println(menu());
            System.out.println("Option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    add(campaignDB, sponsorDB, sponsorListDB);
                    break;
                case 2:
                    SponsorList.sponsorListTable(sponsorListDB);
                    break;
                case 3:
                    search();
                    break;
                case 4:
                    delete(sponsorListDB);
                    break;
                case 5:
                    update(sponsorDB, sponsorListDB);
                    break;
                case 6:
                    System.out.println("Return to previous Page...");
                    break;
                default:
                    System.out.println("Index not correct...");
            }

        } while (option != 7);
    }

    @Override
    public String menu() {
        StringBuilder menu = new StringBuilder();

        menu.append("1. Add new sponsor list \n");
        menu.append("2. Display sponsor list \n");
        menu.append("3. Search sponsor list \n");
        menu.append("4. Deactive sponsor list \n");
        menu.append("5. Update sponsor list \n");
        menu.append("6. Exit \n");

        return menu.toString();
    }

    private void add(RedBlackTree<LocalDate, Campaign> campaignDB,
            DoublyLinkedList<Sponsor> sponsorDB, RedBlackTree<LocalDate, SponsorList> sponsorListDB) {

        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String campaignID = "";
        String lastSponsorID = "";
        Campaign campaign = new Campaign();
        Sponsor sponsor = new Sponsor();
        SponsorList sponsorList = new SponsorList();
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        boolean hasSponsor = true;

        do {

            Campaign.campaignTable(campaignDB);

            System.out.println("Enter campaign ID: ");
            campaignID = input.nextLine();

            if (campaignDB.contains(new Campaign(campaignID)) == true) {
                do {

                    do {
                        hasSponsor = true;
                        Sponsor.sponsorTable(sponsorDB);
                        System.out.println("Enter sponsor ID: ");
                        lastSponsorID = input.nextLine();

                        if (sponsorDB.contains(new Sponsor(lastSponsorID))) {
                            SponsorList[] sponsorListArr = sponsorListDB.getAllArrayList();

                            for (int i = 0; i < sponsorListArr.length; i++) {
                                if (sponsorListArr[i].getCampaign().equals(campaign) && sponsorListArr[i].getSponsor().equals(sponsor)) {
                                    hasSponsor = false;
                                    break;
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

                    sponsor = sponsorDB.getAt(sponsorDB.indexOf(new Sponsor(lastSponsorID)));
                    campaign = campaignDB.get(new Campaign(campaignID));

                    sponsorList.setCampaign(campaign);
                    sponsorList.setSponsor(sponsor);
                    System.out.println("Enter date join [dd. MMM. yyyy]: ");
                    sponsorList.setDateJoin(LocalDate.parse(input.nextLine(), dtfDate));
                    sponsorList.setDateModified(new Timestamp(System.currentTimeMillis()));
                    sponsorList.setStatus("Active");
                    sponsorList.setSponsorListID(sponsorList.autoGenerateID());

                    System.out.println("Confirm add sponsor to this campaign ? (Y/N)");
                    confirmation = input.nextLine();

                    if (confirmation.toUpperCase().equals("Y")) {
                        sponsorListDB.addData(sponsorList.getDateJoin(), sponsorList);
                    }

                    System.out.println(confirmation.toUpperCase().equals("Y") ? "Added sponsor successfully" : "Add sponsor abort");

                    System.out.println("Continue add sponsor to this campaign ? (Y/N)");
                    option = input.nextLine();
                } while (option.toUpperCase().equals("Y"));
            } else {
                System.out.println("Campaign ID not found, add sponsor abort");
            }

            System.out.println("Continue add sponsor ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");

        } while (option.toUpperCase().equals("Y"));
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String sponsorListUpdateMenu() {
        StringBuilder menu = new StringBuilder();

        menu.append("1. Sponsor\n");
        menu.append("2. Sponsor Join Date\n");

        return menu.toString();

    }

    public void update(DoublyLinkedList<Sponsor> sponsorDB, RedBlackTree<LocalDate, SponsorList> sponsorListDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String sponsorListID = "";
        String lastSponsorID = "";
        String indexSelected = "";
        boolean hasSponsor = true;
        SponsorList sponsorList = new SponsorList();
        LocalDate oriJoinDate = null;
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");

        do {
            SponsorList.sponsorListTable(sponsorListDB);

            System.out.println("Enter sponsor list ID: ");
            sponsorListID = input.nextLine();

            if (sponsorListDB.contains(new SponsorList(sponsorListID)) == true) {
                sponsorList = sponsorListDB.get(new SponsorList(sponsorListID));
                oriJoinDate = sponsorList.getDateJoin();
                boolean validIndex = true;
                do {
                    System.out.println(sponsorListUpdateMenu());
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
                                    do {
                                        hasSponsor = true;
                                        Sponsor.sponsorTable(sponsorDB);
                                        System.out.println("Enter Sponsor ID: ");
                                        lastSponsorID = input.nextLine();

                                        if (sponsorDB.contains(new Sponsor(lastSponsorID))) {
                                            SponsorList[] SponsorListArr = sponsorListDB.getAllArrayList();

                                            for (int j = 0; j < SponsorListArr.length; j++) {
                                                if (SponsorListArr[j].getCampaign().equals(sponsorList.getCampaign()) && SponsorListArr[j].getSponsor().equals(new Sponsor(lastSponsorID))) {
                                                    hasSponsor = false;
                                                    break;
                                                }
                                            }
                                            if (hasSponsor == false) {
                                                System.out.println("Sponsor ID exist in the campaign already, try again the other Sponsor");
                                            }
                                        } else {
                                            hasSponsor = false;
                                            System.out.println("Sponsor ID not found, try again");
                                        }
                                    } while (hasSponsor == false);
                                    sponsorList.setSponsor(sponsorDB.getAt(sponsorDB.indexOf(new Sponsor(lastSponsorID))));
                                    hasUpdateSomething = true;
                                    break;
                                case 2:
                                    System.out.print("Enter the new Sponsor join date [dd. MMM. yyyy]: ");
                                    sponsorList.setDateJoin(LocalDate.parse(input.nextLine(), dtfDate));
                                    hasUpdateSomething = true;
                                    break;

                                default:
                                    System.out.println("Index " + splitIndexInt[i] + "out of bound!");
                            }
                        }

                        if (splitIndexInt.length != 0) {
                            System.out.println("Confirm update sponsor list ? (Y/N)");
                            confirmation = input.nextLine();

                            if (confirmation.toUpperCase().equals("Y")) {
                                sponsorList.setDateModified(new Timestamp(System.currentTimeMillis()));
                                if (oriJoinDate != sponsorList.getDateJoin()) {
                                    sponsorListDB.delData(oriJoinDate, sponsorList);
                                    sponsorListDB.addData(sponsorList.getDateJoin(), sponsorList);
                                } else {
                                    sponsorListDB.updateData(sponsorList.getDateJoin(), sponsorList);
                                }
                            }

                            System.out.println(confirmation.toUpperCase().equals("Y") ? "Update sponsor list successfully" : "Update Sponsor list abort");
                        } else {
                            System.out.println("No data selected to be update...");
                        }
                    }

                } while (validIndex == false);
            } else {
                System.out.println("Sponsor list ID not found, update Sponsor list abort");
            }

            System.out.println("Continue update sponsor list ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");
        } while (option.toUpperCase().equals("Y"));
    }

    public void delete(RedBlackTree<LocalDate, SponsorList> sponsorListDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String sponsorListID = "";

        do {
            SponsorList.sponsorListTable(sponsorListDB);

            System.out.println("Enter sponsor list ID: ");
            sponsorListID = input.nextLine();
            DoublyLinkedList<SponsorList> sponsorLists = sponsorListDB.getAllList();
            if (sponsorLists.contains(new SponsorList(sponsorListID)) == true) {
                System.out.println("Confirm deactive sponsor list ? (Y/N)");
                confirmation = input.nextLine();

                if (confirmation.toUpperCase().equals("Y")) {
                    SponsorList sponsorList = sponsorLists.getAt(sponsorLists.indexOf(new SponsorList(sponsorListID)));
                    sponsorList.setStatus("Inactive");
                    sponsorList.setDateModified(new Timestamp(System.currentTimeMillis()));
                    sponsorListDB.updateData(sponsorList.getDateJoin(), sponsorList);
                }
            } else {
                System.out.println("Sponsor list ID not found, deactive Sponsor list abort");
            }
            System.out.println("Continue deactive sponsor list  ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");
        } while (option.toUpperCase().equals("Y"));

    }

    @Override
    public void search() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controlPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
