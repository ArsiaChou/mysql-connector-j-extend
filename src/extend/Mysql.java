package extend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @package: database
 * @Description: Encapsulation of database connections
 * @program: mysql-database-j-extend
 * @Author: Arsia Chou
 * @date: 2019-05-16 22:18
 */
public class Mysql {
	public static final String DEFAULT_HOST = "localhost";
	public static final String DEFAULT_PORT = "3306";
    /*
    *   default driver
    * */
    private static String Driver = "com.mysql.cj.jdbc.Driver";
    private String host;
    private String port;
    private String database;
    private String user;
    private String password;

    private Connection connect;
    private Statement statement;
    /*
    *   default url
    * */
    private String url = "jdbc:mysql://%HOST%:%PORT%/%DATABASE%?useUnicode=true&characterEncoding=utf-8&useSSL=false";

    /**
    * @Description: construct
    * @Name: Mysql
    * @Param: [host, port, database, user, password]
    * @return: none
    * @Author: Arsia Chou
    * @Date: 19-5-16
    */
    public Mysql(String host, String port, String database, String user, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
        flushUrl();

    }
    public  Mysql(Properties info) {
        this(info.getProperty("host"),
                info.getProperty("port"),
                info.getProperty("database"),
                info.getProperty("user"),
                info.getProperty("password"));
    }

    /*
    *   load the driver
    * */
    static {
        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Mysql: Cannot found the driver --> "+Driver);
        }
    }
    
    /**
    * @Description: Update new links in time
    * @Name: flushUrl
    * @Param: []
    * @return: void
    * @Author: Arsia Chou
    * @Date: 19-5-16
    */
    private void flushUrl() {
        url = url.replace("%HOST%", host)
                .replace("%PORT%", port)
                .replace("%DATABASE%", database);
    }

    /**
    * @Description: database to the database
    * @Name: database
    * @Param: []
    * @return: void
    * @Author: Arsia Chou
    * @Date: 19-5-16
    */
    public void connect() {
        try {
            connect = DriverManager.getConnection(url, user, password);
            statement = connect.createStatement();
        } catch (SQLException e) {
            System.out.println("Mysql: Connection Failed.");
        }
    }

    /**
    * @Description: disconnect from the database
    * @Name: disconnect
    * @Param: []
    * @return: void
    * @Author: Arsia Chou
    * @Date: 19-5-16
    */
    public void disconnect() {
        try {
            if (statement.isClosed()) {
                statement.close();
            }
            if (connect.isClosed()) {
                connect.close();
            }
        } catch (SQLException e) {
            System.out.println("Mysql: Disconnect Failed.");
        }
    }

    /*
    *   Getter and Setter
    * */
    public static void setDriver(String driver) {
        Driver = driver;
        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Mysql: Cannot found the driver --> "+Driver);
        }
    }

    public void setHost(String host) {
        this.host = host;
        flushUrl();
    }

    public void setPort(String port) {
        this.port = port;
        flushUrl();
    }

    public void setDatabase(String database) {
        this.database = database;
        flushUrl();
    }

    public void setUser(String user) {
        this.user = user;
        flushUrl();
    }

    public void setPassword(String password) {
        this.password = password;
        flushUrl();
    }

    public static String getDriver() {
        return Driver;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Connection getConnect() {
        return connect;
    }

    public Statement getStatement() {
        return statement;
    }

    public String getUrl() {
        return url;
    }
}
