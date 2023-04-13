package com.application.start.GestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class prepare {

    public static  Connection getConnection()
    {
        Connection con = null ;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionCabinet","root","") ;
        } catch (SQLException e) {

        }
        return con ;
    }


}
