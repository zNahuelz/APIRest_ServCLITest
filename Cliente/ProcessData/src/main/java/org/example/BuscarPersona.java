package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class BuscarPersona {
    public static void main(String[] args) throws MalformedURLException {
        Long buscar = 2L;
        URL url = new URL("http://localhost:8080/api/personas/"+buscar);
        try{
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int response = conn.getResponseCode();
            if(response == 200){
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());
                while(scanner.hasNext()){
                    inline += scanner.nextLine();
                }
                scanner.close();
                System.out.println("JSON::\n"+inline);
                ObjectMapper mapper = new ObjectMapper();
                Persona[] pp1 = mapper.readValue(inline,Persona[].class);
                System.out.println("Objeto ::");
                System.out.println("ID/NOMBRE/EDAD/CLASE");
                for(Persona p : pp1){
                    System.out.println(p.getId()+" "+p.getNombre()+" "+p.getEdad()+" "+p.getClass());
                }
            }

        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}
