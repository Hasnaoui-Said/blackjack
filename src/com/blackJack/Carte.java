package com.blackJack;

public class Carte {
    private Hauteur hauteur;
    private Coulour coulour;

    Carte(Hauteur hauteur, Coulour coulour) {
        this.hauteur = hauteur;
        this.coulour = coulour;
    }

    Carte() {}

    public Hauteur getHauteur() {
        return hauteur;
    }

    public void setHauteur(Hauteur hauteur) {
        this.hauteur = hauteur;
    }

    public Coulour getCoulour() {
        return coulour;
    }

    public void setCoulour(Coulour coulour) {
        this.coulour = coulour;
    }

    @Override
    public String toString() {
        return  hauteur +" - " + coulour;
    }
}
