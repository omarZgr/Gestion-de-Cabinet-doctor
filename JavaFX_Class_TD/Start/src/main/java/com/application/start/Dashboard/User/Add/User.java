package com.application.start.Dashboard.User.Add;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {
    String nom;
    String prenom;
    String userName;
    String password;
    String cin;
    String dateCreation ;

    public User(String  nom,String prenom, String userName, String password, String cin) {
        this.nom = nom ;
        this.prenom = prenom;
        this.userName = userName;
        this.password = password;
        this.cin = cin;

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        this.dateCreation = formattedDate ;

    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getCin() {
        return cin;
    }

    public String getDateCreation() {
        return dateCreation;
    }
}

