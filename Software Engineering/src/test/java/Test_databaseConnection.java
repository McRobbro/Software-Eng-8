import Software.Engineering.Gruppe.Config.SqliteDatabase;
import Software.Engineering.Gruppe.Repository.StoreRepository;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_databaseConnection {


    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);
    }

    @Test
    public void Test_Connection() {

        System.out.println(SqliteDatabase.connect());
        assertEquals(true, SqliteDatabase.connect());

    }



}
