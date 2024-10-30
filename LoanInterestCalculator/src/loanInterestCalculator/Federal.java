package loanInterestCalculator;

class Federal extends LoanCalculator {
    @Override
    public double calculateMonthlyInstallment(LoanApplication loanApplication) {
        double baseEMI = calculateBaseEMI(loanApplication, loanApplication.getRequestedIR());

        if (baseEMI < 1000) {
            System.out.println("Federal Policy: No EMI for the first 3 months.");
            int gracePeriodMonths = 3;
            int n = loanApplication.getTenure() * 12 - gracePeriodMonths;

            if (n <= 0) {
                return 0.0;
            }

            return (loanApplication.getAmount() * (loanApplication.getRequestedIR() / 12 / 100) *
                    Math.pow(1 + (loanApplication.getRequestedIR() / 12 / 100), n)) /
                    (Math.pow(1 + (loanApplication.getRequestedIR() / 12 / 100), n) - 1);
        }

        return baseEMI;
    }
}