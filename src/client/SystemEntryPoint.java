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
import entity.DonorList;
import entity.Sponsor;
import entity.SponsorList;
import java.time.LocalDate;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class SystemEntryPoint {

    public static void main(String[] args) throws CloneNotSupportedException {
        RedBlackTree<LocalDate, Campaign> campaignDB = new Campaign().generateDummyCampaign();

        DoublyLinkedList<Sponsor> sponsorDB = new DoublyLinkedList<>();//new Sponsor().generateDummySponsor();]
        CircularLinkedQueue<Donee> doneeDB = new Donee().generateDummyDonee();//new Donee().generateDummyDonee();
        DoublyLinkedList<Donee> doneeInHelpDB = new DoublyLinkedList<>();//new Donee().generateDummyDonee();
        SinglyLinkedList<Donor> donorDB = new Donor().generateDummyDonor();//new Donor().generateDummyDonor();

        //<editor-fold defaultstate="collapsed" desc="Temporary delete later">
        sponsorDB.addLast(new Sponsor("S001", "TZX", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name", "Active"));
        sponsorDB.addLast(new Sponsor("S002", "ZZZ", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name", "Active"));
        sponsorDB.addLast(new Sponsor("S003", "AAA", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name", "Active"));
        sponsorDB.addLast(new Sponsor("S004", "QQQ", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name", "Active"));
        sponsorDB.addLast(new Sponsor("S005", "TTT", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name", "Active"));
        sponsorDB.addLast(new Sponsor("S006", "WWW", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name", "Active"));
        sponsorDB.addLast(new Sponsor("S007", "PPP", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name", "Active"));
        sponsorDB.addLast(new Sponsor("S008", "agyj", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name", "Active"));
        sponsorDB.addLast(new Sponsor("S009", "dfg", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name", "Active"));
        sponsorDB.addLast(new Sponsor("S010", "yuio", 'M', "0112", "cc@gmail.com", "012", "company adrdress ", "Company name", "Active"));

        //</editor-fold>
        RedBlackTree<LocalDate, SponsorList> sponsorListDB = new SponsorList().generateDummySponsorList(campaignDB, sponsorDB);
        RedBlackTree<LocalDate, DoneeList> doneeListDB = new DoneeList().generateDummyDoneeList(campaignDB, doneeDB, doneeInHelpDB);
        RedBlackTree<LocalDate, DonorList> donorListDB = new DonorList().generateDummyDonorList(campaignDB, donorDB);
        RedBlackTree<LocalDate, DemandList> demandListDB = new DemandList().generateDummyDemandList(campaignDB);

        MainPanel mainPanel = new MainPanel();
        mainPanel.controlPanel(campaignDB, sponsorDB, sponsorListDB, doneeDB, doneeInHelpDB, doneeListDB, donorDB, donorListDB, demandListDB);
    }
}
