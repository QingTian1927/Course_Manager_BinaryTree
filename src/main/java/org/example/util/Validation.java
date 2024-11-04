package org.example.util;

import java.time.LocalDate;
import java.util.Scanner;

public final class  Validation {
    private Validation() {
    }

    public static boolean isNonNegative(int x) {
        return x >= 0;
    }

    public static boolean isNonNegative(double x) {
        return x >= 0;
    }

    public static boolean isBooleanInt(int x) {
        return x == 0 || x == 1;
    }

    public static int getInteger(String msg, String err, int min, int max) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(msg);
                int num = Integer.parseInt(sc.nextLine());
                if (num > max || num < min) {
                    throw new NumberFormatException();
                }
                return num;
            } catch (NumberFormatException e) {
                System.out.println(err);
            }
        }
    }


    public static String getString() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            if (!s.isEmpty()) {
                return s;
            }
            System.out.println("Input invalid. Please input string.");
        }
    }

    public static boolean isNumber(String s) {
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String getStringYear() {
        int currentYear = LocalDate.now().getYear();
        String s;
        while (true) {
            s = getString();
            if (isNumber(s) && Integer.parseInt(s) <= currentYear) {
                return s;
            }
            System.out.println("Please enter a valid number!");
            System.out.print("Enter year: ");
        }
    }

    public static char getChar() {
        Scanner sc = new Scanner(System.in);
        return sc.next().charAt(0);
    }

    public static double getDouble(String msg, String err, double min, double max) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(msg);
                double num = Double.parseDouble(sc.nextLine());
                if (num < min || num > max) {
                    throw new NumberFormatException();
                }
                return num;
            } catch (NumberFormatException e) {
                System.out.println(err);
            }
        }
    }

    public static int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static double parseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
