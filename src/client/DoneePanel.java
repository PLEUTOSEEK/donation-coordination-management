/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Scanner;
import adt.CircularLinkedQueue;
import adt.QueueInterface;
import entity.Donee;
import java.util.Iterator;

/**
 * @author Wong Jun Yao
 */
public class DoneePanel implements Panel {

    public void doneePanel(CircularLinkedQueue<Donee> doneeDB) {

        QueueInterface<Donee> doneeQueue;
        Iterator<Donee> iterator;

        int opt;
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("\n1.Add new donee");
            System.out.println("2.Modify donee details");
            System.out.println("3.View donee list");
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
                    doneeDB = modifyDonee(doneeDB);
                    break;
                }
                case 3: {
                    displayDonee(doneeDB);
                    break;
                }
                case 4: {
                    doneeDB = searchDonee(doneeDB);
                    break;
                }
                case 5: {
                    doneeDB = deleteDonee(doneeDB);
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
            String id = donee.autoGenerateID();
            donee.setAccountID(id);

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
            donee.setStatus("Active");

            System.out.print("Confirm add donee? (Y/N)");
            confirm = s.nextLine();

            if (confirm.toUpperCase().equals("Y")) {
                doneeDB.enqueue(donee);
            }

            System.out.println(confirm.toUpperCase().equals("Y") ? "Added successfully!!" : "Add failed...");

            System.out.print("\nContinue add donee? (Y/N)");
            opt = s.nextLine();

            System.out.println(confirm.toUpperCase().equals("Y") ? "Continue add..." : "Return to donee main page");

        } while (opt.toUpperCase().equals("Y"));

        return doneeDB;
    }

    public static CircularLinkedQueue<Donee> modifyDonee(CircularLinkedQueue<Donee> doneeDB) {

        String opt, select, confirm;
        String doneeID = "";
        Scanner s = new Scanner(System.in);
        Donee donee = new Donee();
        CircularLinkedQueue<Donee> temp = new CircularLinkedQueue<Donee>();

        do {
            Donee.doneeTable(doneeDB);
            System.out.print("Enter a Donee Id:");
            String id = s.nextLine();

            //if (doneeDB.contains(new Donee(doneeID)) == true) {
            for (int j = doneeDB.size(); j > 0; j--) {
                String id2 = doneeDB.getFront().getAccountID();
                String name = doneeDB.getFront().getName();
                String ic = doneeDB.getFront().getIc();
                String gender = String.valueOf(doneeDB.getFront().getGender());
                String email = doneeDB.getFront().getEmail();
                String phoneNo = doneeDB.getFront().getPhoneNo();
                String addr = doneeDB.getFront().getAddress();
                String reqIss = doneeDB.getFront().getRequestIssue();
                String reqAmt = Double.toString(doneeDB.getFront().getRequestAmount());
                String bankType = doneeDB.getFront().getBankType();
                String bankAcc = doneeDB.getFront().getBankAcc();

                boolean validIndex = true;

                if (id.equals(id2)) {
                    System.out.println("1. Name");
                    System.out.println("2. NRIC");
                    System.out.println("3. Gender");
                    System.out.println("4. Email");
                    System.out.println("5. Phone No");
                    System.out.println("6. Address");
                    System.out.println("7. Request Issue");
                    System.out.println("8. Request Amount");
                    System.out.println("9. Bank Typn");
                    System.out.println("10. Bank Account");

                    System.out.println("Enter the number want to update, if multiple index leave space at between [1 5 6]: ");
                    select = s.nextLine();

                    String[] splitIndex = select.split("\\s+");
                    int[] splitIndexInt = new int[splitIndex.length];

                    donee = doneeDB.getFront();

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
                                    donee.setName(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 2:
                                    System.out.print("Enter the new NRIC: ");
                                    donee.setIc(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 3:
                                    System.out.print("Enter the new gender: ");
                                    donee.setGender(s.next().charAt(0));
                                    s.nextLine();
                                    hasUpdateSomething = true;
                                    break;
                                case 4:
                                    System.out.print("Enter the new email: ");
                                    donee.setEmail(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 5:
                                    System.out.print("Enter the new phone no: ");
                                    donee.setPhoneNo(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 6:
                                    System.out.print("Enter the new address: ");
                                    donee.setAddress(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 7:
                                    System.out.print("Enter the new request issue: ");
                                    donee.setRequestIssue(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 8:
                                    System.out.print("Enter the new request amount: ");
                                    donee.setRequestAmount(s.nextDouble());
                                    hasUpdateSomething = true;
                                    break;
                                case 9:
                                    System.out.print("Enter the new bank type: ");
                                    donee.setBankType(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 10:
                                    System.out.print("Enter the new bank account: ");
                                    donee.setBankAcc(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                default:
                                    System.out.println("Index " + splitIndexInt[i] + "out of bound!");
                            }
                        }

                        if (hasUpdateSomething == true) {
                            System.out.print("Confirm update ? (Y/N)");
                            confirm = s.nextLine();

                            if (confirm.toUpperCase().equals("Y")) {
                                doneeDB.modify(donee, donee);
                            }

                            System.out.println(confirm.toUpperCase().equals("Y") ? "Update successfully!!\n" : "Update failed...");
                            validIndex = false;
                        } else {
                            System.out.println("No data selected");
                            
                        }
                    }
                } //else {
//                    System.out.println("Donee ID not found...");
//                }

                temp.enqueue(doneeDB.dequeue());

            }

            doneeDB = temp;
            System.out.print("Continue update donee ? (Y/N)");
            opt = s.nextLine();

        } while (opt.toUpperCase().equals("Y"));

        return doneeDB;
    }

    public static void displayDonee(CircularLinkedQueue<Donee> doneeDB) {
        Donee.doneeTable(doneeDB);
    }

    public static CircularLinkedQueue<Donee> deleteDonee(CircularLinkedQueue<Donee> doneeDB) {
        String opt, select, confirm;
        String doneeID = "";
        Scanner s = new Scanner(System.in);
        Donee donee = new Donee();
        CircularLinkedQueue<Donee> temp = new CircularLinkedQueue<Donee>();

        do {
            Donee.doneeTable(doneeDB);

            System.out.print("Enter a Donee Id:");
            String id = s.nextLine();

            for (int i = doneeDB.size(); i > 0; i--) {
                String id2 = doneeDB.getFront().getAccountID();

                if (id.equals(id2)) {
                    System.out.println("Confirm deactive donee ? (Y/N)");
                    confirm = s.nextLine();
                    donee = doneeDB.getFront();

                    if (confirm.toUpperCase().equals("Y")) {
                        donee.setStatus("Inactive");
                    }
                }
                temp.enqueue(doneeDB.dequeue());

            }

            doneeDB = temp;

            System.out.println("Continue deactive donee ? (Y/N)");
            opt = s.nextLine();

        } while (opt.toUpperCase().equals("Y"));

        return doneeDB;
    }

    public static CircularLinkedQueue<Donee> searchDonee(CircularLinkedQueue<Donee> doneeDB) {
        //iterator = CircularQueue.getIterator();
        String opt;
        String doneeID = "";

        Scanner s = new Scanner(System.in);
        Donee donee = new Donee();
        CircularLinkedQueue<Donee> temp = new CircularLinkedQueue<Donee>();

        do {
            System.out.print("Enter a Donee Id:");
            String id = s.nextLine();
            System.out.println("\n");

            for (int i = doneeDB.size(); i > 0; i--) {
                //if (doneeDB.contains(new Donee(doneeID)) == true) {
                String id2 = doneeDB.getFront().getAccountID();
                String name = doneeDB.getFront().getName();
                String ic = doneeDB.getFront().getIc();
                String gender = String.valueOf(doneeDB.getFront().getGender());
                String email = doneeDB.getFront().getEmail();
                String phoneNo = doneeDB.getFront().getPhoneNo();
                String addr = doneeDB.getFront().getAddress();
                String reqIss = doneeDB.getFront().getRequestIssue();
                String reqAmt = Double.toString(doneeDB.getFront().getRequestAmount());
                String bankType = doneeDB.getFront().getBankType();
                String bankAcc = doneeDB.getFront().getBankAcc();
                String status = doneeDB.getFront().getStatus();

                if (id.equals(id2)) {
                    System.out.println("Name: " + name);
                    System.out.println("NRIC: " + ic);
                    System.out.println("Gender: " + gender);
                    System.out.println("Email: " + email);
                    System.out.println("Phone No: " + phoneNo);
                    System.out.println("Address: " + addr);
                    System.out.println("Request Issue: " + reqIss);
                    System.out.println("Request Amount: " + reqAmt);
                    System.out.println("Bank Type: " + bankType);
                    System.out.println("Bank Account: " + bankAcc);
                    System.out.println("Status: " + status);
                }
                temp.enqueue(doneeDB.dequeue());
            }

            doneeDB = temp;
            System.out.print("Continue search donee ? (Y/N)");
            opt = s.nextLine();

        } while (opt.toUpperCase().equals("Y"));
        return doneeDB;
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
