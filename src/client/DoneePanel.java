/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.CircularLinkedQueue;
import adt.QueueInterface;
import entity.Donee;
import java.util.Iterator;
import java.util.Scanner;
import utils.DoneePredicates;

/**
 * @author Wong Jun Yao
 */
public class DoneePanel implements Panel {

    public void controlPanel(CircularLinkedQueue<Donee> doneeDB) throws CloneNotSupportedException {

        QueueInterface<Donee> doneeQueue;
        Iterator<Donee> iterator;

        int opt;
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("\n1.Add new donee");
            System.out.println("2.View donee list");
            System.out.println("3.Modify donee details");
            System.out.println("4.Search donee");
            System.out.println("5.Delete donee");
            System.out.println("6.Exit");
            System.out.print("Please select an action: ");
            opt = s.nextInt();

            switch (opt) {
                case 1: {
                    doneeDB = addNewDonee(doneeDB);
                    break;
                }
                case 2: {
                    displayDonee(doneeDB);
                    break;
                }
                case 3: {
                    modifyDonee(doneeDB);
                    break;
                }
                case 4: {
                    searchDonee(doneeDB);
                    //searchDonee(doneeDB);
                    break;
                }
                case 5: {
                    deleteDonee(doneeDB);
                    break;
                }
                case 6: {
                    //exit
                    break;
                }
            }
        } while (opt != 6);
    }

    public CircularLinkedQueue<Donee> addNewDonee(CircularLinkedQueue<Donee> doneeDB) {

        String confirm, opt;
        char gender = ' ';
        double requestAmount;

        Scanner s = new Scanner(System.in);

        do {
            Donee donee = new Donee();

            System.out.print("\nName:");
            donee.setName(s.nextLine());

            System.out.printf("NRIC No:");
            donee.setIc(s.nextLine());

            System.out.printf("Gender(F/M):");
            donee.setGender(s.next().charAt(0));
            s.nextLine(); //clear buffer

            System.out.printf("Email:");
            donee.setEmail(s.nextLine());

            System.out.printf("Contact Number:");
            donee.setPhoneNo(s.nextLine());

            System.out.printf("Address:");
            donee.setAddress(s.nextLine());

            System.out.printf("Request Issue:");
            donee.setRequestIssue(s.nextLine());

            System.out.printf("Request Amount:");
            donee.setRequestAmount(s.nextDouble());
            s.nextLine();

            System.out.printf("Bank Type(Public Bank/Maybank):");
            donee.setBankType(s.nextLine());

            System.out.printf("Bank Account:");
            donee.setBankAcc(s.nextLine());

            System.out.print("\nConfirm add donee? (Y/N)");
            confirm = s.nextLine();

            if (confirm.toUpperCase().equals("Y")) {
                donee.setAccountID(donee.autoGenerateID());
                donee.setStatus("Active");
                doneeDB.enqueue(donee);
            }

            System.out.println(confirm.toUpperCase().equals("Y") ? "Added successfully!!" : "Add donee failed...");

            System.out.print("\nContinue add donee? (Y/N)");
            opt = s.nextLine();

            System.out.print(opt.toUpperCase().equals("Y")
                    ? "" : "Return to donee menu page..\n");

        } while (opt.toUpperCase().equals("Y"));

        return doneeDB;
    }

    public String doneeUpdateMenu() {
        StringBuilder menu = new StringBuilder();

        menu.append("1. Name\n");
        menu.append("2. NRIC\n");
        menu.append("3. Gender\n");
        menu.append("4. Email\n");
        menu.append("5. Phone No\n");
        menu.append("6. Address\n");
        menu.append("7. Request Issue\n");
        menu.append("8. Request Amount No\n");
        menu.append("9. Bank Type\n");
        menu.append("10. Bank Account\n");

        return menu.toString();
    }

    public void modifyDonee(CircularLinkedQueue<Donee> doneeDB) throws CloneNotSupportedException {

        String opt, select, confirm;
        String id = "";
        Scanner s = new Scanner(System.in);
        Donee donee = new Donee();
        Donee memoDonee = new Donee();

        do {
            Donee.doneeTable(doneeDB);
            System.out.print("Enter a Donee Id:");
            id = s.nextLine();

            if (doneeDB.contains(new Donee(id)) == true) {
                CircularLinkedQueue<Donee> donees = doneeDB;
                donee = donees.getAt(donees.indexOf(new Donee(id)));
                memoDonee = donee.clone();
                boolean validIndex = true;
                do {
                    System.out.println(doneeUpdateMenu());
                    validIndex = true;
                    System.out.print("Enter the number want to update, if multiple index leave space at between [1 5 6]: ");
                    select = s.nextLine();

                    String[] splitIndex = select.split("\\s+");
                    int[] splitIndexInt = new int[splitIndex.length];

                    //donee = doneeDB.getFront();
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
                                    System.out.print("Enter the new name: ");
                                    memoDonee.setName(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 2:
                                    System.out.print("Enter the new NRIC: ");
                                    memoDonee.setIc(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 3:
                                    System.out.print("Enter the new gender: ");
                                    memoDonee.setGender(s.next().charAt(0));
                                    s.nextLine();
                                    hasUpdateSomething = true;
                                    break;
                                case 4:
                                    System.out.print("Enter the new email: ");
                                    memoDonee.setEmail(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 5:
                                    System.out.print("Enter the new phone no: ");
                                    memoDonee.setPhoneNo(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 6:
                                    System.out.print("Enter the new address: ");
                                    memoDonee.setAddress(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 7:
                                    System.out.print("Enter the new request issue: ");
                                    memoDonee.setRequestIssue(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 8:
                                    System.out.print("Enter the new request amount: ");
                                    memoDonee.setRequestAmount(s.nextDouble());
                                    s.nextLine();
                                    hasUpdateSomething = true;
                                    break;
                                case 9:
                                    System.out.print("Enter the new bank type: ");
                                    memoDonee.setBankType(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 10:
                                    System.out.print("Enter the new bank account: ");
                                    memoDonee.setBankAcc(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                default:
                                    System.out.println("Index " + splitIndexInt[i] + "out of bound!");
                                    break;
                            }
                        }

                        if (hasUpdateSomething == true) {
                            System.out.print("\nConfirm update ? (Y/N)");
                            confirm = s.nextLine();

                            if (confirm.toUpperCase().equals("Y")) {
                                donee.copy(memoDonee);
                                doneeDB.modify(donees.getAt(donees.indexOf(new Donee(donee.getAccountID()))), donee);
                            }
                            System.out.println(confirm.toUpperCase().equals("Y") ? 
                                    "Update successfully!!\n" : "Update donee failed...\n");

                        } else {
                            System.out.println("No data selected");
                        }
                    }
                } while (validIndex == false);
            } else {
                System.out.println("Donee ID not found..");
            }

            System.out.print("Continue update donee ? (Y/N) ");
            opt = s.nextLine();

            System.out.println(opt.toUpperCase().equals("Y") ? "" : "Return to donee main page...");
            
        } while (opt.toUpperCase().equals("Y"));
    }

    public void displayDonee(CircularLinkedQueue<Donee> doneeDB) {
        Donee.doneeTable(doneeDB);
    }

    public void searchDonee(CircularLinkedQueue<Donee> doneeDB) {

        Donee[] doneeArray = new Donee[doneeDB.getLength()];
        doneeArray = doneeDB.toArray(doneeArray);
        CircularLinkedQueue<Donee> listForPrint = new CircularLinkedQueue<>();
        Donee[] arrListForPrint = null;

        arrListForPrint = DoneePredicates.ControlPanel(doneeArray);

        if (arrListForPrint != null && arrListForPrint.length != 0) {
            for (Donee donee : arrListForPrint) {
                listForPrint.enqueue(donee);
            }
            Donee.doneeTable(listForPrint);
        } else {
            System.out.println("No Record Found...");
        }
    }

    public void deleteDonee(CircularLinkedQueue<Donee> doneeDB) {
        String opt, select, confirm;
        String doneeID = "";
        Scanner s = new Scanner(System.in);
        Donee donee = new Donee();
        CircularLinkedQueue<Donee> temp = new CircularLinkedQueue<Donee>();

        do {
            Donee.doneeTable(doneeDB);
            System.out.print("Enter a Donee Id:");
            doneeID = s.nextLine();

            Donee[] doneeArray = new Donee[doneeDB.getLength()];
            doneeArray = doneeDB.toArray(doneeArray);

            if (doneeDB.contains(new Donee(doneeID)) == true) {
                CircularLinkedQueue<Donee> donees = doneeDB;
                donee = donees.getAt(donees.indexOf(new Donee(doneeID)));

                System.out.print("Confirm deactive " + doneeID + " donee ? (Y/N) ");
                confirm = s.nextLine();

                if (confirm.toUpperCase().equals("Y")) {
                    donee.setStatus("Inactive");
                }

                System.out.println(confirm.toUpperCase().equals("Y") ?
                        "Update successfully!!\n" : "Update failed...");
            }

            System.out.print("Continue deactive donee ? (Y/N) ");
            opt = s.nextLine();

        } while (opt.toUpperCase().equals("Y"));
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
