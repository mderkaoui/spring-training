package fr.dawan.project1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;

@Entity
public class SavingAccount extends BankAccount{

    private double interestRate;

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
