/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Scanner;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class CampaignPanel implements Panel {

    public void controlPanel() {
        Scanner input = new Scanner(System.in);

    }

    @Override
    public String menu() {
        StringBuilder menu = new StringBuilder();

        menu.append("1. Ad\n");
        menu.append("2. \n");
        menu.append("3. \n");
        menu.append("4. \n");
        menu.append("5. \n");
        menu.append("6. \n");

        return menu.toString();
    }

    @Override
    public void add() {
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
