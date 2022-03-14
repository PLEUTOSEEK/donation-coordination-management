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
import java.util.Iterator;
import utils.DonorPredicates;

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
        System.out.println("1. Add new Donor");
        System.out.println("2. View donor list");
        System.out.println("3. Update donor list");
        System.out.println("4. Delete Donor");
        System.out.println("5. Search donor");
        System.out.println("6. Exit");

        return menu.toString();
    }

    public static StringBuilder updateDataMenu() {
        StringBuilder updateDataMenu = new StringBuilder();
        System.out.println("1. Donor name   ");
        System.out.println("2. Donor type   ");
        System.out.println("3. Gender(M/F)  ");
        System.out.println("4. Register No/IC  ");
        System.out.println("5. Email        ");
        System.out.println("6. Phone number ");
        System.out.println("7. Address      ");
        System.out.println("8. tatus       ");
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
                System.out.print("\nIC           :");
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

    public void update(SinglyLinkedList<Donor> donorDB) {
        Scanner sc = new Scanner(System.in);
        char option = ' ';
        String opt = "";
        String donorID = "";
        Donor donor = new Donor();
        boolean update = false;

        do {
            Donor.donorTable(donorDB);

            System.out.println("Enter Donor ID:");
            donorID = sc.nextLine();
            boolean optvalidation = true;
            do {
                optvalidation = true;
                System.out.println(updateDataMenu().toString());
                System.out.println("If multiple selection can type in this format [1 3 5])");
                System.out.print("Enter selection: ");
                opt = sc.nextLine();
                String[] splitOpt = opt.split("\\s+");
                int[] optSplit = new int[splitOpt.length];

                for (int i = 0; 1 < splitOpt.length; i++) {
                    try {
                        optSplit[i] = Integer.valueOf(splitOpt[i]);
                    } catch (Exception e) {
                        optvalidation = false;
                        break;
                    }
                }

                if (optvalidation == true) {

                    for (int i = 0; i < splitOpt.length; i++) {
                        switch (optSplit[i]) {
                            case 1: {
                                System.out.print("\nDonor name   :");
                                donor.setName(sc.nextLine());
                                update = true;
                            }
                            case 2: {
                                System.out.println("Donor type   :");
                                System.out.println("1.Organization");
                                System.out.println("2.Individual");
                                int selection = sc.nextInt();

                                if (selection == 1) {
                                    donor.setDonorType("organization");

                                    donor.setGender(' ');

                                    System.out.print("\nRegister No  :");
                                    donor.setIc(sc.nextLine());

                                } else {
                                    donor.setDonorType("individual");

                                    System.out.print("\nGender(M/F)  :");
                                    //donor.setGender(' ');

                                    System.out.print("\nIC           :");
                                    donor.setIc(sc.nextLine());

                                }
                            }
                            case 3: {
                                String donorType = donor.getDonorType();
                                if (donorType == "organization") {
                                    donor.setGender(' ');
                                    System.out.print("\nThe donor type is organization");
                                } else {
                                    System.out.print("\nGender(M/F)  :");
                                    //donor.setGender(' ');
                                }
                                update = true;
                            }
                            case 4: {
                                String donorType = donor.getDonorType();

                                if (donorType == "organization") {
                                    System.out.println("Register No  :");
                                    donor.setIc(sc.nextLine());
                                } else {
                                    System.out.println("IC           :");
                                    donor.setIc(sc.nextLine());
                                }

                                update = true;
                            }
                            case 5: {
                                System.out.println("Email        :");
                                donor.setEmail(sc.nextLine());
                                update = true;
                            }
                            case 6: {
                                System.out.println("Phone number :");
                                donor.setPhoneNo(sc.nextLine());
                                update = true;
                            }
                            case 7: {
                                System.out.println("Address      :");
                                donor.setAddress(sc.nextLine());
                                update = true;
                            }
                            case 8: {
                                System.out.println("Status       :");
                                donor.setStatus(sc.nextLine());
                                update = true;
                            }
                            default:
                                System.out.println(optSplit[i] + " not found...");
                        }
                    }
                    if (update == true) {
                        System.out.println("Confirm update Donor (Y/N) (Y/N)");
                        option = sc.next().charAt(0);
                        if (option == 'Y' || option == 'y') {
                            System.out.println("Update successfully...");
                        } else {
                            System.out.println("Update abort...");
                        }
                    }

                }
            } while (optvalidation == false);
            System.out.println("Continue update campaign ? (Y/N)");
            option = sc.next().charAt(0);
            display(donorDB);
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

                System.out.print("\nConfirm deactive Donor " + id + " (Y/N):");
                option = sc.nextLine();

                if (option.toUpperCase().equals("Y")) {
                    donor.setStatus("Inactive");
                    System.out.println(donor.getStatus());
                }else{
                    System.out.println("fail");
                }

            } else {
                System.out.println("Donor ID not found,update donor abort");
            }

            System.out.print("\nContinue Deactive Donor (Y/N):");
            option = sc.nextLine();
            
        } while(option.toUpperCase().equals("Y"));

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
