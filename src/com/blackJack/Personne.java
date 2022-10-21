package com.blackJack;

import java.util.ArrayList;
import java.util.Iterator;

public class Personne {
    private ArrayList<Carte> listCartes;
    public Personne() {
        this.listCartes = new ArrayList<>();
    }

    public ArrayList<Carte> getListCartes() {
        return listCartes;
    }

    public void setListCartes(ArrayList<Carte> listCartes) {
        this.listCartes = listCartes;
    }

    public boolean addCartes(ArrayList<Carte> cartes){
        return listCartes.addAll(cartes);
    }
    @Override
    public String toString() {
        String print = "Personne{";
        for (int i = 0; i < this.listCartes.size(); i++) {
            print += "\n" +this.listCartes.get(i).toString();
        }
        print += "}";
        return print;
    }
    public int sumPoint(){
        int as = 0;
        int totalePoint = 0;
        Iterator<Carte> it = this.listCartes.iterator();
        while (it.hasNext()){
            Carte carte = it.next();
            switch (carte.getHauteur()){
                case UN :
                    as++;
                    break;
                case DEUX:
                    totalePoint+=2;
                    break;
                case TROIS:
                    totalePoint+=3;
                    break;
                case QUATRE:
                    totalePoint+=4;
                    break;
                case CINQ:
                    totalePoint+=5;
                    break;
                case SIX:
                    totalePoint+=6;
                    break;
                case SEPT:
                    totalePoint+=7;
                    break;
                case HUIT:
                    totalePoint+=8;
                    break;
                case NEUF:
                    totalePoint+=9;
                    break;
                default:
                    totalePoint+=10;
                    break;
            }
        }
        if(as != 0)
            if (totalePoint<=10){
                totalePoint += 11;
            }else totalePoint += as;
        return totalePoint;
    }

    public void clearList(){
        this.listCartes.clear();
    }
    public boolean addCatres(ArrayList<Carte> cartes) {
        return this.listCartes.addAll(cartes);
    }
}
