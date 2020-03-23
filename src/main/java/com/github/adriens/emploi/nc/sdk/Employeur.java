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
    private String nomEntreprise;
    private String deliveryPoint;
    private String complement;
    private String subdivision;
    private String street;

    /**
     * Return Employeur object, as text.
     *
     * @return Employeur object, as text.
     */
    @Override
    public String toString() {
        return "\n<id: " + this.id + ">" + "\n<contactid :" + this.contactid + ">" + "\n<typeEmployeur :"
                + this.typeEmployeur + ">" + "\n<telephone :" + this.telephone + ">" + "\n<mail :" + this.mail + ">"
                + "\n<adresse :" + this.adresse + ">" + "\n<nomEntreprise :" + this.nomEntreprise + ">"
                + "\n<deliveryPoint :" + this.deliveryPoint + ">" + "\n<complement :" + this.complement + ">"
                + "\n<subdivision :" + this.subdivision + ">"+ "\n<street :" + this.street + ">"

        /*
         * + "\n<logo :"+this.logo+">"
         */;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDeliveryPoint() {
        return deliveryPoint;
    }

    public void setDeliveryPoint(String deliveryPoint) {
        this.deliveryPoint = deliveryPoint;
    }

    public String getId() {
        return id;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
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

}