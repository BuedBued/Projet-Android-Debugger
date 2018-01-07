package com.example.cyril.acquistocyril.db;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.cyril.acquistocyril.activites.Ajouter_Article_Activity;
import com.example.cyril.acquistocyril.donnee.Localite;

import org.json.JSONArray;
import org.json.JSONObject;
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
        String url = "http://10.0.2.2/Android/addLocalite.php";
        String result = "";
        try {
            URL url_connexion = new URL(url);
            HttpURLConnection connexion = (HttpURLConnection) url_connexion.openConnection();
            connexion.setRequestMethod("GET");
            connexion.setConnectTimeout(5000);
            connexion.setReadTimeout(5000);

            connexion.setRequestProperty("Content-Type","application/json");
            connexion.setRequestProperty("charset","utf-8");

            connexion.connect();

            int codeReponse = connexion.getResponseCode();
            if(codeReponse==200){
                InputStream inputStream = connexion.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");

                JsonReader jsonReader = new JsonReader(inputStreamReader);
                jsonReader.beginArray();
                while(jsonReader.hasNext()){
                    Localite loc = new Localite(jsonReader.nextString());
                    Log.d("Test : ",loc.getNomLocalite());
                    listeLocalite.add(loc);
                }
                jsonReader.endArray();
                jsonReader.close();
            }
        } catch (Exception e) {
            Log.d("ERROR EXCEPTION : ",e.getMessage());
        }
        return listeLocalite;
    }

    /*@Override
    protected void onPostExecute(ArrayList<Localite> resultat){
        if(resultat !=null){

        }
        else{

        }
    }*/
}
