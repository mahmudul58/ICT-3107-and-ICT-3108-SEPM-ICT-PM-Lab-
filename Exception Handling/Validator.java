
package com.validation;

public class Validator {

    public static void validate(int age, String dept) throws InvalidAgeException, InvalidDeptException {

        if(age < 18 || age > 60) {
            throw new InvalidAgeException("Age must be between 18 and 60");
        }

        if(!(dept.equalsIgnoreCase("ICT") || dept.equalsIgnoreCase("CSE") || dept.equalsIgnoreCase("EEE"))) {
            throw new InvalidDeptException("Invalid Department");
        }

        System.out.println("Validation Successful");
    }
}
