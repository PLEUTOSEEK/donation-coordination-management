/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.DoneeList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class DoneeListPredicates implements Inputs {
    //<editor-fold defaultstate="collapsed" desc="donor list">

    public static Predicate<DoneeList> isDoneeListIDEquals(String id) {
        return x -> x.getDoneeListID().toLowerCase().equalsIgnoreCase(id.toLowerCase());
    }

    public static Predicate<DoneeList> isDoneeListDateJoinAfter(LocalDate date) {
        return x -> x.getDateJoin().isAfter(date);
    }

    public static Predicate<DoneeList> isDoneeListDateJoinBeforeOrEquals(LocalDate date) {
        return isDoneeListDateJoinAfter(date).negate();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="campaign predicates">
    public static Predicate<DoneeList> isCampaignIDEquals(String id) {
        return x -> x.getCampaign().getCampaignID().equalsIgnoreCase(id);
    }

    public static Predicate<DoneeList> isCampaignNameContains(String name) {
        return x -> x.getCampaign().getCampaignName().toLowerCase().contains(name.toLowerCase());
    }

    public static Predicate<DoneeList> isCampaignStartDateAfter(LocalDate date) {
        return x -> x.getCampaign().getCampaignStartDate().isAfter(date);
    }

    public static Predicate<DoneeList> isCampaignStartDateBeforeOrEquals(LocalDate date) {
        return isCampaignStartDateAfter(date).negate();
    }

    public static Predicate<DoneeList> isCampaignEndDateAfter(LocalDate date) {
        return x -> x.getCampaign().getCampaignEndDate().isAfter(date);
    }

    public static Predicate<DoneeList> isCampaignEndDateBeforeOrEquals(LocalDate date) {
        return isCampaignEndDateAfter(date).negate();
    }

    public static Predicate<DoneeList> isCampaignStatusEquals(String status) {
        return x -> x.getCampaign().getStatus().equalsIgnoreCase(status);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Donee predicates">
    public static Predicate<DoneeList> isDoneeIDEquals(String id) {
        return x -> x.getDonee().getAccountID().equalsIgnoreCase(id);
    }

    public static Predicate<DoneeList> isDoneeNameEquals(String name) {
        return x -> x.getDonee().getName().equalsIgnoreCase(name);
    }

    public static Predicate<DoneeList> isDoneeEmailEquals(String email) {
        return x -> x.getDonee().getEmail().equalsIgnoreCase(email);
    }

    public static Predicate<DoneeList> isDoneePhoneNoEquals(String phoneNo) {
        return x -> x.getDonee().getPhoneNo().equalsIgnoreCase(phoneNo);
    }
    //</editor-fold>

    public static String DoneeListSrchMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("01. Donee list ID\n");
        menu.append("02. Donee list date join after\n");
        menu.append("03. Donee list date join before or equal\n");
        menu.append("04. Donee list status \n");
        menu.append("05. campaign ID equal\n");
        menu.append("06. campaign name equal\n");
        menu.append("07. campaign start date after\n");
        menu.append("08. campaign start date before or equal\n");
        menu.append("09. campaign end date after\n");
        menu.append("10. campaign end date before or equal\n");
        menu.append("10. campaign status equal n");
        menu.append("11. Donee ID equal\n");
        menu.append("12. Donee name equal\n");
        menu.append("13. Donee email equal\n");
        menu.append("14. Donee phone no. equal\n");

        return menu.toString();
    }

    public static DoneeList[] filterDoneeList(DoneeList[] doneeListArray, Predicate condition) {
        return (DoneeList[]) Stream.of(doneeListArray).filter(condition).toArray(DoneeList[]::new);
    }

    public static DoneeList[] ControlPanel(DoneeList[] DoneeListArray) {
        Scanner input = new Scanner(System.in);
        DoneeListPredicates DoneeListPredicates = new DoneeListPredicates();
        System.out.println(DoneeListPredicates.DoneeListSrchMenu());
        int option = 0;
        System.out.print("Option: ");
        option = input.nextInt();

        switch (option) {
            case 1:
                return filterDoneeList(DoneeListArray, isDoneeListIDEquals(DoneeListPredicates.askStr()));

            case 2:
                return filterDoneeList(DoneeListArray, isDoneeListDateJoinAfter(DoneeListPredicates.askDate()));

            case 3:
                return filterDoneeList(DoneeListArray, isDoneeListDateJoinBeforeOrEquals(DoneeListPredicates.askDate()));

            case 4:
                return filterDoneeList(DoneeListArray, isCampaignIDEquals(DoneeListPredicates.askStr()));

            case 5:
                return filterDoneeList(DoneeListArray, isCampaignNameContains(DoneeListPredicates.askStr()));

            case 6:
                return filterDoneeList(DoneeListArray, isCampaignStartDateAfter(DoneeListPredicates.askDate()));

            case 7:
                return filterDoneeList(DoneeListArray, isCampaignStartDateBeforeOrEquals(DoneeListPredicates.askDate()));

            case 8:
                return filterDoneeList(DoneeListArray, isCampaignEndDateAfter(DoneeListPredicates.askDate()));

            case 9:
                return filterDoneeList(DoneeListArray, isCampaignEndDateBeforeOrEquals(DoneeListPredicates.askDate()));

            case 10:
                return filterDoneeList(DoneeListArray, isCampaignStatusEquals(DoneeListPredicates.askStr()));

            case 11:
                return filterDoneeList(DoneeListArray, isDoneeIDEquals(DoneeListPredicates.askStr()));

            case 12:
                return filterDoneeList(DoneeListArray, isDoneeNameEquals(DoneeListPredicates.askStr()));

            case 13:
                return filterDoneeList(DoneeListArray, isDoneeEmailEquals(DoneeListPredicates.askStr()));

            case 14:
                return filterDoneeList(DoneeListArray, isDoneePhoneNoEquals(DoneeListPredicates.askStr()));

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
