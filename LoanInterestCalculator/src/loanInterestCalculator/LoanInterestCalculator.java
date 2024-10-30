package loanInterestCalculator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoanInterestCalculator {
    private static Map<Integer, LoanApplication> loanApplications = new HashMap<>();
    private static int applicationCounter = 1; // Unique ID for each loan application

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Date of Birth (DD/MM/YYYY): ");
        String DOB = sc.nextLine();
        LocalDate dob = LocalDate.parse(DOB, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Enter Loan Amount: ");
        double amount = sc.nextDouble();

        System.out.print("Enter Requested Interest Rate: ");
        double requestedIR = sc.nextDouble(); 

        System.out.print("Enter tenure (in years): ");
        int tenure = sc.nextInt();

        LoanApplication loanApplication = new LoanApplication(name, dob, amount, requestedIR, tenure);

        try {
            if (LoanCalculator.isCustomerMajor(loanApplication.getDob())) {
                System.out.println("Customer is eligible for loan.");

                loanApplications.put(applicationCounter++, loanApplication);

                LoanCalculator hdfc = new HDFC();
                LoanCalculator federal = new Federal();

                double emiRequestedHDFC = hdfc.calculateMonthlyInstallment(loanApplication);
                double emiRequestedFederal = federal.calculateMonthlyInstallment(loanApplication);

                double actualBankInterestRate = LoanCalculator.ACTUAL_BANK_INTEREST_RATE; 
                double emiActualHDFC = hdfc.calculateMonthlyInstallment(
                        new LoanApplication(name, dob, amount, actualBankInterestRate, tenure));
                double emiActualFederal = federal.calculateMonthlyInstallment(
                        new LoanApplication(name, dob, amount, actualBankInterestRate, tenure));

                System.out.printf("Loan Application ID: %d%n", applicationCounter - 1);
                System.out.printf("Requested Monthly Installment (HDFC, %.1f%%): %.2f%n", requestedIR, emiRequestedHDFC);
                System.out.printf("Requested Monthly Installment (Federal, %.1f%%): %.2f%n", requestedIR, emiRequestedFederal);
                System.out.printf("Actual Monthly Installment (HDFC, %.1f%%): %.2f%n", actualBankInterestRate, emiActualHDFC);
                System.out.printf("Actual Monthly Installment (Federal, %.1f%%): %.2f%n", actualBankInterestRate, emiActualFederal);
            } else {
                loanApplication.setMinor(true);
                throw new IllegalArgumentException("Not eligible for loan - customer is a minor.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            sc.close(); 
        }
        
        System.out.println("\nAll Loan Applications:");
        for (Map.Entry<Integer, LoanApplication> entry : loanApplications.entrySet()) {
            System.out.println("Application ID: " + entry.getKey() + ", " + entry.getValue());
        }
    }
}