/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.emploi.nc.sdk;

/**
 *
 * @author JAVAE
 */
public class Emploi {

    /* TODO : Typer les différentes variables d'Emplois */
    private String titreOffre;
    private String aPourvoirLe;
    private String typeContrat;
    private String communeEmploi;
    private String idOffre;
    private String userId;
    private String numeroOffre;
    private String shortnumeroOffre;

    private String experience;
    private String codeROME;
    private String created;
    private String url;
    private String updated;
    private String datePublication;
    private String duree;
    private String uniteDuree;
    private String dureeTempsPartiel;
    private String desQuePossible;

    private Employeur employeur;

    /**
     * Return Emploi object, as text.
     *
     * @return Emploi object, as text.
     */
    @Override
    public String toString() {
        return "\n<Titre offre: " + this.titreOffre + ">" + "\n<aPourvoirLe :" + this.aPourvoirLe + ">"
                + "\n<typeContrat :" + this.typeContrat + ">" + "\n<communeEmploi :" + this.communeEmploi + ">"
                + "\n<idOffre :" + this.idOffre + ">" + "\n<userId :" + this.userId + ">" + "\n<numeroOffre :"
                + this.numeroOffre + ">" + "\n<experience :" + this.experience + ">" + "\n<codeRome :" + this.codeROME
                + ">" + "\n<Url :" + this.url + ">" + ">" + "\n<created :" + this.created + ">" + "\n<updated :"
                + this.updated + ">" + "\n<datePublication :" + this.datePublication + ">" + "\n<duree :" + this.duree
                + ">" + "\n<uniteDuree :" + this.uniteDuree + ">" + "\n<dureeTempsPartiel :" + this.dureeTempsPartiel
                + ">" + "\n<desQuePossible :" + this.desQuePossible + ">"+ "\n<shortnumeroOffre :" + this.shortnumeroOffre + ">";
    }

    public String getShortnumeroOffre() {
        return shortnumeroOffre;
    }

    public void setShortnumeroOffre(String shortnumeroOffre) {
        shortnumeroOffre = shortnumeroOffre.replaceAll(".+-", "");
        shortnumeroOffre = shortnumeroOffre.replaceFirst ("^0*", "");
        System.out.println(shortnumeroOffre);
        this.shortnumeroOffre = shortnumeroOffre;
    }

    public String getDesQuePossible() {
        return desQuePossible;
    }

    public void setDesQuePossible(String desQuePossible) {
        this.desQuePossible = desQuePossible;
    }

    public String getDureeTempsPartiel() {
        return dureeTempsPartiel;
    }

    public void setDureeTempsPartiel(String dureeTempsPartiel) {
        this.dureeTempsPartiel = dureeTempsPartiel;
    }

    public String getUniteDuree() {
        return uniteDuree;
    }

    public void setUniteDuree(String uniteDuree) {
        this.uniteDuree = uniteDuree;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Employeur getEmployeur() {
        return employeur;
    }

    public void setEmployeur(Employeur employeur) {
        this.employeur = employeur;
    }

    public String getCodeROME() {
        return codeROME;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCodeROME(String codeROME) {
        this.codeROME = codeROME;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getNumeroOffre() {
        return numeroOffre;
    }

    public void setNumeroOffre(String numeroOffre) {
        this.numeroOffre = numeroOffre;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(String idOffre) {
        this.idOffre = idOffre;
    }

    public String getCommuneEmploi() {
        return communeEmploi;
    }

    public void setCommuneEmploi(String communeEmploi) {
        this.communeEmploi = communeEmploi;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public String getaPourvoirLe() {
        return aPourvoirLe;
    }

    public void setaPourvoirLe(String aPourvoirLe) {
        this.aPourvoirLe = aPourvoirLe;
    }
     
    /**
     * Return Emploi designation name.
     *
     * @return Emploi designation name.
     */
    public String getTitreOffre() {
        return this.titreOffre;
    }

    /**
     * Set Emploi designation name.
     *
     * @param designation Emploi designation name.
     */
    public void setTitreOffre(String titreOffre) {
        this.titreOffre = titreOffre;
    }
    

}
