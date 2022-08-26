package ir.moke.jaas;

import ir.moke.jaas.security.SecurityCallbackHandler;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.net.URL;
import java.security.PrivilegedAction;
import java.util.Scanner;

public class MainClass {
    private static final String SECURITY_CONFIG_PATH = "META-INF/jaas-security";

    public static void main(String[] args) {
        URL resource = MainClass.class.getClassLoader().getResource(SECURITY_CONFIG_PATH);
        if (resource == null) {
            System.out.println("Failed to load " + SECURITY_CONFIG_PATH);
            System.exit(1);
        }

        String path = resource.getPath();
        System.out.printf("Read security config file %s\n", SECURITY_CONFIG_PATH);

        System.setProperty("java.security.auth.login.config", path);

        try {
            LoginContext loginContext = new LoginContext("myapp-security", new SecurityCallbackHandler());
            loginContext.login();
            cli(loginContext);
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cli(LoginContext loginContext) throws LoginException {
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command : ");
            String cmd = scanner.nextLine();
            switch (cmd) {
                case "logout" -> loginContext.logout();
                case "A" -> actionA(loginContext);
                case "B" -> actionB(loginContext);
            }
        }
    }

    private static void actionA(LoginContext loginContext) {
        PrivilegedAction<Object> privilegedAction = () -> null;
        Subject.doAs(loginContext.getSubject(),privilegedAction);
        System.out.printf("Executed Action A by %s%n",loginContext.getSubject().getPrincipals().iterator().next().getName());
    }

    private static void actionB(LoginContext loginContext) {
        PrivilegedAction<Object> privilegedAction = () -> null;
        Subject.doAs(loginContext.getSubject(),privilegedAction);
        System.out.printf("Executed Action B by %s%n",loginContext.getSubject().getPrincipals().iterator().next().getName());
    }
}
