package model;

import model.exceptions.InvalidTaxPayerIDException;
import model.exceptions.InvalidValueException;
import model.exceptions.InvalidNameException;
import model.interfaces.Bonus;
import model.interfaces.TaxPaying;

import java.time.LocalDate;

public abstract class Employee extends Person implements Bonus, TaxPaying {
    public static final double MINIMAL_SALARY = 68000;
    private String taxPayerID;
    private double salary;
    private double experience;

    public Employee() {

    }

    public Employee(String firstName, String lastName, LocalDate birthday, String taxPayerID, double salary,
                    int experience) throws InvalidNameException {
        super(firstName, lastName, birthday);
        this.taxPayerID = taxPayerID;
        this.salary = salary;
        this.experience = experience;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) throws InvalidValueException {
        if (experience >= 0 && experience <= 50) {
            this.experience = experience;
        } else {
            throw new InvalidValueException("Experience must be in interval [0,50]: " + experience);
        }
    }

    public String getTaxPayerID() {
        return taxPayerID;
    }

    public void setTaxPayerID(String taxPayerID) throws InvalidTaxPayerIDException {
        if (taxPayerID.matches("[A-Z]{2}[0-9]{6}")) {
            this.taxPayerID = taxPayerID;
        } else {
            throw new InvalidTaxPayerIDException(taxPayerID);
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) throws InvalidValueException {
        if (salary >= MINIMAL_SALARY) {
            this.salary = salary;
        } else {
            throw new InvalidValueException("Minimal Salary is " + MINIMAL_SALARY + ", your input: " + salary);
        }
    }

    @Override
    public double calculateTaxAmount() {
        if (this.getSalary() == MINIMAL_SALARY) {
            return this.getSalary() * 5 / 100;
        } else if (this.getSalary() > MINIMAL_SALARY && this.getSalary() < 3 * MINIMAL_SALARY) {
            return this.getSalary() * 10 / 100;
        } else {
            return this.getSalary() * 20 / 100;
        }
    }
}