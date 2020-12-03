package service;

import model.AdministrativeEmployee;
import model.exceptions.EmptyNameException;
import model.exceptions.InvalidNameException;
import model.exceptions.InvalidTaxPayerIDException;
import model.exceptions.InvalidValueException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class AdministrativeEmployeeService {
    private static AdministrativeEmployee[] administrativeEmployees;

    private static AdministrativeEmployee createAdministrativeAssistant() {
        Scanner scanner = new Scanner(System.in);
        AdministrativeEmployee administrativeEmployee = new AdministrativeEmployee();
        boolean indicator = true;
        while (indicator) {
            System.out.print("Enter the first name of Administrative Employee : ");
            String firstName = scanner.nextLine();
            try {
                administrativeEmployee.setFirstName(firstName);
                indicator = false;
            } catch (InvalidNameException e) {
                System.out.println("Please try again");
                e.printStackTrace();
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the last name of Administrative Employee : ");
            String lastName = scanner.nextLine();
            try {
                administrativeEmployee.setLastName(lastName);
                indicator = false;
            } catch (InvalidNameException e) {
                System.out.println("Please try again");
                e.printStackTrace();
            }
        }
        indicator = true;
        while (indicator) {
            boolean flag = true;
            int yearOfBirth = 0;
            while (flag) {
                System.out.print("Enter the year of birth: ");
                yearOfBirth = scanner.nextInt();
                if (yearOfBirth > 1920 && yearOfBirth < 2002) {
                    flag = false;
                } else {
                    System.out.println("Wrong value. Value should be between 1920 and 2002");
                    System.out.println("Please try again");
                }
            }
            System.out.print("Enter the month of birth: (value in [1,12]): ");
            int monthOfBirth = scanner.nextInt();
            System.out.print("Enter the day of birth: (value in [1,31]): ");
            int dayOfBirth = scanner.nextInt();
            try {
                LocalDate date = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
                administrativeEmployee.setBirthday(date);
                indicator = false;
            } catch (Exception e) {
                System.out.println("Please try again");
                e.printStackTrace();
            }

        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Tax Payer ID: ");
            String taxPayerId = scanner.next();
            try {
                administrativeEmployee.setTaxPayerID(taxPayerId);
                indicator = false;
            } catch (InvalidTaxPayerIDException e) {
                System.out.println("Please try again");
                e.printStackTrace();
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Salary of Administrative Employee: ");
            double salary = scanner.nextDouble();
            try {
                administrativeEmployee.setSalary(salary);
                indicator = false;
            } catch (InvalidValueException e) {
                System.out.println("Please try again");
                e.printStackTrace();
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Experience of Administrative Employee: ");
            double experience = scanner.nextDouble();
            try {
                administrativeEmployee.setExperience(experience);
                indicator = false;
            } catch (InvalidValueException e) {
                System.out.println("Please try again");
                e.printStackTrace();
            }
        }
        scanner.nextLine();
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Responsibility: ");
            String responsibility = scanner.nextLine();
            try {
                administrativeEmployee.setResponsibility(responsibility);
                indicator = false;
            } catch (EmptyNameException e) {
                System.out.println("Please try again");
                e.printStackTrace();
            }
        }
        return administrativeEmployee;
    }

    public static void createAdministrativeEmployeeAndWriteToFile() {
        AdministrativeEmployee administrativeEmployee = createAdministrativeAssistant();
        String data = "\n" + administrativeEmployee;
        try {
            FileService.write("src/AdministrativeEmployee.txt", data);
            System.out.println("Information was successfully written to AdministrativeEmployee.txt file!");
        } catch (IOException e) {
            System.out.println("File Not Found / Can't Write");
            e.printStackTrace();
        }
    }

    static void setAdministrativeEmployeesArrayFromFile() throws Exception {
        String[] administrativeEmployeeData = FileService.read("src/AdministrativeEmployee.txt");
        AdministrativeEmployee[] administrativeEmployees = new AdministrativeEmployee[administrativeEmployeeData.length - 1];
        for (int i = 0; i < administrativeEmployees.length; i++) {
            administrativeEmployees[i] = new AdministrativeEmployee(administrativeEmployeeData[i + 1]);
        }
        AdministrativeEmployeeService.administrativeEmployees = administrativeEmployees;
    }

    public static void printLecturersFromFile() throws Exception {
        setAdministrativeEmployeesArrayFromFile();
        if (isEmpty()) {
            System.out.println("File is Empty!");
        } else {
            for (int i = 0; i < administrativeEmployees.length; i++) {
                System.out.print((i + 1) + ". ");
                printAdministrativeEmployee(administrativeEmployees[i]);
            }
        }
        System.out.println();
    }

    private static void printAdministrativeEmployee(AdministrativeEmployee administrativeEmployee) {
        System.out.println("Administrative Employee Name: " + administrativeEmployee.getFirstName() +
                ", Administrative Employee Surname: " + administrativeEmployee.getLastName() + ", Birthday: " +
                administrativeEmployee.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", Tax Payer ID: " + administrativeEmployee.getTaxPayerID() + ", Salary: " +
                administrativeEmployee.getSalary() + ", Experience: " + administrativeEmployee.getExperience() +
                ", Responsibility: " + administrativeEmployee.getResponsibility());
    }

    private static boolean isEmpty() {
        return administrativeEmployees.length == 0;
    }

    public static void printAdminEmployeesWithExperienceMoreThan4Years() throws Exception {
        setAdministrativeEmployeesArrayFromFile();
        if (isEmpty()) {
            System.out.println("No Administrative Employee in File");
        } else {
            int i = 1;
            for (AdministrativeEmployee administrativeEmployee : administrativeEmployees) {
                if (administrativeEmployee.getExperience() > 4) {
                    System.out.print((i) + ". ");
                    printAdministrativeEmployee(administrativeEmployee);
                    i++;
                }
            }
        }
        System.out.println();
    }

    public static void getAdminEmployeeBonusAmount() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Enter the ID of Tax Payer: ");
        String taxPayerID = scanner.next();
        if (giveAdminEmployeeByTaxPayerID(taxPayerID) == null) {
            System.out.println("No Administrative Employee with such Tax Payer ID");
        } else {
            System.out.print("Bonus is: ");
            System.out.println(Objects.requireNonNull(giveAdminEmployeeByTaxPayerID(taxPayerID)).calculateBonus());
        }
        System.out.println();
    }

    private static AdministrativeEmployee giveAdminEmployeeByTaxPayerID(String taxPayerID) throws Exception {
        setAdministrativeEmployeesArrayFromFile();
        for (AdministrativeEmployee administrativeEmployee : administrativeEmployees) {
            if (administrativeEmployee.getTaxPayerID().equals(taxPayerID)) {
                return administrativeEmployee;
            }
        }
        return null;
    }

    public static void getAdministrativeEmployeeTaxAmount() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Enter the ID of Tax Payer: ");
        String taxPayerID = scanner.next();
        if (giveAdminEmployeeByTaxPayerID(taxPayerID) == null) {
            System.out.println("No Administrative Employee with such Tax Payer ID");
        } else {
            System.out.print("Tax Amount is: ");
            System.out.println(Objects.requireNonNull(giveAdminEmployeeByTaxPayerID(taxPayerID)).calculateTaxAmount());
        }
        System.out.println();
    }

    public static void sortAdministrativeEmployeesByExperienceAndPrint() throws Exception {
        setAdministrativeEmployeesArrayFromFile();
        if (isEmpty()) {
            System.out.println("No Administrative Employee in File");
        } else {
            for (int i = 0; i < administrativeEmployees.length - 1; i++) {
                for (int j = 0; j < administrativeEmployees.length - i - 1; j++) {
                    if (administrativeEmployees[j].getExperience() > administrativeEmployees[j + 1].getExperience()) {
                        AdministrativeEmployee temporary = administrativeEmployees[j];
                        administrativeEmployees[j] = administrativeEmployees[j + 1];
                        administrativeEmployees[j + 1] = temporary;
                    }
                }
            }
        }
        printAdministrativeEmployeesArray(administrativeEmployees);
        System.out.println();
    }

    private static void printAdministrativeEmployeesArray(AdministrativeEmployee[] administrativeEmployees) {
        for (AdministrativeEmployee administrativeEmployee : administrativeEmployees) {
            printAdministrativeEmployee(administrativeEmployee);
        }
    }

    public static void sortLecturersByYearOfBirthAndPrint() throws Exception {
        setAdministrativeEmployeesArrayFromFile();
        if (isEmpty()) {
            System.out.println("No Administrative Employee in File");
        } else {
            for (int i = 0; i < administrativeEmployees.length - 1; i++) {
                for (int j = 0; j < administrativeEmployees.length - i - 1; j++) {
                    if (administrativeEmployees[j].getBirthday().getYear() <
                            administrativeEmployees[j + 1].getBirthday().getYear()) {
                        AdministrativeEmployee temporary = administrativeEmployees[j];
                        administrativeEmployees[j] = administrativeEmployees[j + 1];
                        administrativeEmployees[j + 1] = temporary;
                    }
                }
            }
        }
        printAdministrativeEmployeesArray(administrativeEmployees);
        System.out.println();
    }

    public static AdministrativeEmployee[] getAdministrativeEmployees() {
        return administrativeEmployees;
    }
}