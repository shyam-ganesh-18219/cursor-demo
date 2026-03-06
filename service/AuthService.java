public class AuthService {

    private final UserRepository userRepository = new UserRepository();

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }

        // BUG (workshop debugging exercise): passwords are compared with == instead of .equals()
        // This will fail for all non-interned strings even when values are identical.
        if (user.getPassword() == password) {
            return user;
        }

        return null;
    }
}
