package com.github.adriens.emploi.nc.sdk;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stat {

    private String nbOffresPublieesTotales;
    private String nbOffresPublieesEnCours;
    private String nbEmployeursAvecOffresPubliees;

    public static final String STATS_URL = "https://emploi.gouv.nc/api/v1/offres/public/stats";
    
    final static Logger logger = LoggerFactory.getLogger(Stat.class);

    public String getNbOffresPublieesTotales() {
        return nbOffresPublieesTotales;
    }

    public String getNbEmployeursAvecOffresPubliees() {
        return nbEmployeursAvecOffresPubliees;
    }

    public void setNbEmployeursAvecOffresPubliees(String nbEmployeursAvecOffresPubliees) {
        this.nbEmployeursAvecOffresPubliees = nbEmployeursAvecOffresPubliees;
    }

    public String getNbOffresPublieesEnCours() {
        return nbOffresPublieesEnCours;
    }

    public void setNbOffresPublieesEnCours(String nbOffresPublieesEnCours) {
        this.nbOffresPublieesEnCours = nbOffresPublieesEnCours;
    }

    public void setNbOffresPublieesTotales(String nbOffresPublieesTotales) {
        this.nbOffresPublieesTotales = nbOffresPublieesTotales;
    }

    public static Stat getStats() throws IOException {
        Stat stat = new Stat();

        logger.info("------------------------------------------------------------");
        logger.info("Recupération des stats sur emploi.gouv.nc : ");

        URL url = new URL("" +STATS_URL);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = mapper.readValue(url, JsonNode.class);

        try {
            String nbOffresPublieesTotales = jsonNode.get("nbOffresPublieesTotales").asText();
            logger.info("nbOffresPublieesTotales : <" + nbOffresPublieesTotales + ">");
            stat.setNbOffresPublieesTotales(nbOffresPublieesTotales);

            String nbOffresPublieesEnCours = jsonNode.get("nbOffresPublieesTotales").asText();
            logger.info("nbOffresPublieesTotales : <" + nbOffresPublieesEnCours + ">");
            stat.setNbOffresPublieesEnCours(nbOffresPublieesEnCours);

            String nbEmployeursAvecOffresPubliees = jsonNode.get("nbEmployeursAvecOffresPubliees").asText();
            logger.info("nbEmployeursAvecOffresPubliees : <" + nbEmployeursAvecOffresPubliees + ">");
            stat.setNbEmployeursAvecOffresPubliees(nbEmployeursAvecOffresPubliees);
        } catch (Exception e) {
            logger.warn("Une des Stats est introuvable.");
        }

        return stat;
    }
}