package uq.ecosoft.ctrack;

import org.junit.Test;

public class ConfigTest {
    @Test
    public void configTest() {
        System.out.println(System.getenv("ECO_DB_URL"));
        System.out.println(System.getenv("ECO_DB_USR"));
        System.out.println(System.getenv("ECO_DB_PWD"));
        System.out.println(System.getenv("conn"));

        System.out.println(System.getProperty("ECO_DB_URL"));
        System.out.println(System.getProperty("ECO_DB_USR"));
        System.out.println(System.getProperty("ECO_DB_PWD"));
        System.out.println(System.getProperty("conn"));
    }
}
