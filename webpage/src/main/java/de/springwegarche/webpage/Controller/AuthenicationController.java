package de.springwegarche.webpage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.springwegarche.webpage.Controller.Services.UserService;
import de.springwegarche.webpage.Models.User;
import de.springwegarche.webpage.Models.DAO.AuthenticationRequest;
import de.springwegarche.webpage.Models.DAO.AuthenticationResponse;
import de.springwegarche.webpage.Models.DAO.EmailAuthenticationRequest;
import de.springwegarche.webpage.Models.DAO.PasswordForgetRequest;
import de.springwegarche.webpage.Models.DAO.RegisterRequest;
import de.springwegarche.webpage.Models.DAO.SetNewPasswordRequest;
import de.springwegarche.webpage.Util.WebResponses;
import de.springwegarche.webpage.Util.Security.JwtUtil;
import de.springwegarche.webpage.Util.Security.SqlInjectionChecker;
import de.springwegarche.webpage.Util.Security.UserToken.UserTokenGenerator;

@RestController
public class AuthenicationController {
    private final String TAG = "[AuthenicationController] ";
    private final String mainRoute = "/login";

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = mainRoute + "/autheticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenicationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println(TAG + "/autheticate :" + authenticationRequest.toString());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (AuthenticationException e) {
            return WebResponses.badResponse("username_or_pawword_wrong");
        }
        String jwtToken = null;
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        if(userDetails != null) {
            jwtToken = jwtUtil.generateToken(userDetails);
        } else {
            return WebResponses.badResponse("username_wrong");
        }
        if(jwtToken != null && jwtToken != "") {
            jwtToken = "Bearer " + jwtToken;
            return WebResponses.okResponse(new AuthenticationResponse(jwtToken).toString());
        }

        return WebResponses.internalServerErrorResponse("could_not_generate_token");
    }

    @RequestMapping(value = mainRoute + "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) throws Exception {
        System.out.println(TAG + "/register :" + registerRequest.toString());
        // check if user exists
        final UserDetails userDetails = userDetailsService.loadUserByUsername(registerRequest.getUsername());
        if(userDetails != null) {
            return WebResponses.conflictResponse("username_allready_exists");
        }

        // Check validity of User;
        if(registerRequest.getUsername().length() <= 5) {
            return WebResponses.badResponse("username_has_to_be_six_chars_long");
        }
        if(registerRequest.getPassword().length() <= 5) {
            return WebResponses.badResponse("password_has_to_be_six_chars_long");
        }
        if(SqlInjectionChecker.isSafe(registerRequest.getUsername()) == false) {
            return WebResponses.badResponse("password_has_invalid_chars");
        }
        if(SqlInjectionChecker.isSafe(registerRequest.getEmail()) == false) {
            return WebResponses.badResponse("email_has_invalid_chars");
        }

        
        
        
        final User user = new User(registerRequest.getPassword(),registerRequest.getUsername(),registerRequest.getEmail());
        // create User
        userDetailsService.addUser(user);
        User newUser = userDetailsService.getUserByUsername(registerRequest.getUsername());
        
        String emailAuthToken = UserTokenGenerator.generateEmailValidateUserToken();

        userDetailsService.setToken(emailAuthToken, newUser.getUsername());

        // TODO sending email approve email
        // Try to send mail
        System.out.println("[Email Approve Code] email:" + newUser.getEmail() + ", token: " + emailAuthToken);

        return WebResponses.okResponse("user_created_enter_code");
    }

    @RequestMapping(value = mainRoute + "/autheticateEmail", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenicationToken(@RequestBody EmailAuthenticationRequest emailAuthenticationRequest) throws Exception {

        final User user = userDetailsService.getUserByUsername(emailAuthenticationRequest.getUsername());
        if(user != null) {
            if(user.getToken() != "") {
                if(user.getToken().trim().equals(emailAuthenticationRequest.getCode().trim())) {
                    userDetailsService.setToken(null, emailAuthenticationRequest.getUsername());
                    return WebResponses.okResponse("code_valid");
                }
            }
        }
        return WebResponses.badResponse("code_not_valid");
    }
    @RequestMapping(value = mainRoute + "/validatePasswordReset", method = RequestMethod.POST)
    public ResponseEntity<?> setPasswordResetToken(@RequestBody SetNewPasswordRequest newPasswordRequest) throws Exception {
        if(newPasswordRequest.getNewPassword().length() < 6 ) { // TODO static min pw length
            return WebResponses.badResponse("password_has_to_be_six_chars_long");
        }

        final User user = userDetailsService.getUserByUsername(newPasswordRequest.getUsername());
        if(user != null) {
            if(user.getToken() != "") {
                if(user.getToken().trim().equals(newPasswordRequest.getCode().trim())) {
                    userDetailsService.setToken(null, newPasswordRequest.getUsername()); 
                    int changed = userDetailsService.setPassword(newPasswordRequest.getNewPassword(), newPasswordRequest.getUsername());
                }
                return WebResponses.okResponse("code_valid_passwort_set");
            }
        }
        return WebResponses.badResponse("code_not_valid");
    }
    @RequestMapping(value = mainRoute + "/triggerPasswordReset", method = RequestMethod.POST)
    public ResponseEntity<?> triggerPasswordReset(@RequestBody PasswordForgetRequest passwordForgetRequest) throws Exception {

        final User user = userDetailsService.getUserByUsername(passwordForgetRequest.getUsername());
        if(user != null) {
            if(validateUserLogin(user) == loginStates.ALLOWED) {
                String pwResetToken = UserTokenGenerator.generatePasswordResetUserToken();
                userDetailsService.setToken(pwResetToken, passwordForgetRequest.getUsername());
                System.out.println("[Password Reset Code] email:" + user.getEmail() + ", token: " + pwResetToken);
                return WebResponses.okResponse("password_reset_token_send");
            }
        } 
        return WebResponses.badResponse("username_wrong");
    }

    private loginStates validateUserLogin(User user) {
        if(user.getToken() == "" || user.getToken() == null) {
            return loginStates.ALLOWED;
        } else if(user.getToken().startsWith("E")) {
            return loginStates.EMAIL_TOKEN;
        } else if(user.getToken().startsWith("P")) {
            return loginStates.PASSWORD_RESET_TOKEN;
        } else {
            return loginStates.ERROR;
        }
    }
    private enum loginStates {
        ALLOWED,
        EMAIL_TOKEN,
        PASSWORD_RESET_TOKEN,
        ERROR
      }
}
