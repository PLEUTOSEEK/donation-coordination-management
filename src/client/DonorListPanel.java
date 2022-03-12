/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.DoublyLinkedList;
import adt.RedBlackTree;
import entity.Campaign;
import entity.Donor;
import entity.DonorList;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Tee Zhuo Xuan
 */
class DonorListPanel implements Panel {

    public void controlPanel(
            RedBlackTree<LocalDate, Campaign> campaignDB,
            DoublyLinkedList<Donor> donorDB,
            RedBlackTree<LocalDate, DonorList> donorListDB
    ) {

        Scanner input = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println(menu());
            System.out.println("Option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    add(campaignDB, donorDB, donorListDB);
                    break;
                case 2:
                    DonorList.donorListTable(donorListDB);
                    break;
                case 3:
                    search();
                    break;
                case 4:
                    delete(donorListDB);
                    break;
                case 5:
                    update(donorDB, donorListDB);
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

        menu.append("1. Add new donor list \n");
        menu.append("2. Display donor list \n");
        menu.append("3. Search donor list \n");
        menu.append("4. Deactive donor list \n");
        menu.append("5. Update donor list \n");
        menu.append("6. Exit \n");

        return menu.toString();
    }

    @Override
    public void add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void add(RedBlackTree<LocalDate, Campaign> campaignDB,
            DoublyLinkedList<Donor> donorDB, RedBlackTree<LocalDate, DonorList> donorListDB) {

        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String campaignID = "";
        String donorID = "";
        Campaign campaign = new Campaign();
        Donor donor = new Donor();
        DonorList donorList = new DonorList();
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        boolean hasDonor = true;

        do {

            Campaign.campaignTable(campaignDB);

            System.out.println("Enter campaign ID: ");
            campaignID = input.nextLine();

            if (campaignDB.contains(new Campaign(campaignID)) == true) {
                do {

                    do {
                        hasDonor = true;
                        Donor.donorTable(donorDB);
                        System.out.println("Enter donor ID: ");
                        donorID = input.nextLine();

                        if (donorDB.contains(new Donor(donorID))) {
                            DonorList[] donotListArr = donorListDB.getAllArrayList();

                            for (int i = 0; i < donotListArr.length; i++) {
                                if (donotListArr[i].getCampaign().equals(campaign) && donotListArr[i].getDonor().equals(donor)) {
                                    hasDonor = false;
                                    break;
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

                    donor = donorDB.getAt(donorDB.indexOf(new Donor(donorID)));
                    campaign = campaignDB.get(new Campaign(campaignID));

                    donorList.setCampaign(campaign);
                    donorList.setDonor(donor);
                    System.out.println("Enter date join [dd. MMM. yyyy]: ");
                    donorList.setDateJoin(LocalDate.parse(input.nextLine(), dtfDate));
                    donorList.setDateModified(new Timestamp(System.currentTimeMillis()));
                    donorList.setStatus("Active");
                    donorList.setDonorListID(donorList.autoGenerateID());

                    System.out.println("Confirm add donor to this campaign ? (Y/N)");
                    confirmation = input.nextLine();

                    if (confirmation.toUpperCase().equals("Y")) {
                        donorListDB.addData(donorList.getDateJoin(), donorList);
                    }

                    System.out.println(confirmation.toUpperCase().equals("Y") ? "Added donor successfully" : "Add donor abort");

                    System.out.println("Continue add donor to this campaign ? (Y/N)");
                    option = input.nextLine();
                } while (option.toUpperCase().equals("Y"));
            } else {
                System.out.println("Campaign ID not found, add donor abort");
            }

            System.out.println("Continue add donor ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");

        } while (option.toUpperCase().equals("Y"));
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String donorListUpdateMenu() {
        StringBuilder menu = new StringBuilder();

        menu.append("1. Donor\n");
        menu.append("2. Donor Join Date\n");

        return menu.toString();

    }

    public void update(DoublyLinkedList<Donor> donorDB, RedBlackTree<LocalDate, DonorList> donorListDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String donorListID = "";
        String donorID = "";
        String indexSelected = "";
        boolean hasDonor = true;
        DonorList donorList = new DonorList();
        LocalDate oriJoinDate = null;
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("H:mm:ss");

        do {
            DonorList.donorListTable(donorListDB);

            System.out.println("Enter donor list ID: ");
            donorListID = input.nextLine();

            if (donorListDB.contains(new DonorList(donorListID)) == true) {
                donorList = donorListDB.get(new DonorList(donorListID));
                oriJoinDate = donorList.getDateJoin();
                boolean validIndex = true;
                do {
                    System.out.println(donorListUpdateMenu());
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
                                        hasDonor = true;
                                        Donor.donorTable(donorDB);
                                        System.out.println("Enter donor ID: ");
                                        donorID = input.nextLine();

                                        if (donorDB.contains(new Donor(donorID))) {
                                            DonorList[] donorListArr = donorListDB.getAllArrayList();

                                            //check
                                            for (int j = 0; j < donorListArr.length; j++) {
                                                if (donorListArr[j].getCampaign().equals(donorList.getCampaign()) && donorListArr[j].getDonor().equals(new Donor(donorID))) {
                                                    hasDonor = false;
                                                    break;
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
                                    donorList.setDonor(donorDB.getAt(donorDB.indexOf(new Donor(donorID))));
                                    hasUpdateSomething = true;
                                    break;
                                case 2:
                                    System.out.print("Enter the new donor join date [dd. MMM. yyyy]: ");
                                    donorList.setDateJoin(LocalDate.parse(input.nextLine(), dtfDate));
                                    hasUpdateSomething = true;
                                    break;

                                default:
                                    System.out.println("Index " + splitIndexInt[i] + "out of bound!");
                            }
                        }

                        if (hasUpdateSomething == true) {
                            System.out.println("Confirm update donor list ? (Y/N)");
                            confirmation = input.nextLine();

                            if (confirmation.toUpperCase().equals("Y")) {
                                donorList.setDateModified(new Timestamp(System.currentTimeMillis()));
                                if (oriJoinDate != donorList.getDateJoin()) {
                                    donorListDB.delData(oriJoinDate, donorList);
                                    donorListDB.addData(donorList.getDateJoin(), donorList);
                                } else {
                                    donorListDB.updateData(donorList.getDateJoin(), donorList);
                                }
                            }

                            System.out.println(confirmation.toUpperCase().equals("Y") ? "Update donor list successfully" : "Update donor list abort");
                        } else {
                            System.out.println("No data selected to be update...");
                        }
                    }

                } while (validIndex == false);
            } else {
                System.out.println("Donor list ID not found, update campaign abort");
            }

            System.out.println("Continue update donor list ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");
        } while (option.toUpperCase().equals("Y"));
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(RedBlackTree<LocalDate, DonorList> donorListDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String donorListID = "";

        do {
            DonorList.donorListTable(donorListDB);

            System.out.println("Enter donor list ID: ");
            donorListID = input.nextLine();
            DoublyLinkedList<DonorList> donorLists = donorListDB.getAllList();
            if (donorLists.contains(new DonorList(donorListID)) == true) {
                System.out.println("Confirm deactive donor list ? (Y/N)");
                confirmation = input.nextLine();

                if (confirmation.toUpperCase().equals("Y")) {
                    DonorList donorList = donorLists.getAt(donorLists.indexOf(new DonorList(donorListID)));
                    donorList.setStatus("Inactive");
                    donorList.setDateModified(new Timestamp(System.currentTimeMillis()));
                    donorListDB.updateData(donorList.getDateJoin(), donorList);
                }
            } else {
                System.out.println("Donor list ID not found, deactive donor list abort");
            }
            System.out.println("Continue deactive donor list  ? (Y/N)");
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
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controlPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
