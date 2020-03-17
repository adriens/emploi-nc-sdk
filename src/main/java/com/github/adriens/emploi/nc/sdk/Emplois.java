/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.emploi.nc.sdk;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


/**
 *
 * @author JAVAE
 */
public class Emplois {
    
    final static Logger logger = LoggerFactory.getLogger(Emplois.class);
    
    /**
     * number of Emploi we recover
     */
    public static final String sizeEmplois = "3";
    public static final String searchEmploi = "developpeur";
    public static final String priseDePosteDu = "";
    public static final String priseDePosteAu = "";

    /**
     * The base of the url where the data is retrieved.
     */
    public static final String BASE_URL  = "https://emploi.gouv.nc/api/v1/offres/public/search?page=0&"+
    "size="+ sizeEmplois +
    "&sort=datePublication,desc&"+
    "motsCles="+ searchEmploi +
    "&priseDePosteDu="+ priseDePosteDu +
    "&priseDePosteAu="+ priseDePosteAu;
    
    public static final String BASE_URL_OFFRE = "https://emploi.gouv.nc/offres/";

    public static ArrayList<Emploi> getLatestEmploi(int numberLatest) throws IOException {
        ArrayList<Emploi> listeEmplois = new ArrayList<>();
        logger.info("------------------------------------------------------------");
        logger.info("Recupération des derniers emplois sur emploi.gouv.nc : ");
        

        URL url = new URL("" +"https://emploi.gouv.nc/api/v1/offres/public/search?page=0&"+
        "size="+ numberLatest +
        "&sort=datePublication,desc&"+
        "motsCles="+
        "&priseDePosteDu=" +
        "&priseDePosteAu=");

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = mapper.readValue(url, JsonNode.class);

        logger.info("Recherche de "+numberLatest+" offres emplois.");		
        
        for (int i = 0; i < numberLatest; i++) {
            logger.info("Emplois : <" + i + ">\n");
            Emploi emploi = new Emploi();
            listeEmplois.add(getInfoEmploi(jsonNode,emploi, i));
            logger.info("------------------------------------------------------------");
        }
        return listeEmplois;
    }

    public static Emploi getInfoEmploi(JsonNode jsonNode, Emploi emploi ,int i) {

            try {
                String idOffre = jsonNode.get("_embedded").get(i).get("id").asText();
                logger.info("idOffre : <" + idOffre + ">");
                emploi.setIdOffre(idOffre);
            } catch (Exception e) {
                logger.warn("idOffre d'emplois <" + i + "> introuvable.");
            }
            try {
                String userId = jsonNode.get("_embedded").get(i).get("userId").asText();
                logger.info("userId : <" + userId + ">");
                emploi.setUserId(userId);
            } catch (Exception e) {
                logger.warn("userId d'emplois <" + i + "> introuvable.");
            }
            try {
                String titreOffre = jsonNode.get("_embedded").get(i).get("titreOffre").asText();
                logger.info("titreOffre : <" + titreOffre + ">" );
                emploi.setTitreOffre(titreOffre);
            } catch (Exception e) {
                logger.warn("titreOffre d'emplois <" + i + "> introuvable.");
            }
            try {
                String numeroOffre = jsonNode.get("_embedded").get(i).get("numero").asText();
                logger.info("numeroOffre : <" + numeroOffre + ">" );
                emploi.setNumeroOffre(numeroOffre);
            } catch (Exception e) {
                logger.warn("numeroOffre d'emplois <" + i + "> introuvable.");
            }
            try {
                String aPourvoirLe = jsonNode.get("_embedded").get(i).get("aPourvoirLe").asText();
                logger.info("aPourvoirLe : <" + aPourvoirLe + ">");
                emploi.setaPourvoirLe(aPourvoirLe);
            } catch (Exception e) {
                logger.warn("aPourvoirLe d'emplois <" + i + "> introuvable.");
            }
            try {
                String typeContrat = jsonNode.get("_embedded").get(i).get("typeContrat").asText();
                logger.info("typeContrat : <" + typeContrat  + ">");
                emploi.setTypeContrat(typeContrat);
            } catch (Exception e) {
                logger.warn("typeContrat d'emplois <" + i + "> introuvable.");
            }
            try {
                String communeEmploi = jsonNode.get("_embedded").get(i).get("communeEmploi").asText();
                logger.info("communeEmploi : <" + communeEmploi + ">");
                emploi.setCommuneEmploi(communeEmploi);
            } catch (Exception e) {
                logger.warn("communeEmploi d'emplois <" + i + "> introuvable.");
            }
            try {
                String experience = jsonNode.get("_embedded").get(i).get("experience").asText();
                logger.info("experience : <" + experience + ">");
                emploi.setUserId(experience);
            } catch (Exception e) {
                logger.warn("experience d'emplois <" + i + "> introuvable.");
            }
            try {
                String codeROME = jsonNode.get("_embedded").get(i).get("codeROME").asText();
                logger.info("codeROME : <" + codeROME + ">");
                emploi.setUserId(codeROME);
            } catch (Exception e) {
                logger.warn("codeROME d'emplois <" + i + "> introuvable.");
            }
            return emploi;
    }

    public static Employeur getInfoEmployeur(JsonNode jsonNode,Employeur employeur, int i){
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
            logger.info("contenu : <" + contenu + ">");
            employeur.setLogo(contenu);
        } catch (Exception e) {
            logger.warn("contenu d'employeur <" + i + "> introuvable.");
                    }
        return employeur;
    }

    public static ArrayList<Emploi> getAllEmploi() throws IOException {
        logger.info("------------------------------------------------------------");
     
        ArrayList<Emploi> listeEmplois = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        logger.info("Recherche de l'URL : "+ BASE_URL);


        logger.info("Recupération des données de emploi.gouv.nc : ");
        URL url = new URL("" +BASE_URL );

        JsonNode jsonNode = mapper.readValue(url, JsonNode.class);

        logger.info("Recherche de "+sizeEmplois+" offres emplois.");
        int result = Integer.parseInt(sizeEmplois);			
        
        for (int i = 0; i < result; i++) {

            Emploi emploi = new Emploi();
            // TODO : Repenser structure du code
            Employeur employeur = new Employeur();

            logger.info("Emplois : <" + i + ">\n");
            getInfoEmploi(jsonNode,emploi, i);
            getInfoEmployeur(jsonNode,employeur, i);


            // URL OFFRES gouv.nc
            try {
                logger.info("Url vers l'offre  : <   " + BASE_URL_OFFRE+emploi.getNumeroOffre() + "   >");
                employeur.setLogo(BASE_URL_OFFRE+emploi.getNumeroOffre());
            } catch (Exception e) {
                logger.warn("Url vers l'offre d'emploi <" + i + "> introuvable.");
            }
            logger.info("------------------------------------------------------------");
            listeEmplois.add(emploi);
        }
        
        return listeEmplois;
    }
    public static void main (String[] args) throws IOException{
        System.out.println("Hello World");
        getLatestEmploi(5);
    }
}
