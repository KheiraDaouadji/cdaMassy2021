/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.model;

import java.util.Objects;

/**
 * Une réponse est la réponse d'un utlisateur a un sondage.
 * @author thoma
 */
public class Reponse {

    private int idPersonne;
    private int idQuestion;
    private int idReponse;
    private String libelle;
    private String nomPersonne;
    private String dateRendu;

    public Reponse() {

    }

    public Reponse(int idPersonne, int idQuestion, int idReponse,
            String libelle,String nomPersonne, String dateRendu) {
        this.idPersonne = idPersonne;
        this.idQuestion = idQuestion;
        this.idReponse = idReponse;
        this.libelle = libelle;
        this.nomPersonne = nomPersonne;
        this.dateRendu = dateRendu;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }
    
    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }
    
    
    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getNomPersonne() {
        return nomPersonne;
    }

    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }
    
    public String getDateRendu() {
        return dateRendu;
    }

    public void setDateRendu(String dateRendu) {
        this.dateRendu = dateRendu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.idPersonne;
        hash = 17 * hash + this.idQuestion;
        hash = 17 * hash + this.idReponse;
        hash = 17 * hash + Objects.hashCode(this.libelle);
        hash = 17 * hash + Objects.hashCode(this.dateRendu);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reponse other = (Reponse) obj;
        if (this.idPersonne != other.idPersonne) {
            return false;
        }
        if (this.idQuestion != other.idQuestion) {
            return false;
        }
        if (this.idReponse != other.idReponse) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.dateRendu, other.dateRendu)) {
            return false;
        }
        return true;
    }
}
