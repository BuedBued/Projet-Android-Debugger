package com.example.cyril.acquistocyril.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cyril.acquistocyril.R;
import com.example.cyril.acquistocyril.donnee.Article;
import com.example.cyril.acquistocyril.donnee.Localite;

import java.util.ArrayList;


public class Detail_Article_Activity extends AppCompatActivity {
    ArrayList<String> infoArticle;
    ArrayList<String> listeArticle;
    Button btnRetour;
    TextView inputNom;
    TextView inputDescriptif;
    TextView inputPrix;
    TextView inputLocalite;
    TextView inputLivraison;
    TextView inputEtat;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descriptif_article);

        btnRetour = findViewById(R.id.bouton_retour);
        btnRetour.setOnClickListener(listener_retour);

        inputNom = findViewById(R.id.inputNom);
        inputDescriptif = findViewById(R.id.inputDescriptif);
        inputPrix = findViewById(R.id.inputPrix);
        inputEtat = findViewById(R.id.inputEtat);
        inputLocalite = findViewById(R.id.inputLocalite);
        inputLivraison = findViewById(R.id.inputLivraison);

        try{
            Bundle params = getIntent().getExtras();
            listeArticle = params.getStringArrayList("liste");
            infoArticle = params.getStringArrayList("article");
            for(String s : listeArticle){
                Log.d("DATA",s);
            }

            int cptString = 0;
            String tmpNom ="";
            String tmpDesc = "";
            String tmpPrix = "";
            String tmpEtat = "";
            String tmpLivraison = "";
            String tmpLoc = "";
            for(String s : infoArticle) {
                cptString++;
                if (cptString == 1)
                    tmpNom = s;
                else if (cptString == 2)
                    tmpDesc = s;
                else if (cptString == 3)
                    tmpPrix = s;
                else if (cptString == 4)
                    tmpEtat = s;
                else if (cptString == 5)
                    tmpLoc = s;
                else
                    tmpLivraison = s;
            }

            inputNom.setText(tmpNom);
            inputDescriptif.setText(tmpDesc);
            inputPrix.setText(tmpPrix);
            inputLocalite.setText(tmpLoc);
            if(tmpEtat.equals("1"))
                inputEtat.setText(R.string.radio_etat_neuf);
            else
                inputEtat.setText(R.string.radio_etat_utilise);
            if(tmpLivraison.equals("1"))
                inputLivraison.setText(R.string.radio_envoye);
            else
                inputLivraison.setText(R.string.radio_main_propre);
        }
        catch(Exception e){
            Log.d("ERREUR DETAIL", e.getMessage());
            e.printStackTrace();
        }
    }

    View.OnClickListener listener_retour = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(Detail_Article_Activity.this,Resultat_Recherche_Activity.class);
            myIntent.putStringArrayListExtra("listeArticle",listeArticle);
            startActivity(myIntent);
        }
    };
}
