package Software.Engineering.Gruppe.Controller;

import Software.Engineering.Gruppe.Model.Store;
import Software.Engineering.Gruppe.Model.User;
import Software.Engineering.Gruppe.Repository.UserRepository;
import io.javalin.http.Context;

import java.util.List;

public class UserController {


 private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void getUsers(Context context) {
        List<User> allUsers = userRepository.getAllUsers();
        context.json(allUsers);
    }

    public void getSpecificUser(Context context) {
        String username = context.pathParam("username");
        User user = userRepository.getSpecificUserByUsername(username);
        context.json(user);
    }

    public void getSpecificUserByUsername(Context context) {
        String username = context.pathParam("username");
        User user = userRepository.getSpecificUserByUsername(username);
        context.json(user);
    }

    public void updateUser(Context context) {
        String userId = context.pathParam("userId");
        String userEmail = context.formParam("email");
        String userUsername = context.formParam("username");
        String userPassword = context.formParam("password");
        User updatedUser = userRepository.updateUser(userId, userEmail, userUsername, userPassword);
        if(updatedUser != null) {
            context.redirect("/user");
        }
        else {
            context.status(400);
        }
    }
}
