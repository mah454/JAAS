package ir.moke.jaas;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecurityCallbackHandler implements CallbackHandler {
    public void handle(Callback[] callbacks) throws IOException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback nameCallback) {
                System.out.print(nameCallback.getPrompt());
                nameCallback.setName(new BufferedReader(new InputStreamReader(System.in)).readLine());
            }

            if (callback instanceof PasswordCallback passwordCallback) {
                System.out.print(passwordCallback.getPrompt());
                passwordCallback.setPassword(new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray());
            }
        }
    }
}
