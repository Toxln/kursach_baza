package com.SQLsupport;

import java.sql.Connection;

public interface Updatable extends Requestable{
    boolean executeUpdate(Connection conn);
}
