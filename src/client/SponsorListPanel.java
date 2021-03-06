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
import utils.SponsorListPredicates;

/**
 *
 * @author Tee Zhuo Xuan
 */
class SponsorListPanel implements Panel {

    public void controlPanel(
            RedBlackTree<LocalDate, Campaign> campaignDB,
            DoublyLinkedList<Sponsor> sponsorDB,
            RedBlackTree<LocalDate, SponsorList> sponsorListDB
    ) throws CloneNotSupportedException {

        Scanner input = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println(menu());
            System.out.print("Option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    add(campaignDB, sponsorDB, sponsorListDB);
                    break;
                case 2:
                    SponsorList.sponsorListTable(sponsorListDB);
                    break;
                case 3:
                    search(sponsorListDB);
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

        } while (option != 6);
    }

    @Override
    public String menu() {
        StringBuilder menu = new StringBuilder();
        System.out.println();
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
        String oriLastID = SponsorList.getLastSponsorListID();

        do {

            Campaign.campaignTable(campaignDB);

            System.out.print("Enter campaign ID: ");
            campaignID = input.nextLine();
            campaign = new Campaign();

            if (campaignDB.contains(new Campaign(campaignID)) == true) {
                campaign = campaignDB.get(new Campaign(campaignID));
                if (campaign.isPermanentDelete() == false) {

                    do {
                        do {
                            oriLastID = SponsorList.getLastSponsorListID();
                            sponsorList = new SponsorList();
                            hasSponsor = true;
                            Sponsor.sponsorTable(sponsorDB);
                            System.out.print("Enter sponsor ID: ");
                            lastSponsorID = input.nextLine();

                            if (sponsorDB.contains(new Sponsor(lastSponsorID))) {
                                SponsorList[] sponsorListArr = new SponsorList[sponsorListDB.getAllList().getLength()];
                                sponsorListArr = sponsorListDB.getAllListInArray(sponsorListArr);

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

                        sponsor = sponsorDB.getAt(sponsorDB.indexOf(new Sponsor(lastSponsorID)));
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
                                sponsorListDB.addData(sponsorList.getDateJoin(), sponsorList);
                            } else {
                                SponsorList.setLastSponsorListID(oriLastID);
                            }

                            System.out.println(confirmation.toUpperCase().equals("Y") ? "Added sponsor successfully" : "Add sponsor abort");
                        } else {
                            System.out.println("Sponsor is in inactive status, please try again the other sponsor...");
                        }
                        System.out.print("Continue add sponsor to this campaign ? (Y/N) ");
                        option = input.nextLine();

                    } while (option.trim().toUpperCase().equals("Y"));
                } else {
                    System.out.println("Campaign with permanent inactive status unable to perform modification");
                }
            } else {
                System.out.println("Campaign ID not found, add sponsor abort");
            }

            System.out.print("Continue add sponsor ? (Y/N) ");
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
        System.out.println();
        menu.append("1. Sponsor Join Date\n");

        return menu.toString();

    }

    public void update(DoublyLinkedList<Sponsor> sponsorDB, RedBlackTree<LocalDate, SponsorList> sponsorListDB) throws CloneNotSupportedException {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String sponsorListID = "";
        String lastSponsorID = "";
        String indexSelected = "";
        boolean hasSponsor = true;
        SponsorList sponsorList = new SponsorList();
        SponsorList memoSponsorList = new SponsorList();
        LocalDate oriJoinDate = null;
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");

        do {
            SponsorList.sponsorListTable(sponsorListDB);

            System.out.print("Enter sponsor list ID: ");
            sponsorListID = input.nextLine();

            if (sponsorListDB.contains(new SponsorList(sponsorListID)) == true) {
                sponsorList = sponsorListDB.get(new SponsorList(sponsorListID));
                memoSponsorList = sponsorList.clone();
                if (memoSponsorList.getCampaign().isPermanentDelete() == false) {

                    oriJoinDate = memoSponsorList.getDateJoin();
                    boolean validIndex = true;
                    do {
                        System.out.println(sponsorListUpdateMenu());
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
                                        System.out.print("Enter the new Sponsor join date [dd. MMM. yyyy]: ");
                                        memoSponsorList.setDateJoin(LocalDate.parse(input.nextLine(), dtfDate));
                                        hasUpdateSomething = true;
                                        break;

                                    default:
                                        System.out.println("Index " + splitIndexInt[i] + "out of bound!");
                                }
                            }

                            if (splitIndexInt.length != 0 && hasUpdateSomething == true) {
                                System.out.print("Confirm update sponsor list ? (Y/N)");
                                confirmation = input.nextLine();

                                if (confirmation.toUpperCase().equals("Y")) {
                                    memoSponsorList.setDateModified(new Timestamp(System.currentTimeMillis()));
                                    sponsorList.copy(memoSponsorList);
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
                    System.out.println("Campaign with permanent inactive status unable to perform modification");
                }
            } else {
                System.out.println("Sponsor list ID not found, update Sponsor list abort");
            }

            System.out.print("Continue update sponsor list ? (Y/N) ");
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

            System.out.print("Enter sponsor list ID: ");
            sponsorListID = input.nextLine();
            DoublyLinkedList<SponsorList> sponsorLists = (DoublyLinkedList<SponsorList>) sponsorListDB.getAllList();
            if (sponsorLists.contains(new SponsorList(sponsorListID)) == true) {
                SponsorList sponsorList = sponsorLists.getAt(sponsorLists.indexOf(new SponsorList(sponsorListID)));
                if (sponsorList.getCampaign().isPermanentDelete() == false) {

                    System.out.print("Confirm deactive sponsor list ? (Y/N) ");
                    confirmation = input.nextLine();

                    if (confirmation.toUpperCase().equals("Y")) {
                        sponsorList.setStatus("Inactive");
                        sponsorList.setDateModified(new Timestamp(System.currentTimeMillis()));
                        sponsorListDB.updateData(sponsorList.getDateJoin(), sponsorList);
                    }
                } else {
                    System.out.println("Campaign with permanent inactive status unable to perform modification");
                }
            } else {
                System.out.println("Sponsor list ID not found, deactive Sponsor list abort");
            }
            System.out.print("Continue deactive sponsor list  ? (Y/N) ");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");
        } while (option.toUpperCase().equals("Y"));

    }

    @Override
    public void search() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String sponsorListSearchMenu() {
        StringBuilder menu = new StringBuilder();
        System.out.println();
        Sponsor sponsor = new Sponsor();
        menu.append("1. Sponsor Name\n");
        menu.append("2. Sponsor email\n");
        menu.append("3. Sponsor phone no\n");
        menu.append("4. Sponsor address\n");
        menu.append("5. Sponsor date join\n");

        return menu.toString();

    }

    public void search(RedBlackTree<LocalDate, SponsorList> sponsorListDB) {
        SponsorList[] sponsorListArr = new SponsorList[sponsorListDB.getAllList().getLength()];
        sponsorListArr = sponsorListDB.getAllListInArray(sponsorListArr);
        RedBlackTree<LocalDate, SponsorList> listForPrint = new RedBlackTree<>();
        SponsorList[] arrListForPrint = null;

        arrListForPrint = SponsorListPredicates.ControlPanel(sponsorListArr);;

        // CampaignPredicates.ControlPanel(campaignArray);
        if (arrListForPrint != null && arrListForPrint.length != 0) {
            for (SponsorList arrListForPrint1 : arrListForPrint) {
                listForPrint.addData(arrListForPrint1.getDateJoin(), arrListForPrint1);
            }
            SponsorList.sponsorListTable(listForPrint);
        } else {
            System.out.println("No Record Found...");
        }
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
