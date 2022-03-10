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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class CampaignPanel implements Panel {

    public void controlPanel(RedBlackTree<LocalDate, Campaign> campaignList, DoublyLinkedList<Sponsor> sponsorList) {
        Scanner input = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println(menu());
            option = input.nextInt();

            switch (option) {
                case 1:
                    add();
                    break;
                case 2:
                    display();
                    break;
                case 3:
                    search();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    update();
                    break;
                case 6:
                    exit();
                    break;
            }

        } while (option != 6);
    }

    @Override
    public void controlPanel() {

    }

    @Override
    public String menu() {
        StringBuilder menu = new StringBuilder();

        menu.append("1. Add new campaign \n");
        menu.append("2. Display campaign \n");
        menu.append("3. Search campaign \n");
        menu.append("4. Delete campaign \n");
        menu.append("5. Update campaign \n");
        menu.append("6. Exit \n");

        return menu.toString();
    }

    public String addMenu() {
        StringBuilder menu = new StringBuilder();

        menu.append("1. Continue add campaign? \n");
        menu.append("2. Exit \n");

        return menu.toString();
    }

    @Override
    public void add() {
        Scanner input = new Scanner(System.in);
        String option = "";
        String confirmation = "";
        Campaign campaign = new Campaign();
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("H:mm:ss");

        do {
            // ID need to auto generate

            do {

                System.out.print("Enter the campaign name: ");
                campaign.setCampaignName(input.nextLine());
                System.out.print("Enter the campaign start date [dd. MMM. yyyy]: ");
                campaign.setCampaignStartDate(LocalDate.parse(input.nextLine(), dtfDate));
                System.out.print("Enter the campaign start time [H:mm:ss]: ");
                campaign.setCampaignStartTime(LocalTime.parse(input.nextLine(), dtfTime));
                System.out.print("Enter the campaign end date [dd. MMM. yyyy]: ");
                campaign.setCampaignEndDate(LocalDate.parse(input.nextLine(), dtfDate));
                System.out.print("Enter the campaign end time [H:mm:ss]: ");
                campaign.setCampaignEndTime(LocalTime.parse(input.nextLine(), dtfTime));
                System.out.print("Enter the target amount: ");
                campaign.setTargetAmount(input.nextDouble());
                System.out.print("Enter the campaign email: ");
                campaign.setCampaignEmail(input.nextLine());
                System.out.print("Enter the campaign mobile no: ");
                campaign.setCampaignMobileNo(input.nextLine());
                System.out.print("Enter the campaign campaign address: ");
                campaign.setCampagnAddress(input.nextLine());
                System.out.print("Enter the campaign bank no: ");
                campaign.setCampaignBankNo(input.nextLine());
                System.out.print("Enter the campaign description: ");
                campaign.setDescription(input.nextLine());
                campaign.setCampaignRegisterDate(new Timestamp(System.currentTimeMillis()));
                campaign.setDateModified(new Timestamp(System.currentTimeMillis()));

                //
                campaign.setCampaignID(campaign.autoGenerateID());

                System.out.println("Confirm add campaign ? (Y/N)");
                confirmation = input.nextLine();

                System.out.println(confirmation.toUpperCase().equals("Y") ? "Added campaign successfully" : "Add campaign abort");

            } while (confirmation.toUpperCase().equals("Y"));

            System.out.println("Continue add campaign ? (Y/N)");
            option = input.nextLine();

            System.out.println(confirmation.toUpperCase().equals("Y") ? "Return to previous page..." : "");

        } while (option.toUpperCase().equals("Y"));
    }

    public void add(Campaign campaign) {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {
    }

    @Override
    public void search() {
    }

    @Override
    public void exit() {
    }

    public void display(DoublyLinkedList<Campaign> sponsorList) {

    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
