/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Sponsor;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author Angelina Oon
 */
public class SponsorPredicates implements Inputs {

    public static Predicate<Sponsor> isIDEquals(String id) {
        return x -> x.getAccountID().toLowerCase().equalsIgnoreCase(id.toLowerCase());

    }

    public static Predicate<Sponsor> isNameContains(String name) {
        return x -> x.getName().toLowerCase().contains(name.toLowerCase());
    }

    public static Predicate<Sponsor> isCompanyNameContains(String companyName) {
        return x -> x.getCompanyName().toLowerCase().contains(companyName.toLowerCase());
    }

    public static Predicate<Sponsor> isStatusEquals(String status) {
        Predicate<Sponsor> predicate = x -> x.getStatus().equalsIgnoreCase(status);
        return predicate;
    }

    public static Sponsor[] filterSponsor(Sponsor[] sponsorArray, Predicate condition) {
        return (Sponsor[]) Stream.of(sponsorArray).filter(condition).toArray(Sponsor[]::new);
    }

    public static String sponsorSearchMenu() {
        StringBuilder menu = new StringBuilder();
        System.out.println();
        menu.append("01. Sponsor ID \n");
        menu.append("02. Sponsor Name \n");
        menu.append("03. Company Name \n");
        menu.append("04. Sponsor Account Status \n");

        return menu.toString();
    }

    public static Sponsor[] ControlPanel(Sponsor[] sponsorArray) {
        Scanner input = new Scanner(System.in);
        SponsorPredicates sponsorPredicates = new SponsorPredicates();
        System.out.println(SponsorPredicates.sponsorSearchMenu());
        int option = 0;
        System.out.print("Option: ");
        option = input.nextInt();

        switch (option) {
            case 1:
                return filterSponsor(sponsorArray, isIDEquals(sponsorPredicates.askStr()));
            case 2:
                return filterSponsor(sponsorArray, isNameContains(sponsorPredicates.askStr()));
            case 3:
                return filterSponsor(sponsorArray, isCompanyNameContains(sponsorPredicates.askStr()));
            case 4:
                return filterSponsor(sponsorArray, isStatusEquals(sponsorPredicates.askStr()));

            default:
                System.out.println("Index not correct...");
        }

        return null;

    }

    @Override
    public String askStr() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter string value : ");
        return input.nextLine();
    }

    @Override
    public LocalDate askDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
