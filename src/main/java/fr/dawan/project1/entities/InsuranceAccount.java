package fr.dawan.project1.entities;

import jakarta.persistence.Entity;

@Entity
public class InsuranceAccount extends BankAccount{

    private double interestRate;

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
