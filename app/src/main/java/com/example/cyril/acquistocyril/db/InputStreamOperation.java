package com.example.cyril.acquistocyril.db;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cyril on 06-01-18.
 */

public class InputStreamOperation {
    // Cette classe est tirée d'un site sur internet

    /**
     * @param in : buffer with the php result
     * @param bufSize : size of the buffer
     * @return : the string corresponding to the buffer
     */

    public static String InputStreamToString (InputStream in, int bufSize) {
        final StringBuilder out = new StringBuilder();
        final byte[] buffer = new byte[bufSize];
        try {
            for (int ctr; (ctr = in.read(buffer)) != -1;) {
                out.append(new String(buffer, 0, ctr));
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot convert stream to string", e);
        }
        // On retourne la chaine contenant les donnees de l'InputStream
        return out.toString();
    }

    /**
     * @param in : buffer with the php result
     * @return : the string corresponding to the buffer
     */
    public static String InputStreamToString (InputStream in) {
        // On appelle la methode precedente avec une taille de buffer par défaut
        return InputStreamToString(in, 1024);
    }
}
