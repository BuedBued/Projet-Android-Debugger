package com.example.cyril.acquistocyril.db;

import android.os.AsyncTask;
import android.util.Log;

import com.example.cyril.acquistocyril.activites.Ajouter_Article_Activity;
import com.example.cyril.acquistocyril.activites.Rechercher_Article_Prix_Activity;
import com.example.cyril.acquistocyril.donnee.Article;
import com.example.cyril.acquistocyril.donnee.Localite;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class RechercheArticlePrixDB extends AsyncTask<String,Void,ArrayList<String>> {
    Rechercher_Article_Prix_Activity activite;
    private ArrayList<String> listeArticle = new ArrayList<String>();

    public RechercheArticlePrixDB(Rechercher_Article_Prix_Activity activite){this.activite = activite;}

    @Override
    protected ArrayList<String> doInBackground(String...str){
        String param = str[0];
        String result = "";
        try {
            URL url_connexion = new URL("http://10.0.2.2/Android/rechercheArticlePrix.php");
            HttpURLConnection connexion = (HttpURLConnection) url_connexion.openConnection();
            connexion.setRequestMethod("POST");
            connexion.setDoInput(true);
            connexion.setDoOutput(true);

            OutputStream outputStream = connexion.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
            BufferedWriter bfw = new BufferedWriter(outputStreamWriter);
            bfw.write(param);
            bfw.flush();
            bfw.close();

            InputStream inputStream = connexion.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bf = new BufferedReader(inputStreamReader);
            String ligne = "";
            while((ligne = bf.readLine())!=null){
                result += ligne;
            }
            bf.close();
            inputStream.close();
            connexion.disconnect();

            JSONArray json = new JSONArray(result);
            for (int i = 0; i<json.length(); i++){
                listeArticle.add(json.getJSONObject(i).getString("nom"));
                listeArticle.add(json.getJSONObject(i).getString("descriptif"));
                Double prix = json.getJSONObject(i).getDouble("prix");
                listeArticle.add(prix.toString());
                Integer etat = json.getJSONObject(i).getInt("etat");
                listeArticle.add(etat.toString());
                Integer livraison = json.getJSONObject(i).getInt("livraison");
                listeArticle.add(livraison.toString());
                listeArticle.add(json.getJSONObject(i).getString("localite"));
            }

        } catch (Exception e) {
            Log.d("ERROR EXCEPTION : ",e.getMessage());
            e.printStackTrace();
        }
        return listeArticle;
    }
}
