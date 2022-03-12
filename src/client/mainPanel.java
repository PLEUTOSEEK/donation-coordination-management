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
import entity.Donee;
import entity.DoneeList;
import entity.Donor;
import entity.DonorList;
import entity.Sponsor;
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
            DoublyLinkedList<Donee> doneeDB, RedBlackTree<LocalDate, DoneeList> doneeListDB, DoublyLinkedList<Donor> donorDB,
            RedBlackTree<LocalDate, DonorList> donorListDB,
            RedBlackTree<LocalDate, DemandList> demandListDB) {
        CampaignPanel campaignPanel = new CampaignPanel();

        Scanner input = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println(menu());
            System.out.println("Option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    campaignPanel.controlPanel(campaignDB, sponsorDB, sponsorListDB, doneeDB, doneeListDB, donorDB, donorListDB, demandListDB);
                    break;
                case 5:
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

        menu.append("1. Sponsor\n");
        menu.append("2. Donor\n");
        menu.append("3. Donee\n");
        menu.append("4. Campaign\n");
        menu.append("5. Sponsor\n");
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
