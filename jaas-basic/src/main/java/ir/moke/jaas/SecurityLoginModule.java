package ir.moke.jaas;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.Map;

public class SecurityLoginModule implements LoginModule {

    private static final String username = "admin";
    private static final String password = "adminpass";
    private CallbackHandler callbackHandler;
    private boolean authSuccessFlag = false;

    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
    }

    public boolean login() throws LoginException {
        try {
            Callback[] callbackArr = new Callback[2];
            callbackArr[0] = new NameCallback("Username: ");
            callbackArr[1] = new PasswordCallback("Password: ", false);

            callbackHandler.handle(callbackArr);

            String username = ((NameCallback) callbackArr[0]).getName();
            String password = new String(((PasswordCallback) callbackArr[1]).getPassword());
            if (username.equals(SecurityLoginModule.username) && password.equals(SecurityLoginModule.password)) {
                System.out.println("Login success");
                authSuccessFlag = true;
                return true;
            }
        } catch (IOException | UnsupportedCallbackException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean commit() throws LoginException {
        return authSuccessFlag;
    }

    public boolean abort() throws LoginException {
        return false;
    }

    public boolean logout() throws LoginException {
        return false;
    }
}
