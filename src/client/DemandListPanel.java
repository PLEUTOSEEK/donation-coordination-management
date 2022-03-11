/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

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

    private void update(RedBlackTree<LocalDate, DemandList> demandListDB) {

    }

    private void delete(RedBlackTree<LocalDate, DemandList> demandListDB) {
    }

    @Override
    public void controlPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
