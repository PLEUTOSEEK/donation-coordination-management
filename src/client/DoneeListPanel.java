/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.DoublyLinkedList;
import adt.RedBlackTree;
import entity.Campaign;
import entity.Donee;
import entity.DoneeList;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Tee Zhuo Xuan
 */
class DoneeListPanel implements Panel {

    public void controlPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String menu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void add(RedBlackTree<LocalDate, Campaign> campaignDB,
            DoublyLinkedList<Donee> doneeDB, RedBlackTree<LocalDate, DoneeList> doneeListDB) {

        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String campaignID = "";
        String doneeID = "";
        Campaign campaign = new Campaign();
        Donee donee = new Donee();
        DoneeList doneeList = new DoneeList();
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        boolean hasSponsor = true;

        do {

            Campaign.campaignTable(campaignDB);

            System.out.println("Enter campaign ID: ");
            campaignID = input.nextLine();

            if (campaignDB.contains(new Campaign(campaignID)) == true) {
                do {

                    do {
                        hasSponsor = true;
                        Donee.doneeTable(doneeDB);
                        System.out.println("Enter donee ID: ");
                        doneeID = input.nextLine();

                        if (doneeDB.contains(new Donee(doneeID))) {
                            DoneeList[] doneeListArr = doneeListDB.getAllArrayList();

                            for (int i = 0; i < doneeListArr.length; i++) {
                                if (doneeListArr[i].getCampaign().equals(campaign) && doneeListArr[i].getDonee().equals(donee)) {
                                    hasSponsor = false;
                                    break;
                                }
                            }
                            if (hasSponsor == false) {
                                System.out.println("donee ID exist in the campaign already, try again the other donee");
                            }
                        } else {
                            hasSponsor = false;
                            System.out.println("donee ID not found, try again");
                        }
                    } while (hasSponsor == false);

                    donee = doneeDB.getAt(doneeDB.indexOf(new Donee(doneeID)));
                    campaign = campaignDB.get(new Campaign(campaignID));

                    doneeList.setCampaign(campaign);
                    doneeList.setDonee(donee);
                    System.out.println("Enter date join [dd. MMM. yyyy]: ");
                    doneeList.setDateJoin(LocalDate.parse(input.nextLine(), dtfDate));
                    doneeList.setDateModified(new Timestamp(System.currentTimeMillis()));
                    doneeList.setDoneeListID(doneeList.autoGenerateID());

                    System.out.println("Confirm add donee to this campaign ? (Y/N)");
                    confirmation = input.nextLine();

                    if (confirmation.toUpperCase().equals("Y")) {
                        doneeListDB.addData(doneeList.getDateJoin(), doneeList);
                    }

                    System.out.println(confirmation.toUpperCase().equals("Y") ? "Added donee successfully" : "Add sponsor abort");

                    System.out.println("Continue add donee to this campaign ? (Y/N)");
                    option = input.nextLine();
                } while (option.toUpperCase().equals("Y"));
            } else {
                System.out.println("Campaign ID not found, add demand abort");
            }

            System.out.println("Continue add donee ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "" : "Return to previous step...");

        } while (option.toUpperCase().equals("Y"));
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

    @Override
    public void add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
