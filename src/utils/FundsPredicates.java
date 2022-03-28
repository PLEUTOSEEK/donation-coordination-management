/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Funds;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author Angelina Oon
 */
public class FundsPredicates implements Inputs {

    public static Predicate<Funds> isFundsIDEquals(String id) {
        return x -> x.getFundsID().toLowerCase().equalsIgnoreCase(id.toLowerCase());
    }

    public static Predicate<Funds> isFundsDonatePay(LocalDate date) {
        return x -> x.getDatePay().equals(date);
    }

    public static Predicate<Funds> isFundsTotalAmountLargeThan(double totalAmount) {
        return x -> x.getTotalAmount() > totalAmount;
    }

    public static Predicate<Funds> isFundsTotalAmtSmallerOrEquals(double totalAmount) {
        return isFundsTotalAmountLargeThan(totalAmount).negate();
    }

    public static Predicate<Funds> isStatusEquals(String status) {
        return x -> x.getStatus().equalsIgnoreCase(status);
    }

    public static Funds[] filterFunds(Funds[] fundsArray, Predicate condition) {
        return (Funds[]) Stream.of(fundsArray).filter(condition).toArray(Funds[]::new);
    }

    public static String fundsSearchMenu() {
        StringBuilder menu = new StringBuilder();
        System.out.println();
        menu.append("01. Funds ID \n");
        menu.append("02. Date of funds pay \n");
        menu.append("03. Funds Total Amount larger than \n");
        menu.append("04. Funds Total Amount smaller than or equal to \n");
        menu.append("05. Funds status \n");
        return menu.toString();
    }

    public static Funds[] ControlPanel(Funds[] fundsArray) {
        Scanner input = new Scanner(System.in);
        FundsPredicates fundsPredicates = new FundsPredicates();
        System.out.println(fundsPredicates.fundsSearchMenu());
        int option = 0;
        System.out.print("Option: ");
        option = input.nextInt();

        switch (option) {
            case 1:
                return filterFunds(fundsArray, isFundsIDEquals(fundsPredicates.askStr()));

            case 2:
                return filterFunds(fundsArray, isFundsDonatePay(fundsPredicates.askDate()));

            case 3:
                return filterFunds(fundsArray, isFundsTotalAmountLargeThan(fundsPredicates.askDouble()));

            case 4:
                return filterFunds(fundsArray, isFundsTotalAmtSmallerOrEquals(fundsPredicates.askDouble()));

            case 5:
                return filterFunds(fundsArray, isStatusEquals(fundsPredicates.askStr()));

            default:
                System.out.println("Index not correct...");
                return null;
        }
    }

    @Override
    public LocalDate askDate() {
        Scanner input = new Scanner(System.in);
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.print("Enter date value [dd.MM.yyyy] : ");
        return LocalDate.parse(input.nextLine(), dtfDate);
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
    public int askInt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
