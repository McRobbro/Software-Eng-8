package Software.Engineering.Gruppe.Controller;

import Software.Engineering.Gruppe.Repository.UserRepository;
import io.javalin.http.Context;

public class UserController {


 private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

// Metoden må endres/tilpasses. Skal vi ha med denne? Må lage metode i Interface i så fall
    /*public void getAllUsers() {
        List<User> allUsers = userRepository.getAllUsers();
        context.json(allUsers);
    }

    // Må fikses
    public void getSpecificUser() {
        String username = context.pathParam("{username}");
        User getUser = userRepository.getSpecificUser(username);
        context.json(getUser);
    }*/
}
