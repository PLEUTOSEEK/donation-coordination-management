/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.DoublyLinkedList;
import adt.SinglyLinkedList;
import adt.RedBlackTree;
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

    public static void main(String[] args) {
        RedBlackTree<LocalDate, Campaign> campaignDB = new Campaign().generateDummyCampaign();
        DoublyLinkedList<Sponsor> sponsorDB = new Sponsor().generateDummySponsor();
        RedBlackTree<LocalDate, SponsorList> sponsorListDB = new SponsorList().generateDummySponsorList();
        DoublyLinkedList<Donee> doneeDB = new Donee().generateDummyDonee();
        RedBlackTree<LocalDate, DoneeList> doneeListDB = new DoneeList().generateDummyDoneeList();
        SinglyLinkedList<Donor> donorDB = new Donor().generateDummyDonor();
        RedBlackTree<LocalDate, DonorList> donorListDB = new DonorList().generateDummyDonorList();;
        RedBlackTree<LocalDate, DemandList> demandListDB = new DonorList().generateDummyDemandList();

        MainPanel mainPanel = new MainPanel();
        mainPanel.controlPanel(campaignDB, sponsorDB, sponsorListDB, doneeDB, doneeListDB, donorDB, donorListDB, demandListDB);
    }
}
