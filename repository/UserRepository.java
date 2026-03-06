import java.util.List;

public class UserRepository {

    public List<User> findAll() {
        return FakeDatabase.USERS;
    }

    public User findByUsername(String username) {
        return FakeDatabase.USERS.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
