package com.example.cyril.acquistocyril.db;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.cyril.acquistocyril.activites.Ajouter_Article_Activity;
import com.example.cyril.acquistocyril.donnee.Localite;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class SelectVilleDB extends AsyncTask<Void,Void,ArrayList<Localite>> {
    Ajouter_Article_Activity activite;
    ArrayList<Localite> listeLocalite = new ArrayList<Localite>();

    public SelectVilleDB(Ajouter_Article_Activity activite){this.activite = activite;}

    @Override
    protected ArrayList<Localite> doInBackground(Void...arg0){
        String result = "";
        try {
            URL url_connexion = new URL("http://10.0.2.2/Android/selectLocalitePDO.php");
            HttpURLConnection connexion = (HttpURLConnection) url_connexion.openConnection();
            connexion.setRequestMethod("GET");
            connexion.setDoInput(true);
            //connexion.setDoOutput(true);
            connexion.setRequestProperty("Content-Type","text/plain");
            connexion.setRequestProperty("charset","utf-8");

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
                Localite tmp = new Localite(json.getJSONObject(i).getString("nom"));
                listeLocalite.add(tmp);
            }

        } catch (Exception e) {
            Log.d("ERROR EXCEPTION : ",e.getMessage());
            e.printStackTrace();
        }
        return listeLocalite;
    }
}
