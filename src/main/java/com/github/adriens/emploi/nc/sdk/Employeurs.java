package com.github.adriens.emploi.nc.sdk;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author JAVAE
 */

public class Employeurs {


    final static Logger logger = LoggerFactory.getLogger(Emplois.class);

    public static final String SIZE_EMPLOYEURS = "100";
    public static final String SIZE_OFFERS_BY_EMPLOYEURS = "20";
    public static final String ALL_EMPLOYEURS_URL = "";
    public static final String OFFERS_BY_EMPLOYEURS_URL = "https://emploi.gouv.nc/api/v1/offres/public/search?page=0&size="+ SIZE_OFFERS_BY_EMPLOYEURS
    +"&sort=datePublication,desc&motsCles=";

    public static Employeur getInfoEmployeurByName(String nom) throws IOException{
        URL url = new URL(OFFERS_BY_EMPLOYEURS_URL+nom.replaceAll(" ", "%20"));
        logger.info("On cherche : <" + nom + "> dans cet url<"+url+">");

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = mapper.readValue(url, JsonNode.class);

        int result = Integer.parseInt(Emplois.sizeEmplois);

        try {// S'il en trouve au moins une correspondance
            
            String nomEntreprise = jsonNode.get("_embedded").get(0).get("employeur").get("nomEntreprise").asText();
            nomEntreprise = nomEntreprise.replaceAll(" ", "");
            nom = nom.replaceAll(" ", "");
            logger.info("nomEntreprise : <" + nomEntreprise + ">");
            logger.info("nomEntreprise : <" + nomEntreprise + ">" + "nom de la recherche : <" + nom + ">" );
                if ( nom.equals(nomEntreprise) ){
                    logger.info("nomEntreprise : <" + nomEntreprise + "> trouvé on vous renvoie les infos" );
                    return getInfoEmployeur(jsonNode, 0);
                }
        } catch (Exception e) {
            logger.warn("Nom <" + nom + "> est introuvable.");
        }
    
        logger.warn("nomEntreprise d'employeur recherché<" + nom + "> introuvable.");
        return null;
    }

    
    public static Employeur getInfoEmployeur(JsonNode jsonNode,int i) throws IOException {
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
            employeur.setTypeEmployeur(typeEmployeur);
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
            String adresse = jsonNode.get("_embedded").get(i).get("employeur").get("adresseCorrespondance")
                    .get("deliveryPoint").asText();
            logger.info("adresse : <" + adresse + ">");
            employeur.setAdresse(adresse);
        } catch (Exception e) {
            logger.warn("adresse d'employeur <" + i + "> introuvable.");
        }
        try {
            String contenu = jsonNode.get("_embedded").get(i).get("employeur").get("logo").get("contenu").asText();
            // logger.info("contenu : <" + contenu + ">");
            logger.info("contenu is not Empty : <" + !contenu.isEmpty() + ">");
            employeur.setLogo(contenu);
        } catch (Exception e) {
            logger.warn("contenu d'employeur <" + i + "> introuvable.");
        }
        
        try {
            String nomEntreprise = jsonNode.get("_embedded").get(i).get("employeur").get("nomEntreprise").asText();
            logger.info("nomEntreprise : <" + nomEntreprise + ">");
            employeur.setNomEntreprise(nomEntreprise);
        } catch (Exception e) {
            logger.warn("NomEntreprise d'employeur <" + i + "> introuvable.");
        }
        try {
            String complement = jsonNode.get("_embedded").get(i).get("employeur").get("adresseCorrespondance").get("complement").asText();
            logger.info("Complement : <" + complement + ">");
            employeur.setComplement(complement);
        } catch (Exception e) {
            logger.warn("Complement d'employeur <" + i + "> introuvable.");
        }
        try {
            String deliveryPoint = jsonNode.get("_embedded").get(i).get("employeur").get("adresseCorrespondance").get("deliveryPoint").asText();
            logger.info("DeliveryPoint : <" + deliveryPoint + ">");
            employeur.setDeliveryPoint(deliveryPoint);
        } catch (Exception e) {
            logger.warn("DeliveryPoint d'employeur <" + i + "> introuvable.");
        }
        try {
            String subdivision = jsonNode.get("_embedded").get(i).get("employeur").get("adresseCorrespondance").get("subdivision").asText();
            logger.info("subdivision : <" + subdivision + ">");
            employeur.setSubdivision(subdivision);
        } catch (Exception e) {
            logger.warn("Subdivision d'employeur <" + i + "> introuvable.");
        }
        try {
            String street = jsonNode.get("_embedded").get(i).get("employeur").get("adresseCorrespondance").get("street").asText();
            logger.info("Street : <" + street + ">");
            employeur.setStreet(street);
        } catch (Exception e) {
            logger.warn("Street d'employeur <" + i + "> introuvable.");
        }

        // Setting Liste Employeurs
        try {
            employeur.setListEmplois(getAllEmploiOfferForEmployeur(jsonNode));
            logger.info("List Emplois set.");
        } catch (Exception e) {
            logger.warn("List Emplois set Erreur"+e);
        }

        return employeur;
    }

    public static ArrayList<Emploi> getAllEmploiOfferForEmployeur(JsonNode jsonNode){

        int numberOffer = Integer.parseInt( jsonNode.get("page").get("totalElements").asText() );
        ArrayList<Emploi> listEmploi = new ArrayList<>();
        for (int i = 0; i < numberOffer; i++) {
            try {
                listEmploi.add(Emplois.getInfoEmploi(jsonNode,i,false));
                logger.info("Emplois <"+ i +">"+"getAllEmploiOfferForEmployeur de valide");
            }catch (Exception e) {
                logger.warn("Mapping idEmployeur d'employeur <" + i + "> à échoué.");
            }
        }

        return listEmploi;
    }
    
    public static JsonNode  recreateJsonEmployeurs() throws IOException {
        URL url = new URL(ALL_EMPLOYEURS_URL);

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNodeEmployeurs = mapper.readValue(url, JsonNode.class);

        ObjectMapper mapperRESULT = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode rootNode = mapperRESULT.readValue(url, JsonNode.class);

        
        
        int numberEmployeurs = Integer.parseInt(jsonNodeEmployeurs.get("page").get("totalElements").asText());
        for(int i = 0; i < numberEmployeurs; i++){
            try {
                String idEmployeur = jsonNodeEmployeurs.get("content").get(i).get("id").asText();
                //logger.info("idEmployeur pour Mapping : <" + idEmployeur + ">");
                ObjectNode objectNode = ((ObjectNode) rootNode).putObject(idEmployeur);

                objectNode.putObject(idEmployeur);
                objectNode
                    .put("nomEntreprise", jsonNodeEmployeurs.get("content").get(i).get("nomEntreprise").asText())
                    .put("ridetEntreprise", jsonNodeEmployeurs.get("content").get(i).get("nomEntreprise").asText())
                    .put("nbOffres", jsonNodeEmployeurs.get("content").get(i).get("nbOffres").asText())
                    
                    .put("deliveryPoint", jsonNodeEmployeurs.get("content").get(i).get("adresseCorrespondance").get("deliveryPoint").asText())
                    .put("complement", jsonNodeEmployeurs.get("content").get(i).get("adresseCorrespondance").get("complement").asText())
                    .put("street", jsonNodeEmployeurs.get("content").get(i).get("adresseCorrespondance").get("street").asText())
                    .put("subdivision", jsonNodeEmployeurs.get("content").get(i).get("adresseCorrespondance").get("subdivision").asText());

                
            } catch (Exception e) {
                logger.warn("Mapping idEmployeur d'employeur <" + i + "> à échoué.");
            }
        }

        return rootNode;
    }
}