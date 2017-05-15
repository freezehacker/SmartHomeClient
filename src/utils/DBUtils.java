package utils;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by JK.
 *
 * 数据库连接
 */
public class DBUtils {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/me";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    private static MysqlDataSource ds = null;

    static {
        ds = new MysqlDataSource();
        ds.setURL(DB_URL);  // or setUrl() ?
        ds.setUser(USER);
        ds.setPassword(PASSWORD);
    }

    public static DataSource getDataSource() {
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
