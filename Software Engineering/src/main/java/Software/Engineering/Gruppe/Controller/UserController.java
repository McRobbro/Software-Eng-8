package Software.Engineering.Gruppe.Controller;

import Software.Engineering.Gruppe.Model.Product;
import Software.Engineering.Gruppe.Repository.ProductRepository;
import io.javalin.http.Context;
import java.util.List;



public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepostitory userRepostitory) {
        this.userRepository = userRepostitory; }

    //public void getAllUsers() {} // metode for å vise alle brukere?

    //public void getSpecificUser() {} // metode for å vise en bruker?


}
