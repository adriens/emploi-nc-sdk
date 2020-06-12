package com.github.adriens.emploi.nc.sdk;

import java.io.IOException;

import com.github.adriens.emploi.nc.sdk.xml.XMLReader;

public class Main {
    public static void main (String[] args) throws IOException{
    //XMLReader.read();
    Emplois.getCSVLines(10);
    //Emplois.getLatestEmploi(5);
    //Emplois.getNextXOfferNumEmploi(4722,10);
    //Emplois.getPreviousXOfferNumEmploi(4722,10);
    //Emplois.getInfoEmploiByNumero(4722);
    //Stat.getStats();
    //Employeurs.getInfoEmployeurByName("ASSUR PLANET / MONCEAU");
    //Emplois.getInfoEmployeurByNumEmploi(4448);
    // Employeurs.getInfoEmployeurByName("Easy Skill");
    //Employeurs.getAllEmployeurs();
    //Emplois.getSearchInfoEmploi("30","","Dumbéa","CDI","","");
    //Emplois.getSearchInfoEmploi("30","","Houaïlou","CDI","","");
    //Emplois.getSearchInfoEmploi("30","","Île-des-Pins (L')","CDI","","");// TODO pas de donnée
    //Emplois.getSearchInfoEmploi("30","","Païta","CDI","","");
    //Emplois.getSearchInfoEmploi("2","","Dumbéa","CDI","","");
    }
}

