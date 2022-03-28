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
import utils.SponsorItemPredicates;

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

                    addDemandListAndFunds(fundsDB, demandListDB, sponsorItemDB);
                    break;
                }
                case 2: {
                    displaySponsorItem(sponsorItemDB);
                    break;
                }
                case 3: {
                    searchSponsorItem(sponsorItemDB);
                    break;
                }
                case 4: {
                    //exit
                    break;
                }
            }
        } while (opt != 4);

    }

    private void addDemandListAndFunds(DoublyLinkedList<Funds> fundsDB, RedBlackTree<LocalDate, DemandList> demandListDB,
            DoublyLinkedList<SponsorItem> sponsorItemDB) {
        String opt, confirm;
        String lastDemandListID = "";
        String lastFundsID = "";
        String lastSponsorItemID = SponsorItem.getLastSponsoredID();
        Campaign campaign = new Campaign();
        SponsorItem sponsorItem = new SponsorItem();
        Scanner s = new Scanner(System.in);
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        DoublyLinkedList<DemandList> demandList = (DoublyLinkedList<DemandList>) demandListDB.getAllList();

        boolean hasDemandList = true;

        do {
            hasDemandList = true;
            DemandList.demandTable(demandListDB);

            System.out.print("Enter Demand List ID:");
            lastDemandListID = s.nextLine();

            if (demandListDB.contains(new DemandList(lastDemandListID)) == true) {
                DemandList targetDemandList = demandList.getAt(demandList.indexOf(new DemandList(lastDemandListID)));

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

                            } else if (sponsorItem.getQuantity() > targetDemandList.getQuantity()) {

                                System.out.println("Enter over the quantity, please try again");
                                System.out.println("Your quantity in demandList is " + targetDemandList.getQuantity());

                            } else {

                                System.out.print("Enter date of donate[dd.MM.yyyy]:");
                                sponsorItem.setDateDonate(LocalDate.parse(s.nextLine(), dtfDate));

                                sponsorItem.setDateModified(new Timestamp(System.currentTimeMillis()));

                                sponsorItem.setStatus("Active");

                                System.out.print("Confirm ? (Y/N):");
                                confirm = s.nextLine();

                                targetDemandList.setQuantity(targetDemandList.getQuantity() - qty);

                                System.out.println("Successful to deduct qty in demandList\n");

                                System.out.println("Successful to deduct amount in Funds\n");

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

                        } else if (campaign.getStatus().toUpperCase().equals("Permanent Inactive")) {
                            System.out.println("Campaign is permanent inactive, you can't create sponsor item");

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

        } while (opt.toUpperCase().equals("Y"));

    }

    public void displaySponsorItem(DoublyLinkedList<SponsorItem> sponsorItemDB) {
        SponsorItem.sponsorItemTable(sponsorItemDB);
    }

    public void searchSponsorItem(DoublyLinkedList<SponsorItem> sponsorItemDB) {
        SponsorItem[] sponsorItemArray = new SponsorItem[sponsorItemDB.getLength()];
        sponsorItemArray = sponsorItemDB.toArray(sponsorItemArray);
        DoublyLinkedList<SponsorItem> listForPrint = new DoublyLinkedList<>();
        SponsorItem[] arrListForPrint = null;

        arrListForPrint = SponsorItemPredicates.ControlPanel(sponsorItemArray);

        if (arrListForPrint != null && arrListForPrint.length != 0) {
            for (SponsorItem sponsorItem : arrListForPrint) {
                listForPrint.addLast(sponsorItem);
            }
            SponsorItem.sponsorItemTable(listForPrint);

        } else {
            System.out.println("No Record Found...");
        }

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
