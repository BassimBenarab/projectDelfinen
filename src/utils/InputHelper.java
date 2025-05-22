package utils;

import java.util.Scanner;

public class InputHelper {
    private Scanner scanner;

    public InputHelper (Scanner scanner) {
        this.scanner = scanner;
    }

    public int getIntInput(String prompt, int min, int max) {
        int input = -1;
        boolean valid = false;

        while (!valid) {
            System.out.println(prompt);
            if (scanner.hasNextInt()) {
                input =scanner.nextInt();
                scanner.nextLine();
                if (input >= min && input <= max) {
                    valid = true;
                } else {
                    System.out.println("Ugyldigt valg. Vælg et tal mellem " + min + " og " + max + ".");
                }
            } else {
                System.out.println("Ugyldigt indtast. indtast et tal.");
                scanner.nextLine();
            }
        }
        return input;

        }
    public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    public double getDoubleInput(String prompt) {
        double input = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt + " ");
            if (scanner.hasNextDouble()) {
                input = scanner.nextDouble();
                scanner.nextLine();
                valid = true;
            } else {
                System.out.println("Indtast venligst et decimaltal.");
                scanner.nextLine();
            }
        }
        return input;
    }
}
