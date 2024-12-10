module Server {
    requires java.sql;
    requires mysql.connector.java;
    exports com.SQLsupport;
    exports com.SQLsupport.strategies;
    exports com.SQLsupport.DBClass;
}