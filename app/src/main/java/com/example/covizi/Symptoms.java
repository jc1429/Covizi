package com.example.covizi;

public class Symptoms {

    public String ic;

    public Symptoms(String ic, String fever, String cough, String shortbreath, String fatigue, String muscleache, String losstaste, String vomit, String positive, String closecontact, String travel) {
        this.ic = ic;
        this.fever = fever;
        this.cough = cough;
        this.shortbreath = shortbreath;
        this.fatigue = fatigue;
        this.muscleache = muscleache;
        this.losstaste = losstaste;
        this.vomit = vomit;
        this.positive = positive;
        this.closecontact = closecontact;
        this.travel = travel;
    }

    public String fever;
    public String cough;
    public String shortbreath;
    public String fatigue;
    public String muscleache;
    public String losstaste;
    public String vomit;
    public String positive;
    public String closecontact;
    public String travel;

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    public String getCough() {
        return cough;
    }

    public void setCough(String cough) {
        this.cough = cough;
    }

    public String getShortbreath() {
        return shortbreath;
    }

    public void setShortbreath(String shortbreath) {
        this.shortbreath = shortbreath;
    }

    public String getFatigue() {
        return fatigue;
    }

    public void setFatigue(String fatigue) {
        this.fatigue = fatigue;
    }

    public String getMuscleache() {
        return muscleache;
    }

    public void setMuscleache(String muscleache) {
        this.muscleache = muscleache;
    }

    public String getLosstaste() {
        return losstaste;
    }

    public void setLosstaste(String losstaste) {
        this.losstaste = losstaste;
    }

    public String getVomit() {
        return vomit;
    }

    public void setVomit(String vomit) {
        this.vomit = vomit;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getClosecontact() {
        return closecontact;
    }

    public void setClosecontact(String closecontact) {
        this.closecontact = closecontact;
    }

    public String getTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }


}
