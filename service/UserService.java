import java.util.List;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    // TODO: pagination support for workshop exercise
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public String getUsernameById(int id) {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);

        // BUG: user may be null here; calling getUsername() on null causes NullPointerException
        return user.getUsername();
    }
}
