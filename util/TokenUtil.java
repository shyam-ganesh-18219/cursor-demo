public class TokenUtil {

    public static String generateToken(String username) {
        return "TOKEN_" + username + "_" + System.currentTimeMillis();
    }
}
