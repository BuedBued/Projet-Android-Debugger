package com.example.cyril.acquistocyril.donnee;


public class Article {
    private String nom;
    private String descriptif;
    private double prix;
    private boolean etat;
    private Localite localite;
    private boolean livraison;

    public Article(){}
    public Article(String nom, String descriptif, double prix, boolean etat, Localite localite, boolean livraison){
        this.nom = nom;
        this.descriptif = descriptif;
        this.prix = prix;
        this.etat = etat;
        this.localite = localite;
        this.livraison = livraison;
    }

    public String getNom(){return this.nom;}
    public void setNom(String nom){this.nom = nom;}

    public String getDescriptif(){return this.descriptif;}
    public void  setDescriptif(String descriptif){this.descriptif = descriptif;}

    public double getPrix() {return prix;}
    public void setPrix(double prix){this.prix = prix;}

    public boolean getEtat() {return etat;}
    public void setEtat(boolean etat){this.etat = etat;}

    public Localite getLocalite() {return localite;}
    public void setLocalite(Localite localite) {this.localite = localite;}

    public boolean getLivraison() {return livraison;}
    public void setLivraison(boolean livraison) {this.livraison = livraison;}
}
