import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNull;

public class test_user_crud_functionality {
    Path userDir = Paths.get(System.getProperty("user.dir")).getParent();
    String databaseDir = "\\db\\FakeDatabase.db";
    String url = "jdbc:sqlite:" + userDir + databaseDir;
    SqliteDatabase sqliteDatabase = new SqliteDatabase(url);
    UserRepository userRepository = new UserRepository(sqliteDatabase);

    @BeforeEach
    public void data_setup() {
        userRepository.createUser(1, "dummyEmail", "dummyUsername", "dummyPassword");
    }

    @AfterEach
    public void tear_down() {
        userRepository.deleteUser(1);
    }

    /* Følgende tester er ikke knyttet opp til krav (da det er et eksternt innloggingssystem som håndterer registrering
    og håndtering av metoder tilknyttet brukerdata). Men vi trengte å kunne opprette brukere og tilhørende metoder
    for å undersøke funksjonalitet tilhørende brukertypene i prototypen */

    @Test
    public void test_create_user() {
        assertThat(userRepository.getSpecificUser(1), allOf(
                hasProperty("email", is("dummyEmail")),
                hasProperty("username", is("dummyUsername")),
                hasProperty("password", is("dummyPassword"))
        ));
    }

    @Test
    public void test_get_user_by_id() {
        assertThat(userRepository.getSpecificUser(1), allOf(
                hasProperty("email", is("dummyEmail")),
                hasProperty("username", is("dummyUsername")),
                hasProperty("password", is("dummyPassword"))
        ));
    }

    @Test
    public void test_update_user() {
        userRepository.updateUser("1", "NewEmail", "dummyUsername", "dummyPassword");
        assertThat(userRepository.getSpecificUser(1), allOf(
                hasProperty("email", is("NewEmail")),
                hasProperty("username", is("dummyUsername")),
                hasProperty("password", is("dummyPassword"))
        ));
    }

    @Test
    public void test_delete_user() {
        userRepository.deleteUser(1);
        assertNull(userRepository.getSpecificUser(1));
    }



}
