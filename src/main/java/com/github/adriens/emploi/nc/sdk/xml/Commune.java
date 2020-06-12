package com.github.adriens.emploi.nc.sdk.xml;

import java.io.File;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "commune")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Commune {

    private String name;
    private Province province;
    private Localisation localisation;

    public Commune(){
        super();
    }
    public Commune(String name, Province province, Localisation localisation) {
        this.name = name;
        this.province = province;
        this.localisation = localisation;
    }

    @Override
    public String toString() {
        return "\n" + name + " Province "+ province + localisation;
    }

    public String getName() {
        return name;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}

