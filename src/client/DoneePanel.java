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
public class DoneePanel implements Panel{

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

        String opt;
        Scanner s = new Scanner(System.in);
        Donee donee = new Donee();
        
        do{
            Donee.doneeTable(doneeDB);
            
        }while();

        System.out.print("Enter a Donee Id:");
        String id = s.nextLine();

    }

    public static void displayDonee(CircularLinkedQueue<Donee> doneeDB) {
//        int i;
//        Node current = head;
//        if (queue == null) {
//            System.out.println("Queue is empty");
//        } else {
//            System.out.println("Nodes of the circular linked list: ");
//            do {
//                //Prints each node by incrementing pointer.  
//                System.out.print(" " + current.data);
//                current = current.next;
//            } while (current != queue.getFront());
//            System.out.println();
//        }
    }

    public static void deleteDonee(CircularLinkedQueue<Donee> doneeDB) {

        Scanner s = new Scanner(System.in);

        System.out.print("Enter a Donee Id:");
        String id = s.nextLine();

//        for (int i = 0; i< queue.size(); i++){
//            Donee temp = queue.get(queue, id);
//            if (id.equals(temp.getId())){
//                System.out.println(queue.dequeue().getId());
//            }
//            
//            if (queue.isEmpty() == true){
//                break;
//            }
//        }
    }

    public static Donee searchDonee(CircularLinkedQueue<Donee> doneeDB) {
        //iterator = CircularQueue.getIterator();
        String id;
        Scanner s = new Scanner(System.in);

        System.out.print("Enter a Donee Id:");
        id = s.nextLine();

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
        Donee temp[] = queue.get(queue);

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
        return null;
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
