/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class testStringSplit {

    public static void main(String[] args) {
        String str = "Hello   I'm    your               String";
        String[] splitStr = str.split("\\s+");

        System.out.println(splitStr[3]);
    }
}
