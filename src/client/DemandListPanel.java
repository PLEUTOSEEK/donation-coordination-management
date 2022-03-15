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
import utils.DemandListPredicates;

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
            System.out.print("Option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    add(campaignDB, demandListDB);
                    break;
                case 2:
                    DemandList.demandTable(demandListDB);
                    break;
                case 3:
                    search(demandListDB);
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
        StringBuilder menu = new StringBuilder();
        System.out.println();
        menu.append("1. Add new demand list \n");
        menu.append("2. Display demand list \n");
        menu.append("3. Search demand list \n");
        menu.append("4. Deactive demand list \n");
        menu.append("5. Update demand list \n");
        menu.append("6. Exit \n");

        return menu.toString();
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

    public void search(RedBlackTree<LocalDate, DemandList> demandListDB) {
        DemandList[] DemandListArr = new DemandList[demandListDB.getAllList().getLength()];
        DemandListArr = demandListDB.getAllArrayList(DemandListArr);
        RedBlackTree<LocalDate, DemandList> listForPrint = new RedBlackTree<>();
        DemandList[] arrListForPrint = null;

        arrListForPrint = DemandListPredicates.ControlPanel(DemandListArr);;

        // CampaignPredicates.ControlPanel(campaignArray);
        if (arrListForPrint != null && arrListForPrint.length != 0) {
            for (DemandList arrListForPrint1 : arrListForPrint) {
                listForPrint.addData(arrListForPrint1.getDateRegister(), arrListForPrint1);
            }
            DemandList.demandTable(listForPrint);
        } else {
            System.out.println("No Record Found...");
        }
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

            System.out.print("Enter campaign ID: ");
            campaignID = input.nextLine();

            if (campaignDB.contains(new Campaign(campaignID)) == true) {
                campaign = campaignDB.get(new Campaign(campaignID));
                if (campaign.isPermanentDelete() == false) {

                    do {

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

                        System.out.print("Continue add demand to this campaign ? (Y/N) ");
                        option = input.nextLine();
                    } while (option.toUpperCase().equals("Y"));
                } else {
                    System.out.println("Campaign with permanent inactive status unable to perform modification");
                }
            } else {
                System.out.println("Campaign ID not found, add demand abort");
            }

            System.out.print("Continue add demand ? (Y/N) ");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");

        } while (option.toUpperCase().equals("Y"));
    }

    public String demandListUpdateMenu() {
        StringBuilder menu = new StringBuilder();
        System.out.println();
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
                if (demandList.getCampaign().isPermanentDelete() == false) {

                    oriRegisterDate = demandList.getDateRegister();
                    boolean validIndex = true;
                    do {
                        System.out.println(demandListUpdateMenu());
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
                                System.out.print("Confirm update Demand list ? (Y/N) ");
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
                    System.out.println("Campaign with permanent inactive status unable to perform modification");
                }
            } else {
                System.out.println("Demand list ID not found, update Demand list abort");
            }

            System.out.print("Continue update Demand list ? (Y/N) ");
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
                DemandList demandList = demandLists.getAt(demandLists.indexOf(new DemandList(demandListID)));
                if (demandList.getCampaign().isPermanentDelete() == false) {
                    System.out.print("Confirm deactive demand list ? (Y/N) ");

                    confirmation = input.nextLine();

                    if (confirmation.toUpperCase().equals("Y")) {
                        demandList.setStatus("Inactive");
                        demandList.setDateModified(new Timestamp(System.currentTimeMillis()));
                        demandListDB.updateData(demandList.getDateRegister(), demandList);
                    }
                } else {
                    System.out.println("Campaign with permanent inactive status unable to perform modification");
                }
            } else {
                System.out.println("Demand list ID not found, update campaign abort");
            }
            System.out.print("Continue deactive Demand list  ? (Y/N) ");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");
        } while (option.toUpperCase().equals("Y"));

    }

    @Override
    public void controlPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
