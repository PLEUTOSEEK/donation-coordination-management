/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.DemandList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class DemandListPredicates implements Inputs {

    //<editor-fold defaultstate="collapsed" desc="demand list predicates">
    public static Predicate<DemandList> isDemandListIDEquals(String id) {
        return x -> x.getDemandListID().equalsIgnoreCase(id);
    }

    public static Predicate<DemandList> isDemandNameEquals(String name) {
        return x -> x.getDemandName().equalsIgnoreCase(name);
    }

    public static Predicate<DemandList> isQtyBiggerTan(int qty) {
        return x -> x.getQuantity() > qty;
    }

    public static Predicate<DemandList> isQtySmallerOrEquals(int qty) {
        return isQtyBiggerTan(qty).negate();
    }

    public static Predicate<DemandList> isPricePerUnitBiggerTan(double price) {
        return x -> x.getPricePerUnit() > price;
    }

    public static Predicate<DemandList> isPricePerUnitSmallerOrEquals(double price) {
        return isPricePerUnitBiggerTan(price).negate();
    }

    public static Predicate<DemandList> isRegisterDateAfter(LocalDate date) {
        return x -> x.getDateRegister().isAfter(date);
    }

    public static Predicate<DemandList> isRegisterDateBeforeOrEquals(LocalDate date) {
        return isRegisterDateAfter(date).negate();
    }

    public static Predicate<DemandList> isDemandListStatusEquals(String status) {
        return x -> x.getStatus().equalsIgnoreCase(status);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="campaign predicates">

    public static Predicate<DemandList> isCampaignIDEquals(String id) {
        return x -> x.getCampaign().getCampaignID().equalsIgnoreCase(id);
    }

    public static Predicate<DemandList> isCampaignNameContains(String name) {
        return x -> x.getCampaign().getCampaignName().toLowerCase().contains(name.toLowerCase());
    }

    public static Predicate<DemandList> isCampaignStartDateAfter(LocalDate date) {
        return x -> x.getCampaign().getCampaignStartDate().isAfter(date);
    }

    public static Predicate<DemandList> isCampaignStartDateBeforeOrEquals(LocalDate date) {
        return isCampaignStartDateAfter(date).negate();
    }

    public static Predicate<DemandList> isCampaignEndDateAfter(LocalDate date) {
        return x -> x.getCampaign().getCampaignEndDate().isAfter(date);
    }

    public static Predicate<DemandList> isCampaignEndDateBeforeOrEquals(LocalDate date) {
        return isCampaignEndDateAfter(date).negate();
    }

    public static Predicate<DemandList> isCampaignStatusEquals(String status) {
        return x -> x.getCampaign().getStatus().equalsIgnoreCase(status);
    }
    //</editor-fold>

    public static String DemandListSrchMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("01. Demand list ID equal\n");
        menu.append("02. Demand list name equal\n");
        menu.append("03. Demand list quantity bigger than\n");
        menu.append("04. Demand list quantity smaller than or equal \n");
        menu.append("05. Demand list price per unit bigger than \n");
        menu.append("06. Demand list price per unit smaller than or equal \n");
        menu.append("07. Demand list register date after \n");
        menu.append("08. Demand list register date before or equal \n");
        menu.append("09. Demand list status equal \n");
        menu.append("10. Campaign ID equal\n");
        menu.append("11. Campaign name equal\n");
        menu.append("12. Campaign start date after\n");
        menu.append("13. Campaign start date before or equal\n");
        menu.append("14. Campaign end date after\n");
        menu.append("15. Campaign end date before or equal\n");
        menu.append("16. Campaign status equal n");

        return menu.toString();
    }

    public static DemandList[] filterDemandList(DemandList[] demandListArray, Predicate condition) {
        return (DemandList[]) Stream.of(demandListArray).filter(condition).toArray(DemandList[]::new);
    }

    public static DemandList[] ControlPanel(DemandList[] demandListArray) {
        Scanner input = new Scanner(System.in);
        DemandListPredicates demandListPredicates = new DemandListPredicates();
        System.out.println(demandListPredicates.DemandListSrchMenu());
        int option = 0;
        System.out.print("Option: ");
        option = input.nextInt();

        switch (option) {
            case 1:
                return filterDemandList(demandListArray, isDemandListIDEquals(demandListPredicates.askStr()));

            case 2:
                return filterDemandList(demandListArray, isDemandNameEquals(demandListPredicates.askStr()));

            case 3:
                return filterDemandList(demandListArray, isQtyBiggerTan(demandListPredicates.askInt()));

            case 4:
                return filterDemandList(demandListArray, isQtySmallerOrEquals(demandListPredicates.askInt()));

            case 5:
                return filterDemandList(demandListArray, isPricePerUnitBiggerTan(demandListPredicates.askDouble()));

            case 6:
                return filterDemandList(demandListArray, isPricePerUnitSmallerOrEquals(demandListPredicates.askDouble()));

            case 7:
                return filterDemandList(demandListArray, isRegisterDateAfter(demandListPredicates.askDate()));

            case 8:
                return filterDemandList(demandListArray, isRegisterDateBeforeOrEquals(demandListPredicates.askDate()));

            case 9:
                return filterDemandList(demandListArray, isDemandListStatusEquals(demandListPredicates.askStr()));

            case 10:
                return filterDemandList(demandListArray, isCampaignIDEquals(demandListPredicates.askStr()));

            case 11:
                return filterDemandList(demandListArray, isCampaignNameContains(demandListPredicates.askStr()));

            case 12:
                return filterDemandList(demandListArray, isCampaignStartDateAfter(demandListPredicates.askDate()));

            case 13:
                return filterDemandList(demandListArray, isCampaignStartDateBeforeOrEquals(demandListPredicates.askDate()));

            case 14:
                return filterDemandList(demandListArray, isCampaignEndDateAfter(demandListPredicates.askDate()));

            case 15:
                return filterDemandList(demandListArray, isCampaignEndDateBeforeOrEquals(demandListPredicates.askDate()));

            case 16:
                return filterDemandList(demandListArray, isCampaignStatusEquals(demandListPredicates.askStr()));

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
