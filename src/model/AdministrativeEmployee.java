package model;

import model.exceptions.EmptyNameException;
import model.exceptions.InvalidNameException;
import model.exceptions.InvalidTaxPayerIDException;
import model.exceptions.InvalidValueException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdministrativeEmployee extends Employee {
    private String responsibility;

    public AdministrativeEmployee() {

    }

    public AdministrativeEmployee(String data) throws InvalidNameException, InvalidTaxPayerIDException,
            InvalidValueException, EmptyNameException {
        String[] splitData = data.split(",");
        setFirstName(splitData[0]);
        setLastName(splitData[1]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        setBirthday(LocalDate.parse(splitData[2], formatter));
        setTaxPayerID(splitData[3]);
        setSalary(Double.parseDouble(splitData[4]));
        setExperience(Double.parseDouble(splitData[5]));
        setResponsibility(splitData[6]);
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) throws EmptyNameException {
        if (!responsibility.isEmpty()) {
            this.responsibility = responsibility;
        } else {
            throw new EmptyNameException();
        }
    }

    @Override
    public boolean isEligibleForBonus() {
        return this.getSalary() < 150000 && this.getExperience() > 1;
    }

    @Override
    public double calculateBonus() {
        if (this.isEligibleForBonus()) {
            return this.getSalary() * 0.2;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return this.getFirstName() + "," + this.getLastName() + "," +
                this.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "," + this.getTaxPayerID() + "," +
                this.getSalary() + "," + this.getExperience() + "," + this.getResponsibility();
    }
}