package com.example.cyril.acquistocyril.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.cyril.acquistocyril.R;

public class Rechercher_Article_Prix_Activity extends AppCompatActivity {
    Button btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_article_prix);

        btnRetour = findViewById(R.id.bouton_retour);
        btnRetour.setOnClickListener(listener_Retour);
    }

    View.OnClickListener listener_Retour = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(Rechercher_Article_Prix_Activity.this, MainActivity.class);
            startActivity(myIntent);
        }
    };
}
