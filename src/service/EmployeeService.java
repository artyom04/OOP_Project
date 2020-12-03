package service;

public class EmployeeService {
    public static void printEmployeesWithExperienceMoreThan4Years() throws Exception {
        LecturerService.printLecturersWithExperienceMoreThan4Years();
        AdministrativeEmployeeService.printAdminEmployeesWithExperienceMoreThan4Years();
    }

    public static void printEmployees() throws Exception {
        LecturerService.printLecturersFromFile();
        AdministrativeEmployeeService.printLecturersFromFile();
    }

    public static void makeBudgetReport() throws Exception {
        LecturerService.setLecturersArrayFromFile();
        AdministrativeEmployeeService.setAdministrativeEmployeesArrayFromFile();
        if (LecturerService.getLecturers().length == 0 && AdministrativeEmployeeService.getAdministrativeEmployees().length == 0) {
            System.out.println("No Data To Write!");
        } else {
            for (int i = 0; i < LecturerService.getLecturers().length; i++) {
                String temporaryData = "\n" + LecturerService.getLecturers()[i].getTaxPayerID() + "," +
                        LecturerService.getLecturers()[i].getSalary() + "," +
                        LecturerService.getLecturers()[i].calculateBonus() + ","
                        + LecturerService.getLecturers()[i].calculateTaxAmount();
                FileService.write("src/FinancialReport.txt",
                        temporaryData);
            }
            for (int i = 0; i < AdministrativeEmployeeService.getAdministrativeEmployees().length; i++) {
                String temporaryData = "\n" + AdministrativeEmployeeService.getAdministrativeEmployees()[i].getTaxPayerID()
                        + "," + AdministrativeEmployeeService.getAdministrativeEmployees()[i].getSalary() + ","
                        + AdministrativeEmployeeService.getAdministrativeEmployees()[i].calculateBonus()
                        + "," + AdministrativeEmployeeService.getAdministrativeEmployees()[i].calculateTaxAmount();
                FileService.write("src/FinancialReport.txt",
                        temporaryData);
            }
            System.out.println("Information was successfully written");
            System.out.println();
        }

    }

    public static void printBudgetReport() throws Exception {
        String[] temporaryDataArray = FileService.read("src/FinancialReport.txt");
        for (String s : temporaryDataArray) {
            System.out.println(s);
        }
        System.out.println();
    }
}