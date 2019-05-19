package extend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @package: operation
 * @Description: database operation
 * @program: mysql-connect-j-extend
 * @Author: Arsia Chou
 * @date: 2019-05-16 23:19
 */
public class Operation {
    Mysql mysql;

    public Operation(Properties info) {
        mysql = new Mysql(info);
    }
    
    public Operation(String host, String port, String database, String user, String password) {
        mysql = new Mysql(host, port, database, user, password);
    }
    public Operation(Mysql mysql) {
        this.mysql = mysql;
    }

    /**
    * @Description: Update a row
    * @Name: update
    * @Param: [sqlCode]
    * @return: boolean
    * @Author: Arsia Chou
    * @Date: 19-5-16
    */
    public boolean update (String sqlCode) {
        boolean result;
        mysql.connect();
        Statement state = mysql.getStatement();
        try {
            int reply = state.executeUpdate(sqlCode);
            if (reply == 1) 
                result = true;
            else 
                result = false;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        mysql.disconnect();
        return result;
    }
    
    /**
    * @Description: Update multiple rows
    * @Name: updates
    * @Param: [sqlCodes]
    * @return: int
    * @Author: Arsia Chou
    * @Date: 19-5-16
    */
    public int updates (String[] sqlCodes) {
        int result = 0, reply;
        mysql.connect();
        Statement state = mysql.getStatement();
        try {
            for (String setence : sqlCodes){
                reply = state.executeUpdate(setence);
                if (reply == 1) result ++;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return result;
    }

    /**
    * @Description: 
    * @Name: Querry
    * @Param: [sqlCode]
    * @return: java.sql.ResultSet
    * @Author: Arsia Chou
    * @Date: 19-5-17
    */
    public ResultSet Query(String sqlCode) {
        ResultSet result = null;
        mysql.connect();
        Statement state = mysql.getStatement();
        try {
            result = state.executeQuery(sqlCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mysql.disconnect();
        return result;
    }
}
