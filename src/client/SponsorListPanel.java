/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.DoublyLinkedList;
import adt.RedBlackTree;
import entity.Campaign;
import entity.Sponsor;
import entity.SponsorList;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Tee Zhuo Xuan
 */
class SponsorListPanel implements Panel {

    public void controlPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String menu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void add(RedBlackTree<LocalDate, Campaign> campaignDB,
            DoublyLinkedList<Sponsor> sponsorDB, RedBlackTree<LocalDate, SponsorList> sponsorListDB) {

        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        String campaignID = "";
        String sponsorID = "";
        Campaign campaign = new Campaign();
        Sponsor sponsor = new Sponsor();
        SponsorList sponsorList = new SponsorList();
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
                        Sponsor.sponsorTable(sponsorDB);
                        System.out.println("Enter sponsor ID: ");
                        sponsorID = input.nextLine();

                        if (sponsorDB.contains(new Sponsor(sponsorID))) {
                            SponsorList[] sponsorListArr = sponsorListDB.getAllArrayList();

                            for (int i = 0; i < sponsorListArr.length; i++) {
                                if (sponsorListArr[i].getCampaign().equals(campaign) && sponsorListArr[i].getSponsor().equals(sponsor)) {
                                    hasSponsor = false;
                                    break;
                                }
                            }
                            if (hasSponsor == false) {
                                System.out.println("Sponsor ID exist in the campaign already, try again the other sponsor");
                            }
                        } else {
                            hasSponsor = false;
                            System.out.println("Sponsor ID not found, try again");
                        }
                    } while (hasSponsor == false);

                    sponsor = sponsorDB.getAt(sponsorDB.indexOf(new Sponsor(sponsorID)));
                    campaign = campaignDB.get(new Campaign(campaignID));

                    sponsorList.setCampaign(campaign);
                    sponsorList.setSponsor(sponsor);
                    System.out.println("Enter date join [dd. MMM. yyyy]: ");
                    sponsorList.setDateJoin(LocalDate.parse(input.nextLine(), dtfDate));
                    sponsorList.setDateModified(new Timestamp(System.currentTimeMillis()));
                    sponsorList.setSponsorListID(sponsorList.autoGenerateID());

                    System.out.println("Confirm add sponsor to this campaign ? (Y/N)");
                    confirmation = input.nextLine();

                    if (confirmation.toUpperCase().equals("Y")) {
                        sponsorListDB.addData(sponsorList.getDateJoin(), sponsorList);
                    }

                    System.out.println(confirmation.toUpperCase().equals("Y") ? "Added sponsor successfully" : "Add sponsor abort");

                    System.out.println("Continue add sponsor to this campaign ? (Y/N)");
                    option = input.nextLine();
                } while (option.toUpperCase().equals("Y"));
            } else {
                System.out.println("Campaign ID not found, add demand abort");
            }

            System.out.println("Continue add sponsor ? (Y/N)");
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
