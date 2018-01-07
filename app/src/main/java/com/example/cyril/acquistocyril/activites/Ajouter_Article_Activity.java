package com.example.cyril.acquistocyril.activites;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.cyril.acquistocyril.R;
import com.example.cyril.acquistocyril.db.SelectVilleDB;
import com.example.cyril.acquistocyril.donnee.Localite;

import java.util.ArrayList;

public class Ajouter_Article_Activity extends AppCompatActivity{
    String nom;
    String descriptif;
    double prix;
    boolean etat;
    String localite;
    boolean livraison;

    EditText inputNom;
    EditText inputDescriptif;
    EditText inputPrix;
    RadioButton radioEtatNeuf;
    RadioButton radioEtatUtilise;
    TableLayout tableLocalite;
    RadioButton radioEnvoye;
    RadioButton radioNonEnvoye;
    Button btnRetour;
    Button btnSupprimer;
    Button btnAjouter;

    ArrayList<Localite> listeLocalite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_article);

        tableLocalite = findViewById(R.id.tableLocalite);
        /*TableRow tr = new TableRow(this);
        TextView labelLocalite = new TextView(this);
        labelLocalite.setText(SelectVilleDB.getLocalites());
        tr.addView(labelLocalite);
        tableLocalite.addView(tr);*/

        try {
            SelectVilleDB selectDB = new  SelectVilleDB(Ajouter_Article_Activity.this);
            listeLocalite = selectDB.execute().get();
            int cpt=1;
            for (Localite l:listeLocalite) {
                TableRow tr = new TableRow(this);
                TextView labelLocalite = new TextView(this);
                labelLocalite.setText(l.getNomLocalite());
                labelLocalite.setTextSize(20);
                tr.addView(labelLocalite);
                tr.setId(cpt);
                tr.setGravity(Gravity.CENTER);
                tr.setClickable(true);
                tr.setOnClickListener(listener_localite);
                tableLocalite.addView(tr);
                cpt++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


        btnAjouter = findViewById(R.id.bouton_ajouter);
        //btnAjouter.setOnClickListener(listener_AjouterArticle);

        btnSupprimer = findViewById(R.id.bouton_effacer);
        btnSupprimer.setOnClickListener(listener_Supprimer);

        btnRetour = findViewById(R.id.bouton_retour);
        btnRetour.setOnClickListener(listener_Retour);
    }

    //Différents listeners
    View.OnClickListener listener_AjouterArticle = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    View.OnClickListener listener_Supprimer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            inputNom = findViewById(R.id.inputNom);
            inputNom.setText("");
            inputDescriptif = findViewById(R.id.input_Descriptif);
            inputDescriptif.setText("");
            inputPrix = findViewById(R.id.inputPrix);
            inputPrix.setText("");
        }
    };

    View.OnClickListener listener_Retour = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(Ajouter_Article_Activity.this, MainActivity.class);
            startActivity(myIntent);
        }
    };

    View.OnClickListener listener_localite = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            view.setSelected(true);
            view.setBackgroundColor(Color.GRAY);
        }
    };
}
