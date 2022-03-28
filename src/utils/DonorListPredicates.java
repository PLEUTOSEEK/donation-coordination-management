/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.DonorList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class DonorListPredicates implements Inputs {

    //<editor-fold defaultstate="collapsed" desc="donor list">
    public static Predicate<DonorList> isDonorListIDEquals(String id) {
        return x -> x.getDonorListID().toLowerCase().equalsIgnoreCase(id.toLowerCase());
    }

    public static Predicate<DonorList> isDonorListDateJoinAfter(LocalDate date) {
        return x -> x.getDateJoin().isAfter(date);
    }

    public static Predicate<DonorList> isDonorListDateJoinBeforeOrEquals(LocalDate date) {
        return isDonorListDateJoinAfter(date).negate();
    }

    public static Predicate<DonorList> isDonorStatusEquals(String status) {
        return x -> x.getStatus().equalsIgnoreCase(status);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="campaign predicates">
    public static Predicate<DonorList> isCampaignIDEquals(String id) {
        return x -> x.getCampaign().getCampaignID().equalsIgnoreCase(id);
    }

    public static Predicate<DonorList> isCampaignNameContains(String name) {
        return x -> x.getCampaign().getCampaignName().toLowerCase().contains(name.toLowerCase());
    }

    public static Predicate<DonorList> isCampaignStartDateAfter(LocalDate date) {
        return x -> x.getCampaign().getCampaignStartDate().isAfter(date);
    }

    public static Predicate<DonorList> isCampaignStartDateBeforeOrEquals(LocalDate date) {
        return isCampaignStartDateAfter(date).negate();
    }

    public static Predicate<DonorList> isCampaignEndDateAfter(LocalDate date) {
        return x -> x.getCampaign().getCampaignEndDate().isAfter(date);
    }

    public static Predicate<DonorList> isCampaignEndDateBeforeOrEquals(LocalDate date) {
        return isCampaignEndDateAfter(date).negate();
    }

    public static Predicate<DonorList> isCampaignStatusEquals(String status) {
        return x -> x.getCampaign().getStatus().equalsIgnoreCase(status);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="sponsor predicates">
    public static Predicate<DonorList> isDonorIDEquals(String id) {
        return x -> x.getDonor().getAccountID().equalsIgnoreCase(id);
    }

    public static Predicate<DonorList> isDonorNameEquals(String name) {
        return x -> x.getDonor().getName().equalsIgnoreCase(name);
    }

    public static Predicate<DonorList> isDonorEmailEquals(String email) {
        return x -> x.getDonor().getEmail().equalsIgnoreCase(email);
    }

    public static Predicate<DonorList> isDonorPhoneNoEquals(String phoneNo) {
        return x -> x.getDonor().getPhoneNo().equalsIgnoreCase(phoneNo);
    }

    //</editor-fold>
    public static String donorListSrchMenu() {
        StringBuilder menu = new StringBuilder();
        System.out.println();
        menu.append("01. donor list ID\n");
        menu.append("02. donor list date join after\n");
        menu.append("03. donor list date join before or equal\n");
        menu.append("04. donor list status \n");
        menu.append("05. campaign ID equal\n");
        menu.append("06. campaign name equal\n");
        menu.append("07. campaign start date after\n");
        menu.append("08. campaign start date before or equal\n");
        menu.append("09. campaign end date after\n");
        menu.append("10. campaign end date before or equal\n");
        menu.append("11. campaign status equal \n");
        menu.append("12. donor ID equal\n");
        menu.append("13. donor name equal\n");
        menu.append("14. donor email equal\n");
        menu.append("15. donor phone no. equal\n");

        return menu.toString();
    }

    public static DonorList[] filterSponsorList(DonorList[] donorListArray, Predicate condition) {
        return (DonorList[]) Stream.of(donorListArray).filter(condition).toArray(DonorList[]::new);
    }

    public static DonorList[] ControlPanel(DonorList[] donorListArray) {
        Scanner input = new Scanner(System.in);
        DonorListPredicates donorListPredicates = new DonorListPredicates();
        System.out.println(DonorListPredicates.donorListSrchMenu());
        int option = 0;
        System.out.print("Option: ");
        option = input.nextInt();

        switch (option) {
            case 1:
                return filterSponsorList(donorListArray, isDonorListIDEquals(donorListPredicates.askStr()));

            case 2:
                return filterSponsorList(donorListArray, isDonorListDateJoinAfter(donorListPredicates.askDate()));

            case 3:
                return filterSponsorList(donorListArray, isDonorListDateJoinBeforeOrEquals(donorListPredicates.askDate()));

            case 4:
                return filterSponsorList(donorListArray, isDonorStatusEquals(donorListPredicates.askStr()));

            case 5:
                return filterSponsorList(donorListArray, isCampaignIDEquals(donorListPredicates.askStr()));

            case 6:
                return filterSponsorList(donorListArray, isCampaignNameContains(donorListPredicates.askStr()));

            case 7:
                return filterSponsorList(donorListArray, isCampaignStartDateAfter(donorListPredicates.askDate()));

            case 8:
                return filterSponsorList(donorListArray, isCampaignStartDateBeforeOrEquals(donorListPredicates.askDate()));

            case 9:
                return filterSponsorList(donorListArray, isCampaignEndDateAfter(donorListPredicates.askDate()));

            case 10:
                return filterSponsorList(donorListArray, isCampaignEndDateBeforeOrEquals(donorListPredicates.askDate()));

            case 11:
                return filterSponsorList(donorListArray, isCampaignStatusEquals(donorListPredicates.askStr()));

            case 12:
                return filterSponsorList(donorListArray, isDonorIDEquals(donorListPredicates.askStr()));

            case 13:
                return filterSponsorList(donorListArray, isDonorNameEquals(donorListPredicates.askStr()));

            case 14:
                return filterSponsorList(donorListArray, isDonorEmailEquals(donorListPredicates.askStr()));

            case 15:
                return filterSponsorList(donorListArray, isDonorPhoneNoEquals(donorListPredicates.askStr()));

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
