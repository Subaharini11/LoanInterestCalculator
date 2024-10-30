package loanInterestCalculator;

import java.time.LocalDate;

class LoanApplication {
    private String name;
    private LocalDate dob;
    private double amount;
    private double requestedIR;
    private int tenure;
    private boolean isMinor;

    public LoanApplication(String name, LocalDate dob, double amount, double requestedIR, int tenure) {
        this.name = name;
        this.dob = dob;
        this.amount = amount;
        this.requestedIR = requestedIR;
        this.tenure = tenure;
        this.isMinor = false;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public double getAmount() {
        return amount;
    }

    public double getRequestedIR() {
        return requestedIR;
    }

    public int getTenure() {
        return tenure;
    }

    public boolean isMinor() {
        return isMinor;
    }

    public void setMinor(boolean minor) {
        isMinor = minor;
    }

    
}

