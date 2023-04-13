package com.application.start.GestionBD.User;

import com.application.start.GestionBD.prepare;
import com.application.start.Dashboard.User.User ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Get {

    public static User getDataUser(int id) throws SQLException {
        Connection etat = prepare.getConnection() ;

        String query = "Select * from Users where user_id=?" ;
        PreparedStatement ps=etat.prepareStatement(query) ;
        ps.setInt(1,id);
        ResultSet rs= ps.executeQuery();
        if (rs.next())
        {
            String user_nom = rs.getString("user_nom") ;
            String user_prenom = rs.getString("user_prenom") ;
            String user_name = rs.getString("user_name") ;
            String user_dateInscrrire = rs.getString("user_dateInscrrire") ;
            String user_cin = rs.getString("user_cin") ;

            return new User(id,user_nom,user_prenom,user_name,user_cin, user_dateInscrrire) ;
        }

        return null ;

    }

}
