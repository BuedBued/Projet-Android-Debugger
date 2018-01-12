package com.example.cyril.acquistocyril.activites;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.cyril.acquistocyril.R;
import com.example.cyril.acquistocyril.donnee.Article;
import com.example.cyril.acquistocyril.donnee.Localite;

import java.util.ArrayList;

public class Resultat_Recherche_Activity extends AppCompatActivity {
    ArrayList<Article> listeArticle = new ArrayList<Article>();
    ArrayList<String> listeArticleParam;
    int cptArticle = 0;
    int borneInf = 0;
    int borneSup = 0;
    RadioButton croissant;
    RadioButton decroissant;
    Button btnRetour;
    Button btnPageSuivante;
    Button btnPagePrecedente;
    TableLayout listeRecherche;
    FrameLayout layoutPS;
    FrameLayout layoutPP;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_recherche);

        btnRetour = findViewById(R.id.bouton_retour);
        btnRetour.setOnClickListener(listener_retour);

        btnPageSuivante = findViewById(R.id.bouton_page_suivante);
        btnPageSuivante.setOnClickListener(listener_PS);

        btnPagePrecedente = findViewById(R.id.bouton_page_precedente);
        btnPagePrecedente.setOnClickListener(listener_PP);

        listeRecherche = findViewById(R.id.liste_recherche);

        layoutPS = findViewById(R.id.layout_page_suivante);
        layoutPP = findViewById(R.id.layout_page_precedente);

        croissant = findViewById(R.id.radioCroissant);
        croissant.setOnClickListener(listener_croissant);
        decroissant = findViewById(R.id.radioDecroissant);
        decroissant.setOnClickListener(listener_decroissant);

        try {
            Bundle params = getIntent().getExtras();
            listeArticleParam = params.getStringArrayList("listeArticle");

            int cptString = 0;
            String tmpNom ="";
            String tmpDesc = "";
            double tmpPrix = 0;
            int tmpEtat = 0;
            int tmpLivraison = 0;
            Localite tmpLoc = new Localite();
            Article tmp;
            for(String s : listeArticleParam){
                cptString++;
                 if (cptString == 1)
                    tmpNom = s;
                 else if (cptString == 2)
                    tmpDesc = s;
                 else if (cptString == 3)
                    tmpPrix = Double.parseDouble(s);
                 else if (cptString == 4)
                    tmpEtat = Integer.parseInt(s);
                 else if (cptString == 5)
                    tmpLivraison = Integer.parseInt(s);
                 else if (cptString == 6) {
                     tmpLoc = new Localite(s);
                     tmp = new Article(tmpNom, tmpDesc, tmpPrix, tmpEtat, tmpLoc, tmpLivraison);
                     listeArticle.add(tmp);
                     cptArticle++;
                     cptString = 0;
                 }
            }
            layoutPP.setVisibility(FrameLayout.GONE);
            if(cptArticle<5){
                layoutPS.setVisibility(FrameLayout.GONE);
                construireTableau(borneInf,cptArticle);
            }
            else{
                borneSup = 5;
                construireTableau(borneInf,borneSup);
            }

        }
        catch(Exception e){
            Log.d("ERREUR RECHERCHE",e.getMessage());
            e.printStackTrace();
        }
    }

    View.OnClickListener listener_PS = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            borneInf+=5;
            if(cptArticle-borneSup<5) {
                borneSup += cptArticle-borneSup;
                layoutPS.setVisibility(FrameLayout.GONE);
            }
            else
                borneSup+=5;
            construireTableau(borneInf,borneSup);
            layoutPP.setVisibility(FrameLayout.VISIBLE);
        }
    };

    View.OnClickListener listener_PP = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            borneSup = borneInf;
            borneInf -=5;
            construireTableau(borneInf,borneSup);
            layoutPS.setVisibility(FrameLayout.VISIBLE);
            if(borneInf==0)
                layoutPP.setVisibility(FrameLayout.GONE);
        }
    };

    View.OnClickListener listener_retour = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(Resultat_Recherche_Activity.this,MainActivity.class);
            startActivity(myIntent);
        }
    };

    View.OnClickListener listener_croissant = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                trierTableau(true);
                construireTableau(borneInf,borneSup);
        }
    };

    View.OnClickListener listener_decroissant = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            trierTableau(false);
            construireTableau(borneInf,borneSup);
        }
    };

     View.OnClickListener listener_detail = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int i = view.getId();
            Article aEnvoyer = listeArticle.get(i);
            ArrayList<String> param = new ArrayList<>();
            param.add(aEnvoyer.getNom());
            param.add(aEnvoyer.getDescriptif());
            Double prix = aEnvoyer.getPrix();
            param.add(prix.toString());
            Integer etat = aEnvoyer.getEtat();
            Integer livraison = aEnvoyer.getLivraison();
            Localite loc = aEnvoyer.getLocalite();
            param.add(etat.toString());
            param.add(loc.getNomLocalite());
            param.add(livraison.toString());

            Intent myIntent = new Intent(Resultat_Recherche_Activity.this, Detail_Article_Activity.class);
            myIntent.putStringArrayListExtra("article", param);
            myIntent.putStringArrayListExtra("liste",listeArticleParam);
            startActivity(myIntent);

        }
    };

    public void construireTableau(int debut, int fin){
        listeRecherche.removeAllViews();
        for(int i=debut; i<fin;i++){
            TableRow tr = new TableRow(Resultat_Recherche_Activity.this);
            tr.setGravity(Gravity.CENTER);
            tr.setPadding(0,0,0,30);
            tr.setId(i);
            TextView nom = new TextView(Resultat_Recherche_Activity.this);
            nom.setPadding(0,0,20,0);
            Article a = (Article)listeArticle.get(i);
            nom.setText(a.getNom());
            nom.setTextSize(20);
            TextView prix = new TextView(Resultat_Recherche_Activity.this);
            Double tprix = a.getPrix();
            prix.setText(tprix.toString());
            prix.setTextSize(20);
            prix.setPadding(0,0,20,0);
            tr.addView(nom);
            tr.addView(prix);
            tr.setClickable(true);
            tr.setOnClickListener(listener_detail);
            listeRecherche.addView(tr);
        }
    }

    public void trierTableau(boolean isCroissant){
        Article tmp;
        if(!isCroissant){
            for (int i=0;i<cptArticle;i++){
                for (int j=i;j<cptArticle;j++){
                    if(listeArticle.get(i).getPrix()<listeArticle.get(j).getPrix()){
                        tmp = listeArticle.get(i);
                        listeArticle.set(i,listeArticle.get(j));
                        listeArticle.set(j,tmp);
                    }
                }
            }
        }
        else{
            for (int i=0;i<cptArticle;i++){
                for (int j=i;j<cptArticle;j++){
                    if(listeArticle.get(i).getPrix()>listeArticle.get(j).getPrix()){
                        tmp = listeArticle.get(i);
                        listeArticle.set(i,listeArticle.get(j));
                        listeArticle.set(j,tmp);
                    }
                }
            }
        }
    }
}
