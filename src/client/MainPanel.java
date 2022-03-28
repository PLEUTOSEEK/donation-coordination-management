/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.CircularLinkedList;
import adt.CircularLinkedQueue;
import adt.DoublyLinkedList;
import adt.RedBlackTree;
import adt.SinglyLinkedList;
import entity.Campaign;
import entity.DemandList;
import entity.Donation;
import entity.Donee;
import entity.DoneeList;
import entity.Donor;
import entity.DonorList;
import entity.Funds;
import entity.Sponsor;
import entity.SponsorItem;
import entity.SponsorList;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class MainPanel implements Panel {

    public void controlPanel(
            RedBlackTree<LocalDate, Campaign> campaignDB,
            DoublyLinkedList<Sponsor> sponsorDB,
            RedBlackTree<LocalDate, SponsorList> sponsorListDB,
            CircularLinkedQueue<Donee> doneeDB, DoublyLinkedList<Donee> doneeInHelpDB, RedBlackTree<LocalDate, DoneeList> doneeListDB, SinglyLinkedList<Donor> donorDB,
            RedBlackTree<LocalDate, DonorList> donorListDB,
            RedBlackTree<LocalDate, DemandList> demandListDB,
            DoublyLinkedList<Funds> fundsDB,
            CircularLinkedList<Donation> donationDB,
            DoublyLinkedList<SponsorItem> sponsorItemDB) throws CloneNotSupportedException {
<<<<<<< HEAD
=======

        CampaignPanel campaignPanel = new CampaignPanel();
>>>>>>> 83b877b6f04438dc724889cb0296f29ff9b3ad35

        CampaignPanel campaignPanel = new CampaignPanel();
        DoneePanel doneePanel = new DoneePanel();
<<<<<<< HEAD
        SponsorPanel sponsorPanel = new SponsorPanel();
        DonorPanel donorPanel = new DonorPanel();
=======
        DonorPanel donorPanel = new DonorPanel();

>>>>>>> 83b877b6f04438dc724889cb0296f29ff9b3ad35
        DonationPanel donationPanel = new DonationPanel();

        Scanner input = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println(menu());
            System.out.print("Option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    sponsorPanel.controlPanel(sponsorDB, fundsDB, demandListDB, sponsorItemDB);
                    break;
                case 2:
                    donorPanel.controlPanel(donorDB);
                    break;
                case 3:
                    doneePanel.controlPanel(doneeDB);
                    break;
                case 4:
                    campaignPanel.controlPanel(campaignDB, sponsorDB, sponsorListDB, doneeDB, doneeInHelpDB, doneeListDB, donorDB, donorListDB, demandListDB);
                    break;
                case 5:
                    donationPanel.controlPanel(donationDB, campaignDB, doneeDB, donorDB);
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
        System.out.println();
        menu.append("1. Sponsor\n");
        menu.append("2. Donor\n");
        menu.append("3. Donee\n");
        menu.append("4. Campaign\n");
        menu.append("5. Donation\n");
        menu.append("6. Exit\n");

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

    @Override
    public void exit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void controlPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
