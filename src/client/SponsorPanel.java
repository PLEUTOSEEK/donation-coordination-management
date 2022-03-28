/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.DoublyLinkedList;
import adt.ListInterface;
import adt.RedBlackTree;
import entity.DemandList;
import entity.Funds;
import entity.Sponsor;
import entity.SponsorItem;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;
import utils.DoneePredicates;
import utils.SponsorPredicates;

/**
 *
 * @author Angelina Oon
 */
public class SponsorPanel implements Panel {

    public void controlPanel(DoublyLinkedList<Sponsor> sponsorDB, DoublyLinkedList<Funds> fundsDB,
            RedBlackTree<LocalDate, DemandList> demandListDB,
            DoublyLinkedList<SponsorItem> sponsorItemDB) throws CloneNotSupportedException {
        ListInterface<Sponsor> sponsor;

        FundsPanel fundsPanel = new FundsPanel();
        SponsorItemPanel sponsorItemPanel = new SponsorItemPanel();

        int opt;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("\n1.Add new sponsor");
            System.out.println("2.Modify sponsor details");
            System.out.println("3.View sponsor list");
            System.out.println("4.Search sponsor");
            System.out.println("5.Delete sponsor");
            System.out.println("6.Funds");
            System.out.println("7.Sponsor Item");
            System.out.println("8.Exit");
            System.out.print("Please select an action: ");
            opt = s.nextInt();

            switch (opt) {
                case 1: {
                    sponsorDB = addNewSponsor(sponsorDB);
                    break;
                }
                case 2: {
                    modifySponsor(sponsorDB);
                    break;
                }
                case 3: {
                    displaySponsor(sponsorDB);
                    break;
                }
                case 4: {
                    searchSponsor(sponsorDB);
                    break;
                }
                case 5: {
                    deleteSponsor(sponsorDB);
                    break;
                }
                case 6: {
                    fundsPanel.controlPanel(fundsDB, sponsorDB, demandListDB);
                    break;
                }
                case 7: {
                    sponsorItemPanel.controlPanel(sponsorItemDB, fundsDB, demandListDB);
                    break;
                }
                case 8: {
                    // exit
                }
            }

        } while (opt != 8);

    }

    public DoublyLinkedList<Sponsor> addNewSponsor(DoublyLinkedList<Sponsor> sponsorDB) {

        String confirm, opt;
        char gender = ' ';

        Scanner s = new Scanner(System.in);
        String originalLastID = Sponsor.getLastSponsorID();

        do {
            Sponsor sponsor = new Sponsor();
            sponsor.setAccountID(sponsor.autoGenerateID());

            System.out.print("\nName:");
            sponsor.setName(s.nextLine());

            System.out.printf("\nNRIC No:");
            sponsor.setIc(s.nextLine());

            System.out.printf("Gender(F/M):");
            sponsor.setGender(s.next().charAt(0));
            s.nextLine(); //clear buffer

            System.out.printf("Phone No:");
            sponsor.setPhoneNo(s.nextLine());

            System.out.print("\nCompany Name:");
            sponsor.setCompanyName(s.nextLine());

            System.out.printf("Company Email:");
            sponsor.setEmail(s.nextLine());

            System.out.printf("Company Address:");
            sponsor.setAddress(s.nextLine());

            sponsor.setStatus("Active");

            System.out.print("Confirm add sponsor? (Y/N)");
            confirm = s.nextLine();

            if (confirm.toUpperCase().equals("Y")) {
                sponsorDB.addLast(sponsor);
            } else {
                Sponsor.setLastSponsorID(originalLastID);
            }

            System.out.println(confirm.toUpperCase().equals("Y") ? "Added successfully!!" : "Add Sponsor failed...");

            System.out.print("\nContinue add Sponsor? (Y/N)");
            opt = s.nextLine();

            System.out.println(opt.toUpperCase().equals("Y") ? "" : "Return to sponsor main page..");

        } while (opt.toUpperCase().equals("Y"));

        return sponsorDB;

    }

    public String sponsorUpdateMenu() {
        StringBuilder menu = new StringBuilder();

        menu.append("1. Sponsor Name\n");
        menu.append("2. NRIC\n");
        menu.append("3. Gender\n");
        menu.append("4. Phone No\n");
        menu.append("5. Company Name\n");
        menu.append("6. Company Email\n");
        menu.append("7. Company Address\n");

        return menu.toString();
    }

    public void modifySponsor(DoublyLinkedList<Sponsor> sponsorDB) throws CloneNotSupportedException {

        String opt, select, confirm;
        String id = "";
        Scanner s = new Scanner(System.in);
        Sponsor sponsor = new Sponsor();

        do {
            Sponsor.sponsorTable(sponsorDB);
            System.out.print("Enter a Sponsor Id:");
            id = s.nextLine();

            if (sponsorDB.contains(new Sponsor(id)) == true) {
                DoublyLinkedList<Sponsor> sponsors = sponsorDB;
                sponsor = sponsors.getAt(sponsors.indexOf(new Sponsor(id))).clone();
                boolean validIndex = true;

                do {
                    System.out.println(sponsorUpdateMenu());
                    validIndex = true;
                    System.out.print("Enter the number want to update, if multiple index leave space at between [1 5 6]: ");
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
                                    sponsor.setName(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 2:
                                    System.out.print("Enter the new NRIC: ");
                                    sponsor.setIc(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 3:
                                    System.out.print("Enter the new gender: ");
                                    sponsor.setGender(s.next().charAt(0));
                                    s.nextLine();
                                    hasUpdateSomething = true;
                                    break;
                                case 4:
                                    System.out.print("Enter the new phone no: ");
                                    sponsor.setPhoneNo(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 5:
                                    System.out.print("Enter the new company name: ");
                                    sponsor.setCompanyName(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 6:
                                    System.out.print("Enter the new company email: ");
                                    sponsor.setEmail(s.nextLine());
                                    hasUpdateSomething = true;
                                    break;
                                case 7:
                                    System.out.print("Enter the new company address: ");
                                    sponsor.setAddress(s.nextLine());
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

                                sponsorDB.replaceAt(sponsor, sponsorDB.indexOf(sponsor));

                            }

                            System.out.println(confirm.toUpperCase().equals("Y") ? "Update successfully!!\n" : "Update sponsor failed...\n");
                        } else {
                            System.out.println("No data selected");
                        }

                    }

                } while (validIndex == false);

            } else {
                System.out.println("Sponsor ID not found..");
            }
            System.out.print("Continue update sponsor ? (Y/N) ");
            opt = s.nextLine();

            System.out.println(opt.toUpperCase().equals("Y") ? "" : "Return to sponsor main page...");

        } while (opt.toUpperCase().equals("Y"));

    }

    public void displaySponsor(DoublyLinkedList<Sponsor> sponsorDB) {
        Sponsor.sponsorTable(sponsorDB);
    }

    public void searchSponsor(DoublyLinkedList<Sponsor> sponsorDB) {
        Sponsor[] sponsorArray = new Sponsor[sponsorDB.getLength()];
        sponsorArray = sponsorDB.toArray(sponsorArray);
        DoublyLinkedList<Sponsor> listForPrint = new DoublyLinkedList<>();
        Sponsor[] arrListForPrint = null;

        arrListForPrint = SponsorPredicates.ControlPanel(sponsorArray);

        if (arrListForPrint != null && arrListForPrint.length != 0) {
            for (Sponsor sponsor : arrListForPrint) {
                listForPrint.addLast(sponsor);
            }
            Sponsor.sponsorTable(listForPrint);
        } else {
            System.out.println("No Record Found...");
        }

    }

    public void deleteSponsor(DoublyLinkedList<Sponsor> sponsorDB) {
        String opt, select, confirm;
        String sponsorID = "";
        Scanner s = new Scanner(System.in);
        Sponsor sponsor = new Sponsor();

        DoublyLinkedList<Sponsor> temp = new DoublyLinkedList<Sponsor>();

        do {
            Sponsor.sponsorTable(sponsorDB);
            System.out.print("Enter Sponsor ID:");
            sponsorID = s.nextLine();

            Sponsor[] sponsorArray = new Sponsor[sponsorDB.getLength()];
            sponsorArray = sponsorDB.toArray(sponsorArray);

            if (sponsorDB.contains(new Sponsor(sponsorID)) == true) {
                DoublyLinkedList<Sponsor> sponors = sponsorDB;
                sponsor = sponsorDB.getAt(sponsorDB.indexOf(new Sponsor(sponsorID)));

                System.out.print("Confirm deactive " + sponsorID + " sponsor ? (Y/N) ");
                confirm = s.nextLine();

                if (confirm.toUpperCase().equals("Y")) {
                    sponsor.setStatus("Inactive");
                }
                System.out.println(confirm.toUpperCase().equals("Y") ? "Update successfully!!\n" : "Update failed...");
            }
            System.out.print("Continue deactive sponsor account ? (Y/N) ");
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
