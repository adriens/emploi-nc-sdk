package com.github.adriens.emploi.nc.sdk;

/**
 *
 * @author JAVAE
 */

public class Employeur {

    /* TODO : Typer les différentes variables d'Employeur */
    private String id;
    private String contactid;
    private String typeEmployeur;
    private String telephone;
    private String mail;
    private String adresse; // deliveryPoint
    private String logo;



    public String getId() {
        return id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTypeEmployeur() {
        return typeEmployeur;
    }

    public void setTypeEmployeur(String typeEmployeur) {
        this.typeEmployeur = typeEmployeur;
    }

    public String getContactid() {
        return contactid;
    }

    public void setContactid(String contactid) {
        this.contactid = contactid;
    }

    public void setId(String id) {
        this.id = id;
    }

        /**
     * Return Employeur object, as text.
     *
     * @return Employeur object, as text.
     */
    @Override
    public String toString() {
        return 
        "\n<id: " + this.id + ">"+
        "\n<contactid :"+this.contactid+">"+
        "\n<typeEmployeur :"+this.typeEmployeur+">"+
        "\n<telephone :"+this.telephone+">"+
        "\n<mail :"+this.mail+">"+
        "\n<adresse :"+this.adresse+">"/*+
        "\n<logo :"+this.logo+">"*/;
    }
}