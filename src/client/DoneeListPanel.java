/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.CircularLinkedQueue;
import adt.DoublyLinkedList;
import adt.RedBlackTree;
import entity.Campaign;
import entity.Donee;
import entity.DoneeList;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import utils.DoneeListPredicates;

/**
 *
 * @author Tee Zhuo Xuan
 */
class DoneeListPanel implements Panel {

    public void controlPanel(
            RedBlackTree<LocalDate, Campaign> campaignDB,
            CircularLinkedQueue<Donee> doneeDB,
            DoublyLinkedList<Donee> doneeInHelpDB,
            RedBlackTree<LocalDate, DoneeList> doneeListDB
    ) throws CloneNotSupportedException {

        Scanner input = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println(menu());
            System.out.print("Option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    add(campaignDB, doneeDB, doneeInHelpDB, doneeListDB);
                    break;
                case 2:
                    DoneeList.doneeListTable(doneeListDB);
                    break;
                case 3:
                    search(doneeListDB);
                    break;
                case 4:
                    delete(doneeDB, doneeInHelpDB, doneeListDB);
                    break;
                case 5:
                    update(doneeListDB);
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
        menu.append("1. Add new donee list \n");
        menu.append("2. Display donee list \n");
        menu.append("3. Search donee list \n");
        menu.append("4. Delete donee list \n");
        menu.append("5. Update donee list \n");
        menu.append("6. Exit \n");

        return menu.toString();
    }

    private void add(RedBlackTree<LocalDate, Campaign> campaignDB,
            CircularLinkedQueue<Donee> doneeDB, DoublyLinkedList<Donee> doneeInHelpDB, RedBlackTree<LocalDate, DoneeList> doneeListDB) {

        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String campaignID = "";
        String doneeID = "";
        Campaign campaign = new Campaign();
        Donee donee = new Donee();
        DoneeList doneeList = new DoneeList();
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        boolean hasDonee = true;
        String originalLastId = DoneeList.getLastDoneeListID();
        do {

            Campaign.campaignTable(campaignDB);

            System.out.print("Enter campaign ID: ");
            campaignID = input.nextLine();
            campaign = new Campaign();
            if (campaignDB.contains(new Campaign(campaignID)) == true) {
                campaign = campaignDB.get(new Campaign(campaignID));
                if (campaign.isPermanentDelete() == false) {

                    do {
                        originalLastId = DoneeList.getLastDoneeListID();
                        doneeList = new DoneeList();
                        Donee.doneeTable(doneeDB);
                        hasDonee = true;
                        Donee initialFront = doneeDB.getFront();

                        if (doneeDB.getFront() != null && doneeDB.getFront().isInActive() == true) {
                            for (int i = 1; i <= doneeDB.getLength(); i++) {
                                if (doneeDB.getFront().isInActive() == true) {
                                    doneeDB.enqueue(doneeDB.dequeue());
                                }

                                if (doneeDB.getFront() == initialFront) {
                                    hasDonee = false;
                                    break;
                                }
                            }
                        }

                        if (doneeDB.getFront() != null && hasDonee == true) {
                            System.out.print("Enter date join [dd. MMM. yyyy]: ");
                            doneeList.setDateJoin(LocalDate.parse(input.nextLine(), dtfDate));

                            System.out.print("Confirm add donee ? (Y/N) ");

                            confirmation = input.nextLine();

                            if (confirmation.toUpperCase().equals("Y")) {
                                donee = new Donee();
                                donee = doneeDB.dequeue();
                                doneeInHelpDB.addLast(donee);
                                doneeList.setDonee(donee);
                                doneeList.setCampaign(campaign);
                                doneeList.setDateModified(new Timestamp(System.currentTimeMillis()));
                                doneeList.setStatus("Active");
                                doneeList.setDoneeListID(doneeList.autoGenerateID());
                                doneeListDB.addData(doneeList.getDateJoin(), doneeList);
                            }else{
                                 DoneeList.setLastDoneeListID(originalLastId);
                            }

                            System.out.println(confirmation.toUpperCase().equals("Y") ? "Added donee successfully" : "Add donee abort");

                        } else {
                            System.out.println("No donee need help, add donee abort");
                        }

                        System.out.print("Continue add donee to this campaign ? (Y/N) ");
                        option = input.nextLine();

                        System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");

//                        System.out.println("At least one donee needed for a campaign...");
//                        option = "Y";

                    } while (option.toUpperCase().equals("Y"));
                } else {
                    System.out.println("Campaign with permanent inactive status unable to perform modification");
                }
            } else {
                System.out.println("Campaign ID not found, add donee abort");
            }

            System.out.println("Continue add donee ? (Y/N)");
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

    public void update(RedBlackTree<LocalDate, DoneeList> doneeListDB) throws CloneNotSupportedException {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String doneeListID = "";
        String doneeID = "";
        String indexSelected = "";
        boolean hasDonee = true;
        DoneeList doneeList = new DoneeList();
        LocalDate oriJoinDate = null;
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");

        do {
            DoneeList.doneeListTable(doneeListDB);

            System.out.print("Enter donee list ID: ");
            doneeListID = input.nextLine();

            if (doneeListDB.contains(new DoneeList(doneeListID)) == true) {
                doneeList = doneeListDB.get(new DoneeList(doneeListID)).clone();
                if (doneeList.getCampaign().isPermanentDelete() == false) {

                    oriJoinDate = doneeList.getDateJoin();
                    boolean validIndex = true;
                    do {
                        System.out.println(doneeListUpdateMenu());
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

                                        System.out.print("Enter the new donee join date [dd. MMM. yyyy]: ");
                                        doneeList.setDateJoin(LocalDate.parse(input.nextLine(), dtfDate));
                                        hasUpdateSomething = true;
                                        break;

                                    default:
                                        System.out.println("Index " + splitIndexInt[i] + " out of bound!");
                                }
                            }

                            if (splitIndexInt.length != 0 && hasUpdateSomething == true) {
                                System.out.print("Confirm update donee list ? (Y/N) ");
                                confirmation = input.nextLine();

                                if (confirmation.toUpperCase().equals("Y")) {
                                    doneeList.setDateModified(new Timestamp(System.currentTimeMillis()));
                                    if (oriJoinDate != doneeList.getDateJoin()) {
                                        doneeListDB.delData(oriJoinDate, doneeList);
                                        doneeListDB.addData(doneeList.getDateJoin(), doneeList);
                                    } else {
                                        doneeListDB.updateData(doneeList.getDateJoin(), doneeList);
                                    }
                                }

                                System.out.println(confirmation.toUpperCase().equals("Y") ? "Update donee list successfully" : "Update donee list abort");
                            } else {
                                System.out.println("No data selected to be update...");
                            }
                        }

                    } while (validIndex == false);
                } else {
                    System.out.println("Campaign with permanent inactive status unable to perform modification");
                }
            } else {
                System.out.println("Donee list ID not found, update donee list abort");
            }

            System.out.print("Continue update donee list ? (Y/N) ");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");
        } while (option.toUpperCase().equals("Y"));
    }

    public String doneeListUpdateMenu() {
        StringBuilder menu = new StringBuilder();
        System.out.println();
        menu.append("1. Donee Join Date\n");

        return menu.toString();

    }

    public void delete(CircularLinkedQueue<Donee> doneeDB, DoublyLinkedList<Donee> doneeInHelpDB, RedBlackTree<LocalDate, DoneeList> doneeListDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String doneeListID = "";
        
        if (doneeDB.getFront() != null) {
            do {
                DoneeList.doneeListTable(doneeListDB);

                System.out.print("Enter donee list ID: ");
                doneeListID = input.nextLine();
                DoublyLinkedList<DoneeList> doneeLists = doneeListDB.getAllList();
                if (doneeLists.contains(new DoneeList(doneeListID)) == true) {
                    DoneeList doneeList = doneeLists.getAt(doneeLists.indexOf(new DoneeList(doneeListID)));
                    if (doneeList.getCampaign().isPermanentDelete() == false) {

                        DoneeList[] doneeListArr = new DoneeList[doneeLists.getLength()];
                        doneeListArr = doneeLists.toArray(doneeListArr);
                        boolean atLeastOne = false;

                        for (int i = 0; i < doneeListArr.length; i++) {
                            if (doneeListArr[i].getCampaign().equals(doneeList.getCampaign()) && doneeListArr[i].getDoneeListID().equals(doneeList.getDoneeListID()) == false) {
                                atLeastOne = true;
                                break;
                            }
                        }

                        if (atLeastOne == true) {

                            System.out.print("Confirm delete donee list ? (Y/N) ");
                            confirmation = input.nextLine();

                            if (confirmation.toUpperCase().equals("Y")) {
                                doneeDB.enqueue(doneeList.getDonee());
                                doneeInHelpDB.delAt(doneeInHelpDB.indexOf(doneeList.getDonee()));
                                doneeListDB.delData(doneeList.getDateJoin(), doneeList);
                            }
                                
                            System.out.println(confirmation.toUpperCase().equals("Y") ? "Delete successfully" : "Delete donee list abort");
                        } else {
                            System.out.println("At least one donee require for a campaign...");
                        }
                    } else {
                        System.out.println("Campaign with permanent inactive status unable to perform modification");
                    }
                } else {
                    System.out.println("Donee list ID not found, delete donee list abort");
                }
                System.out.print("Continue delete donee list  ? (Y/N) ");
                option = input.nextLine();

                System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");
            } while (option.toUpperCase().equals("Y"));
        } else {
            System.out.println("No donee need help");
        }
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void search() {
    }

    public void search(RedBlackTree<LocalDate, DoneeList> doneeListDB) {
        DoneeList[] DoneeListArr = new DoneeList[doneeListDB.getAllList().getLength()];
        DoneeListArr = doneeListDB.getAllListInArray(DoneeListArr);
        RedBlackTree<LocalDate, DoneeList> listForPrint = new RedBlackTree<>();
        DoneeList[] arrListForPrint = null;

        arrListForPrint = DoneeListPredicates.ControlPanel(DoneeListArr);;

        // CampaignPredicates.ControlPanel(campaignArray);
        if (arrListForPrint != null && arrListForPrint.length != 0) {
            for (DoneeList arrListForPrint1 : arrListForPrint) {
                listForPrint.addData(arrListForPrint1.getDateJoin(), arrListForPrint1);
            }
            DoneeList.doneeListTable(listForPrint);
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
    public void controlPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
