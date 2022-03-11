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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Tee Zhuo Xuan
 */
class DemandListPanel implements Panel {

    public void controlPanel(RedBlackTree<LocalDate, Campaign> campaignDB,
            RedBlackTree<LocalDate, DemandList> demandListDB) {
        Scanner input = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println(menu());
            option = input.nextInt();

            switch (option) {
                case 1:
                    add(campaignDB, demandListDB);
                    break;
                case 2:
                    DemandList.demandTable(demandListDB);
                    break;
                case 3:
                    search();
                    break;
                case 4:
                    delete(demandListDB);
                    break;
                case 5:
                    update(demandListDB);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void display() {
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

    @Override
    public void search() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void add(RedBlackTree<LocalDate, Campaign> campaignDB, RedBlackTree<LocalDate, DemandList> demandListDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String campaignID = "";
        DemandList demandList = new DemandList();
        Campaign campaign = new Campaign();
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("H:mm:ss");

        do {

            Campaign.campaignTable(campaignDB);

            System.out.println("Enter campaign ID: ");
            campaignID = input.nextLine();

            if (campaignDB.contains(new Campaign(campaignID)) == true) {
                do {
                    campaign = campaignDB.get(new Campaign(campaignID));

                    demandList.setCampaign(campaign);
                    System.out.println("Enter demand name: ");
                    demandList.setDemandName(input.nextLine());
                    System.out.println("Enter demand description: ");
                    demandList.setDescription(input.nextLine());
                    System.out.println("Enter demand quantity: ");
                    int qty = input.nextInt();
                    demandList.setQuantity(qty);
                    demandList.setOrgiQty(qty);
                    System.out.println("Enter demand price per unit: ");
                    demandList.setPricePerUnit(input.nextDouble());
                    System.out.println("Enter demand register date [dd. MMM. yyyy]: ");
                    demandList.setDateRegister(LocalDate.parse(input.nextLine(), dtfDate));
                    demandList.setDateModified(new Timestamp(System.currentTimeMillis()));
                    demandList.setStatus("Active");
                    demandList.setDemandListID(demandList.autoGenerateID());

                    System.out.println("Confirm add demand list to this campaign? (Y/N)");
                    confirmation = input.nextLine();

                    if (confirmation.toUpperCase().equals("Y")) {
                        demandListDB.addData(demandList.getDateRegister(), demandList);
                    }

                    System.out.println(confirmation.toUpperCase().equals("Y") ? "Added demand successfully" : "Add demand abort");

                    System.out.println("Continue add demand to this campaign ? (Y/N)");
                    option = input.nextLine();
                } while (option.toUpperCase().equals("Y"));
            } else {
                System.out.println("Campaign ID not found, add demand abort");
            }

            System.out.println("Continue add demand ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");

        } while (option.toUpperCase().equals("Y"));
    }

    public String demandListUpdateMenu() {
        StringBuilder menu = new StringBuilder();

        menu.append("1. Demand name\n");
        menu.append("2. Demand description\n");
        menu.append("3. Demand register date\n");
        return menu.toString();
    }

    private void update(RedBlackTree<LocalDate, DemandList> demandListDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String demandListID = "";
        String indexSelected = "";
        boolean hasSponsor = true;
        DemandList demandList = new DemandList();
        LocalDate oriRegisterDate = null;
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");

        do {
            DemandList.demandTable(demandListDB);

            System.out.println("Enter demand list ID: ");
            demandListID = input.nextLine();

            if (demandListDB.contains(new DemandList(demandListID)) == true) {
                demandList = demandListDB.get(new DemandList(demandListID));
                oriRegisterDate = demandList.getDateRegister();
                boolean validIndex = true;
                do {
                    System.out.println(demandListUpdateMenu());
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
                                    System.out.println("Enter demand name: ");
                                    demandList.setDemandName(input.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 2:
                                    System.out.println("Enter demand description: ");
                                    demandList.setDescription(input.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 3:

                                    System.out.println("Enter demand register date [dd. MMM. yyyy]: ");
                                    demandList.setDateRegister(LocalDate.parse(input.nextLine(), dtfDate));
                                    break;

                                default:
                                    System.out.println("Index " + splitIndexInt[i] + "out of bound!");
                            }
                        }

                        if (splitIndexInt.length != 0) {
                            System.out.println("Confirm update Demand list ? (Y/N)");
                            confirmation = input.nextLine();

                            if (confirmation.toUpperCase().equals("Y")) {
                                demandList.setDateModified(new Timestamp(System.currentTimeMillis()));
                                if (oriRegisterDate != demandList.getDateRegister()) {
                                    demandListDB.delData(oriRegisterDate, demandList);
                                    demandListDB.addData(demandList.getDateRegister(), demandList);
                                } else {
                                    demandListDB.updateData(demandList.getDateRegister(), demandList);
                                }
                            }

                            System.out.println(confirmation.toUpperCase().equals("Y") ? "Update Demand list successfully" : "Update Demand list abort");
                        } else {
                            System.out.println("No data selected to be update...");
                        }
                    }

                } while (validIndex == false);
            } else {
                System.out.println("Demand list ID not found, update Demand list abort");
            }

            System.out.println("Continue update Demand list ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");
        } while (option.toUpperCase().equals("Y"));

    }

    public void delete(RedBlackTree<LocalDate, DemandList> demandListDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String demandListID = "";

        do {
            DemandList.demandTable(demandListDB);

            System.out.println("Enter demand list ID: ");
            demandListID = input.nextLine();
            DoublyLinkedList<DemandList> demandLists = demandListDB.getAllList();
            if (demandLists.contains(new DemandList(demandListID)) == true) {
                System.out.println("Confirm deactive demand list ? (Y/N)");
                confirmation = input.nextLine();

                if (confirmation.toUpperCase().equals("Y")) {
                    DemandList demandList = demandLists.getAt(demandLists.indexOf(new DemandList(demandListID)));
                    demandList.setStatus("Inactive");
                    demandList.setDateModified(new Timestamp(System.currentTimeMillis()));
                    demandListDB.updateData(demandList.getDateRegister(), demandList);
                }
            } else {
                System.out.println("Demand list ID not found, update campaign abort");
            }
            System.out.println("Continue deactive Demand list  ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");
        } while (option.toUpperCase().equals("Y"));

    }

    @Override
    public void controlPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
