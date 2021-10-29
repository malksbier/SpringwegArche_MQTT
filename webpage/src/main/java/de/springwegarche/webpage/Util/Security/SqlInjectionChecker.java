package de.springwegarche.webpage.Util.Security;

public class SqlInjectionChecker {
    public static boolean isSafe(String toCheck) {
        if(toCheck.contains("/") || toCheck.contains("\\") ||toCheck.contains(";") || toCheck.contains("*") || toCheck.contains("\"") || toCheck.contains("\'")) {
            return false;
        } else {
            return true;
        }
    }
} 
