package loanInterestCalculator;

import java.time.LocalDate;

abstract class LoanCalculator {
    protected static final double ACTUAL_BANK_INTEREST_RATE = 12.0; // Actual bank interest rate

    protected double calculateBaseEMI(LoanApplication loanApplication, double interestRate) {
        double p = loanApplication.getAmount(); // Principal loan amount
        double r = interestRate / 12 / 100; // Monthly interest rate
        int n = loanApplication.getTenure() * 12; // Total number of monthly payments

        return (p * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1); // EMI calculation formula
    }

    public abstract double calculateMonthlyInstallment(LoanApplication loanApplication);

    public static boolean isCustomerMajor(LocalDate dob) {
        return java.time.Period.between(dob, LocalDate.now()).getYears() >= 18;
    }
}
