package com.application.start.Dashboard.Patient;

public class Patient {

    int id ;
    String nom;
    String prenom;
    String tel;
    String cin;
    String  dateNaissance;

    public Patient(int id, String nom, String prenom, String tel, String cin, String dateNaissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
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

    public String getTel() {
        return tel;
    }

    public String getCin() {
        return cin;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }
}
