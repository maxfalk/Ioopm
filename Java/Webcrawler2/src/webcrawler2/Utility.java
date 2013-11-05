/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webcrawler2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Methods for reading and making an URL.
 * @author Max
 */
public class Utility {
   /**
    * Converts a url in string format to an url in URL format
    * @param s the string to convert
    * @return the string s as an URL.
    */
	
	public static URL urlify(String s) {
        // Crazy ugly coding
        try { return new URL(s); } catch (MalformedURLException e) {}
        return null;
    }
	/**
	 * Reads the contents at the URL location.
	 * @param url the URL of the location to read at
	 * @return the text read at url.
	 */
    public static String readURL(URL url) {
        String inputLine;
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            
            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }

        } catch (FileNotFoundException fnfe) {
        } catch (IOException ioe) {
        } finally {
            try { if (in != null) {
                in.close();
            } } catch (IOException ignore) {}
        }
        
        return result.toString();
    }

    private Utility() {
    }
    
    
    
    
}