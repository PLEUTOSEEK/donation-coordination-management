/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.SponsorList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class SponsorListPredicates implements Inputs {

    //<editor-fold defaultstate="collapsed" desc="sponsor list">
    public static Predicate<SponsorList> isSponsorListIDEquals(String id) {
        return x -> x.getSponsorListID().toLowerCase().equalsIgnoreCase(id.toLowerCase());
    }

    public static Predicate<SponsorList> isSponsorListDateJoinAfter(LocalDate date) {
        return x -> x.getDateJoin().isAfter(date);
    }

    public static Predicate<SponsorList> isSponsorListDateJoinBeforeOrEquals(LocalDate date) {
        return isSponsorListDateJoinAfter(date).negate();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="campaign predicates">
    public static Predicate<SponsorList> isCampaignIDEquals(String id) {
        return x -> x.getCampaign().getCampaignID().equalsIgnoreCase(id);
    }

    public static Predicate<SponsorList> isCampaignNameContains(String name) {
        return x -> x.getCampaign().getCampaignName().toLowerCase().contains(name.toLowerCase());
    }

    public static Predicate<SponsorList> isCampaignStartDateAfter(LocalDate date) {
        return x -> x.getCampaign().getCampaignStartDate().isAfter(date);
    }

    public static Predicate<SponsorList> isCampaignStartDateBeforeOrEquals(LocalDate date) {
        return isCampaignStartDateAfter(date).negate();
    }

    public static Predicate<SponsorList> isCampaignEndDateAfter(LocalDate date) {
        return x -> x.getCampaign().getCampaignEndDate().isAfter(date);
    }

    public static Predicate<SponsorList> isCampaignEndDateBeforeOrEquals(LocalDate date) {
        return isCampaignEndDateAfter(date).negate();
    }

    public static Predicate<SponsorList> isCampaignStatusEquals(String status) {
        return x -> x.getCampaign().getStatus().equalsIgnoreCase(status);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="sponsor predicates">
    public static Predicate<SponsorList> isSponsorIDEquals(String id) {
        return x -> x.getSponsor().getAccountID().equalsIgnoreCase(id);
    }

    public static Predicate<SponsorList> isSponsorNameEquals(String name) {
        return x -> x.getSponsor().getName().equalsIgnoreCase(name);
    }

    public static Predicate<SponsorList> isSponsorEmailEquals(String email) {
        return x -> x.getSponsor().getEmail().equalsIgnoreCase(email);
    }

    public static Predicate<SponsorList> isSponsorPhoneNoEquals(String phoneNo) {
        return x -> x.getSponsor().getPhoneNo().equalsIgnoreCase(phoneNo);
    }

    //</editor-fold>
    public static String sponsorListSrchMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("01. sponsor list ID\n");
        menu.append("02. sponsor list date join after\n");
        menu.append("03. sponsor list date join before or equal\n");
        menu.append("04. sponsor list status \n");
        menu.append("05. campaign ID equal\n");
        menu.append("06. campaign name equal\n");
        menu.append("07. campaign start date after\n");
        menu.append("08. campaign start date before or equal\n");
        menu.append("09. campaign end date after\n");
        menu.append("10. campaign end date before or equal\n");
        menu.append("10. campaign status equal n");
        menu.append("11. sponsor ID equal\n");
        menu.append("12. sponsor name equal\n");
        menu.append("13. sponsor email equal\n");
        menu.append("14. sponsor phone no. equal\n");

        return menu.toString();
    }

    public static SponsorList[] filterSponsorList(SponsorList[] sponsorListArray, Predicate condition) {
        return (SponsorList[]) Stream.of(sponsorListArray).filter(condition).toArray(SponsorList[]::new);
    }

    public static SponsorList[] ControlPanel(SponsorList[] sponsorListArray) {
        Scanner input = new Scanner(System.in);
        SponsorListPredicates sponsorListPredicates = new SponsorListPredicates();
        System.out.println(SponsorListPredicates.sponsorListSrchMenu());
        int option = 0;
        System.out.print("Option: ");
        option = input.nextInt();

        switch (option) {
            case 1:
                return filterSponsorList(sponsorListArray, isSponsorListIDEquals(sponsorListPredicates.askStr()));

            case 2:
                return filterSponsorList(sponsorListArray, isSponsorListDateJoinAfter(sponsorListPredicates.askDate()));

            case 3:
                return filterSponsorList(sponsorListArray, isSponsorListDateJoinBeforeOrEquals(sponsorListPredicates.askDate()));

            case 4:
                return filterSponsorList(sponsorListArray, isCampaignIDEquals(sponsorListPredicates.askStr()));

            case 5:
                return filterSponsorList(sponsorListArray, isCampaignNameContains(sponsorListPredicates.askStr()));

            case 6:
                return filterSponsorList(sponsorListArray, isCampaignStartDateAfter(sponsorListPredicates.askDate()));

            case 7:
                return filterSponsorList(sponsorListArray, isCampaignStartDateBeforeOrEquals(sponsorListPredicates.askDate()));

            case 8:
                return filterSponsorList(sponsorListArray, isCampaignEndDateAfter(sponsorListPredicates.askDate()));

            case 9:
                return filterSponsorList(sponsorListArray, isCampaignEndDateBeforeOrEquals(sponsorListPredicates.askDate()));

            case 10:
                return filterSponsorList(sponsorListArray, isCampaignStatusEquals(sponsorListPredicates.askStr()));

            case 11:
                return filterSponsorList(sponsorListArray, isSponsorIDEquals(sponsorListPredicates.askStr()));

            case 12:
                return filterSponsorList(sponsorListArray, isSponsorNameEquals(sponsorListPredicates.askStr()));

            case 13:
                return filterSponsorList(sponsorListArray, isSponsorEmailEquals(sponsorListPredicates.askStr()));

            case 14:
                return filterSponsorList(sponsorListArray, isSponsorPhoneNoEquals(sponsorListPredicates.askStr()));

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
