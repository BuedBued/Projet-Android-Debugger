package com.example.cyril.acquistocyril.db;

import com.example.cyril.acquistocyril.donnee.Localite;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class SelectVilleDB {
    public static String getLocalites() {
        ArrayList<Localite> listeLocalite = new ArrayList<Localite>();
        String url = "http://127.0.0.1/addLocalite.php";
        String result = "";
        try {
            URL url_connexion = new URL(url);
            HttpURLConnection connexion = (HttpURLConnection) url_connexion.openConnection();
            connexion.connect();
            InputStream inputStream =connexion.getInputStream();
            result = InputStreamOperation.InputStreamToString(inputStream);

            /*JSONObject jsonOb = new JSONObject(result);
            JSONArray array = new JSONArray(jsonOb.getString("localite"));

            for (int i = 0; i < array.length(); i++){
                JSONObject obj = new JSONObject(array.getString(i));
                Localite loc = new Localite(obj.getString("nomLocalite"));
                listeLocalite.add(loc);
            }*/
            //connexion.setRequestMethod("GET");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
