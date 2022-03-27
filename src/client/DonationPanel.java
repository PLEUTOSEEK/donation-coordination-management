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
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import utils.DonationPredicates;

/**
 *
 * @author Looi Jia Toong
 */
public class DonationPanel implements Panel {

    @Override
    public void controlPanel() {

    }

    public void controlPanel(
            CircularLinkedList<Donation> donationDB,
            RedBlackTree<LocalDate, Campaign> campaignDB,
            CircularLinkedQueue<Donee> doneeDB,
            SinglyLinkedList<Donor> donorDB) throws CloneNotSupportedException {

        int opt;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(menu());
            System.out.print("Option: ");
            opt = sc.nextInt();

            switch (opt) {
                case 1:
                    addDonorToDonee(donationDB, doneeDB, donorDB); //donor to donee
                    break;
                case 2:
                    addDonorToCampaign(donationDB, campaignDB, donorDB); //donor to campaign
                    break;
                case 3:
                    Donation.donationTable(donationDB);
                    break;
                case 4:
                    searchDonation(donationDB);
                    break;
                case 5:
                    deleteDonation(donationDB);
                    break;
                case 6:
                    updateDonation(donationDB);
                    break;
                case 7:
                    System.out.println("Return to previous page...");
                    break;
                default:
                    System.out.println("Please enter number between 1 to 7");
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
        String campaignID = "";

        Donation donation = new Donation();
        Donor donor = new Donor();
        Donee donee = new Donee();
        Campaign campaign = new Campaign();

        String originalLastID = Donation.getLastDonationID();
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        do {
            Donor.donorTable(donorDB);

            System.out.print("Enter Donor ID: ");
            donorID = input.nextLine();

            if (donorDB.contains(new Donor(donorID)) == true) {
                donor = donorDB.getAt(donorDB.indexOf(new Donor(donorID)));

                if (donor.getStatus().equals("Active")) {
                    Donee.doneeTable(doneeDB);
                    System.out.print("Enter Donee ID: ");
                    doneeID = input.nextLine();
                    if (doneeDB.contains(new Donee(doneeID)) == true) {
                        donee = doneeDB.getAt(doneeDB.indexOf(new Donee(doneeID)));

                        if (donee.getStatus().equals("Active")) {
                            originalLastID = Donation.getLastDonationID();
                            donation.setDonor(donor);
                            donation.setDonee(donee);

                            System.out.print("Enter total amount: ");
                            donation.setTotalAmount(input.nextDouble());
                            input.nextLine();
                            System.out.print("Enter description: ");
                            donation.setDescription(input.nextLine());
                            System.out.print("Enter date of donation [dd. MM. yyyy] : ");
                            donation.setDateOfDonation(LocalDate.parse(input.nextLine(), dtfDate));
                            donation.setDateModified(new Timestamp(System.currentTimeMillis()));
                            donation.setStatus("Active");
                            donation.setDonationID(donation.autoGenerateID());
                            donation.setCampaign(null);

                            System.out.print("Confirm to add this donation record? (Y/N)  ");
                            confirmation = input.nextLine();

                            if (confirmation.toUpperCase().equals("Y")) {
                                donationDB.addLastNode(donation);
                                System.out.println("Added successfully...");
                            } else {
                                Donation.setLastDonationID(originalLastID);
                                System.out.println("Cancelled...");
                            }
                        } else {
                            System.out.println("Donee with inactive status cannot be added...");
                        }
                    } else {
                        System.out.println("Donee ID not found ...");
                    }

                } else {
                    System.out.println("Donor with inactive status cannot be added...");
                }
                System.out.print("Continue adding new donation? (Y/N) ");
                option = input.nextLine();

            } else {
                System.out.println("Donor ID not found...");
            }

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
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        do {
            Donor.donorTable(donorDB);

            System.out.print("Enter Donor ID: ");
            donorID = input.nextLine();

            if (donorDB.contains(new Donor(donorID)) == true) {
                donor = donorDB.getAt(donorDB.indexOf(new Donor(donorID)));
                if (donor.getStatus().equals("Active")) {
                    Campaign.campaignTable(campaignDB);
                    System.out.print("Enter Campaign ID: ");
                    campaignID = input.nextLine();

                    if (campaignDB.contains(new Campaign(campaignID)) == true) {
                        campaign = campaignDB.get(new Campaign(campaignID));

                        if (campaign.isPermanentDelete() == false) {
                            originalLastID = Donation.getLastDonationID();
                            donation.setDonor(donor);
                            donation.setCampaign(campaign);

                            System.out.print("Enter total amount: ");
                            donation.setTotalAmount(input.nextDouble());
                            input.nextLine();
                            System.out.print("Enter description: ");
                            donation.setDescription(input.nextLine());
                            System.out.print("Enter date of donation [dd. MM. yyyy] : ");
                            donation.setDateOfDonation(LocalDate.parse(input.nextLine(), dtfDate));
                            donation.setDateModified(new Timestamp(System.currentTimeMillis()));
                            donation.setStatus("Active");
                            donation.setDonee(null);
                            donation.setDonationID(donation.autoGenerateID());

                            System.out.print("Confirm to add this donation record? (Y/N)  ");
                            confirmation = input.nextLine();

                            if (confirmation.toUpperCase().equals("Y")) {
                                donationDB.addLastNode(donation);
                                System.out.println("Added successfully...");
                            } else {
                                Donation.setLastDonationID(originalLastID);
                                System.out.println("Cancelled...");
                            }
                        } else {
                            System.out.println("Campaign with permanent inactive status cannot be added.");
                        }
                    } else {
                        System.out.println("Campaign ID not found...");
                    }
                } else {
                    System.out.println("Donor with inactive status cannot be added.");
                }
            } else {
                System.out.println("Donor ID not found...");
            }
            System.out.print("Continue adding new donation? (Y/N) ");
            option = input.nextLine();

        } while (option.toUpperCase().equals("Y"));

        System.out.println("Return to previous step...");
    }

    @Override

    public void display() {
    }

    @Override
    public void update() {

    }

    public void updateDonation(CircularLinkedList<Donation> donationDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String selectedIndex = "";
        String donationID = "";
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Donation donation = new Donation();

        do {
            Donation.donationTable(donationDB);

            System.out.print("Enter donation ID: ");
            donationID = input.nextLine();

            if (donationDB.contains(new Donation(donationID)) == true) {
                CircularLinkedList<Donation> donations = donationDB;
                donation = (Donation) donationDB.getAnyNode(donationDB.indexOf(new Donation(donationID)));

                if (donation.getStatus().equals("Active")) {
                    boolean validIndex = true;

                    do {
                        System.out.println(donationUpdateMenu());
                        System.out.print("Enter the index of option that you want to update, if multiple index leave space at between [1 5 6]: ");
                        selectedIndex = input.nextLine();

                        String[] splitIndex = selectedIndex.split("\\s+");
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
                                        System.out.print("Enter the new total amount of donation: ");
                                        donation.setTotalAmount(input.nextDouble());
                                        hasUpdateSomething = true;
                                        break;
                                    case 2:
                                        input.nextLine();
                                        System.out.print("Enter the new description: ");
                                        donation.setDescription(input.nextLine());
                                        hasUpdateSomething = true;
                                        break;
                                    case 3:
                                        System.out.print("Enter the new date of donation [dd. MM. yyyy]: ");
                                        donation.setDateOfDonation(LocalDate.parse(input.nextLine(), dtfDate));
                                        hasUpdateSomething = true;
                                        break;
                                    default:
                                        System.out.println("Please choose digit between 1 - 3!");
                                }

                                if (hasUpdateSomething == true) {
                                    input.nextLine();
                                    System.out.print("Confirm update donation record? (Y/N) ");
                                    confirmation = input.nextLine();

                                    if (confirmation.toUpperCase().equals("Y")) {
                                        donation.setDateModified(new Timestamp(System.currentTimeMillis()));
                                        donationDB.replaceAnyNode(donation, donationDB.indexOf(new Donation(donationID)));
                                        System.out.println("Updated successfully...");
                                    } else {
                                        System.out.println("Update cancelled...");
                                    }
                                } else {
                                    System.out.println("Nothing is selected for update...");
                                }
                            }
                        }
                    } while (validIndex == false);
                } else {
                    System.out.println("Donation with inactive status cannot be updated...");
                }

            } else {
                System.out.println("Donation ID not found...");
            }

            System.out.print("Continue updating donation records? (Y/N) ");
            option = input.nextLine();

        } while (option.toUpperCase().equals("Y"));

        System.out.println("Return to the previous page...");
    }

    public void deleteDonation(CircularLinkedList<Donation> donationDB) {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String donationID = "";
        String[] status = {"Active", "Inactive"};
        int selection;
        Donation donation = new Donation();

        do {
            Donation.donationTable(donationDB);

            System.out.print("Enter Donation ID : ");
            donationID = input.nextLine();

            if (donationDB.contains(new Donation(donationID)) == true) {
                CircularLinkedList<Donation> donations = donationDB;
                donation = (Donation) donationDB.getAnyNode(donationDB.indexOf(new Donation(donationID)));

                StringBuilder statusMenu = new StringBuilder();
                for (int i = 0; i < status.length; i++) {
                    statusMenu.append((i + 1) + ". " + status[i] + "\n");
                }

                System.out.println(statusMenu.toString());
                System.out.print("Option: ");
                selection = input.nextInt();
                input.nextLine();
                System.out.print("Confirm " + status[selection - 1] + " this record ? (Y/N) ");
                confirmation = input.nextLine();

                if (confirmation.toUpperCase().equals("Y")) {
                    donation.setStatus(status[selection - 1].toString());
                    donation.setDateModified(new Timestamp(System.currentTimeMillis()));
                    donationDB.replaceAnyNode(donation, donationDB.indexOf(new Donation(donationID)));
                }

                System.out.println("Updated successfully");
            } else {
                System.out.println("Donation ID not found");
            }
            System.out.print("Continue deleting ? (Y/N) ");
            option = input.nextLine();

        } while (option.toUpperCase().equals("Y"));

        System.out.println("Return to previous step...");
    }

    public String donationUpdateMenu() {
        StringBuilder menu = new StringBuilder();
        System.out.println();
        menu.append("1. Total Amount of Donation\n");
        menu.append("2. Donation description\n");
        menu.append("3. Date of Donation\n");

        return menu.toString();
    }

    public void searchDonation(CircularLinkedList<Donation> donationDB) {
        Donation[] donationArray = new Donation[donationDB.countNodes()];
        donationArray = donationDB.toArray(donationArray);
        CircularLinkedList<Donation> listForPrint = new CircularLinkedList<>();
        Donation[] arrListForPrint = null;

        arrListForPrint = DonationPredicates.ControlPanel(donationArray);
        if (arrListForPrint != null && arrListForPrint.length != 0) {
            for (Donation arrListForPrint1 : arrListForPrint) {
                listForPrint.addLastNode(arrListForPrint1);
            }
            Donation.donationTable(listForPrint);
        } else {
            System.out.println("No Record Found...");
        }
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
