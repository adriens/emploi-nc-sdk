package com.github.adriens.emploi.nc.sdk;

import java.util.Date;

public class CSVLine {

    private String numeroOffre;
    private String titreOffre;
    private String nomEntreprise;
    private String aPourvoirLe;
    private String communeEmploi;
    private String experience;
    private String niveauFormation;
    private String diplome;
    private String nbPostes;
    private String datePublication;

    public String getNumeroOffre() {
        return numeroOffre;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        datePublication = datePublication.replaceAll("-","/");
        datePublication.subSequence(0,10);
        this.datePublication = datePublication.subSequence(0,10).toString();
    }

    public void setNumeroOffre(String numeroOffre) {
        this.numeroOffre = numeroOffre;
    }

    public String getNbPostes() {
        return nbPostes;
    }

    public void setNbPostes(String nbPostes) {
        this.nbPostes = nbPostes;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getNiveauFormation() {
        return niveauFormation;
    }

    public void setNiveauFormation(String niveauFormation) {
        this.niveauFormation = niveauFormation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCommuneEmploi() {
        return communeEmploi;
    }

    public void setCommuneEmploi(String communeEmploi) {
        this.communeEmploi = communeEmploi;
    }

    public String getaPourvoirLe() {
        return aPourvoirLe;
    }

    public void setaPourvoirLe(String aPourvoirLe) {
        String year = (String) aPourvoirLe.subSequence(6,10);
        String month = aPourvoirLe.subSequence(2,6).toString();
        String day = aPourvoirLe.subSequence(0,2).toString();
        this.aPourvoirLe = year+month+day;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getTitreOffre() {
        return titreOffre;
    }

    public void setTitreOffre(String titreOffre) {
        this.titreOffre = titreOffre;
    }

}