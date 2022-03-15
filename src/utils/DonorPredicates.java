/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Donor;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;
/**
 *
 * @author pheyz
 */
public class DonorPredicates implements Inputs {

    public static Predicate<Donor> isIDEquals(String accountID){
        return x -> x.getAccountID().toLowerCase().contains(accountID.toLowerCase());
    }
            
    public static Predicate<Donor> isNameContains(String name){
        return x -> x.getName().toLowerCase().contains(name.toLowerCase());
    }
    
    public static Predicate<Donor> isDonorTypeContains(String DonorType){
        return x -> x.getDonorType().toLowerCase().contains(DonorType.toLowerCase());
    }
    
    public static Predicate<Donor> isICContains(String ic){
        return x -> x.getIc().toLowerCase().contains(ic.toLowerCase());
    }
    
     public static Predicate<Donor> isEmailContains(String email){
        return x -> x.getEmail().toLowerCase().contains(email.toLowerCase());
    }
     
     public static Predicate<Donor> isPhoneNoContains(String phoneNo){
        return x -> x.getPhoneNo().toLowerCase().contains(phoneNo.toLowerCase());
    }
     
     public static Predicate<Donor> isAddressContains(String address){
        return x -> x.getAddress().toLowerCase().contains(address.toLowerCase());
    }
     
     public static Predicate<Donor> isStatusContains(String status){
        return x -> x.getStatus().toLowerCase().contains(status.toLowerCase());
    }
     
      public static Donor[] filterDonor (Donor[] donorArray, Predicate condition) {
        return (Donor[]) Stream.of(donorArray).filter(condition).toArray(Donor[]::new);
    }
    
     public static String donorSearchMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("01. Donor ID \n");
        menu.append("02. Name \n");
        menu.append("03. Donor Type \n");
        menu.append("04. Register No / IC \n");
        menu.append("05. Email \n");
        menu.append("06. Phone NUmber \n");
        menu.append("07. Address \n");
        menu.append("08. Status \n");
        return menu.toString();
    }
     
     public static Donor[] ControlPanel(Donor[] donorArray) {
        Scanner input = new Scanner(System.in);
        DonorPredicates donorPredicates = new DonorPredicates();
        System.out.println(DonorPredicates.donorSearchMenu());
        int option = 0;
        System.out.print("Option: ");
        option = input.nextInt();

        switch (option) {
            case 1:
                return filterDonor(donorArray, isIDEquals(donorPredicates.askStr()));

            case 2:
                return filterDonor(donorArray, isNameContains(donorPredicates.askStr()));

            case 3:
                return filterDonor(donorArray, isDonorTypeContains(donorPredicates.askStr()));

            case 4:
                return filterDonor(donorArray, isICContains(donorPredicates.askStr()));

            case 5:
                return filterDonor(donorArray, isAddressContains(donorPredicates.askStr()));

            case 6:
                return filterDonor(donorArray, isEmailContains(donorPredicates.askStr()));

            case 7:
                return filterDonor(donorArray, isNameContains(donorPredicates.askStr()));

            case 8:
                System.out.println("halo");
                return filterDonor(donorArray, isStatusContains(donorPredicates.askStr()));
           
            default:
                System.out.println("Index not correct...");
                return null;
        }
    }
     
    @Override
    public LocalDate askDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String askStr() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter string value : ");
        return input.nextLine();
    }

    @Override
    public int askInt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double askDouble() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
