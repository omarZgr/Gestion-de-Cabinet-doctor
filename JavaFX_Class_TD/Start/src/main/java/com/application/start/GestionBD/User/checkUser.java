package com.application.start.GestionBD.User;

import com.application.start.GestionBD.prepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class checkUser {

    public static int  checkUser(String userName,String password) throws SQLException {
        Connection etat = prepare.getConnection() ;
        String query = "Select user_id from Users where user_name=? and user_password=?  " ;
        PreparedStatement ps = etat.prepareStatement(query);
        ps.setString(1,userName);
        ps.setString(2,password);

        ResultSet rs =ps.executeQuery() ;
        if (rs.next()) return rs.getInt("user_id") ;

        return -1 ;
    }

    public static int  getUser_By_CIN(String cin) throws SQLException {
        Connection etat = prepare.getConnection() ;
        String query = "Select user_id from Users where user_cin=?" ;
        PreparedStatement ps = etat.prepareStatement(query);
        ps.setString(1, String.valueOf(cin));
        ResultSet rs =ps.executeQuery() ;
        if (rs.next()) return rs.getInt(1) ;

        return -1 ;
    }
}
