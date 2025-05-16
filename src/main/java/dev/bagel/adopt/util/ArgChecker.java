package dev.bagel.adopt.util;

public class ArgChecker {

    public static int checkAge(String ageStr) throws NumberFormatException {
        int age = Integer.parseInt(ageStr);
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0");
        }

        return age;
    }

}
