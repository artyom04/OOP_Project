package service;

import model.Lecturer;
import model.exceptions.EmptyNameException;
import model.exceptions.InvalidNameException;
import model.exceptions.InvalidTaxPayerIDException;
import model.exceptions.InvalidValueException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class LecturerService {
    private static Lecturer[] lecturers;

    private static Lecturer createLecturer() {
        Scanner scanner = new Scanner(System.in);
        Lecturer lecturer = new Lecturer();
        boolean indicator = true;
        while (indicator) {
            System.out.print("Enter the first name of Lecturer : ");
            String firstName = scanner.nextLine();
            try {
                lecturer.setFirstName(firstName);
                indicator = false;
            } catch (InvalidNameException e) {
                System.out.println("Please try again");
                e.printStackTrace();
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the last name of Lecturer : ");
            String lastName = scanner.nextLine();
            try {
                lecturer.setLastName(lastName);
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
                lecturer.setBirthday(date);
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
                lecturer.setTaxPayerID(taxPayerId);
                indicator = false;
            } catch (InvalidTaxPayerIDException e) {
                System.out.println("Please try again");
                e.printStackTrace();
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Salary of Lecturer: ");
            double salary = scanner.nextDouble();
            try {
                lecturer.setSalary(salary);
                indicator = false;
            } catch (InvalidValueException e) {
                System.out.println("Please try again");
                e.printStackTrace();
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Experience of Lecturer: ");
            double experience = scanner.nextDouble();
            try {
                lecturer.setExperience(experience);
                indicator = false;
            } catch (InvalidValueException e) {
                System.out.println("Please try again");
                e.printStackTrace();
            }
        }
        scanner.nextLine();
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Academic Degree of Lecturer: ");
            String academicDegree = scanner.nextLine();
            try {
                lecturer.setAcademicDegree(academicDegree);
                indicator = false;
            } catch (EmptyNameException e) {
                System.out.println("Please try again");
                e.printStackTrace();
            }
        }
        indicator = true;
        while (indicator) {
            System.out.print("Enter the Teaching Course: ");
            String course = scanner.nextLine();
            try {
                lecturer.setTaughtCourse(course);
                indicator = false;
            } catch (EmptyNameException e) {
                System.out.println("Please try again");
                e.printStackTrace();
            }
        }
        return lecturer;
    }

    public static void createLecturerAndWriteToFile() {
        Lecturer lecturer = createLecturer();
        String data = "\n" + lecturer;
        try {
            FileService.write("src/Lecturer.txt", data);
            System.out.println("Information was successfully written to Lecturer.txt file!");
        } catch (IOException e) {
            System.out.println("File Not Found / Can't Write");
            e.printStackTrace();
        }
    }

    static void setLecturersArrayFromFile() throws Exception {
        String[] lecturersData = FileService.read("src/Lecturer.txt");
        Lecturer[] lecturers = new Lecturer[lecturersData.length - 1];
        for (int i = 0; i < lecturers.length; i++) {
            lecturers[i] = new Lecturer(lecturersData[i + 1]);
        }
        LecturerService.lecturers = lecturers;
    }

    public static void printLecturersFromFile() throws Exception {
        setLecturersArrayFromFile();
        if (isEmpty()) {
            System.out.println("File is Empty!");
        } else {
            for (int i = 0; i < lecturers.length; i++) {
                System.out.print((i + 1) + ". ");
                printLecturer(lecturers[i]);
            }
        }
    }

    private static void printLecturer(Lecturer lecturer) {
        System.out.println("Lecturer Name: " + lecturer.getFirstName() + ", Lecturer Surname: " +
                lecturer.getLastName() + ", Birthday: " +
                lecturer.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", Tax Payer ID: "
                + lecturer.getTaxPayerID() + ", Salary: " + lecturer.getSalary() + ", Experience: "
                + lecturer.getExperience() + ", Academic Degree: " + lecturer.getAcademicDegree() + ", Taught Course: "
                + lecturer.getTaughtCourse());
    }

    public static void printLecturersWithExperienceMoreThan4Years() throws Exception {
        setLecturersArrayFromFile();
        if (isEmpty()) {
            System.out.println("No Lecturer in File");
        } else {
            int i = 1;
            for (Lecturer lecturer : lecturers) {
                if (lecturer.getExperience() > 4) {
                    System.out.print((i) + ". ");
                    printLecturer(lecturer);
                    i++;
                }
            }
        }
    }

    public static void getLecturerBonusAmount() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Enter the ID of Tax Payer: ");
        String taxPayerID = scanner.next();
        if (giveLecturerByTaxPayerID(taxPayerID) == null) {
            System.out.println("No Lecturer with such Tax Payer ID");
        } else {
            System.out.print("Bonus is: ");
            System.out.println(Objects.requireNonNull(giveLecturerByTaxPayerID(taxPayerID)).calculateBonus());
        }
        System.out.println();
    }

    private static Lecturer giveLecturerByTaxPayerID(String taxPayerID) throws Exception {
        setLecturersArrayFromFile();
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getTaxPayerID().equals(taxPayerID)) {
                return lecturer;
            }
        }
        return null;
    }

    public static void sortLecturersByExperienceAndPrint() throws Exception {
        setLecturersArrayFromFile();
        if (isEmpty()) {
            System.out.println("No Lecturer in File");
        } else {
            for (int i = 0; i < lecturers.length - 1; i++) {
                for (int j = 0; j < lecturers.length - i - 1; j++) {
                    if (lecturers[j].getExperience() > lecturers[j + 1].getExperience()) {
                        Lecturer temporary = lecturers[j];
                        lecturers[j] = lecturers[j + 1];
                        lecturers[j + 1] = temporary;
                    }
                }
            }
        }
        printLecturersArray(lecturers);
        System.out.println();
    }

    private static void printLecturersArray(Lecturer[] lecturers) {
        for (Lecturer lecturer : lecturers) {
            printLecturer(lecturer);
        }
    }

    public static void sortLecturersByYearOfBirthAndPrint() throws Exception {
        setLecturersArrayFromFile();
        if (isEmpty()) {
            System.out.println("No Lecturer in File");
        } else {
            for (int i = 0; i < lecturers.length - 1; i++) {
                for (int j = 0; j < lecturers.length - i - 1; j++) {
                    if (lecturers[j].getBirthday().getYear() < lecturers[j + 1].getBirthday().getYear()) {
                        Lecturer temporary = lecturers[j];
                        lecturers[j] = lecturers[j + 1];
                        lecturers[j + 1] = temporary;
                    }
                }
            }
        }
        printLecturersArray(lecturers);
        System.out.println();
    }

    public static void getLecturerTaxAmount() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Enter the ID of Tax Payer: ");
        String taxPayerID = scanner.next();
        if (giveLecturerByTaxPayerID(taxPayerID) == null) {
            System.out.println("No Lecturer with such Tax Payer ID");
        } else {
            System.out.print("Tax Amount is: ");
            System.out.println(Objects.requireNonNull(giveLecturerByTaxPayerID(taxPayerID)).calculateTaxAmount());
        }
        System.out.println();
    }

    private static boolean isEmpty() {
        return lecturers.length == 0;
    }

    public static Lecturer[] getLecturers() {
        return lecturers;
    }
}