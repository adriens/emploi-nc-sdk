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
    public static final String sizeEmplois = "200";
    public static final String searchEmploi = "";
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
        

        URL url = new URL("" +BASE_URL);

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = mapper.readValue(url, JsonNode.class);



        logger.info("Recherche de "+numberLatest+" offres emplois.");		
        
        for (int i = 0; i < numberLatest; i++) {
            Emploi emploi = getInfoEmploi(jsonNode, i);
            Employeur employeur = Employeurs.getInfoEmployeur(jsonNode, i);
            logger.info("Récupéré emploi : <" + i  + "> "+emploi);
            logger.info("Récupéré l'employeur pour l'emploi : <" + i  + "> "+employeur);
            
            logger.info("Ajout de l'employeur <" + i + "> à la liste");
            logger.info("Ajout de l'emploi <" + i + "> à la liste");

            emploi.setEmployeur(employeur);
            listeEmplois.add( emploi );

            logger.info("------------------------------------------------------------");
        }
        return listeEmplois;
    }

    public static Emploi getInfoEmploiByNumero(int numero) throws IOException {
        URL url = new URL("" +BASE_URL);

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = mapper.readValue(url, JsonNode.class);

        int result = Integer.parseInt(sizeEmplois);

        for (int i = 0; i < result; i++) {
            try {
                String numeroOffre = jsonNode.get("_embedded").get(i).get("numero").asText();
                numeroOffre = numeroOffre.replaceAll(".+-", "");
                numeroOffre = numeroOffre.replaceFirst ("^0*", "");
                 if ( numeroOffre.equals(""+numero) ){
                    logger.info("numeroOffre : <" + numeroOffre + "> trouvé on vous renvoie les infos" );
                    return getInfoEmploi(jsonNode, i);
                 }
            } catch (Exception e) {
                logger.warn("numeroOffre d'emplois parcours<" + i + "> est introuvable.");
            }
        }
        logger.warn("numeroOffre d'emplois recherché<" + numero + "> introuvable.");
        return null;
    }

    public static Emploi getInfoEmploi(JsonNode jsonNode,int i) {
            Emploi emploi = new Emploi();

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
            try {
                String created = jsonNode.get("_embedded").get(i).get("created").asText();
                logger.info("created : <" + created + ">");
                emploi.setCreated(created);
            } catch (Exception e) {
                logger.warn("Date création offre d'emplois <" + i + "> introuvable.");
            }
            try {
                String updated = jsonNode.get("_embedded").get(i).get("updated").asText();
                logger.info("updated : <" + updated + ">");
                emploi.setUpdated(updated);
            } catch (Exception e) {
                logger.warn("Date modification offre d'emplois <" + i + "> introuvable.");
            }
            try {
                String datePublication = jsonNode.get("_embedded").get(i).get("datePublication").asText();
                logger.info("datePublication : <" + datePublication + ">");
                emploi.setDatePublication(datePublication);
            } catch (Exception e) {
                logger.warn("DatePublication offre d'emplois <" + i + "> introuvable.");
            }
            try {
                String duree = jsonNode.get("_embedded").get(i).get("duree").asText();
                logger.info("duree : <" + duree + ">");
                emploi.setDuree(duree);
            } catch (Exception e) {
                logger.warn("Duree offre d'emplois <" + i + "> introuvable.");
            }
            try {
                String uniteDuree = jsonNode.get("_embedded").get(i).get("uniteDuree").asText();
                logger.info("uniteDuree : <" + uniteDuree + ">");
                emploi.setUniteDuree(uniteDuree);
            } catch (Exception e) {
                logger.warn("uniteDuree offre d'emplois <" + i + "> introuvable.");
            }
            try {
                String dureeTempsPartiel = jsonNode.get("_embedded").get(i).get("dureeTempsPartiel").asText();
                logger.info("dureeTempsPartiel : <" + dureeTempsPartiel + ">");
                emploi.setDureeTempsPartiel(dureeTempsPartiel);
            } catch (Exception e) {
                logger.warn("dureeTempsPartiel offre d'emplois <" + i + "> introuvable.");
            }
            try {
                String desQuePossible = jsonNode.get("_embedded").get(i).get("desQuePossible").asText();
                logger.info("desQuePossible : <" + desQuePossible + ">");
                emploi.setDesQuePossible(desQuePossible);
            } catch (Exception e) {
                logger.warn("desQuePossible offre d'emplois <" + i + "> introuvable.");
            }
            try {
                String shortnumeroOffre = jsonNode.get("_embedded").get(i).get("numero").asText();
                emploi.setShortnumeroOffre(shortnumeroOffre);
                logger.info("shortnumeroOffre : <" + emploi.getShortnumeroOffre() + ">");
            } catch (Exception e) {
                logger.warn("shortnumeroOffre offre d'emplois <" + i + "> introuvable.");
            }
            
            // URL OFFRES gouv.nc
            try {
                logger.info("Url vers l'offre  : <" + BASE_URL_OFFRE+emploi.getNumeroOffre() + "   >");
                emploi.setUrl(BASE_URL_OFFRE+emploi.getNumeroOffre());
            } catch (Exception e) {
                logger.warn("Url vers l'offre d'emploi <" + i + "> introuvable.");
            }
            return emploi;
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
            
            emploi = getInfoEmploi(jsonNode, i);
            employeur = Employeurs.getInfoEmployeur(jsonNode,i);

            emploi.setEmployeur(employeur);
         
            logger.info("Récupéré emploi : <" + i + ">"+emploi);
            logger.info("Ajout de l'emploi <" + i + "> à la liste");
            listeEmplois.add(emploi);
            logger.info("------------------------------------------------------------");
        }
        
        return listeEmplois;
    }

    public static Employeur getInfoEmployeurByNumEmploi(int numero) throws IOException {
        Emploi emploi = new Emploi();

        logger.info("Récupéré de l'employeur de l'offre : <" + numero + ">");
        try {
            emploi = getInfoEmploiByNumero(numero);
        }
        catch(NullPointerException e){
            logger.warn("Numero Offre d'emploi non trouvé"+e);
        }
        try {
            emploi.getEmployeur();
        }catch(NullPointerException e){
            logger.warn("Employeur introuvable"+e);
            return null;
        }
        return emploi.getEmployeur();
    }
    public static void main (String[] args) throws IOException{
        //getLatestEmploi(5);
        getInfoEmploiByNumero(4488);
        //Stat.getStats();
        //Employeurs.getInfoEmployeurByName("ASSUR PLANET / MONCEAU");
        //getInfoEmployeurByNumEmploi(4448);
       // Employeurs.getInfoEmployeurById("2c948a416bf3ba22016fb02ac3657d75");
    }
}
