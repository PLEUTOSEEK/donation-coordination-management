/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.SponsorItem;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author Angelina Oon
 */
public class SponsorItemPredicates implements Inputs {
    
    public static Predicate<SponsorItem> isIDEquals(String id) {
        return x -> x.getSponsoredID().toLowerCase().equalsIgnoreCase(id.toLowerCase());
    }
    
    public static Predicate<SponsorItem> isSponsorItemDonateDate(LocalDate date) {
        return x -> x.getDateDonate().equals(date);
    }
    
    public static Predicate<SponsorItem> isStatusEquals(String status) {
        Predicate<SponsorItem> predicate = x -> x.getStatus().equalsIgnoreCase(status);
        return predicate;
    }
    
    public static SponsorItem[] filterSponsorItem(SponsorItem[] SponsorItemArray, Predicate condition) {
        return (SponsorItem[]) Stream.of(SponsorItemArray).filter(condition).toArray(SponsorItem[]::new);
    }
    
    public static String sponsorItemSearchMenu() {
        StringBuilder menu = new StringBuilder();
        System.out.println();
        menu.append("01. Sponsor Item ID \n");
        menu.append("02. Sponsor Item date donate \n");
        menu.append("03. Sponsor Item Status\n");
        return menu.toString();
        
    }
    
    public static SponsorItem[] ControlPanel(SponsorItem[] sponsorItemArray) {
        Scanner input = new Scanner(System.in);
        SponsorItemPredicates sponsorItemPredicates = new SponsorItemPredicates();
        System.out.println(SponsorItemPredicates.sponsorItemSearchMenu());
        int option = 0;
        System.out.print("Option: ");
        option = input.nextInt();
        
        switch (option) {
            case 1:
                return filterSponsorItem(sponsorItemArray, isIDEquals(sponsorItemPredicates.askStr()));
            case 2:
                return filterSponsorItem(sponsorItemArray, isSponsorItemDonateDate(sponsorItemPredicates.askDate()));
            case 3:
                return filterSponsorItem(sponsorItemArray, isStatusEquals(sponsorItemPredicates.askStr()));
            
            default:
                System.out.println("Index not correct...");
        }
        
        return null;
        
    }
    
    @Override
    public LocalDate askDate() {
        Scanner input = new Scanner(System.in);
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.print("Enter date value [dd. MMM. yyyy] : ");
        return LocalDate.parse(input.nextLine(), dtfDate);
    }
    
    @Override
    public String askStr() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter string value : ");
        return input.nextLine();
    }
    
    @Override
    public int askInt() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter integer value : ");
        return input.nextInt();
    }
    
    @Override
    public double askDouble() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter double value : ");
        return input.nextDouble();
    }
}
