package com.example.cyril.acquistocyril.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cyril.acquistocyril.R;


public class MainActivity extends AppCompatActivity {
    Button btnAjoutArticle;
    Button btnRecherchePrix;
    Button btnRechercheVille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAjoutArticle = findViewById(R.id.bouton_Ajouter_Article);
        btnAjoutArticle.setOnClickListener(listener_AjoutArticle);

        btnRecherchePrix = findViewById(R.id.bouton_Rechercher_Par_Prix);
        btnRecherchePrix.setOnClickListener(listener_RecherchePrix);

        btnRechercheVille = findViewById(R.id.bouton_Recherche_Par_Ville);
        btnRechercheVille.setOnClickListener(listener_RechercheVille);
    }

    //Différents listeners
    private View.OnClickListener listener_AjoutArticle = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(MainActivity.this, Ajouter_Article_Activity.class);
            startActivity(myIntent);
        }
    };

    private View.OnClickListener listener_RecherchePrix = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(MainActivity.this, Ajouter_Article_Activity.class);
            startActivity(myIntent);
        }
    };

    private View.OnClickListener listener_RechercheVille = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(MainActivity.this, Ajouter_Article_Activity.class);
            startActivity(myIntent);
        }
    };
}
