/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.DoublyLinkedList;
import adt.ListInterface;
import adt.RedBlackTree;
import entity.Campaign;
import entity.DemandList;
import entity.Funds;
import entity.Sponsor;
import entity.SponsorItem;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import utils.SponsorItemInformation;

/**
 *
 * @author Angelina Oon
 */
public class SponsorItemPanel implements Panel {

    public void controlPanel(DoublyLinkedList<SponsorItem> sponsorItemDB, DoublyLinkedList<Sponsor> sponsorDB,
            DoublyLinkedList<Funds> fundsDB, RedBlackTree<LocalDate, DemandList> demandListDB) throws CloneNotSupportedException {

        ListInterface<SponsorItem> sponsorItem;

        int opt;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("\n1.Add new sponsor item");
            System.out.println("2.View sponsor item list");
            System.out.println("3.Search sponsor item");
            System.out.println("4.Exit");
            System.out.print("Please select an action: ");
            opt = s.nextInt();

            switch (opt) {
                case 1: {
                    sponsorItemDB = addNewSponsorItem(sponsorItemDB, sponsorDB, fundsDB, demandListDB);
                    break;
                }
                case 2: {
                    displaySponsorItem(sponsorItemDB);
                    break;
                }
                case 3: {
                    //searchSponsorItem(sponsorItemDB);
                    break;
                }
                case 4: {
                    //exit
                    break;
                }
            }
        } while (opt != 4);

    }

    public DoublyLinkedList<SponsorItem> addNewSponsorItem(DoublyLinkedList<SponsorItem> sponsorItemDB, DoublyLinkedList<Sponsor> sponsorDB,
            DoublyLinkedList<Funds> fundsDB, RedBlackTree<LocalDate, DemandList> demandListDB) {

        String confirm, opt;

        Scanner s = new Scanner(System.in);
        Sponsor sponsor = new Sponsor();
        DemandList demandList = new DemandList();
        Funds funds = new Funds();
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("H:mm:ss");

        String originalLastID = SponsorItem.getLastSponsoredID();

        do {
            SponsorItem sponsorItem = new SponsorItem();
            sponsorItem.setSponsoredID(sponsorItem.autoGenerateID());

            //SponsorItemInformation sponsorItemInformation = addDemandList(fundsDB, demandListDB, campaignDB);
            sponsorItem.setStatus("Active");

            System.out.print("Confirm add sponsor item? (Y/N)");
            confirm = s.nextLine();

            if (confirm.toUpperCase().equals("Y")) {
                sponsorItemDB.addLast(sponsorItem);

            } else {
                SponsorItem.setLastSponsoredID(originalLastID);

            }
            System.out.println(confirm.toUpperCase().equals("Y") ? "Added successfully!!" : "Add Sponsor Item failed...");

            System.out.print("\nContinue add Item? (Y/N)");
            opt = s.nextLine();

            System.out.println(opt.toUpperCase().equals("Y") ? "" : "Return to sponsor item main page..");

        } while (opt.toUpperCase().equals("Y"));

        return sponsorItemDB;

    }

    public void displaySponsorItem(DoublyLinkedList<SponsorItem> sponsorItemDB) {
        SponsorItem.sponsorItemTable(sponsorItemDB);
    }

    @Override

    public void controlPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String menu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private SponsorItemInformation addDemandList(DoublyLinkedList<Funds> fundsDB, RedBlackTree<LocalDate, DemandList> demandListDB,
            RedBlackTree<LocalDate, Campaign> campaignDB, DoublyLinkedList<SponsorItem> sponsorItemDB) {
        String opt, confirm;
        String lastDemandListID = "";
        Campaign campaign = new Campaign();
        SponsorItem sponsorItem = new SponsorItem();
        Scanner s = new Scanner(System.in);

        DoublyLinkedList<DemandList> demandList = demandListDB.getAllList();

        do {
            //enter demand list id 
            System.out.print("Enter Demand List ID:");
            lastDemandListID = s.nextLine();
            demandList.getAt(demandList.indexOf(new DemandList(lastDemandListID))); //retrieve demand list based on id

            System.out.println(lastDemandListID);

            System.out.print("Confirm ? (Y/N)");
            confirm = s.nextLine();

            if (confirm.toUpperCase().equals("Y")) {
                //demandListDB.addData(;
            } else {

            }

            System.out.println(confirm.toUpperCase().equals("Y") ? "Added successfully!!" : "Add failed...");

            System.out.print("\nContinue add? (Y/N)");
            opt = s.nextLine();

            System.out.println(opt.toUpperCase().equals("Y") ? "" : "Return to main page..");

        } while (opt.toUpperCase().equals("Y"));

        if (campaign.getStatus().toUpperCase().equals("Permanent Inactive")) {
            SponsorItem[] sponsorItemArr = new SponsorItem[sponsorItemDB.getLength()];

        }

//if campaign status = permanent inactive then cannot create sponsorItem
        //type in qty
        //check is type in qty more than needee qty or not,and also check wheather the funds able to cover the amount of Donate
        //qty deduct,funds deduct(qty*per unit)--need to return to addNewSponsorItem
        {
            return null;
        }
    }

    private SponsorItemInformation addFunds() {

        //private addFunds
        //enter funds id
        //retrieve funds based on id
        return null;

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

}
