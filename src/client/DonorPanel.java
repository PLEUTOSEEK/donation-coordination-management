/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Scanner;
import adt.SinglyLinkedList;
import adt.SinglyLinkedListInterface;
import entity.Donor;
import java.util.Iterator;

/**
 *
 * @author Wong Phey Zhen
 */
public class DonorPanel implements Panel {

    public void donorPanel(SinglyLinkedList<Donor> donorDB) {
        int option;
        Scanner sc = new Scanner(System.in);
       
        do {
            menu();
            System.out.println("Enter a selection:");
            option = sc.nextInt();

            switch (option) {
                case 1: {
                    //add(donorDB);
                    break;
                }
                case 2: {
                    
                    break;
                }
                case 3: {
                   
                    break;
                }
                case 4: {
                    
                    break;
                }
                case 5: {
                    
                    break;
                }
                case 6: {
                    
                    break;
                }
                
            }
        }while(option != 6);
    
    }

    @Override
    public void controlPanel() {

    }

    @Override
    public String menu() {
        StringBuilder menu = new StringBuilder();
        System.out.println("1.Add new Donor");
        System.out.println("2.View donor list");
        System.out.println("3.Update donor list");
        System.out.println("4.Delete Donor");
        System.out.println("5.Search donor");
        System.out.println("6.Exit");

        return menu.toString();
    }

    
    public void add(SinglyLinkedList<Donor> donorDB) {
        
         
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
