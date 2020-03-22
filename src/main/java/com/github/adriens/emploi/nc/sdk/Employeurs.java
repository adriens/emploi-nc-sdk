package com.github.adriens.emploi.nc.sdk;

import com.fasterxml.jackson.databind.JsonNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author JAVAE
 */

public class Employeurs {

    final static Logger logger = LoggerFactory.getLogger(Emplois.class);
    
    public static Employeur getInfoEmployeur(JsonNode jsonNode, int i){
        Employeur employeur = new Employeur();
        
        // Employeur
        logger.info("Info employeur :--------------------------------------------");
        try {
            String idEmployeur = jsonNode.get("_embedded").get(i).get("employeur").get("id").asText();
            logger.info("idEmployeur : <" + idEmployeur + ">");
            employeur.setId(idEmployeur);
        } catch (Exception e) {
            logger.warn("idEmployeur d'employeur <" + i + "> introuvable.");
        }
        try {
            String contactid = jsonNode.get("_embedded").get(i).get("contact").get("id").asText();
            logger.info("contactid : <" + contactid + ">");
            employeur.setContactid(contactid);
        } catch (Exception e) {
            logger.warn("contactid d'employeur <" + i + "> introuvable.");
        }
        try {
            String typeEmployeur = jsonNode.get("_embedded").get(i).get("employeur").get("type").asText();
            logger.info("typeEmployeur : <" + typeEmployeur + ">");
            employeur.setContactid(typeEmployeur);
        } catch (Exception e) {
            logger.warn("typeEmployeur d'employeur <" + i + "> introuvable.");
        }
        try {
            String telephone = jsonNode.get("_embedded").get(i).get("contact").get("telephone").asText();
            logger.info("telephone : <" + telephone + ">");
            employeur.setTelephone(telephone);
        } catch (Exception e) {
            logger.warn("telephone d'employeur <" + i + "> introuvable.");
        }
        try {
            String mail = jsonNode.get("_embedded").get(i).get("contact").get("mail").asText();
            logger.info("mail : <" + mail + ">");
            employeur.setMail(mail);
        } catch (Exception e) {
            logger.warn("mail d'employeur <" + i + "> introuvable.");
        }
        try {
            String adresse = jsonNode.get("_embedded").get(i).get("employeur").get("adresseCorrespondance").get("deliveryPoint").asText();
            logger.info("adresse : <" + adresse + ">");
            employeur.setAdresse(adresse);
        } catch (Exception e) {
            logger.warn("adresse d'employeur <" + i + "> introuvable.");
        }
        try {
            String contenu = jsonNode.get("_embedded").get(i).get("employeur").get("logo").get("contenu").asText();
            //logger.info("contenu : <" + contenu + ">");
            logger.info("contenu is not Empty : <" + !contenu.isEmpty() + ">");
            employeur.setLogo(contenu);
        } catch (Exception e) {
            logger.warn("contenu d'employeur <" + i + "> introuvable.");
                    }
        return employeur;
    }
}