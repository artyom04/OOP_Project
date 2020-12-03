package model.exceptions;

public class InvalidNameException extends Exception {
    public InvalidNameException(String firstName) {
        super("Invalid Name: It Should Not Be Empty, Must Contain Only Letters, First Letter Must Be Uppercase: "
                + firstName);
    }
}