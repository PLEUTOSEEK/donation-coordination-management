/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Donee;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author Wong Jun Yao
 */
public class DoneePredicates implements Inputs {

    public static Predicate<Donee> isIDEquals(String id) {
        return x -> x.getAccountID().toLowerCase().equalsIgnoreCase(id.toLowerCase());
    }

    public static Predicate<Donee> isNameContains(String name) {
        return x -> x.getName().toLowerCase().contains(name.toLowerCase());
    }

    public static Predicate<Donee> isRequestAmountBiggerThan(double requestAmount) {
        return x -> x.getRequestAmount() > requestAmount;
    }

    public static Predicate<Donee> isRequestAmountSmallOrEqual(double requestAmount) {
        return isRequestAmountBiggerThan(requestAmount).negate();
    }

    public static Predicate<Donee> isBankEquals(String bankType) {
        Predicate<Donee> predicate = x -> x.getBankType().equalsIgnoreCase(bankType);
        return predicate;
    }

    public static Predicate<Donee> isStatusEquals(String status) {
        Predicate<Donee> predicate = x -> x.getStatus().equalsIgnoreCase(status);
        return predicate;
    }

    public static Donee[] filterDonee(Donee[] doneeArray, Predicate condition) {
        return (Donee[]) Stream.of(doneeArray).filter(condition).toArray(Donee[]::new);
    }

    public static String doneeSearchMenu() {
        StringBuilder menu = new StringBuilder();
        System.out.println();
        menu.append("01. Donee ID \n");
        menu.append("02. Donee Name \n");
        menu.append("03. Donee request amount bigger than \n");
        menu.append("04. Donee request amount smaller or equal \n");
        menu.append("05. Bank Type\n");
        menu.append("06. Donee status\n");

        return menu.toString();
    }

    public static Donee[] ControlPanel(Donee[] doneeArray) {
        Scanner input = new Scanner(System.in);
        DoneePredicates doneePredicates = new DoneePredicates();
        System.out.println(DoneePredicates.doneeSearchMenu());
        int option = 0;
        System.out.print("Option: ");
        option = input.nextInt();

        switch (option) {
            case 1:
                return filterDonee(doneeArray, isIDEquals(doneePredicates.askStr()));
            case 2:
                return filterDonee(doneeArray, isNameContains(doneePredicates.askStr()));
            case 3:
                return filterDonee(doneeArray, isRequestAmountBiggerThan(doneePredicates.askDouble()));
            case 4:
                return filterDonee(doneeArray, isRequestAmountSmallOrEqual(doneePredicates.askDouble()));
            case 5:
                return filterDonee(doneeArray, isBankEquals(doneePredicates.askStr()));
            case 6:
                return filterDonee(doneeArray, isStatusEquals(doneePredicates.askStr()));
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
    public double askDouble() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter double value : ");
        return input.nextDouble();
    }

    @Override
    public LocalDate askDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int askInt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
