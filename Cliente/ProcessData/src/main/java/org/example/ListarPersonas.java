package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ListarPersonas {
    public static void main(String[] args) throws MalformedURLException {

       URL url = new URL("http://localhost:8080/api/personas");
        try {
            //Realizar GET a la API.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int response = conn.getResponseCode(); //Obtener código HTTP.
            if(response == 200){ //200 HttpStatus.OK
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());
                while(scanner.hasNext()){
                    inline += scanner.nextLine(); // ^ Abrir conexion y llenar string con JSON.
                }
                scanner.close();

                System.out.println("JSON: "+inline);
                ObjectMapper mapper = new ObjectMapper();
                Persona[] pp1 = mapper.readValue(inline,Persona[].class); //Leer valores del string y transformar a Objeto.
                System.out.println("Objetos ::");
                System.out.println(("ID/NOMBRE/EDAD/CLASE"));
                for(Persona e : pp1){
                    System.out.println(e.getId() +" "+ e.getNombre() +" "+ e.getEdad()+" "+e.getClass());

                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}