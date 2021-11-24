package Software.Engineering.Gruppe.Repository;
import Software.Engineering.Gruppe.Model.User;

import java.util.List;

public interface UserInterface {

    User createUser(String email, String username, String password);

    User getSpecificUser(int userId);

    User getSpecificUserByUsername(String username);

    User updateUser(String userId, String email, String username, String password);

    List<User> getAllUsers();

    boolean deleteUser(int userId);

}
