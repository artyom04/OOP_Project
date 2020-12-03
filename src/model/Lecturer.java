package model;

import model.exceptions.EmptyNameException;
import model.exceptions.InvalidNameException;
import model.exceptions.InvalidTaxPayerIDException;
import model.exceptions.InvalidValueException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Lecturer extends Employee {
    private String academicDegree;
    private String taughtCourse;

    public Lecturer() {

    }

    public Lecturer(String data) throws InvalidNameException, InvalidTaxPayerIDException, InvalidValueException,
            EmptyNameException {
        String[] splitData = data.split(",");
        setFirstName(splitData[0]);
        setLastName(splitData[1]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        setBirthday(LocalDate.parse(splitData[2], formatter));
        setTaxPayerID(splitData[3]);
        setSalary(Double.parseDouble(splitData[4]));
        setExperience(Double.parseDouble(splitData[5]));
        setAcademicDegree(splitData[6]);
        setTaughtCourse(splitData[7]);
    }


    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) throws EmptyNameException {
        if (!academicDegree.isEmpty()) {
            this.academicDegree = academicDegree;
        } else {
            throw new EmptyNameException();
        }
    }

    public String getTaughtCourse() {
        return taughtCourse;
    }

    public void setTaughtCourse(String taughtCourse) throws EmptyNameException {
        if (!taughtCourse.isEmpty()) {
            this.taughtCourse = taughtCourse;
        } else {
            throw new EmptyNameException();
        }
    }

    @Override
    public boolean isEligibleForBonus() {
        return this.getExperience() >= 2;
    }

    @Override
    public double calculateBonus() {
        if (this.isEligibleForBonus()) {
            return this.getSalary() * 0.3;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return this.getFirstName() + "," + this.getLastName() + "," +
                this.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "," + this.getTaxPayerID() + "," +
                this.getSalary() + "," + this.getExperience() + "," + this.getAcademicDegree() + "," +
                this.getTaughtCourse();
    }
}