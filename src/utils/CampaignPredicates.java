/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Campaign;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class CampaignPredicates implements Inputs {

    public static Predicate<Campaign> isStartDateAfter(LocalDate date) {
        return x -> x.getCampaignStartDate().isAfter(date);
    }

    public static Predicate<Campaign> isStartDateBeforeOrEquals(LocalDate date) {
        Predicate<Campaign> predicate = x -> x.getCampaignStartDate().isAfter(date);
        return predicate.negate();
    }

    public static Predicate<Campaign> isEndDateAfter(LocalDate date) {
        return x -> x.getCampaignEndDate().isAfter(date);
    }

    public static Predicate<Campaign> isEndDateBeforeOrEquals(LocalDate date) {
        Predicate<Campaign> predicate = x -> x.getCampaignEndDate().isAfter(date);
        return predicate.negate();
    }

    public static Predicate<Campaign> isAddressContains(String address) {
        return x -> x.getCampaignAddress().toLowerCase().contains(address.toLowerCase());
    }

    public static Predicate<Campaign> isEmailContains(String email) {
        return x -> x.getCampaignEmail().toLowerCase().contains(email.toLowerCase());
    }

    public static Predicate<Campaign> isNameContains(String name) {
        return x -> x.getCampaignName().toLowerCase().contains(name.toLowerCase());
    }

    public static Predicate<Campaign> isIDEquals(String id) {
        return x -> x.getCampaignID().toLowerCase().equalsIgnoreCase(id.toLowerCase());
    }

    public static Predicate<Campaign> isStatusEquals(String status) {
        Predicate<Campaign> predicate = x -> x.getStatus().equalsIgnoreCase(status);
        return predicate;
    }

    public static Campaign[] filterCampaign(Campaign[] campaignArray, Predicate condition) {
        return (Campaign[]) Stream.of(campaignArray).filter(condition).toArray(Campaign[]::new);
    }

    public static String campaignSearchMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("01. campaign start date after \n");
        menu.append("02. campaign start date before or equal \n");
        menu.append("03. campaign end date after \n");
        menu.append("04. campaign end date before or equal \n");
        menu.append("05. campaign address contains \n");
        menu.append("06. campaign email contains \n");
        menu.append("07. campaign name contains \n");
        menu.append("08. campaign ID equal \n");
        menu.append("09. campaign status equal \n");
        return menu.toString();
    }

    public static Campaign[] ControlPanel(Campaign[] campaignArray) {
        Scanner input = new Scanner(System.in);
        CampaignPredicates campaignPredicates = new CampaignPredicates();
        System.out.println(CampaignPredicates.campaignSearchMenu());
        int option = 0;
        System.out.print("Option: ");
        option = input.nextInt();

        switch (option) {
            case 1:
                return filterCampaign(campaignArray, isStartDateAfter(campaignPredicates.askDate()));

            case 2:
                return filterCampaign(campaignArray, isStartDateBeforeOrEquals(campaignPredicates.askDate()));

            case 3:
                return filterCampaign(campaignArray, isEndDateAfter(campaignPredicates.askDate()));

            case 4:
                return filterCampaign(campaignArray, isEndDateBeforeOrEquals(campaignPredicates.askDate()));

            case 5:
                return filterCampaign(campaignArray, isAddressContains(campaignPredicates.askStr()));

            case 6:
                return filterCampaign(campaignArray, isEmailContains(campaignPredicates.askStr()));

            case 7:
                return filterCampaign(campaignArray, isNameContains(campaignPredicates.askStr()));

            case 8:
                return filterCampaign(campaignArray, isIDEquals(campaignPredicates.askStr()));

            case 9:
                return filterCampaign(campaignArray, isStatusEquals(campaignPredicates.askStr()));
            default:
                System.out.println("Index not correct...");
                return null;
        }
    }

    @Override
    public LocalDate askDate() {
        Scanner input = new Scanner(System.in);
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd. MMM. yyyy");
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
