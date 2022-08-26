package ir.moke.jaas;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.URL;

public class BasicMainClass {
    private static final String SECURITY_CONFIG_FILE = "META-INF/basic-security";

    public static void main(String[] args) throws IOException {
        URL resource = BasicMainClass.class.getClassLoader().getResource(SECURITY_CONFIG_FILE);
        if (resource == null) {
            System.out.println("Failed to load security config file");
            System.exit(1);
        }

        String path = resource.getPath();
        System.out.println("Read security config file: " + path);

        System.setProperty("java.security.auth.login.config", path);
        try {
            LoginContext context = new LoginContext("BasicSecurity", new SecurityCallbackHandler());
            context.login();
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }
    }
}
