package com.application.start.Dashboard.Patient.Add;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Patient {
    String nom;
    String prenom;
    String tel;
    String cin;
    LocalDate dateNaissance;
    String dateCreation ;

    public Patient(String nom, String prenom, String tel, String cin, LocalDate dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.cin = cin;
        this.dateNaissance = dateNaissance;

        // Initializable Date Creation

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        this.dateCreation = formattedDate ;
    }

    public String getDateCreation() {
        return dateCreation;
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

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }
}

