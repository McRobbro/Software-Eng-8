package Software.Engineering.Gruppe.Controller;

import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Repository.ProductRepository;
import Software.Engineering.Gruppe.Repository.UserRepository;
import io.javalin.http.Context;
import java.util.List;

public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository; }


    //public void getAllUsers() {} // metode for å vise alle brukere?

    //public void getSpecificUser() {} // metode for å vise en bruker?


}
