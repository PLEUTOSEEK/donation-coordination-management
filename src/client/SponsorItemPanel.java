package client;

import adt.DoublyLinkedList;
import adt.ListInterface;
import adt.RedBlackTree;
import entity.Campaign;
import entity.DemandList;
import entity.Funds;
import entity.Sponsor;
import entity.SponsorItem;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Angelina Oon
 */
public class SponsorItemPanel implements Panel {

    public void controlPanel(DoublyLinkedList<SponsorItem> sponsorItemDB,
            DoublyLinkedList<Funds> fundsDB,
            RedBlackTree<LocalDate, DemandList> demandListDB) throws CloneNotSupportedException {

        ListInterface<SponsorItem> sponsorItem;

        int opt;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("\n1.Add new sponsor item");
            System.out.println("2.View sponsor item list");
            System.out.println("3.Search sponsor item");
            System.out.println("4.Exit");
            System.out.print("Please select an action: ");
            opt = s.nextInt();

            switch (opt) {
                case 1: {
                    //sponsorItemDB = addNewSponsorItem(sponsorItemDB, sponsorDB, fundsDB, demandListDB);
                    addDemandListAndFunds(fundsDB, demandListDB, sponsorItemDB);
                    break;
                }
                case 2: {
                    displaySponsorItem(sponsorItemDB);
                    break;
                }
                case 3: {
                    //searchSponsorItem(sponsorItemDB);
                    break;
                }
                case 4: {
                    //exit
                    break;
                }
            }
        } while (opt != 4);

    }

    public DoublyLinkedList<SponsorItem> addNewSponsorItem(DoublyLinkedList<SponsorItem> sponsorItemDB,
            DoublyLinkedList<Funds> fundsDB, RedBlackTree<LocalDate, DemandList> demandListDB) {

        String confirm, opt;

        Scanner s = new Scanner(System.in);
        Sponsor sponsor = new Sponsor();
        DemandList demandList = new DemandList();
        Funds funds = new Funds();
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("H:mm:ss");

        String originalLastID = SponsorItem.getLastSponsoredID();

        do {
            SponsorItem sponsorItem = new SponsorItem();
            sponsorItem.setSponsoredID(sponsorItem.autoGenerateID());

            //SponsorItemInformation sponsorItemInformation = addDemandList(fundsDB, demandListDB, campaignDB);
            sponsorItem.setStatus("Active");

            System.out.print("Confirm add sponsor item? (Y/N)");
            confirm = s.nextLine();

            if (confirm.toUpperCase().equals("Y")) {
                sponsorItemDB.addLast(sponsorItem);

            } else {
                SponsorItem.setLastSponsoredID(originalLastID);

            }
            System.out.println(confirm.toUpperCase().equals("Y") ? "Added successfully!!" : "Add Sponsor Item failed...");

            System.out.print("\nContinue add Item? (Y/N)");
            opt = s.nextLine();

            System.out.println(opt.toUpperCase().equals("Y") ? "" : "Return to sponsor item main page..");

        } while (opt.toUpperCase().equals("Y"));

        return sponsorItemDB;

    }

    public void displaySponsorItem(DoublyLinkedList<SponsorItem> sponsorItemDB) {
        SponsorItem.sponsorItemTable(sponsorItemDB);
    }

    private void addDemandListAndFunds(DoublyLinkedList<Funds> fundsDB, RedBlackTree<LocalDate, DemandList> demandListDB,
            DoublyLinkedList<SponsorItem> sponsorItemDB) {
        String opt, confirm;
        String lastDemandListID = "";
        String lastFundsID = "";
        String lastSponsorItemID = SponsorItem.getLastSponsoredID();
        //Campaign campaign = new Campaign();
        SponsorItem sponsorItem = new SponsorItem();
        Scanner s = new Scanner(System.in);
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        DoublyLinkedList<DemandList> demandList = demandListDB.getAllList();

        boolean hasDemandList = true;

        do {
            hasDemandList = true;
            DemandList.demandTable(demandListDB);

            System.out.print("Enter Demand List ID:");
            lastDemandListID = s.nextLine();

            if (demandListDB.contains(new DemandList(lastDemandListID)) == true) {
                DemandList targetDemandList = demandList.getAt(demandList.indexOf(new DemandList(lastDemandListID))); //retrieve demand list based on id

                if (targetDemandList.getCampaign().isPermanentDelete() || targetDemandList.getCampaign().isInactive() || targetDemandList.isInactive() == false) {

                    Funds.fundsTable(fundsDB);
                    System.out.print("Enter Funds ID:");
                    lastFundsID = s.nextLine();

                    if (fundsDB.contains(new Funds(lastFundsID)) == true) {
                        Funds targetFunds = fundsDB.getAt(fundsDB.indexOf(new Funds(lastFundsID)));

                        if (targetFunds.isInActive() == false) {

                            lastSponsorItemID = SponsorItem.getLastSponsoredID();
                            sponsorItem.setSponsoredID(sponsorItem.autoGenerateID());

                            sponsorItem.setDemandList(targetDemandList);
                            sponsorItem.setFunds(targetFunds);
                            System.out.print("Enter Quantity:");
                            int qty = s.nextInt();
                            sponsorItem.setQuantity(qty);

                            s.nextLine();

                            if (targetDemandList.getQuantity() == 0) {

                                targetDemandList.setStatus("Inactive");
                                System.out.println("Your quantity is 0, you cant use this Demand List ID");

                            } else if (targetFunds.getTotalAmount() == 0.00) {

                                targetFunds.setStatus("Inactive");
                                System.out.println("Your amount is 0, you cant use this Funds ID");
                            } else {

                                System.out.print("Enter date of donate[dd.MM.yyyy]:");
                                sponsorItem.setDateDonate(LocalDate.parse(s.nextLine(), dtfDate));

                                sponsorItem.setDateModified(new Timestamp(System.currentTimeMillis()));

                                sponsorItem.setStatus("Active");

                                //hasDemandList = false;
                                System.out.print("Confirm ? (Y/N):");
                                confirm = s.nextLine();

                                targetDemandList.setQuantity(targetDemandList.getQuantity() - qty);

                                System.out.println(qty);
                                System.out.println(targetDemandList.getPricePerUnit());
                                System.out.println(targetDemandList.getPricePerUnit() * qty);

                                System.out.println("Successful to deduct qty in demandList");
                                //how to know the demanList qty has been deducted?

                                System.out.println(targetFunds.getTotalAmount());
                                System.out.println(targetFunds.getTotalAmount() - (targetDemandList.getPricePerUnit() * qty));
                                System.out.println("Successful to deduct amount in Funds");

                                if (confirm.toUpperCase().equals("Y")) {
                                    sponsorItemDB.addLast(sponsorItem);
                                    System.out.println("Added successfully");
                                } else {
                                    DemandList.setLastDemandID(lastDemandListID);
                                    Funds.setLastFundsID(lastFundsID);
                                    SponsorItem.setLastSponsoredID(lastSponsorItemID);

                                    System.out.println("Added Failed");
                                }

                            }

                        } else {
                            System.out.println("This is Inactive");
                        }
                    }

                } else {
                    System.out.println("Status Inactive!");
                }
            } else {
                System.out.println("DemandList ID not found!");
            }
            System.out.print("Continue add? (Y/N) ");
            opt = s.nextLine();
            //ask demandList ID
            //retrieve demandList obj
            //ask funds ID
            //retrieve funds obj
            //enter qty 
            //qty*price
            //confirm?
            //deduct funds, stored in total amount, store donated qty in sponsoredItem 
            //deduct qty on demandList = targetDemandList.setQuantity(demandListIndividual.getQuantity() - qtyDeduct);
            //check balance of qty on demandList 
            //if qty=0,if targetDemandList.getQty()=0
            //targetDemandList.setStatus("inactive"), funds same
            //if the enter qty more than demandList qty ,then auto assign max qty & print message
            //qty*price
            //retrieve balance of the funds
            //if the enter qty of total price more than funds then auto decrease the qty able to cover
        } while (opt.toUpperCase().equals("Y"));

        //if (campaign.getStatus().toUpperCase().equals("Permanent Inactive")) {
        //    SponsorItem[] sponsorItemArr = new SponsorItem[sponsorItemDB.getLength()];
        // }
        //if campaign status = permanent inactive then cannot create sponsorItem
        //type in qty
        //check is type in qty more than needee qty or not,and also check wheather the funds able to cover the amount of Donate
        //qty deduct,funds deduct(qty*per unit)--need to return to addNewSponsorItem
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
