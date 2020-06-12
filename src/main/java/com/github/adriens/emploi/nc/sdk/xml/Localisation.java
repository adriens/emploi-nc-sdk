package com.github.adriens.emploi.nc.sdk.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "localisation")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Localisation {

    private String lat;
    private String longitude;
    private String urlgooglemap;

    public Localisation(){
        super();
    }
    public Localisation(String lat, String longitude, String urlgooglemap) {
        this.lat = lat ;
        this.longitude = longitude;
        this.urlgooglemap = urlgooglemap;
    }

    @Override
    public String toString() {
        return "\nLocalisation [lat=" + lat + " | longitude="+ longitude+ "]";
    }

    public String getUrlgooglemap() {
        return urlgooglemap;
    }

    public void setUrlgooglemap(String urlgooglemap) {
        this.urlgooglemap = urlgooglemap;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}