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

    private String titreOffre;
    private String aPourvoirLe;
    private String typeContrat;
    private String communeEmploi;
    private String idOffre;
    private String userId;
    private String numeroOffre;
    private String experience;
    private String codeROME;

    private String url;


    
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
    
    // TODO : Faire la method tostring
    /**
     * Return Emploi object, as text.
     *
     * @return Emploi object, as text.
     */
    @Override
    public String toString() {
        return "<Titre offre: " + this.titreOffre + ">\naPourvoirLe :"+this.aPourvoirLe+
        "\ntypeContrat :"+this.typeContrat+
        "\ncommuneEmploi :"+this.communeEmploi;
    }
}
