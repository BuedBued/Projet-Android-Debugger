package com.example.cyril.acquistocyril.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cyril.acquistocyril.R;
import com.example.cyril.acquistocyril.db.RechercheArticleLocaliteDB;
import com.example.cyril.acquistocyril.donnee.Article;

import java.util.ArrayList;

public class Rechercher_Article_Ville_Activity extends AppCompatActivity {
    EditText inputLocalite;
    TextView labelErreur;
    Button btnRetour;
    Button btnRecherche;

    String localite;
    ArrayList<String> listeArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_article_ville);

        btnRecherche = findViewById(R.id.bouton_recherche);
        btnRecherche.setOnClickListener(listener_recherche);

        btnRetour = findViewById(R.id.bouton_retour);
        btnRetour.setOnClickListener(listener_Retour);
    }

    View.OnClickListener listener_recherche = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            inputLocalite = findViewById(R.id.inputLocalite);
            labelErreur = findViewById(R.id.labelErreur);

            if(inputLocalite.getText().toString().isEmpty()){
                labelErreur.setText(R.string.erreur_champ_vide);
            }
            else{
                localite = inputLocalite.getText().toString();
                String param = "localite="+localite;
                RechercheArticleLocaliteDB recherche = new RechercheArticleLocaliteDB(Rechercher_Article_Ville_Activity.this);
                try{
                    listeArticle = recherche.execute(param).get();
                    Intent myIntent = new Intent (Rechercher_Article_Ville_Activity.this,Resultat_Recherche_Activity.class);
                    myIntent.putStringArrayListExtra("listeArticle",listeArticle);
                    startActivity(myIntent);
                }
                catch(Exception e){
                    Log.d("Probleme Recherche",e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    };

    View.OnClickListener listener_Retour = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(Rechercher_Article_Ville_Activity.this, MainActivity.class);
            startActivity(myIntent);
        }
    };
}