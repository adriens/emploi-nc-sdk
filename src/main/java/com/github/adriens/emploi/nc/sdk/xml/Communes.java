package com.github.adriens.emploi.nc.sdk.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "communes")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Communes {
    
    @XmlElement(name = "commune")
    List<Commune> communes = null;

    public List<Commune> getCommunes() {
        return this.communes;
    }
    public Communes(){
        super();
    }
    @Override
    public String toString() {
        return "[Liste des Communes=" + communes + "]";
    }
}