/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.SinglyLinkedList;
import entity.Donor;
import java.util.Scanner;
import utils.DonorPredicates;

/**
 *
 * @author Wong Phey Zhen
 */
public class DonorPanel implements Panel {

    public void donorPanel(SinglyLinkedList<Donor> donorDB) throws CloneNotSupportedException {
        int option = 0;
        Scanner sc = new Scanner(System.in);

        do {
            try {
                menu();
                System.out.print("\nEnter a selection:");
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
                System.out.println("Please enter a numeric...");
            }

        } while (option != 6);

    }

    @Override
    public void controlPanel() {

    }

    @Override
    public String menu() {
        StringBuilder menu = new StringBuilder();

        System.out.println();
        System.out.println("1.Add new Donor");
        System.out.println("2.View donor list");
        System.out.println("3.Update donor list");
        System.out.println("4.Delete Donor");
        System.out.println("5.Search donor");
        System.out.println("6.Exit");

        return menu.toString();
    }

    public static StringBuilder updateDataMenu() {
        StringBuilder updateDataMenu = new StringBuilder();
        System.out.println("1. Donor name   ");
        System.out.println("2. Donor type   ");
        System.out.println("3. Gender(M/F)  ");
        System.out.println("4. Register No/NRIC  ");
        System.out.println("5. Email        ");
        System.out.println("6. Phone number ");
        System.out.println("7. Address      ");
        System.out.println("8. Status       ");
        return updateDataMenu;
    }

    public void add(SinglyLinkedList<Donor> donorDB) {
        Scanner sc = new Scanner(System.in);
        Scanner s = new Scanner(System.in);
        String donorType = "";
        char option = ' ';
        int opt = 0;

        do {
            Donor donor = new Donor();
            sc.nextLine();
            System.out.print("\nDonor name   :");
            donor.setName(sc.nextLine());

            System.out.println("\nDonor type   :");
            System.out.println("1.Organization");
            System.out.println("2.Individual");
            System.out.print("Enter a selection:");
            opt = sc.nextInt();

            if (opt == 1) {
                donor.setDonorType("organization");

                donor.setGender(' ');

                sc.nextLine();
                System.out.print("\nRegister No  :");
                donor.setIc(sc.nextLine());

            } else {
                donor.setDonorType("individual");
                sc.nextLine();
                System.out.print("\nGender(M/F)  :");
                donor.setGender(sc.next().charAt(0));
                sc.nextLine();
                System.out.print("\nNRIC         :");
                donor.setIc(sc.nextLine());

            }

            System.out.print("\nEmail        :");
            donor.setEmail(sc.nextLine());

            System.out.print("\nPhone number :");
            donor.setPhoneNo(sc.nextLine());

            System.out.print("\nAddress      :");
            donor.setAddress(sc.nextLine());

            donor.setStatus("active");

            System.out.print("\nConfirm add Donor(Y/N) :");
            option = sc.next().charAt(0);
            if (option == 'Y' || option == 'y') {
                donor.setAccountID(donor.autoGenerateID());
                donorDB.add(donor);
            }

            System.out.print("\nContinue add Donor (Y/N):");
            option = sc.next().charAt(0);

        } while (option == 'Y' || option == 'y');

    }

    public void display(SinglyLinkedList<Donor> donorDB) {
        Donor.donorTable(donorDB);
    }

    public void update(SinglyLinkedList<Donor> donorDB) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);
        char option = ' ';
        String opt = "";
        String donorID = "";
        Donor donor = new Donor();

        do {
            Donor.donorTable(donorDB);
            sc.nextLine();
            System.out.print("Enter Donor ID:");
            donorID = sc.nextLine();

            if (donorDB.contains(new Donor(donorID)) == true) {
                SinglyLinkedList<Donor> donors = donorDB;
                donor = donors.getAt(donors.indexOf(new Donor(donorID))).clone();
                boolean optvalidation = true;

                do {

                    System.out.println(updateDataMenu().toString());
                    optvalidation = true;
                    System.out.print("\nEnter the number want to update, if multiple index leave space at between [1 5 6]: ");
                    opt = sc.nextLine();

                    String[] splitOpt = opt.split("\\s+");
                    int[] optSplit = new int[splitOpt.length];

                    for (int i = 0; i < splitOpt.length; i++) {
                        try {
                            optSplit[i] = Integer.valueOf(splitOpt[i]);
                        } catch (Exception e) {
                            optvalidation = false;
                            break;
                        }
                    }

                    if (optvalidation == true) {
                        boolean hasUpdateSomething = false;
                        for (int i = 0; i < optSplit.length; i++) {
                            switch (optSplit[i]) {
                                case 1:
                                    sc.nextLine();
                                    System.out.print("\nDonor name   :");
                                    donor.setName(sc.nextLine());
                                    hasUpdateSomething = true;
                                    break;

                                case 2:
                                    System.out.println("Donor type   :");
                                    System.out.println("1.Organization");
                                    System.out.println("2.Individual");
                                    int selection = sc.nextInt();

                                    if (selection == 1) {
                                        donor.setDonorType("organization");

                                        donor.setGender(' ');
                                        sc.nextLine();
                                        System.out.print("\nRegister No  :");
                                        donor.setIc(sc.nextLine());

                                    } else {
                                        donor.setDonorType("individual");
                                        sc.nextLine();
                                        System.out.print("\nGender(M/F)  :");
                                        donor.setGender(sc.next().charAt(0));
                                        sc.nextLine();
                                        System.out.print("\nNRIC         :");
                                        donor.setIc(sc.nextLine());

                                    }
                                    hasUpdateSomething = true;
                                    break;

                                case 3:
                                    String donorType = donor.getDonorType();
                                    if (donorType == "organization") {
                                        donor.setGender(' ');
                                        System.out.print("\nThe donor type is organization");

                                    } else {
                                        System.out.print("\nGender(M/F)  :");
                                        donor.setGender(sc.next().charAt(0));
                                        hasUpdateSomething = true;
                                    }
                                    break;

                                case 4:
                                    donorType = donor.getDonorType();
                                    sc.nextLine();
                                    if (donorType == "organization") {
                                        System.out.print("\nRegister No  :");
                                        donor.setIc(sc.nextLine());
                                        hasUpdateSomething = true;
                                    } else {
                                        System.out.print("\nNRIC           :");
                                        donor.setIc(sc.nextLine());
                                        hasUpdateSomething = true;
                                    }
                                    break;

                                case 5:
                                    sc.nextLine();
                                    System.out.print("\nEmail        :");
                                    donor.setEmail(sc.nextLine());
                                    hasUpdateSomething = true;
                                    break;

                                case 6:
                                    sc.nextLine();
                                    System.out.print("\nPhone number :");
                                    donor.setPhoneNo(sc.nextLine());
                                    hasUpdateSomething = true;
                                    break;

                                case 7:
                                    sc.nextLine();
                                    System.out.print("\nAddress      :");
                                    donor.setAddress(sc.nextLine());
                                    hasUpdateSomething = true;
                                    break;

                                case 8:
                                    sc.nextLine();
                                    System.out.println("\nStatus       :");
                                    donor.setStatus(sc.nextLine());
                                    hasUpdateSomething = true;
                                    break;

                                default:
                                    System.out.println("Index " + optSplit[i] + " not found...");
                                    break;
                            }
                        }
                        if (hasUpdateSomething == true) {
                            System.out.print("\nConfirm update (Y/N)");
                            option = sc.next().charAt(0);
                            if (option == 'Y' || option == 'y') {
                                donorDB.replace(donor, donorDB.indexOf(donor));
                                System.out.println("\nUpdate successfully...");
                            } else {
                                System.out.println("\nUpdate abort...");
                            }
                        }

                    }
                } while (optvalidation == false);
            }
            System.out.print("\nContinue update (Y/N):");
            option = sc.next().charAt(0);
        } while (option == 'Y' || option == 'y');

    
    }
    
    public void delete(SinglyLinkedList<Donor> donorDB) {
        Scanner sc = new Scanner(System.in);
        String id = "";
        String donorID = "";
        String option = "";

        display(donorDB);

        do {
            System.out.print("\nEnter donor ID     :");
            id = sc.nextLine();

            Donor[] donorArray = new Donor[donorDB.getDataCount()];
            donorArray = donorDB.toArray(donorArray);

            //GET SPECIFIC DATA
            if (donorDB.contains(new Donor(id)) == true) {
                Donor donor = donorDB.getAt(donorDB.indexOf(new Donor(id)));

                System.out.print("\nConfirm deactive Donor " + id + " (Y/N): ");
                option = sc.nextLine();

                if (option.toUpperCase().equals("Y")) {
                    donor.setStatus("Inactive");
                    System.out.println(donor.getStatus());
                } else {
                    System.out.println("fail");
                }

            } else {
                System.out.println("Donor ID not found,update donor abort");
            }

            System.out.print("\nContinue Deactive Donor (Y/N): ");
            option = sc.nextLine();

        } while (option.toUpperCase().equals("Y"));

    }

    public void search(SinglyLinkedList<Donor> donorDB) {
        Donor[] donorArray = new Donor[donorDB.getDataCount()];
        donorArray = donorDB.toArray(donorArray);

        SinglyLinkedList<Donor> listForPrint = new SinglyLinkedList<>();
        Donor[] arrListForPrint = null;

        arrListForPrint = DonorPredicates.ControlPanel(donorArray);

        if (arrListForPrint != null && arrListForPrint.length != 0) {
            for (Donor arrListForPrint1 : arrListForPrint) {
                listForPrint.add(arrListForPrint1);
            }
            Donor.donorTable(listForPrint);
        } else {
            System.out.println("No Record Found...");
        }

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



