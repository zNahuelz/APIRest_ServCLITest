package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EditarPersona {
    public static void main(String[] args) throws JsonProcessingException {

        Persona p = new Persona(2L,"REGISTRO_ACTUALIZADO",666);
        //Conectado a DB ID debe autogestionarse...
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(p); //Transformar objeto a JSON.
        System.out.println("JSON ENVIADO:: \n");
        System.out.println("json = " + json);
        try{
            URL url = new URL("http://localhost:8080/api/edit/persona");
            HttpURLConnection con = (HttpURLConnection) url.openConnection(); //Abrir conexión
            con.setRequestMethod("PUT"); //PUT
            con.setRequestProperty("Content-Type", "application/json"); //Formato de contenido.
            con.setRequestProperty("Accept", "application/json"); //Formato de respuesta, **API devuelve JSON.
            con.setDoOutput(true); //Permitir output de la solicitud.
            try(OutputStream os = con.getOutputStream()){
                byte[] input = json.getBytes("utf-8"); //Transformar en array de bytes JSON, en UTF-8
                os.write(input,0,input.length); //0 -> off? //Enviar solicitud.
            }

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder(); //Obtener respuesta del servidor. (JSON)
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim()); //Remover espacios y llenar string.
                }
                //Respuesta del servidor.
                System.out.println("RESPUESTA DEL SERVIDOR:: ");
                System.out.println(response.toString());

            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}
