package com.github.adriens.emploi.nc.sdk;

import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException{
    //getLatestEmploi(5);
    //Emplois.getInfoEmploiByNumero(4488);
    //Stat.getStats();
    //Employeurs.getInfoEmployeurByName("ASSUR PLANET / MONCEAU");
        //Emplois.getInfoEmployeurByNumEmploi(4448);
        
        // Employeurs.getInfoEmployeurByName("Easy Skill");
        Employeurs.getAllEmployeurs();
    }
}