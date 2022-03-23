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
import java.sql.Timestamp;
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
                    delete(donationDB);
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

        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String donorID = "";
        String doneeID = "";
        Donation donation = new Donation();
        Donor donor = new Donor();
        Donee donee = new Donee();
        String originalLastID = Donation.getLastDonationID();

        do {
            Donor.donorTable(donorDB);
            Donee.doneeTable(doneeDB);

            System.out.println("Enter Donor ID: ");
            donorID = input.nextLine();

            if (donorDB.contains(new Donor(donorID)) == true) {
                //donor = donorDB.get(new Donor(donorID));

                System.out.println("Enter Donee ID: ");
                doneeID = input.nextLine();
                if (doneeDB.contains(new Donee(doneeID)) == true) {
                    //donee = doneeDB.get(new Donee(doneeID));

                    originalLastID = Donation.getLastDonationID();
                    donation.setDonor(donor);
                    donation.setDonee(donee);

                    System.out.println("Enter total amount: ");
                    donation.setTotalAmount(input.nextDouble());
                    System.out.println("Enter description: ");
                    donation.setDescription(input.nextLine());
                    System.out.println("Enter date of donation [dd. MM. yyyy] : ");
                    donation.setDateOfDonation(LocalDate.parse(input.nextLine()));
                    donation.setDateModified(new Timestamp(System.currentTimeMillis()));
                    donation.setStatus("Active");
                    donation.setDonationID(donation.autoGenerateID());

                    System.out.println("Confirm to add this donation record? (Y/N)  ");
                    confirmation = input.nextLine();

                    if (confirmation.toUpperCase().equals("Y")) {
                        donationDB.addLastNode(donation);
                        System.out.println("Added successfully...");
                    } else {
                        Donation.setLastDonationID(originalLastID);
                        System.out.println("Cancelled...");
                    }

                }

            }
            System.out.println("Continue adding new donation? (Y/N) ");
            option = input.nextLine();

        } while (option.toUpperCase().equals("Y"));
    }

    public void addDonorToCampaign(CircularLinkedList<Donation> donationDB,
            RedBlackTree<LocalDate, Campaign> campaignDB,
            SinglyLinkedList<Donor> donorDB) throws CloneNotSupportedException {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String donorID = "";
        String campaignID = "";
        Donation donation = new Donation();
        Donor donor = new Donor();
        Campaign campaign = new Campaign();
        String originalLastID = Donation.getLastDonationID();

        do {
            Donor.donorTable(donorDB);
            Campaign.campaignTable(campaignDB);

            System.out.println("Enter Donor ID: ");
            donorID = input.nextLine();

            if (donorDB.contains(new Donor(donorID)) == true) {
                //donor = donorDB.get(new Donor(donorID));

                System.out.println("Enter Campaign ID: ");
                campaignID = input.nextLine();
                if (campaignDB.contains(new Campaign(campaignID)) == true) {
                    campaign = campaignDB.get(new Campaign(campaignID));

                    originalLastID = Donation.getLastDonationID();
                    donation.setDonor(donor);
                    donation.setCampaign(campaign);

                    System.out.println("Enter total amount: ");
                    donation.setTotalAmount(input.nextDouble());
                    System.out.println("Enter description: ");
                    donation.setDescription(input.nextLine());
                    System.out.println("Enter date of donation [dd. MM. yyyy] : ");
                    donation.setDateOfDonation(LocalDate.parse(input.nextLine()));
                    donation.setDateModified(new Timestamp(System.currentTimeMillis()));
                    donation.setStatus("Active");
                    donation.setDonationID(donation.autoGenerateID());

                    System.out.println("Confirm to add this donation record? (Y/N)  ");
                    confirmation = input.nextLine();

                    if (confirmation.toUpperCase().equals("Y")) {
                        donationDB.addLastNode(donation);
                        System.out.println("Added successfully...");
                    } else {
                        Donation.setLastDonationID(originalLastID);
                        System.out.println("Cancelled...");
                    }

                }

            }
            System.out.println("Continue adding new donation? (Y/N) ");
            option = input.nextLine();

        } while (option.toUpperCase().equals("Y"));
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {

    }

    public void update(CircularLinkedList<Donation> donationDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String donationID = "";
        Donation donation = new Donation();

        do {
            Donation.donationTable(donationDB);

            System.out.println("Enter donation ID: ");
            donationID = input.nextLine();

            /* if(donationDB.getAll().contains(new Donation(donationID)) == true){
               CircularLinkedList<Donation> donations = donationDB.getAll();
            donation = new Donation();
            donation = donationDB.get(new Donation(donationID)).clone();
            
            
            }*/
        } while (option.toUpperCase().equals("Y"));

    }

    public void delete(CircularLinkedList<Donation> donationDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String donationID = "";
        String[] status = {"Active", "Inactive"};
        int selection;

        do {
            Donation.donationTable(donationDB);

            System.out.println("Enter Donation ID : ");
            donationID = input.nextLine();

            /*CircularLinkedList<Donation> donations = donations.getAll();
            if (donations.contains(new Donation(donationID)) == true) {
                Donation donation = donations.getAtAnyNode(donations.indexOf(new Donation(donationID)));

                StringBuilder statusMenu = new StringBuilder();
                for (int i = 0; i < status.length; i++) {
                    statusMenu.append((i + 1) + ". " + status[i] + "\n");
                }

                System.out.println(statusMenu.toString());
                System.out.println("Option: ");
                selection = input.nextInt();

                System.out.print("Confirm " + status[selection - 1] + " this record ? (Y/N) ");
                confirmation = input.nextLine();

                if (confirmation.toUpperCase().equals("Y")) {
                    donation.setStatus(status[selection-1]);
                    donation.setDateModified(new Timestamp(System.currentTimeMillis()));
                    donationDB.replaceAtAnyNode(donation.indexOf(donationID));
                }
                
                System.out.println("Updated successfully");
            }else{
                System.out.println("Donation record not found");
            }*/
            
            System.out.println("Continue deleting ? (Y/N) ");
            option = input.nextLine();
            
        } while (option.toUpperCase().equals("Y"));
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
