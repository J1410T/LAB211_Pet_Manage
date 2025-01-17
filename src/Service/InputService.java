package Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputService {

    //HANDLE INPUT EXCEPTION
    public static final Scanner SC = new Scanner(System.in);

    //ENTER INTEGER WITH MIN MAX BOUND
    public static int inputInt(String message, int min, int max) { //[0, Max value]
        //[0, 15], Enter value: 
        boolean isValid = false;
        int value = 0;
        while (!isValid) {
            System.out.print(message);
            try {
                value = Integer.parseInt(SC.nextLine());
                if (value >= min && value <= max) {
                    isValid = true;
                } else {
                    System.out.println("Value is out of bound!");
                }
            } catch (Exception e) {
                System.out.println("Value is not valid!");
            }
        }
        return value;
    }

    //ENTER INTEGER
    public static int inputInt(String message) {
        boolean isValid = false;
        int value = 0;
        while (!isValid) {
            System.out.print(message);
            try {
                value = Integer.parseInt(SC.nextLine());
                isValid = true;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return value;
    }

    //ENTER DOUBLE WITH MIN MAX BOUND
    public static double inputDouble(String message, double min, double max) {
        boolean isValid = false;
        double value = 0;
        while (!isValid) {
            System.out.print(message);
            try {
                value = Double.parseDouble(SC.nextLine());
                if (value >= min && value <= max) {
                    isValid = true;
                } else {
                    System.out.println("Value is out of bound!");
                }
            } catch (Exception e) {
                System.out.println("Value is not valid!");
            }
        }
        return value;
    }

    //ENTER NON EMPTRY STRING
    public static String inputString(String message) {
        boolean isValid = false;
        String value = "";
        while (!isValid) {
            System.out.print(message);
            value = SC.nextLine();
            if (!value.isEmpty()) {
                isValid = true;
            } else {
                System.out.println("Value can not be empty!");
            }
        }
        return value;
    }

    public static String inputPattern(String message, String pattern, String patternOnScreen) {
        boolean isValid = false;
        String value = "";
        while (!isValid) {
            System.out.print(message);
            value = SC.nextLine();
            if (value.matches(pattern)) {
                //pattern: form
                isValid = true;
            } else {
                System.out.println("Value must be in format " + patternOnScreen);
            }
        }
        return value;
    }

    public static Date inputDate(String message) {
        boolean check = true;
        String date;
        do {
            try {
                System.out.print(message + "[dd/mm/yyyy]: ");
                date = SC.nextLine();
                String[] result = date.split("[/-]");
                check = isDate(date);
                if(check){
                    Calendar cal = Calendar.getInstance();
                    cal.set(Integer.parseInt(result[2].trim()), Integer.parseInt(result[1].trim())-1, Integer.parseInt(result[0].trim()));
                    return cal.getTime();
                }
                throw new Exception();
            } catch (Exception ex) {
                System.out.println("Wrong format");
            }
        } while (!check);
        return null;
    }

    public static boolean isDate(String date) {
        boolean check = false;
        try {
            String[] result = date.split("[/-]");
            if(result.length != 3) return false;
            switch (result[1].trim()) {
                case "1", "3", "5", "7", "8", "10", "12" -> {
                    check = Integer.parseInt(result[0].trim()) <= 31;
                    break;
                }
                case "4", "6", "9", "11" -> {
                    check = Integer.parseInt(result[0].trim()) <= 30;
                    break;
                }
                case "2" -> {
                    if (Integer.parseInt(result[2]) % 4 == 0) {
                        check = Integer.parseInt(result[0].trim()) <= 29;
                    }
                    else check = Integer.parseInt(result[0].trim()) <= 28;
                    break;
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Error input");
        }
        return check;    
    }
    
    public static String date(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.format("%2s/%2s/%4s", cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
    }

    public static boolean inputBoolean(String message) {
        String data = inputPattern(message, "[TtFfYyNn]", "Only T(true) or F(false)");
        if (data.equalsIgnoreCase("T") || data.equalsIgnoreCase("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNumberic(String input) {
        try {
            double data = Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static String toTitleCase(String input){
        return input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase();
    }
    
    
}
