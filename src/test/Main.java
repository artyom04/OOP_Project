package test;

import service.AdministrativeEmployeeService;
import service.EmployeeService;
import service.LecturerService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isMenuActive = true;
        while (isMenuActive) {
            System.out.println("------------MENU------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Print All Employees");
            System.out.println("3. Print Employees whose Experience is Greater than 4 Years");
            System.out.println("4. Calculate Bonus of Employee by Tax Payer ID");
            System.out.println("5. Calculate Tax Amount of Employee by Tax Payer ID");
            System.out.println("6. Sort Employees by Experience and Print");
            System.out.println("7. Sort Employees by Year of Birth and Print");
            System.out.println("8. Save Budget Report");
            System.out.println("9. Print Budget Report");
            System.out.println("10. Exit Menu");

            int choice = scanner.nextInt();
            int innerChoice;
            switch (choice) {
                case 1:
                    System.out.println("1. Create Lecturer 2. Create Administrative Employee");
                    innerChoice = scanner.nextInt();
                    switch (innerChoice) {
                        case 1:
                            System.out.println("Creating Lecturer");
                            LecturerService.createLecturerAndWriteToFile();
                            break;
                        case 2:
                            System.out.println("Creating Administrative Employee");
                            AdministrativeEmployeeService.createAdministrativeEmployeeAndWriteToFile();
                            break;
                        default:
                            System.out.println("You have chosen wrong value");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("1. Print All Lecturers 2. Print All Administrative Employees 3. Both");
                    innerChoice = scanner.nextInt();
                    switch (innerChoice) {
                        case 1:
                            System.out.println("Printing Lecturers");
                            try {
                                LecturerService.printLecturersFromFile();
                            } catch (Exception e) {
                                System.out.println("Invalid Data in File");
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            System.out.println("Printing Administrative Employees");
                            try {
                                AdministrativeEmployeeService.printLecturersFromFile();
                            } catch (Exception e) {
                                System.out.println("Invalid Data in File");
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            System.out.println("Printing All Employees");
                            try {
                                EmployeeService.printEmployees();
                            } catch (Exception e) {
                                System.out.println("Invalid Data in File");
                                e.printStackTrace();
                            }
                            break;
                        default:
                            System.out.println("You have chosen wrong value");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("1. For Lecturers 2. For Administrative Employees 3. For Both");
                    innerChoice = scanner.nextInt();
                    switch (innerChoice) {
                        case 1:
                            try {
                                LecturerService.printLecturersWithExperienceMoreThan4Years();
                            } catch (Exception e) {
                                System.out.println("Invalid Data in File");
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                AdministrativeEmployeeService.printAdminEmployeesWithExperienceMoreThan4Years();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case 3:
                            try {
                                EmployeeService.printEmployeesWithExperienceMoreThan4Years();
                            } catch (Exception e) {
                                System.out.println("Invalid Data in File");
                                e.printStackTrace();
                            }
                            break;
                        default:
                            System.out.println("You have chosen wrong value");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("1. For Lecturer  2. For Administrative Employee");
                    innerChoice = scanner.nextInt();
                    switch (innerChoice) {
                        case 1:
                            try {
                                LecturerService.getLecturerBonusAmount();
                            } catch (Exception e) {
                                System.out.println("Invalid Data in File");
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                AdministrativeEmployeeService.getAdminEmployeeBonusAmount();
                            } catch (Exception e) {
                                System.out.println("Invalid Data in File");
                                e.printStackTrace();
                            }
                            break;
                        default:
                            System.out.println("You have chosen wrong value");
                            break;
                    }
                    break;
                case 5:
                    System.out.println("1. For Lecturers 2. For Administrative Employee");
                    innerChoice = scanner.nextInt();
                    switch (innerChoice) {
                        case 1:
                            try {
                                LecturerService.getLecturerTaxAmount();
                            } catch (Exception e) {
                                System.out.println("Invalid Data in File");
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                AdministrativeEmployeeService.getAdministrativeEmployeeTaxAmount();
                            } catch (Exception e) {
                                System.out.println("Invalid Data in File");
                                e.printStackTrace();
                            }
                            break;
                        default:
                            System.out.println("You have chosen wrong value");
                            break;
                    }
                    break;
                case 6:
                    System.out.println("1. For Lecturers  2. For Administrative Employees");
                    innerChoice = scanner.nextInt();
                    switch (innerChoice) {
                        case 1:
                            System.out.println("Sorted by Experience");
                            try {
                                LecturerService.sortLecturersByExperienceAndPrint();
                            } catch (Exception e) {
                                System.out.println("Invalid Data in File");
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            System.out.println("Sorted by Experience");
                            try {
                                AdministrativeEmployeeService.sortAdministrativeEmployeesByExperienceAndPrint();
                            } catch (Exception e) {
                                System.out.println("Invalid Data in File");
                                e.printStackTrace();
                            }
                            break;
                        default:
                            System.out.println("You have chosen wrong value");
                            break;
                    }
                    break;
                case 7:
                    System.out.println("1. For Lecturers  2. For Administrative Employees");
                    innerChoice = scanner.nextInt();
                    switch (innerChoice) {
                        case 1:
                            System.out.println("Sorted by Year of Birth");
                            try {
                                LecturerService.sortLecturersByYearOfBirthAndPrint();
                            } catch (Exception e) {
                                System.out.println("Invalid Data in File");
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            System.out.println("Sorted by Year of Birth");
                            try {
                                AdministrativeEmployeeService.sortLecturersByYearOfBirthAndPrint();
                            } catch (Exception e) {
                                System.out.println("Invalid Data in File");
                                e.printStackTrace();
                            }
                            break;
                        default:
                            System.out.println("You have chosen wrong value");
                            break;
                    }
                    break;
                case 8:
                    try {
                        EmployeeService.makeBudgetReport();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 9:
                    System.out.println("Budget Report");
                    try {
                        EmployeeService.printBudgetReport();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 10:
                    System.out.println("Exiting");
                    isMenuActive = false;
                    break;
                default:
                    System.out.println("Wrong Command");
                    break;
            }
        }
    }
}