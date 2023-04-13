package com.application.start.GestionBD.Patient;

import com.application.start.GestionBD.prepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class Check {

    public static int  checkPatient(String cin) throws SQLException {
        Connection etat = prepare.getConnection() ;
        String query = "Select patient_id from Patient where patient_cin=?" ;
        PreparedStatement ps = etat.prepareStatement(query);
        ps.setString(1, String.valueOf(cin));
        ResultSet rs =ps.executeQuery() ;
        if (rs.next()) return rs.getInt(1) ;

        return -1 ;
    }

}
