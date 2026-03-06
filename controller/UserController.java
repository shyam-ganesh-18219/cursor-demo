import java.util.List;

public class UserController {

    private final UserService userService = new UserService();

    public List<User> getUsers() {
        return userService.getUsers();
    }
}
