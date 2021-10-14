import Software.Engineering.Gruppe.Config.SqliteDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test_sqlite_database_connection {



    @Test
    public void Test_Database_Connection() {
        System.out.println(SqliteDatabase.connect());
        assertEquals(true, SqliteDatabase.connect());

    }



}
