package com.example.cyril.acquistocyril.db;

import android.os.AsyncTask;
import android.util.Log;

import com.example.cyril.acquistocyril.activites.Ajouter_Article_Activity;
import com.example.cyril.acquistocyril.donnee.Article;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AjouterArticleDB extends AsyncTask<Article,Void,Integer> {
    Ajouter_Article_Activity activite;
    public AjouterArticleDB(Ajouter_Article_Activity activite){this.activite = activite;}

    @Override
    protected Integer doInBackground(Article... articles){
        Article article = articles[0];
        int result = 0;
        try{
            URL url_connexion = new URL("http://10.0.2.2/Android/AjouterArticle.php");
            HttpURLConnection connexion = (HttpURLConnection) url_connexion.openConnection();
            connexion.setRequestMethod("POST");
            connexion.setDoInput(true);
            connexion.setDoOutput(true);

            OutputStream outputStream = connexion.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
            BufferedWriter bf = new BufferedWriter(outputStreamWriter);
            String param = article.toParam();
            bf.write(param);
            bf.flush();
            bf.close();

            InputStream inputStream = connexion.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bfr = new BufferedReader(inputStreamReader);
            result = Integer.parseInt(bfr.readLine());
        }
        catch(Exception e){
            Log.d("DEBUG : ", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
