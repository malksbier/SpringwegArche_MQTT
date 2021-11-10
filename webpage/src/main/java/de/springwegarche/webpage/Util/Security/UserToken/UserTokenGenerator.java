package de.springwegarche.webpage.Util.Security.UserToken;

import java.util.Random;

public class UserTokenGenerator {
    private static final String TYPECONNECTOR = "-";

    public static String generateEmailValidateUserToken () {
        // e for email
        return "E" + TYPECONNECTOR +UserTokenGenerator.generateToken();
    }
    public static String generatePasswordResetUserToken () {
        // p for password
        return "P" + TYPECONNECTOR + UserTokenGenerator.generateToken();
    }
    private static String generateToken() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

        return generatedString;
    }
}
