package Software.Engineering.Gruppe.Controller;

import io.javalin.http.Context;

public class UserController {

    public void login(Context ctx) {
        String role = ctx.formParam("role");
        ctx.header("Set-Cookie", String.format("role=%s; Path=/", role));
        ctx.redirect("/stores");
    }

    
//    private final UserRepository userRepository;
//
//    public UserController() {
//        this.userRepository = userRepository;
//
//    }

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
