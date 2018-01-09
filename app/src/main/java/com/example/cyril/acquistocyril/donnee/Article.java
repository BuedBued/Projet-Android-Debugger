package com.example.cyril.acquistocyril.donnee;


public class Article {
    private String nom;
    private String descriptif;
    private double prix;
    private int etat;
    private Localite localite;
    private int livraison;

    public Article(){}
    public Article(String nom, String descriptif, double prix, int etat, Localite localite, int livraison){
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

    public int getEtat() {return etat;}
    public void setEtat(int etat){this.etat = etat;}

    public Localite getLocalite() {return localite;}
    public void setLocalite(Localite localite) {this.localite = localite;}

    public int getLivraison() {return livraison;}
    public void setLivraison(int livraison) {this.livraison = livraison;}

    public String toParam(){
        return "nom="+nom+"&descriptif="+descriptif+"&prix="+prix+"&etat="+etat+"&localite="+localite.getNomLocalite()+"&livraison="+livraison;
    }
}
