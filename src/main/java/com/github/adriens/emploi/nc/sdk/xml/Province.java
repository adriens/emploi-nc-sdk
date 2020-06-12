package com.github.adriens.emploi.nc.sdk.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "province")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Province {

    private String name;

    public Province(){
        super();
    }
    public Province(String name) {
        this.name = name;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}