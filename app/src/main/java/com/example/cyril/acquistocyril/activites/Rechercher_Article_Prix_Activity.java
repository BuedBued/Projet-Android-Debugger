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
import com.example.cyril.acquistocyril.db.RechercheArticlePrixDB;
import com.example.cyril.acquistocyril.donnee.Article;

import java.util.ArrayList;

public class Rechercher_Article_Prix_Activity extends AppCompatActivity {
    Button btnRetour;
    Button btnRecherche;
    EditText labelPrixMin;
    EditText labelPrixMax;
    TextView labelErreur;
    String prixMin, prixMax;
    ArrayList<String> listeArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_article_prix);

        btnRetour = findViewById(R.id.bouton_retour);
        btnRetour.setOnClickListener(listener_Retour);

        btnRecherche = findViewById(R.id.bouton_recherche);
        btnRecherche.setOnClickListener(listener_Recherche);
    }

    View.OnClickListener listener_Recherche = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            labelPrixMax = findViewById(R.id.labelPrixMax);
            labelPrixMin = findViewById(R.id.labelPrixMin);
            labelErreur = findViewById(R.id.labelErreur);

            if(labelPrixMin.getText().toString().isEmpty() || labelPrixMax.getText().toString().isEmpty()){
                labelErreur.setText(R.string.erreur_champ_vide);
            }
            else{
                prixMin = labelPrixMin.getText().toString();
                prixMax = labelPrixMax.getText().toString();
                String param = "min="+prixMin+"&max="+prixMax;
                RechercheArticlePrixDB recherche = new RechercheArticlePrixDB(Rechercher_Article_Prix_Activity.this);
                try{
                    listeArticle = recherche.execute(param).get();
                    Intent myIntent = new Intent (Rechercher_Article_Prix_Activity.this,Resultat_Recherche_Activity.class);
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
            Intent myIntent = new Intent(Rechercher_Article_Prix_Activity.this, MainActivity.class);
            startActivity(myIntent);
        }
    };
}
