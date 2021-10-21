package Software.Engineering.Gruppe.Repository;
import Software.Engineering.Gruppe.Model.User;

public interface UserInterface {

    User createUser(String email, String username, String password);

    User getSpecificUser(int userId, String email, String username, String password);

    boolean deleteUser(int userId);

}
