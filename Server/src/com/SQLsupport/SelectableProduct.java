package com.SQLsupport;

import com.SQLsupport.DBClass.Product;
import com.SQLsupport.DBClass.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

public interface SelectableProduct extends Requestable{
    Vector<Product> executeSelect(Connection conn);
}
