import Software.Engineering.Gruppe.Config.SqliteDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test_sqlite_database_connection {

    /* Tester kontakt til databasen som blir brukt i prototypen */
    @Test
    public void Test_Real_Database_Connection() {
        System.out.println(SqliteDatabase.connect("group8dbftw.db"));
        assertEquals(true, SqliteDatabase.connect("group8dbftw.db"));

    }

    /* Tester kontakt til databasen som blir brukt til testene*/
    @Test
    public void Test_Fake_Database_Connection() {
        System.out.println(SqliteDatabase.connect("FakeDatabase.db"));
        assertEquals(true, SqliteDatabase.connect("FakeDatabase.db"));

    }



}
