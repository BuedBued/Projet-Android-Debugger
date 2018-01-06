package com.example.cyril.acquistocyril.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cyril.acquistocyril.R;

public class Rechercher_Article_Ville_Activity extends AppCompatActivity {
    EditText inputLocalite;
    Button btnRetour;
    Button btnRecherche;

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
            if(inputLocalite.getText().toString().isEmpty()){
                Toast.makeText(Rechercher_Article_Ville_Activity.this,R.string.erreur_encodage_ville_recherche,Toast.LENGTH_SHORT).show();
            }
            else{

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