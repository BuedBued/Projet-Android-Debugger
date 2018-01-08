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
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.cyril.acquistocyril.R;
import com.example.cyril.acquistocyril.db.AjouterArticleDB;
import com.example.cyril.acquistocyril.db.SelectVilleDB;
import com.example.cyril.acquistocyril.donnee.Article;
import com.example.cyril.acquistocyril.donnee.Localite;

import java.util.ArrayList;

public class Ajouter_Article_Activity extends AppCompatActivity{
    String nom;
    String descriptif;
    double prix;
    boolean etat;
    Localite localite;
    boolean livraison;

    TextView erreur;
    EditText inputNom;
    EditText inputDescriptif;
    EditText inputPrix;
    RadioButton radioEtatNeuf;
    RadioButton radioEtatUtilise;
    RadioGroup listeRadio;
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

        inputNom = findViewById(R.id.inputNom);
        inputPrix = findViewById(R.id.inputPrix);
        inputDescriptif = findViewById(R.id.input_Descriptif);
        erreur = findViewById(R.id.erreur);
        radioEtatNeuf = findViewById(R.id.radioNew);
        radioEtatUtilise = findViewById(R.id.radioUsed);
        radioEnvoye = findViewById(R.id.radioEnvoye);
        radioNonEnvoye = findViewById(R.id.radioNonEnvoye);
        listeRadio = (RadioGroup)findViewById(R.id.listeLocalite);

        try {
            SelectVilleDB selectDB = new  SelectVilleDB(Ajouter_Article_Activity.this);
            listeLocalite = selectDB.execute().get();
            for (Localite l:listeLocalite) {
                RadioButton rb = new RadioButton(this);
                rb.setText(l.getNomLocalite());
                rb.setTextSize(20);
                listeRadio.addView(rb);
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
            if(inputNom.getText().toString().equals("")){
                erreur.setText(R.string.erreur_nom_vide);
            }
            else if(inputPrix.getText().toString().equals("")){
                erreur.setText(R.string.erreur_prix_vide);
            }
            else{
                nom = inputNom.getText().toString();
                descriptif = inputDescriptif.getText().toString();
                try{
                    prix = Double.parseDouble(inputPrix.getText().toString());
                }
                catch (Exception e){
                    erreur.setText(R.string.erreur_prix_incorrect);
                }
                if(prix<=0)
                    erreur.setText(R.string.erreur_prix_incorrect);
                else{
                    int cpt = 0;
                    boolean checked = false;
                    RadioButton rb;
                    do{
                        rb = (RadioButton)listeRadio.getChildAt(cpt);
                        if(rb.isChecked())
                            checked = true;
                        cpt++;
                    }
                    while(cpt<listeRadio.getChildCount() && !checked);
                    if(!checked)
                        erreur.setText(R.string.erreur_ville_vide);
                    else{
                        localite = new Localite (rb.getText().toString());
                        if(!radioEtatNeuf.isChecked() && !radioEtatUtilise.isChecked())
                            erreur.setText(R.string.erreur_etat_vide);
                        else if (!radioEnvoye.isChecked() && radioNonEnvoye.isChecked())
                            erreur.setText(R.string.erreur_envoie_vide);
                        else{
                            if(radioEnvoye.isChecked())
                                livraison = true;
                            else
                                livraison = false;
                            if(radioEtatNeuf.isChecked())
                                etat = true;
                            else
                                etat = false;
                            Article a = new Article(nom,descriptif,prix,etat,localite,livraison);
                            AjouterArticleDB add = new AjouterArticleDB(Ajouter_Article_Activity.this);
                            try{
                                int res = add.execute(a).get();
                                if (res ==1){
                                    Intent myIntent = new Intent(Ajouter_Article_Activity.this, MainActivity.class);
                                    startActivity(myIntent);
                                }
                                else{
                                    if (res==0)
                                        erreur.setText("Erreur dans la DB");
                                    else if(res==2)
                                        erreur.setText("Localite non trouvee dans la DB");
                                    else
                                        erreur.setText("Article déjà existant");
                                }
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
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
}
