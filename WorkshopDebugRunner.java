public class WorkshopDebugRunner {

    public static void main(String[] args) {
        System.out.println("=== Workshop Debug Runner ===");
        runLoginBugScenario();
        runNullPointerScenario();
    }

    private static void runLoginBugScenario() {
        System.out.println("\n[Scenario 1] Login bug reproduction");
        AuthController authController = new AuthController();

        String result = authController.login("alice", new String("password123"));
        System.out.println("AuthController.login(\"alice\", \"password123\") => " + result);
    }

    private static void runNullPointerScenario() {
        System.out.println("\n[Scenario 2] NullPointerException reproduction");
        UserService userService = new UserService();

        try {
            String username = userService.getUsernameById(999);
            System.out.println("UserService.getUsernameById(999) => " + username);
        } catch (Exception exception) {
            System.out.println("Captured exception trace:");
            exception.printStackTrace(System.out);
        }
    }
}
