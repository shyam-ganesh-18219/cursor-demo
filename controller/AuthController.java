public class AuthController {

    private final AuthService authService = new AuthService();

    public String login(String username, String password) {
        User user = authService.login(username, password);
        if (user == null) {
            return "Invalid credentials";
        }
        return TokenUtil.generateToken(user.getUsername());
    }
}
