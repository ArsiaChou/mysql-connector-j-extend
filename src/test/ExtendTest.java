package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import extend.Mysql;
import extend.Operation;

public class ExtendTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mysql mysql;
		Operation operation;
		Properties info = new Properties();
		
		info.put("host", Mysql.DEFAULT_HOST);
		info.put("port", Mysql.DEFAULT_PORT);
		info.put("database", "arsiadb");
		info.put("user", "arsia");
		info.put("password", "154785");
		
		mysql = new Mysql(info);
		operation = new Operation(mysql);
		
		//clear old data
		operation.update("delete from test");
		
		//create new insert sentence
		String[] sqlCodes = new String[3];
		for (int i=0; i<sqlCodes.length; i++)
			sqlCodes[i] = "insert into test (name, value) values ('test"+i+"', 'value"+i+"')";
		
		//execute multiple statements
		int count = operation.updates(sqlCodes);
		System.out.println("count: "+count+"  length: "+sqlCodes.length);
		
		//query all
		ResultSet result = operation.Query("select * from test");
		
		try {
			while (result.next()) {
				System.out.println("test { id: "+result.getString(1)+", name: "+result.getString(2)+", value: "+result.getString(3)+" }");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
