package com.example.cyril.acquistocyril.activites;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.cyril.acquistocyril.R;
import com.example.cyril.acquistocyril.donnee.Article;
import com.example.cyril.acquistocyril.donnee.Localite;

import java.util.ArrayList;

public class Resultat_Recherche_Activity extends AppCompatActivity {
    ArrayList<Article> listeArticle = new ArrayList<Article>();
    int cptArticle = 0;
    int borneInf = 0;
    int borneSup = 0;
    Button btnRetour;
    Button btnPageSuivante;
    Button btnPagePrecedente;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_recherche);

        btnRetour = findViewById(R.id.bouton_retour);
        btnRetour.setOnClickListener(listener_retour);

        btnPageSuivante = findViewById(R.id.bouton_page_suivante);
        btnPageSuivante.setOnClickListener(listener_PS);

        btnPagePrecedente = findViewById(R.id.bouton_page_precedente);
        btnPagePrecedente.setOnClickListener(listener_PP);

        try {
            Bundle params = getIntent().getExtras();
            ArrayList<String> test = params.getStringArrayList("listeArticle");

            int cptString = 0;
            String tmpNom ="";
            String tmpDesc = "";
            double tmpPrix = 0;
            int tmpEtat = 0;
            int tmpLivraison = 0;
            Localite tmpLoc = new Localite();
            Article tmp;
            for(String s : test){
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
            FrameLayout layoutPP = findViewById(R.id.layout_page_precedente);
            layoutPP.setVisibility(FrameLayout.GONE);
            if(cptArticle<5){
                FrameLayout layoutPS = findViewById(R.id.layout_page_suivante);
                layoutPS.setVisibility(FrameLayout.GONE);
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

        }
    };

    View.OnClickListener listener_PP = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    View.OnClickListener listener_retour = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(Resultat_Recherche_Activity.this,MainActivity.class);
            startActivity(myIntent);
        }
    };
}
