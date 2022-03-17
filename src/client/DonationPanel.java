/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.CircularLinkedList;
import adt.CircularLinkedListInterface;
import adt.CircularLinkedQueue;
import adt.DoublyLinkedList;
import adt.RedBlackTree;
import adt.SinglyLinkedList;
import entity.Campaign;
import entity.Donation;
import entity.Donee;
import entity.Donor;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Looi Jia Toong
 */
public class DonationPanel implements Panel {

    @Override
    public void controlPanel() {

    }

    public void donationPanel(
            CircularLinkedList<Donation> donationDB,
            RedBlackTree<LocalDate, Campaign> campaignDB,
            CircularLinkedQueue<Donee> doneeDB,
            SinglyLinkedList<Donor> donorDB) throws CloneNotSupportedException {

        int opt;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(menu());
            System.out.println("Option: ");
            opt = sc.nextInt();

            switch (opt) {
                case 1:
                    addDonorToDonee(donationDB, doneeDB, donorDB); //donor to donee
                    break;
                case 2:
                    addDonorToCampaign(donationDB, campaignDB, donorDB); //donor to campaign
                    break;
                case 3:
                    //Donation.donationTable(donationDB);
                    break;
                case 4:
                    //search
                    break;
                case 5:
                    //delete
                    break;
                case 6:
                    //update
                    break;
                case 7:
                    System.out.println("Return to previous page...");
                    break;
                default:
                    System.out.println("Please enter number between 1 to 6");
            }
        } while (opt != 7);
    }

    @Override
    public String menu() {
        StringBuilder menu = new StringBuilder();
        System.out.println();
        menu.append("1. Add new donation (donor to donee) \n");
        menu.append("2. Add new donation (donor to campaign) \n");
        menu.append("3. Display all donation \n");
        menu.append("4. Search donation \n");
        menu.append("5. Delete donation \n");
        menu.append("6. Update donation \n");
        menu.append("7. Exit \n");

        return menu.toString();
    }

    @Override
    public void add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addDonorToDonee(CircularLinkedList<Donation> donationDB,
            CircularLinkedQueue<Donee> doneeDB,
            SinglyLinkedList<Donor> donorDB) throws CloneNotSupportedException {

    }

    public void addDonorToCampaign(CircularLinkedList<Donation> donationDB,
            RedBlackTree<LocalDate, Campaign> campaignDB,
            SinglyLinkedList<Donor> donorDB) throws CloneNotSupportedException {
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
