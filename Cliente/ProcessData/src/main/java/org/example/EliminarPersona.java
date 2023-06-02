package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EliminarPersona {
    //Eliminar persona
    public static void main(String[] args) throws IOException {
        String id = "3"; //Id a eliminar...
        URL url = new URL("http://localhost:8080/api/del/persona/"+id);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpCon.setRequestMethod("DELETE"); //Metodo http.
        httpCon.connect(); //Conectar.

        //Obtener respuesta del servidor e imprimirla.
        try(BufferedReader br = new BufferedReader(new InputStreamReader(httpCon.getInputStream(),"utf-8"))){
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            //Respuesta del servidor.
            System.out.println("RESPUESTA DEL SERVIDOR:: ");
            System.out.println(response.toString());
        }
        catch(Exception ex){
            System.out.println(ex); //Tiende a retornar http 500 si se elimina el ultimo registro del array.
        }

    }


}
