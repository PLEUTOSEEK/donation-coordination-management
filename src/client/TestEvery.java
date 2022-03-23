/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.CircularLinkedQueue;
import adt.DoublyLinkedList;
import adt.RedBlackTree;
import adt.SinglyLinkedList;
import entity.Campaign;
import entity.DemandList;
import entity.Donee;
import entity.DoneeList;
import entity.Donor;
import entity.Funds;
import entity.Sponsor;
import entity.SponsorItem;
import java.time.LocalDate;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class TestEvery {

    public static void main(String[] args) throws CloneNotSupportedException {

        //RedBlackTree<LocalDate, Campaign> campaignDB = new Campaign().generateDummyCampaign();
//        DoneePanel doneeP = new DoneePanel();
        DonorPanel donor = new DonorPanel();
        SponsorPanel sponsor = new SponsorPanel();
        //SponsorItemPanel sponsorItemPanel = new SponsorItemPanel();

//        DoublyLinkedList<Sponsor> sponsorDB = new DoublyLinkedList<>();//new Sponsor().generateDummySponsor();
//        CircularLinkedQueue<Donee> doneeDB = new Donee().generateDummyDonee();//new Donee().generateDummyDonee();
//        DoublyLinkedList<Donee> doneeInHelpDB = new DoublyLinkedList<>();//new Donee().generateDummyDonee();
//         SinglyLinkedList<Donor> donorDB = new Donor().generateDummyDonor();//new Donor().generateDummyDonor();
        RedBlackTree<LocalDate, Campaign> campaignDB = new Campaign().generateDummyCampaign();
        RedBlackTree<LocalDate, DemandList> demandListDB = new DemandList().generateDummyDemandList(campaignDB);

        //DoneePanel doneeP = new DoneePanel();
        //DonorPanel donor = new DonorPanel();
        DoneeListPanel doneeL = new DoneeListPanel();

        //DoublyLinkedList<Sponsor> sponsorDB = new DoublyLinkedList<>();//new Sponsor().generateDummySponsor();
//        DoublyLinkedList<Sponsor> sponsorDB = new DoublyLinkedList<>();//new Sponsor().generateDummySponsor();
        CircularLinkedQueue<Donee> doneeDB = new Donee().generateDummyDonee();//new Donee().generateDummyDonee();
        DoublyLinkedList<Donee> doneeInHelpDB = new DoublyLinkedList<>();//new Donee().generateDummyDonee();
        SinglyLinkedList<Donor> donorDB = new Donor().generateDummyDonor();//new Donor().generateDummyDonor();
        DoublyLinkedList<Sponsor> sponsorDB = new Sponsor().generateDummySponsor();
        DoublyLinkedList<Funds> fundsDB = new Funds().generateDummyFunds(demandListDB);
//      SinglyLinkedList<Donor> donorDB = new Donor().generateDummyDonor();//new Donor().generateDummyDonor();
        // DoublyLinkedList<SponsorItem> sponsorItemDB = new SponsorItem().generateDummySponsorItem(demandListDB, fundsDB, sponsorDB);

//        RedBlackTree<LocalDate, Campaign> campaignDB = new Campaign().generateDummyCampaign();
        //DoneePanel doneeP = new DoneePanel();
        //DonorPanel donor = new DonorPanel();
//        DoneeListPanel doneeL = new DoneeListPanel();
//        DoublyLinkedList<Sponsor> sponsorDB = new DoublyLinkedList<>();//new Sponsor().generateDummySponsor();
        //  DoublyLinkedList<Sponsor> sponsorDB = new DoublyLinkedList<>();//new Sponsor().generateDummySponsor();
//        CircularLinkedQueue<Donee> doneeDB = new Donee().generateDummyDonee();//new Donee().generateDummyDonee();
        //       DoublyLinkedList<Donee> doneeInHelpDB = new DoublyLinkedList<>();//new Donee().generateDummyDonee();
      
        //<editor-fold defaultstate="collapsed" desc="Temporary delete later">
        //        sponsorDB.addLast(new Sponsor("S001", "TZX", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name"));
        //        sponsorDB.addLast(new Sponsor("S002", "ZZZ", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name"));
        //        sponsorDB.addLast(new Sponsor("S003", "AAA", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name"));
        //        sponsorDB.addLast(new Sponsor("S004", "QQQ", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name"));
        //        sponsorDB.addLast(new Sponsor("S005", "TTT", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name"));
        //        sponsorDB.addLast(new Sponsor("S006", "WWW", 'M', "0112", "cc@gmail1.com", "012", "company adrdress ", "Company name"));
        //        sponsorDB.addLast(new Sponsor("S007", "PPP", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name"));
        //        sponsorDB.addLast(new Sponsor("S008", "agyj", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name"));
        //        sponsorDB.addLast(new Sponsor("S009", "dfg", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name"));
        //        sponsorDB.addLast(new Sponsor("S010", "yuio", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name"));
        //</editor-fold>
        //        RedBlackTree<LocalDate, SponsorList> sponsorListDB = new SponsorList().generateDummySponsorList(campaignDB, sponsorDB);
        //
//        RedBlackTree<LocalDate, DoneeList> doneeListDB = new DoneeList().generateDummyDoneeList(campaignDB, doneeDB, doneeInHelpDB);

//        RedBlackTree<LocalDate, SponsorList> sponsorListDB = new SponsorList().generateDummySponsorList(campaignDB, sponsorDB);
//
//        RedBlackTree<LocalDate, DoneeList> doneeListDB = new DoneeList().generateDummyDoneeList(campaignDB, doneeDB, doneeInHelpDB);
//
//        RedBlackTree<LocalDate, DonorList> donorListDB = new DonorList().generateDummyDonorList(campaignDB, donorDB);
//        RedBlackTree<LocalDate, DemandList> demandListDB = new DemandList().generateDummyDemandList(campaignDB);
//
//        Sponsor.sponsorTable(sponsorDB);
//        Donee.doneeTable(doneeDB);
//        Donor.donorTable(donorDB);
//        SponsorList.sponsorListTable(sponsorListDB);
//        DoneeList.doneeListTable(doneeListDB);
//        DonorList.donorListTable(donorListDB);
//        DemandList.demandTable(demandListDB);
//
//        MainPanel mainPanel = new MainPanel();
//      doneeP.doneePanel(doneeDB);
//      doneeP.doneePanel(doneeDB
        //donor.donorPanel(donorDB);
        //         donor.donorPanel(donorDB);
        //       doneeL.controlPanel(campaignDB, doneeDB, doneeInHelpDB, doneeListDB);
//        doneeP.controlPanel(doneeDB);
        //         donor.donorPanel(donorDB);

        donor.donorPanel(donorDB);
//       sponsor.controlPanel(sponsorDB);


        //donor.donorPanel(donorDB);2
        
 //       sponsor.controlPanel(sponsorDB);
        //sponsorItemPanel.controlPanel(sponsorItemDB, sponsorDB, fundsDB, demandListDB);

        //         donor.donorPanel(donorDB);
        //doneeL.controlPanel(campaignDB, doneeDB, doneeInHelpDB, doneeListDB);
        //        doneeP.controlPanel(doneeDB);
        //         donor.donorPanel(donorDB);
    }

}
