package util;

public class CpfValidator {

    public static boolean validate(String Cpf) {

        Cpf = Cpf.replaceAll("[^0-9]", "");

        if (Cpf.length() != 11) {
            return false;
        }

        if (Cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (Cpf.charAt(i) - '0') * (10 - i);
        }
        int firstCheckDigit = 11 - (sum % 11);
        if (firstCheckDigit == 10 || firstCheckDigit == 11) {
            firstCheckDigit = 0;
        }
        if (firstCheckDigit != (Cpf.charAt(9) - '0')) {
            return false;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (Cpf.charAt(i) - '0') * (11 - i);
        }
        int secondCheckDigit = 11 - (sum % 11);
        if (secondCheckDigit == 10 || secondCheckDigit == 11) {
            secondCheckDigit = 0;
        }
        if (secondCheckDigit != (Cpf.charAt(10) - '0')) {
            return false;
        }

        return true;
    }
}
