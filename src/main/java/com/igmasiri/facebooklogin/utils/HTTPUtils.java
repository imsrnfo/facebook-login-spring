package com.igmasiri.facebooklogin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public final class HTTPUtils {
    
    public static String getJSON(String direccionURL) throws Exception {
        try {
            URL url = new URL(direccionURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200 && conn.getResponseCode() != 400) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(((conn.getResponseCode() == 200) ? conn.getInputStream() : conn.getErrorStream())));
            String resultado ="";
            String output;
            while ((output = br.readLine()) != null) {
                resultado+=output;
            }
            conn.disconnect();
            System.out.println(resultado);
            return resultado;
        } catch (MalformedURLException e) {
            throw new Exception("El formato de la URL no es correcto.");
        } catch (IOException e) {
            throw new Exception("Ocurrio un error al obtener el contenido de la URL.");
        } catch (Exception e) {
            throw new Exception("No se pudo conectar con el servidor.");
        }
    }
    
    public static String postJSON(String direccionURL, String stringJSON) throws Exception {
        try {
            URL url = new URL(direccionURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String input = stringJSON;
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED && conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String resultado ="";
            String output;
            while ((output = br.readLine()) != null) {
                resultado+=output;
            }
            conn.disconnect();
            System.out.println(resultado);
            return resultado;
        } catch (MalformedURLException e) {
            throw new Exception("El formato de la URL no es correcto.");
        } catch (IOException e) {
            throw new Exception("Ocurrio un error al obtener el contenido de la URL.");
        }
    }
    
}