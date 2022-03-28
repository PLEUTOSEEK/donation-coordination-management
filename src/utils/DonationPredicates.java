/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Donation;
import entity.Donor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author Looi Jia Toong
 */
public class DonationPredicates implements Inputs {

    public static Predicate<Donation> isDonationIDEquals(String id) {
        return x -> x.getDonationID().toLowerCase().equalsIgnoreCase(id.toLowerCase());
    }

    public static Predicate<Donation> isTotalAmtBiggerThan(double totalAmount) {
        return x -> x.getTotalAmount() > totalAmount;
    }

    public static Predicate<Donation> isTotalAmtSmallerOrEquals(double totalAmount) {
        return isTotalAmtBiggerThan(totalAmount).negate();
    }

    public static Predicate<Donation> isDateOfDonationAfter(LocalDate date) {
        return x -> x.getDateOfDonation().equals(date);
    }

    public static Predicate<Donation> isDateOfDonationBeforeOrEquals(LocalDate date) {
        return isDateOfDonationAfter(date).negate();
    }

    public static Predicate<Donation> isStatusEquals(String status) {
        return x -> x.getStatus().equalsIgnoreCase(status);
    }

    public static Predicate<Donation> isDonorIDEquals(String id) {
        return x -> x.getDonor().getAccountID().toLowerCase().equalsIgnoreCase(id.toLowerCase());
    }

    public static Predicate<Donation> isDoneeIDEquals(String id) {
        return x -> x.getDonee().getAccountID().toLowerCase().equalsIgnoreCase(id.toLowerCase());
    }

    public static Predicate<Donation> isCampaignIDEquals(String id) {
        return x -> x.getCampaign().getCampaignID().toLowerCase().equalsIgnoreCase(id.toLowerCase());
    }

    public static String donationSearchMenu() {
        StringBuilder menu = new StringBuilder();
        System.out.println();
        menu.append("01. Donation ID \n");
        menu.append("02. Donation Total Amount larger than \n");
        menu.append("03. Donation Total Amount smaller than or equal to \n");
        menu.append("04. Donation status \n");
        menu.append("05. Date of Donation Before or Equals \n");
        menu.append("06. Date of Donation After \n");
        menu.append("07. Donor ID \n");
        menu.append("08. Donee ID \n");
        menu.append("09. Campaign ID \n");
        return menu.toString();
    }

    public static Donation[] ControlPanel(Donation[] donationArray) {
        Scanner input = new Scanner(System.in);
        DonationPredicates donationPredicates = new DonationPredicates();
        System.out.println(donationPredicates.donationSearchMenu());
        int option = 0;
        System.out.print("Option: ");
        option = input.nextInt();

        switch (option) {
            case 1:
                return filterDonation(donationArray, isDonationIDEquals(donationPredicates.askStr()));

            case 2:
                return filterDonation(donationArray, isTotalAmtBiggerThan(donationPredicates.askDouble()));

            case 3:
                return filterDonation(donationArray, isTotalAmtSmallerOrEquals(donationPredicates.askDouble()));

            case 4:
                return filterDonation(donationArray, isStatusEquals(donationPredicates.askStr()));

            case 5:
                return filterDonation(donationArray, isDateOfDonationBeforeOrEquals(donationPredicates.askDate()));

            case 6:
                return filterDonation(donationArray, isDateOfDonationAfter(donationPredicates.askDate()));

            case 7:
                return filterDonation(donationArray, isDonorIDEquals(donationPredicates.askStr()));

            case 8:
                return filterDonation(donationArray, isDoneeIDEquals(donationPredicates.askStr()));

            case 9:
                return filterDonation(donationArray, isCampaignIDEquals(donationPredicates.askStr()));

            default:
                System.out.println("Index not correct...");
                return null;
        }
    }

    public static Donation[] filterDonation(Donation[] donationArray, Predicate condition) {
        return (Donation[]) Stream.of(donationArray).filter(condition).toArray(Donation[]::new);
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
