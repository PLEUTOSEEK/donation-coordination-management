/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import adt.DoublyLinkedList;
import adt.ListInterface;
import entity.Funds;
import entity.Sponsor;
import entity.SponsorItem;
import java.util.Scanner;

/**
 *
 * @author Angelina Oon
 */
public class FundsPanel implements Panel {

    public void controlPanel(DoublyLinkedList<SponsorItem> sponsorItemDB, DoublyLinkedList<Sponsor> sponsorDB, DoublyLinkedList<Funds> fundsDB) {

        ListInterface<Funds> funds;

        int opt;
        Scanner s = new Scanner(System.in);

        do {
            System.out.println("\n1.Add new funds");
            System.out.println("2.View funds list");
            System.out.println("3.Search funds item");
            System.out.println("4.Exit");
            System.out.print("Please select an action: ");
            opt = s.nextInt();
            switch (opt) {
                case 1: {

                    break;
                }
                case 2: {

                    break;
                }
                case 3: {
                    //searchFunds();
                    break;
                }
                case 4: {
                    //exit
                    break;
                }
            }

        } while (opt != 4);

    }

    public DoublyLinkedList<Funds> addFunds(DoublyLinkedList<Funds> fundsDB) {

        return fundsDB;
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
