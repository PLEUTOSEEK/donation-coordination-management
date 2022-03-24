/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.DoublyLinkedList;
import adt.ListInterface;
import adt.RedBlackTree;
import entity.DemandList;
import entity.Funds;
import entity.Sponsor;
import entity.SponsorItem;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Angelina Oon
 */
public class FundsPanel implements Panel {

    public void controlPanel(DoublyLinkedList<Funds> fundsDB, DoublyLinkedList<Sponsor> sponsorDB,
            RedBlackTree<LocalDate, DemandList> demandListDB) throws CloneNotSupportedException {

        ListInterface<Funds> funds;

        int opt;
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("\n1.Add new funds");
            System.out.println("2.View funds list");
            System.out.println("3.Search funds item");
            System.out.println("4.Exit");
            System.out.print("Please select an action: ");
            opt = s.nextInt();
            switch (opt) {
                case 1: {
                    addFunds(fundsDB, sponsorDB);
                    break;
                }
                case 2: {
                    displayFunds(fundsDB);
                    break;
                }
                case 3: {
                    //searchFunds();
                    break;
                }
                case 4: {
                    //exit
                    break;
                }
            }

        } while (opt != 4);

    }
// if demandList inactive sponsor cannot add funds

    public void addFunds(DoublyLinkedList<Funds> fundsDB,
            DoublyLinkedList<Sponsor> sponsorDB) {

        String confirm, opt;
        String lastDemandListID = "";
        String lastsponsorID = "";
        double tAmount = 0.00;
        Scanner s = new Scanner(System.in);
        String fundsLastID = Funds.getLastFundsID();
        Funds funds = new Funds();
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        do {
            Sponsor sponsor = new Sponsor();
            Sponsor.sponsorTable(sponsorDB);
            System.out.print("Enter Sponsor ID:");
            lastsponsorID = s.nextLine();

            if (sponsorDB.contains(new Sponsor(lastsponsorID)) == true) {
                sponsor = sponsorDB.getAt(sponsorDB.indexOf(new Sponsor(lastsponsorID)));

                if (sponsor.isInActive() == false) {

                    fundsLastID = Funds.getLastFundsID();
                    funds.setSponsor(sponsor);
                    funds.setFundsID(funds.autoGenerateID());

                    System.out.print("Enter total amount:");
                    tAmount = s.nextDouble();
                    funds.setOriginalTotalAmount(tAmount);

                    funds.setTotalAmount(tAmount);

                    s.nextLine(); //clear buffer

                    System.out.print("Enter date of pay [dd.MM.yyyy]:");
                    funds.setDatePay(LocalDate.parse(s.nextLine(), dtfDate));

                    funds.setDateModified(new Timestamp(System.currentTimeMillis()));

                    funds.setStatus("Active");

                    System.out.print("Confirm to add Funds?(Y/N):");
                    confirm = s.nextLine();

                    if (confirm.toUpperCase().equals("Y")) {
                        fundsDB.addLast(funds);
                        System.out.println("Added successfully...");

                    } else {
                        Funds.setLastFundsID(fundsLastID);
                        Sponsor.setLastSponsorID(lastsponsorID);

                        System.out.println("Add Funds Failed");

                    }

                } else {
                    System.out.println("This is Inactive");
                }

            }

            System.out.print("Continue adding new funds? (Y/N) ");
            opt = s.nextLine();

        } while (opt.toUpperCase().equals("Y"));

    }

    public void displayFunds(DoublyLinkedList<Funds> fundsDB) {
        Funds.fundsTable(fundsDB);
    }

    @Override
    public void controlPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
