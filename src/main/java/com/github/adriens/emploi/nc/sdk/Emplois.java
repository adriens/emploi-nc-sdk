/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.emploi.nc.sdk;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.adriens.emploi.nc.sdk.xml.Commune;
import com.github.adriens.emploi.nc.sdk.xml.Communes;
import com.github.adriens.emploi.nc.sdk.xml.XMLReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.commands.Commands;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public static final String BASE_URL = "https://emploi.gouv.nc/api/v1/offres/public/search?page=0&" + "size="
            + sizeEmplois + "&sort=datePublication,desc&" + "motsCles=" + searchEmploi + "&priseDePosteDu="
            + priseDePosteDu + "&priseDePosteAu=" + priseDePosteAu;

    public static final String BASE_URL_OFFRE = "https://emploi.gouv.nc/offres/";

    public static ArrayList<Emploi> getPreviousXOfferNumEmploi(int numero, int last) throws IOException {

        URL url = new URL("" + BASE_URL);

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = mapper.readValue(url, JsonNode.class);

        int result = Integer.parseInt(sizeEmplois);

        ArrayList<Emploi> emplois = new ArrayList<>();
        logger.info("Recherche des <" + last + "> offres d'emplois suivantes à partir de l'offre <" + numero + ">.");

        for (int j = 0; j < result; j++) {
            try {
                String numeroOffre = jsonNode.get("_embedded").get(j).get("numero").asText();
                numeroOffre = numeroOffre.replaceAll(".+-", "");
                numeroOffre = numeroOffre.replaceFirst("^0*", "");

                if (numeroOffre.equals("" + numero)) {

                    for (int i = 1; i <= last && j + i <= result; i++) {
                        Emploi emploi = new Emploi();
                        logger.info("numeroOffre : <" + numeroOffre + "> trouvé on vous renvoie les infos");
                        try {
                            emploi = getInfoEmploi(jsonNode, j + i, true);
                            if (emploi != null) {
                                emplois.add(emploi);
                            }
                        } catch (Exception e) {
                            logger.info("Recupération des derniers emplois sur emploi.gouv.nc : ");
                        }
                    }
                    return emplois;
                }

            } catch (Exception e) {
                logger.warn("numeroOffre d'emplois parcours<" + j + "> est introuvable.");
                return emplois;
            }

        }

        return emplois;
    }

    public static ArrayList<Emploi> getNextXOfferNumEmploi(int numero, int previous) throws IOException {

        URL url = new URL("" + BASE_URL);

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = mapper.readValue(url, JsonNode.class);

        int result = Integer.parseInt(sizeEmplois);

        ArrayList<Emploi> emplois = new ArrayList<>();
        logger.info(
                "Recherche des <" + previous + "> offres d'emplois suivantes à partir de l'offre <" + numero + ">.");

        for (int j = 0; j < result; j++) {
            try {
                String numeroOffre = jsonNode.get("_embedded").get(j).get("numero").asText();
                numeroOffre = numeroOffre.replaceAll(".+-", "");
                numeroOffre = numeroOffre.replaceFirst("^0*", "");

                if (numeroOffre.equals("" + numero)) {

                    for (int i = 1; i <= previous && j - i > 0; i++) {
                        Emploi emploi = new Emploi();
                        logger.info("numeroOffre : <" + numeroOffre + "> trouvé on vous renvoie les infos");
                        try {
                            emploi = getInfoEmploi(jsonNode, j - i, true);
                            if (emploi != null) {
                                emplois.add(emploi);
                            }
                        } catch (Exception e) {
                            logger.info("Recupération des derniers emplois sur emploi.gouv.nc : ");
                            return emplois;
                        }
                    }
                    return emplois;
                }

            } catch (Exception e) {
                logger.warn("numeroOffre d'emplois parcours<" + j + "> est introuvable.");
            }

        }

        return emplois;
    }

    public static ArrayList<Emploi> getLatestEmploi(int numberLatest) throws IOException {

        ArrayList<Emploi> listeEmplois = new ArrayList<>();

        logger.info("------------------------------------------------------------");
        logger.info("Recupération des derniers emplois sur emploi.gouv.nc : ");

        URL url = new URL("" + BASE_URL);

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = mapper.readValue(url, JsonNode.class);

        logger.info("Recherche de " + numberLatest + " offres emplois.");

        for (int i = 0; i < numberLatest; i++) {
            Emploi emploi = getInfoEmploi(jsonNode, i, true);
            Employeur employeur = Employeurs.getInfoEmployeur(jsonNode, i);
            logger.info("Récupéré emploi : <" + i + "> " + emploi);
            logger.info("Récupéré l'employeur pour l'emploi : <" + i + "> " + employeur);

            logger.info("Ajout de l'employeur <" + i + "> à la liste");
            logger.info("Ajout de l'emploi <" + i + "> à la liste");

            emploi.setEmployeur(employeur);
            listeEmplois.add(emploi);

            logger.info("------------------------------------------------------------");
        }
        return listeEmplois;
    }

    public static Emploi getInfoEmploi(JsonNode jsonNode, int i, Boolean searchEmployeurs) {
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
            logger.info("titreOffre : <" + titreOffre + ">");
            emploi.setTitreOffre(titreOffre);
        } catch (Exception e) {
            logger.warn("titreOffre d'emplois <" + i + "> introuvable.");
        }
        try {
            String numeroOffre = jsonNode.get("_embedded").get(i).get("numero").asText();
            logger.info("numeroOffre : <" + numeroOffre + ">");
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
            logger.info("typeContrat : <" + typeContrat + ">");
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
            String niveauFormation = jsonNode.get("_embedded").get(i).get("niveauFormation").asText();
            logger.info("niveauFormation : <" + niveauFormation + ">");
            emploi.setNiveauFormation(niveauFormation);
        } catch (Exception e) {
            logger.warn("niveauFormation d'emplois <" + i + "> introuvable.");
        }
        try {
            String diplome = jsonNode.get("_embedded").get(i).get("diplome").asText();
            logger.info("diplome : <" + diplome + ">");
            emploi.setDiplome(diplome);
        } catch (Exception e) {
            logger.warn("diplome d'emplois <" + i + "> introuvable.");
        }
        try {
            String experience = jsonNode.get("_embedded").get(i).get("experience").asText();
            logger.info("experience : <" + experience + ">");
            emploi.setExperience(experience);
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
            logger.info("Url vers l'offre  : <" + BASE_URL_OFFRE + emploi.getNumeroOffre() + "   >");
            emploi.setUrl(BASE_URL_OFFRE + emploi.getNumeroOffre());
        } catch (Exception e) {
            logger.warn("Url vers l'offre d'emploi <" + i + "> introuvable.");
        }

        String nomEntreprise = jsonNode.get("_embedded").get(i).get("employeur").get("nomEntreprise").asText();
        // Renvoie des infos employeurs liées à l'offre
        if (searchEmployeurs && nomEntreprise != "Anonyme" && nomEntreprise != "") {
            Employeur employeur = new Employeur();
            try {
                logger.info("Employeur lié à l'offre  : <" + emploi.getNumeroOffre() + ">");
                employeur = Employeurs.getInfoEmployeurByName(nomEntreprise);
                logger.info("Ajout de l'employeur à l'offre d'emploi.");
                emploi.setEmployeur(employeur);
                logger.info("<" + emploi + ">");
            } catch (Exception e) {
                logger.warn("Url vers l'offre d'emploi <" + i + "> introuvable.");
            }
        }

        return emploi;
    }

    public static ArrayList<Emploi> getAllEmploi() throws IOException {
        logger.info("------------------------------------------------------------");

        ArrayList<Emploi> listeEmplois = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        logger.info("Recherche de l'URL : " + BASE_URL);

        logger.info("Recupération des données de emploi.gouv.nc : ");
        URL url = new URL("" + BASE_URL);

        JsonNode jsonNode = mapper.readValue(url, JsonNode.class);

        logger.info("Recherche de " + sizeEmplois + " offres emplois.");
        int result = Integer.parseInt(sizeEmplois);

        for (int i = 0; i < result; i++) {

            Emploi emploi = new Emploi();

            emploi = getInfoEmploi(jsonNode, i, true);
            logger.info("Récupéré emploi : <" + i + ">" + emploi);
            logger.info("Ajout de l'emploi <" + i + "> à la liste");
            listeEmplois.add(emploi);
            logger.info("------------------------------------------------------------");
        }

        return listeEmplois;
    }

    public static Emploi getInfoEmploiByNumero(int numero) throws IOException {
        URL url = new URL("" + BASE_URL);

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = mapper.readValue(url, JsonNode.class);

        int result = Integer.parseInt(sizeEmplois);

        for (int i = 0; i < result; i++) {
            try {
                String numeroOffre = jsonNode.get("_embedded").get(i).get("numero").asText();
                numeroOffre = numeroOffre.replaceAll(".+-", "");
                numeroOffre = numeroOffre.replaceFirst("^0*", "");
                if (numeroOffre.equals("" + numero)) {
                    logger.info("numeroOffre : <" + numeroOffre + "> trouvé on vous renvoie les infos");
                    return getInfoEmploi(jsonNode, i, true);
                }
            } catch (Exception e) {
                logger.warn("numeroOffre d'emplois parcours<" + i + "> est introuvable.");
            }
        }
        logger.warn("numeroOffre d'emplois recherché<" + numero + "> introuvable.");
        return null;
    }

    public static Employeur getInfoEmployeurByNumEmploi(int numero) throws IOException {
        Emploi emploi = new Emploi();

        logger.info("Récupéré de l'employeur de l'offre : <" + numero + ">");
        try {
            emploi = getInfoEmploiByNumero(numero);
        } catch (NullPointerException e) {
            logger.warn("Numero Offre d'emploi non trouvé" + e);
        }
        try {
            emploi.getEmployeur();
        } catch (NullPointerException e) {
            logger.warn("Employeur introuvable" + e);
            return null;
        }
        System.out.println(emploi.getEmployeur());
        return emploi.getEmployeur();
    }

    public static ArrayList<Emploi> getSearchInfoEmploi(String searchsizeEmplois, String searchmotsCles,
            String searchcommunehEmploi, String searchtypeContrat, String searchpriseDePosteDu,
            String searchpriseDePosteAu) throws IOException {
        logger.info("------------------------------------------------------------");

        String surl = "https://emploi.gouv.nc/api/v1/offres/public/search?page=0&" + "size=" + searchsizeEmplois
                + "&sort=datePublication,desc&" + "motsCles=" + searchmotsCles + "&communeEmploi="
                + searchcommunehEmploi + "&typeContrat=" + searchtypeContrat + "&priseDePosteDu=" + searchpriseDePosteDu
                + "&priseDePosteAu=" + searchpriseDePosteAu;
        surl = surl.replaceAll("é", "%C3%A9");
        surl = surl.replaceAll("î", "%C3%8E");
        surl = surl.replaceAll("ï", "%C3%AF");
        surl = surl.replaceAll("'", "%27");
        surl = surl.replaceAll("è", "%C3%A8");
        URL url = new URL(surl);

        logger.info("URL de recherche : " + url);

        logger.info("RECHERCHE - Recupération des données de emploi.gouv.nc : ");
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = mapper.readValue(url, JsonNode.class);
        int numberOfferMax = Integer.parseInt(jsonNode.get("page").get("totalElements").asText());
        int numberOffer = Integer.parseInt(searchsizeEmplois);

        if (numberOffer > numberOfferMax)
            numberOffer = numberOfferMax;
        ArrayList<Emploi> listeEmplois = new ArrayList<>();
        for (int i = 0; i < numberOffer; i++) {
            Emploi emploi = new Emploi();
            Employeur employeur = Employeurs.getInfoEmployeur(jsonNode, i);
            emploi = getInfoEmploi(jsonNode, i, true);
            logger.info("SEARCH : Récupéré emploi : <" + i + "> " + emploi);
            logger.info("SEARCH : Récupéré l'employeur pour l'emploi : <" + i + "> " + employeur);

            logger.info("SEARCH : Ajout de l'employeur <" + i + "> à la liste");
            logger.info("SEARCH : Ajout de l'emploi <" + i + "> à la liste");

            emploi.setEmployeur(employeur);
            listeEmplois.add(emploi);
            logger.info("------------------------------------------------------------");
        }
        return listeEmplois;
    }

    public static ArrayList<CSVLine> getCSVLines(int n) throws IOException {

        ArrayList<CSVLine> csvs = new ArrayList<>();

        URL url = new URL("" + BASE_URL);

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = mapper.readValue(url, JsonNode.class);

        int numberOfferMax = Integer.parseInt(jsonNode.get("page").get("totalElements").asText());

        logger.info("------------------------------------------------------------");
        logger.info("Recupération des derniers emplois sur emploi.gouv.nc : ");
        logger.info(BASE_URL);
        for (int i = 0; i < n && i < numberOfferMax; i++) {
            CSVLine line = new CSVLine();
            try{
                String numeroOffre = jsonNode.get("_embedded").get(i).get("numero").asText();
                line.setNumeroOffre(numeroOffre);
                logger.info("idOffre : <" + line.getNumeroOffre() + ">");
                
                String titreOffre = jsonNode.get("_embedded").get(i).get("titreOffre").asText();
                line.setTitreOffre(titreOffre);
                logger.info("titreOffre : <" + line.getTitreOffre() + ">");

                String nomEntreprise = jsonNode.get("_embedded").get(i).get("employeur").get("nomEntreprise").asText();
                line.setNomEntreprise(nomEntreprise);
                logger.info("nomEntreprise : <" + line.getNomEntreprise() + ">");

                String aPourvoirLe = jsonNode.get("_embedded").get(i).get("aPourvoirLe").asText();
                line.setaPourvoirLe(aPourvoirLe);
                logger.info("aPourvoirLe : " + line.getaPourvoirLe() + ">");
                
                String experience = jsonNode.get("_embedded").get(i).get("experience").asText();
                line.setExperience(experience);
                logger.info("experience : <" + line.getExperience() + ">");

                String niveauFormation = jsonNode.get("_embedded").get(i).get("niveauFormation").asText();
                line.setNiveauFormation(niveauFormation);
                logger.info("niveauFormation : <" + line.getNiveauFormation() + ">");

                String diplome = jsonNode.get("_embedded").get(i).get("diplome").asText();
                line.setDiplome(diplome);
                logger.info("diplome : <" + line.getDiplome() + ">");
                
                String nbPostes = jsonNode.get("_embedded").get(i).get("nbPostes").asText();
                line.setNbPostes(nbPostes);
                logger.info("nbPostes : <" + line.getNbPostes() + ">");

                String datePublication = jsonNode.get("_embedded").get(i).get("datePublication").asText();
                line.setDatePublication(datePublication);
                logger.info("datePublication : <" + line.getDatePublication() + ">");

                String typeContrat = jsonNode.get("_embedded").get(i).get("typeContrat").asText();
                line.setTypeContrat(typeContrat);
                logger.info("typeContrat : <" + line.getTypeContrat() + ">");

                logger.info("Url vers l'offre  : <" + BASE_URL_OFFRE + line.getNumeroOffre() + "   >");
                line.setUrl(BASE_URL_OFFRE + line.getNumeroOffre());
                String communeEmploi = jsonNode.get("_embedded").get(i).get("communeEmploi").asText();
                
                line.setCommuneEmploi(communeEmploi);
                logger.info("communeEmploi : <" + line.getCommuneEmploi() + ">");
            } catch (Exception e) {
                logger.error("Erreur Structure changées ou liens invalide,indiponible ou autres", e);
            }
            csvs.add(line);

            logger.info("------------------------------------------------------------");
        }

        csvs = findProvince(csvs);

        return csvs;
    }

    public static ArrayList<CSVLine> findProvince(ArrayList<CSVLine> csvlines) {
        Communes communes = XMLReader.read();
        List<Commune> listCommunes = communes.getCommunes();

        for ( CSVLine l : csvlines ){
            for ( Commune c :  listCommunes ){
                if ( l.getCommuneEmploi().contains(c.getName()) ){
                    l.setProvince(c.getProvince().getName());
                    l.setLatitude(c.getLocalisation().getLat());
                    l.setLongitude(c.getLocalisation().getLongitude());
                    l.setUrlgooglemap(c.getLocalisation().getUrlgooglemap());
                }
            }
        }
        return csvlines;
    }
}
