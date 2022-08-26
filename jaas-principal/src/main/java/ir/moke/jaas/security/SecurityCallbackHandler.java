package ir.moke.jaas.security;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecurityCallbackHandler implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) throws IOException {
        NameCallback nameCallback = (NameCallback) callbacks[0];
        System.out.print(nameCallback.getPrompt());
        nameCallback.setName(new BufferedReader(new InputStreamReader(System.in)).readLine());

        PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];
        System.out.print(passwordCallback.getPrompt());
        passwordCallback.setPassword(new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray());
    }
}
