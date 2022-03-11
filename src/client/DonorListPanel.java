/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.DoublyLinkedList;
import adt.RedBlackTree;
import entity.Campaign;
import entity.Donor;
import entity.DonorList;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Tee Zhuo Xuan
 */
class DonorListPanel implements Panel {

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

    private void add(RedBlackTree<LocalDate, Campaign> campaignDB,
            DoublyLinkedList<Donor> donorDB, RedBlackTree<LocalDate, DonorList> donorListDB) {

        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String campaignID = "";
        String donorID = "";
        Campaign campaign = new Campaign();
        Donor donor = new Donor();
        DonorList donorList = new DonorList();
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
                        Donor.donorTable(donorDB);
                        System.out.println("Enter donee ID: ");
                        donorID = input.nextLine();

                        if (donorDB.contains(new Donor(donorID))) {
                            DonorList[] donotListArr = donorListDB.getAllArrayList();

                            for (int i = 0; i < donotListArr.length; i++) {
                                if (donotListArr[i].getCampaign().equals(campaign) && donotListArr[i].getDonor().equals(donor)) {
                                    hasSponsor = false;
                                    break;
                                }
                            }
                            if (hasSponsor == false) {
                                System.out.println("donor ID exist in the campaign already, try again the other donor");
                            }
                        } else {
                            hasSponsor = false;
                            System.out.println("donor ID not found, try again");
                        }
                    } while (hasSponsor == false);

                    donor = donorDB.getAt(donorDB.indexOf(new Donor(donorID)));
                    campaign = campaignDB.get(new Campaign(campaignID));

                    donorList.setCampaign(campaign);
                    donorList.setDonor(donor);
                    System.out.println("Enter date join [dd. MMM. yyyy]: ");
                    donorList.setDateJoin(LocalDate.parse(input.nextLine(), dtfDate));
                    donorList.setDateModified(new Timestamp(System.currentTimeMillis()));
                    donorList.setDonorListID(donorList.autoGenerateID());

                    System.out.println("Confirm add donee to this campaign ? (Y/N)");
                    confirmation = input.nextLine();

                    if (confirmation.toUpperCase().equals("Y")) {
                        donorListDB.addData(donorList.getDateJoin(), donorList);
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

}
