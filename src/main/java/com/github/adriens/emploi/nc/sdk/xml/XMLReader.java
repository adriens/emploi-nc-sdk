package com.github.adriens.emploi.nc.sdk.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLReader {



    public static Communes read() {

        File xmlFile = new File("communes.xml");
        JAXBContext jaxbContext;
        Communes communes = null ;
        try {
            jaxbContext = JAXBContext.newInstance(Communes.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
 
            communes = (Communes) jaxbUnmarshaller.unmarshal(xmlFile);

            System.out.println(communes);
            
        } catch (JAXBException e) {
            e.printStackTrace();
        }
 
        
        return communes;

    }
}