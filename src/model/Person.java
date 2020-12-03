package model;

import model.exceptions.InvalidNameException;

import java.time.LocalDate;

public abstract class Person {
    private String firstName;
    private String lastName;
    private LocalDate birthday;

    public Person() {

    }

    public Person(String firstName, String lastName, LocalDate birthday) throws InvalidNameException {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws InvalidNameException {
        if (firstName.matches("[A-Z][a-z]*") && !firstName.isEmpty()) {
            this.firstName = firstName;
        } else {
            throw new InvalidNameException(firstName);
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws InvalidNameException {
        if (lastName.matches("[A-Z][a-z]*") && !lastName.isEmpty()) {
            this.lastName = lastName;
        } else {
            throw new InvalidNameException(lastName);
        }
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}