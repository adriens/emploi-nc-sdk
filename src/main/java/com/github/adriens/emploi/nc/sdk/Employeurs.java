package com.github.adriens.emploi.nc.sdk;

import java.io.IOException;
import java.net.URL;
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

    public static final String size = "100";

    public static final String ALLEMPLOYEURS_URL = "https://emploi.gouv.nc/api/v1/offres/employeurs/public/findAllEmployeurWithCountOffres?page=0&size="
            + size;

    public static Employeur getInfoEmployeurByName(String nom) throws IOException{
        URL url = new URL(Emplois.BASE_URL);

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = mapper.readValue(url, JsonNode.class);

        int result = Integer.parseInt(Emplois.sizeEmplois);

        for (int i = 0; i < result; i++) {
            try {
                String nomEntreprise = jsonNode.get("_embedded").get(i).get("employeur").get("nomEntreprise").asText();
                logger.info("nomEntreprise : <" + nomEntreprise + ">");
                 if ( nom.equals(nomEntreprise) ){
                    logger.info("nomEntreprise : <" + nomEntreprise + "> trouvé on vous renvoie les infos" );
                    return getInfoEmployeur(jsonNode, i);
                 }
            } catch (Exception e) {
                logger.warn("numeroOffre d'emplois parcours<" + i + "> est introuvable.");
            }
        }
        logger.warn("nomEntreprise d'employeur recherché<" + nom + "> introuvable.");
        return null;
    }

    public static Employeur getInfoEmployeurById(String id) throws IOException{
        URL url = new URL(Emplois.BASE_URL);

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = mapper.readValue(url, JsonNode.class);

        int result = Integer.parseInt(Emplois.sizeEmplois);

        for (int i = 0; i < result; i++) {
            try {
                String idEmploi = jsonNode.get("_embedded").get(i).get("employeur").get("id").asText();
                logger.info("id : <" + id + ">"+"idEmploi : <" + idEmploi + ">");
                 if ( idEmploi.equals(id) ){
                    logger.info("id : <" + idEmploi + "> trouvé on vous renvoie les infos" );
                    return getInfoEmployeur(jsonNode, i);
                 }
            } catch (Exception e) {
                logger.warn("numeroOffre d'emplois parcours<" + i + "> est introuvable.");
            }
        }
        logger.warn("idEmploi d'employeur recherché<" + id + "> introuvable.");
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

        return employeur;
    }

    public static JsonNode  recreateJsonEmployeurs() throws IOException {
        URL url = new URL(ALLEMPLOYEURS_URL);

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