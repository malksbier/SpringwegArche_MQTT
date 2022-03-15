package de.springwegarche.webpage.Util;

public class TimeUtil {

    public static boolean isValidTime(String time) {
        if(time.length() == 5 && time.charAt(2) == ':') {
            if(isSingleNumber(time.charAt(0)) && isSingleNumber(time.charAt(1)) && isSingleNumber(time.charAt(3)) && isSingleNumber(time.charAt(4)) ) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSingleNumber(char number) {
        if(number == '0' || number == '1' || number == '2' || number == '3' || number == '4' ||
         number == '5' || number == '6' || number == '7' || number == '8' || number == '9') {
             return true;
         } else {
             return false;
         }
    }
}
