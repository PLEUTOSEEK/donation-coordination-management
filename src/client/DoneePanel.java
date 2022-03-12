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
                    addNewDonee(doneeDB);
                    break;
                }
                case 2: {
                    //modifyDonee();
                    break;
                }
                case 3: {
                    //displayDonee(queue);
                    break;
                }
                case 4: {
                    //searchDonee(queue);
                    break;
                }
                case 5: {
                    //deleteDonee(queue);
                    break;
                }
                case 6: {
                    //exit()
                    break;
                }
            }
        } while (opt != 6);
    }

    public void addNewDonee(CircularLinkedQueue<Donee> doneeDB) {

        String confirm, opt;
        char gender = ' ';
        double requestAmount;

        Donee donee = new Donee();
        Scanner s = new Scanner(System.in);

//        if (queue.isEmpty() == true) {
//            id = "DE1001";
//        } else {
//            id = queue.getLast().getId();
//            int n = Integer.parseInt(id.substring(2));
//            n++;
//            id = "DE" + n;
//        }
        do {
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

            System.out.println("Confirm add donee? (Y/N)");
            confirm = s.nextLine();

            if (confirm.toUpperCase().equals("Y")) {
                doneeDB.enqueue(donee);
            }

            System.out.println(confirm.toUpperCase().equals("Y") ? "Added successfully" : "Add failed..");

            System.out.println("Continue add donee? (Y/N)");
            opt = s.nextLine();

            System.out.println(confirm.toUpperCase().equals("Y") ? "Continue add donee" : " ");

        } while (opt.toUpperCase().equals("Y"));
    }

    public static void display(CircularLinkedQueue<Donee> doneeDB) {
        //System.out.println(queue.dequeue().getId());

    }

    public static void modifyDonee(CircularLinkedQueue<Donee> doneeDB) {

        String opt, select, confirm;
        String doneeID = "";
        Scanner s = new Scanner(System.in);
        Donee donee = new Donee();

        do {
            Donee.doneeTable(doneeDB);
            System.out.print("Enter a Donee Id:");
            String id = s.nextLine();

            if (doneeDB.contains(new Donee(doneeID)) == true) {
                boolean validIndex = true;
                do {
                    validIndex = true;

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
                            System.out.println("Confirm update ? (Y/N)");
                            confirm = s.nextLine();

                            if (confirm.toUpperCase().equals("Y")) {

                                doneeDB.modify(donee, donee);
                            }

                            System.out.println(confirm.toUpperCase().equals("Y") ? "Update successfully" : "Update failed...");
                        } else {
                            System.out.println("No data selected");
                        }
                    }
                } while (validIndex == false);
            } else {
                System.out.println("Donee ID not found...");
            }
            System.out.println("Continue update campaign ? (Y/N)");
            opt = s.nextLine();

        } while (opt.toUpperCase().equals("Y"));
    }

    public static void displayDonee(CircularLinkedQueue<Donee> doneeDB) {
        Donee.doneeTable(doneeDB);
    }

    public static void deleteDonee(CircularLinkedQueue<Donee> doneeDB) {
        String opt, select, confirm;
        String doneeID = "";
        Scanner s = new Scanner(System.in);
        Donee donee = new Donee();

        do {
            Donee.doneeTable(doneeDB);

            System.out.print("Enter a Donee Id:");
            String id = s.nextLine();

            if (doneeDB.contains(new Donee(doneeID)) == true) {
                System.out.println("Confirm deactive campaign ? (Y/N)");
                confirm = s.nextLine();

                if (confirm.toUpperCase().equals("Y")) {
                    donee.setStatus("Inactive");
                }
            } else {
                System.out.println("Donee ID not found...");
            }
            
            System.out.println("Continue deactive campaign ? (Y/N)");
            opt = s.nextLine();
            
        } while(opt.toUpperCase().equals("Y"));   
    }

    public static void searchDonee(CircularLinkedQueue<Donee> doneeDB) {
        //iterator = CircularQueue.getIterator();
        String opt;
        String doneeID = "";
        Scanner s = new Scanner(System.in);
        Donee donee = new Donee();

        do {
            Donee.doneeTable(doneeDB);

            System.out.print("Enter a Donee Id:");
            String id = s.nextLine();

            if (doneeDB.contains(new Donee(doneeID)) == true) {
                
            }
            
            System.out.println("Continue deactive campaign ? (Y/N)");
            opt = s.nextLine();
            
        } while(opt.toUpperCase().equals("Y"));

        //iterator = CircularQueue.getIterator();
//        while (iterator.hasNext()) {
//            doneetosearch = iterator.next();
//
//        }
//        if (!doneetosearch.getName().equalsIgnoreCase(id)) {
//            doneetosearch = null;
//        }
//        if (doneetosearch == null) {
//            System.out.printf("No record found!!\n\n");
//        }
//        return doneetosearch;
//
//
//        for (int i = 0; i < temp.length; i++) {
//            if (id.equals(temp[i].getId())) {
//                System.out.println(temp[i].getId());
//            }
//        }
//        for (int i = 0; i< queue.size(); i++){
//            Donee temp = queue.get(queue, id);
//            if (id.equals(temp.getId())){
//                System.out.println(queue);
//            }
//            
//            if (queue.isEmpty() == true){
//                break;
//            }
//        }
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
