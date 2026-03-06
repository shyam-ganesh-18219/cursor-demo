import java.util.Arrays;
import java.util.List;

public class FakeDatabase {
    public static final List<User> USERS = Arrays.asList(
        new User(1, "alice",   "password123"),
        new User(2, "bob",     "password123"),
        new User(3, "charlie", "password123")
    );
}
