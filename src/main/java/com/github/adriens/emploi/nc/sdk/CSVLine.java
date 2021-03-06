package com.github.adriens.emploi.nc.sdk;

public class CSVLine {

    private String url;
    private String numeroOffre;
    private String titreOffre;
    private String nomEntreprise;
    private String aPourvoirLe;
    private String communeEmploi;
    private String experience;
    private String niveauFormation;
    private String diplome;
    private String nbPostes;
    private String datePublication;
    private String typeContrat;

    private String province;
    private String latitude;
    private String longitude;
    private String urlgooglemap;
    public char doubleQuote = '\"';

    public String getNumeroOffre() {
        return numeroOffre;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        datePublication = datePublication.replaceAll("-","/");
        datePublication.subSequence(0,10);
        this.datePublication = datePublication.subSequence(0,10).toString();
    }

    public void setNumeroOffre(String numeroOffre) {
        this.numeroOffre = numeroOffre;
    }

    public String getNbPostes() {
        return nbPostes;
    }

    public void setNbPostes(String nbPostes) {
        this.nbPostes = nbPostes;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = this.doubleQuote+diplome+this.doubleQuote;
    }

    public String getNiveauFormation() {
        return niveauFormation;
    }

    public void setNiveauFormation(String niveauFormation) {
        this.niveauFormation = this.doubleQuote+niveauFormation+this.doubleQuote;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = this.doubleQuote+experience+this.doubleQuote;
    }

    public String getCommuneEmploi() {
        return communeEmploi;
    }

    public void setCommuneEmploi(String communeEmploi) {
        communeEmploi = communeEmploi.replaceAll("-", " ");
        communeEmploi = communeEmploi.replaceAll("(L')", "");
        communeEmploi = communeEmploi.replaceAll("(Le)", "");
        this.communeEmploi = this.doubleQuote+communeEmploi+this.doubleQuote;
    }

    public String getaPourvoirLe() {
        return aPourvoirLe;
    }

    public void setaPourvoirLe(String aPourvoirLe) {
        String year = (String) aPourvoirLe.subSequence(6,10);
        String month = aPourvoirLe.subSequence(2,6).toString();
        String day = aPourvoirLe.subSequence(0,2).toString();
        this.aPourvoirLe = year+month+day;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = this.doubleQuote+nomEntreprise+this.doubleQuote;
    }

    public String getTitreOffre() {
        return titreOffre;
    }

    public void setTitreOffre(String titreOffre) {
        this.titreOffre = this.doubleQuote+titreOffre+this.doubleQuote;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = this.doubleQuote+typeContrat+this.doubleQuote;
    }
}