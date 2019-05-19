# mysql-connector-j-extend
 Simplify database connection

### Create Create instances of Mysql
``` java
	/*Method 1*/
    Mysql mysql = new Mysql(host, port, database, user, password);

    /*Method 2*/
    Properties info = new Properties();
	info.put("host", host);
	info.put("port", port);
	info.put("database", database);
	info.put("user", username);
	info.put("password", password);
    Mysql mysql = new Mysql()
```

### Create Create instances of Operation
``` java
	/* Method 1 */
    Properties info = new Properties();
    ...
    ...
    ...
    Operation operation = new Operation(info);

    /* Menthod 2 */
    Operation operation = new Operation(host, port, database, user, password);

	/* Method 3 */
    Mysql mysql = new Mysql(...);
    Operation operation = new Operation(mysql);
```

### Usage

- connect
    ``` java
        Mysql mysql;
        Operation operation;
        Properties info = new Properties();
        /* localhost */
        info.put("host", Mysql.DEFAULT_HOST);
        /* 3306 */
        info.put("port", Mysql.DEFAULT_PORT);
        info.put("database", "database");
        info.put("user", "username");
        info.put("password", "password");
        /* connect */
        mysql = new Mysql(info);
        operation = new Operation(mysql);
    ```

- insert, update, delete ...
	``` java
    	boolean isSuccess = operation.update(sqlCode);
        String[] sqlCodes = new String[5];
        ...
        ...
        ...
        int successRow = operation.updates(sqlCodes);
        if (successRow == sqlCodes.length){
        	isSuccess = true;
        } else {
        	isSuccess = false;
        }
    ```
- query [select ...]
    ``` java
    	opeartion.Query(sqlCode);
    ```


