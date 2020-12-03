package model.exceptions;

public class InvalidTaxPayerIDException extends Exception {
    public InvalidTaxPayerIDException(String taxPayerID) {
        super("Tax Payer ID should be of the following pattern: \"AA000000\": your input: " + taxPayerID);
    }
}