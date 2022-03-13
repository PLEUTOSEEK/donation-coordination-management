/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Scanner;
import adt.SinglyLinkedList;
import adt.SinglyLinkedListInterface;
import entity.Donor;
import entity.Account;
import java.util.Iterator;

/**
 *
 * @author Wong Phey Zhen
 */
public class DonorPanel implements Panel {

    public void donorPanel(SinglyLinkedList<Donor> donorDB) {
        int option = 0;
        Scanner sc = new Scanner(System.in);

        do {
            try {
                menu();
                System.out.println("Enter a selection:");
                option = sc.nextInt();

                switch (option) {
                    case 1: {
                        add(donorDB);
                        break;
                    }
                    case 2: {
                        display(donorDB);
                        break;
                    }
                    case 3: {
                        update(donorDB);
                        break;
                    }
                    case 4: {
                        delete(donorDB);
                        break;
                    }
                    case 5: {
                        search(donorDB);
                        break;
                    }
                    case 6: {
                        System.out.println("Return to main page...");
                        break;

                    }
                    default:
                        System.out.println("Please enter a numeric.");

                }

            } catch (Exception ex) {
                sc.nextLine();
                System.out.println("Please enter a numeric.");
            }

        } while (option != 6);

    }

    @Override
    public void controlPanel() {

    }

    @Override
    public String menu() {
        StringBuilder menu = new StringBuilder();
        System.out.println("1.Add new Donor");
        System.out.println("2.View donor list");
        System.out.println("3.Update donor list");
        System.out.println("4.Delete Donor");
        System.out.println("5.Search donor");
        System.out.println("6.Exit");

        return menu.toString();
    }

    public void add(SinglyLinkedList<Donor> donorDB) {
        Scanner sc = new Scanner(System.in);
        Scanner s = new Scanner(System.in);
        String donorType = "";
        char option = ' ';
        int opt = 0;
        Donor donor = new Donor();

        do {
            System.out.println("Donor name   :");
            donor.setName(sc.nextLine());

            System.out.println("Donor type   :");
            System.out.println("1.Organization");
            System.out.println("2.Individual");
            opt = sc.nextInt();

            if (opt == 1) {
                donor.setDonorType("organization");

                donor.setGender(' ');

                System.out.println("Register No  :");
                donor.setIc(sc.nextLine());

            } else {
                donor.setDonorType("individual");

                System.out.println("Gender(M/F)  :");
                //donor.setGender(' ');

                System.out.println("IC           :");
                donor.setIc(sc.nextLine());

            }

            System.out.println("Email        :");
            donor.setEmail(sc.nextLine());

            System.out.println("Phone number :");
            donor.setPhoneNo(sc.nextLine());

            System.out.println("Address      :");
            donor.setAddress(sc.nextLine());

            donor.setStatus("active");

            donor.setAccountID(donor.autoGenerateID());

            System.out.println("Continue add Donor (Y/N):");
            option = sc.next().charAt(0);

        } while (option == 'Y' || option == 'y');

    }

    public void display(SinglyLinkedList<Donor> donorDB) {
        Donor.donorTable(donorDB);
    }

    public void update(SinglyLinkedList<Donor> donorDB) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(SinglyLinkedList<Donor> donorDB) {
        Scanner sc = new Scanner(System.in);
        String id = "";
        String donorID = "";
        char option = ' ';

        Donor donor = new Donor();

        display(donorDB);

        do {
            System.out.println("Donor ID     :");
            id = sc.nextLine();

            //GET SPECIFIC DATA
            if (donorDB.contains(new Donor(donorID)) == true) {
                System.out.println("Confirm deactive Donor " + id + " (Y/N):");
                option = sc.next().charAt(0);

                if (option == 'Y' || option == 'y') {
                    donor.setStatus("Inactive");
                }

            } else {
                System.out.println("Donor ID not found...");
            }

            System.out.println("Continue Deactive Donor (Y/N):");
            option = sc.next().charAt(0);
        } while (option == 'Y' || option == 'y');

    }

    public void search(SinglyLinkedList<Donor> donorDB) {
        Scanner sc = new Scanner(System.in);
        String id = "";
        String donorID = "";
        char option = ' ';

        do {
            System.out.println("Donor ID     :");
            id = sc.nextLine();

            //GET SPECIFIC DATA
            if (donorDB.contains(new Donor(id)) == true) {
                donorDB.printSpec(new Donor(id));
            } else {
                System.out.println("Donor ID not found...");
            }

            System.out.println("Continue Search Donor (Y/N):");
            option = sc.next().charAt(0);

        } while (option == 'Y' || option == 'y');
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

}
