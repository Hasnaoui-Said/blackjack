package com.blackJack;

import java.util.ArrayList;
import java.util.Random;

public class BlackJack {
    ArrayList<Carte> cartes;
    ArrayList<Carte> defaussers;
    public BlackJack() {
        this.defaussers = new ArrayList<>();
        this.cartes = new ArrayList<>();
    }
    public ArrayList<Carte> getCartes() {
        return cartes;
    }
    public void setCartes(ArrayList<Carte> cartes) {
        this.cartes = cartes;
    }
    public void creer_cartes(){
        for (Hauteur hauteur: Hauteur.values()){
            for (Coulour coulour: Coulour.values()){
                this.cartes.add(new Carte(hauteur, coulour));
            }
        }
    }
    public Carte extraire_ieme_carte(int ieme) {
        Carte ieme_carte = cartes.get(ieme);
        this.cartes.remove(ieme);
        return ieme_carte;
    }
    public Carte tirer_une_carte(){
        Random random = new Random();
        int rand = random.nextInt((this.cartes.size()));
        Carte iem_carte = extraire_ieme_carte(rand);
        return iem_carte;
    }
    public void melanger_jeu_cartes(){
        ArrayList<Carte> c = new ArrayList<>();
        Carte carte = tirer_une_carte();
        Random random = new Random();
        int cout = this.cartes.size();
        for (int i = 0; i<cout; i++){
            int rand = random.nextInt(this.cartes.size());
            c.add(cartes.get(rand));
            this.cartes.remove(rand);
        }
        c.add(carte);
        this.setCartes(c);
    }
    public ArrayList<Carte> piocherNCartes(int n){
        ArrayList<Carte> c = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            c.add(this.cartes.get(i));
            this.cartes.remove(i);
        }
        return c;
    }
    public void defausserCartes(){
        this.cartes.addAll(this.defaussers);
        this.defaussers.clear();
    }
    public void addDefausserCartes(ArrayList<Carte> defaussers){
        this.defaussers.addAll(defaussers);
    }


    @Override
    public String toString() {
        String cartes = "";
        int i = 1;
        for (Carte carte: this.cartes){
            cartes += i + " - "+carte.getHauteur()+" - " + carte.getCoulour() + "\n";
            i++;
        }
        return cartes;
    }
}
