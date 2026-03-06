import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Workshop Demo Starter ===");

        if (args.length > 0 && "interactive".equalsIgnoreCase(args[0])) {
            runInteractiveLogin();
            return;
        }

        if (args.length > 0 && "web".equalsIgnoreCase(args[0])) {
            try {
                WebServer.main(args);
            } catch (java.io.IOException e) {
                System.err.println("Failed to start web server: " + e.getMessage());
            }
            return;
        }

        System.out.println("Running WorkshopDebugRunner...\n");
        WorkshopDebugRunner.main(args);
    }

    private static void runInteractiveLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n[Interactive] Try the login flow");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        AuthController authController = new AuthController();
        String result = authController.login(username, password);
        System.out.println("AuthController.login(\"" + username + "\", \"***\") => " + result);
    }
}
