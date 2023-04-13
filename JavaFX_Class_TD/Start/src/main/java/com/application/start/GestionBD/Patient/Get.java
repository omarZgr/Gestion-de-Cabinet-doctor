package com.application.start.GestionBD.Patient;

import com.application.start.GestionBD.prepare;
import com.application.start.Dashboard.Patient.Patient;

import java.sql.*;
import java.util.ArrayList;

public class Get {

    public static Patient getDataPatient(int id) throws SQLException {
        Connection etat = prepare.getConnection() ;

        String query = "Select * from Patient where patient_id=?" ;
        PreparedStatement ps=etat.prepareStatement(query) ;
        ps.setInt(1,id);
        ResultSet rs= ps.executeQuery();
        if (rs.next())
        {
            String nom = rs.getString("patient_nom") ;
            String prenom = rs.getString("patient_prenom") ;
            String tel = rs.getString("patient_tel") ;
            String cin = rs.getString("patient_cin") ;
            String dateNass = rs.getString("patient_dateNass") ;

            return new Patient(id,nom,prenom,tel,cin, dateNass) ;
        }

        return null ;

    }

    public static ArrayList<Patient> getData() throws SQLException {
        ArrayList<Patient> datA = new ArrayList<>() ;
        Connection etat = prepare.getConnection() ;
        String query = "Select * from Patient" ;
        PreparedStatement ps=etat.prepareStatement(query) ;
        ResultSet rs= ps.executeQuery();

        String patient_nom,patient_prenom,patient_tel,patient_cin,patient_dateNass ;
        int patient_id ;

        while (rs.next())
        {
            patient_id=rs.getInt("patient_id") ;
            patient_nom=rs.getString("patient_nom") ;
            patient_prenom=rs.getString("patient_prenom") ;
            patient_tel=rs.getString("patient_tel") ;
            patient_cin=rs.getString("patient_cin") ;
            patient_dateNass=rs.getString("patient_dateNass") ;

            datA.add(new Patient(patient_id,patient_nom,patient_prenom,patient_tel,patient_cin,patient_dateNass)) ;
        }



        return  datA ;

    }
}
