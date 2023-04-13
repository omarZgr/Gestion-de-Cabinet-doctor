package com.application.start.Dashboard.User;

public class User {

    int id ;
    String nom;
    String prenom;
    String userName;
    String cin;
    String  dateCreation;

    public User(int id, String nom, String prenom, String userName, String cin, String dateCreation) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.userName = userName;
        this.cin = cin;
        this.dateCreation = dateCreation;
    }

    public int getId() {
        return id;
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

    public String getCin() {
        return cin;
    }

    public String getDateCreation() {
        return dateCreation;
    }
}
