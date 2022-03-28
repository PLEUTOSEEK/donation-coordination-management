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
import entity.Funds;
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

        DoublyLinkedList<Sponsor> sponsorDB = new Sponsor().generateDummySponsor();//new Sponsor().generateDummySponsor();]
        CircularLinkedQueue<Donee> doneeDB = new Donee().generateDummyDonee();//new Donee().generateDummyDonee();
        DoublyLinkedList<Donee> doneeInHelpDB = new DoublyLinkedList<>();//new Donee().generateDummyDonee();
        SinglyLinkedList<Donor> donorDB = new Donor().generateDummyDonor();//new Donor().generateDummyDonor();
        DoublyLinkedList<Funds> fundsDB = new Funds().generateDummyFunds(sponsorDB);

        RedBlackTree<LocalDate, SponsorList> sponsorListDB = new SponsorList().generateDummySponsorList(campaignDB, sponsorDB);
        RedBlackTree<LocalDate, DoneeList> doneeListDB = new DoneeList().generateDummyDoneeList(campaignDB, doneeDB, doneeInHelpDB);
        RedBlackTree<LocalDate, DonorList> donorListDB = new DonorList().generateDummyDonorList(campaignDB, donorDB);
        RedBlackTree<LocalDate, DemandList> demandListDB = new DemandList().generateDummyDemandList(campaignDB);

        Campaign.deactiveExpiredCampaign(campaignDB);

        MainPanel mainPanel = new MainPanel();
        mainPanel.controlPanel(campaignDB, sponsorDB, sponsorListDB, doneeDB, doneeInHelpDB, doneeListDB, donorDB, donorListDB, demandListDB, fundsDB);
    }
}
