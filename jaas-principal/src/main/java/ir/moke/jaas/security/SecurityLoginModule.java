package ir.moke.jaas.security;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class SecurityLoginModule implements LoginModule {

    private String username = "admin";
    private String password = "adminpass";
    private static final Set<String> roles = Set.of("member","user");
    private SecurityPrincipal securityPrincipal;
    private CallbackHandler callbackHandler;
    private Subject subject ;
    private boolean authSuccessFlag = false;

    public SecurityLoginModule() {
        System.out.println("Object created");
    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
        this.subject = subject;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("Username: ");
        callbacks[1] = new PasswordCallback("Password: ", false);
        try {
            this.callbackHandler.handle(callbacks);

            NameCallback nameCallbacks = (NameCallback) callbacks[0];
            PasswordCallback passwordCallbacks = (PasswordCallback) callbacks[1];

            String username = nameCallbacks.getName();
            String password = new String(passwordCallbacks.getPassword());
            if (username.equals(this.username) && password.equals(this.password)) {
                System.out.println("Login success");
                securityPrincipal = new SecurityPrincipal(username,roles);
                authSuccessFlag = true;
                return true;
            }
        } catch (IOException | UnsupportedCallbackException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean commit() throws LoginException {
        if (subject != null && !subject.getPrincipals().contains(securityPrincipal)) {
            subject.getPrincipals().add(securityPrincipal);
            return true;
        }
        return false;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
