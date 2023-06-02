package com.example.demo;

import org.apache.coyote.Response;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PersonaController {
    private static final List<Persona> personas = new ArrayList<>();

    private final AtomicLong contador = new AtomicLong(); //Contador, en 0.
    public PersonaController(){
        initData();
    }
    public void initData(){
        //incrementAndGet -> Incrementa en 1 al contador y obtiene el valor...
        Persona a = new Persona(contador.incrementAndGet(),"Francisco",30);
        personas.add(a);
        Persona b = new Persona(contador.incrementAndGet(),"Pepe",40);
        personas.add(b);
        Persona c = new Persona(contador.incrementAndGet(),"Manuel",85);
        personas.add(c);
        Persona d = new Persona(contador.incrementAndGet(),"Eduardo",20);
        personas.add(d);
    }

    @GetMapping("/api/personas")
    public ResponseEntity<List<Persona>> listar(){
        //Listado de personas...
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping("/api/personas/{id}")
    public ResponseEntity<List<Persona>> listarPorID(@PathVariable Long id){
        //Persona en especifico por ID...
        List<Persona> filtro = personas.stream().filter(e -> e.getId() ==(Long)id).toList();
        return new ResponseEntity<>(filtro, HttpStatus.OK);
    }
    @PostMapping("/api/reg/persona")
    public ResponseEntity<Persona> regPersona(@RequestBody Persona p){
        //POST. Añadir persona responde con HTTP 200 y datos del registro.
        personas.add(p);
        return new ResponseEntity<>(p,HttpStatus.OK);
    }

    @PutMapping("/api/edit/persona")
    public ResponseEntity<Persona> editarPersona(@RequestBody Persona p){
        Persona editada = new Persona();
        for(Persona e : personas){
            if(e.getId() == p.getId()) {
                e.setEdad(p.getEdad());
                e.setNombre(p.getNombre());
                //Random.
                editada = e;
            }
        }
        //Response con los datos editados y http 200.
        return new ResponseEntity<Persona>(editada, HttpStatus.OK);
    }

    @DeleteMapping("/api/del/persona/{id}")
    public ResponseEntity<Persona> eliminarPersona(@PathVariable Long id){
        //Eliminar persona segun ID
        Persona eliminada = new Persona();
        for(Persona e : personas){
            if(e.getId() == id){
                eliminada = e;
                personas.remove(e);
                //Nota: Al eliminar id 2 http 500: Siempre que elimine el ultimo elemento del array.
                //Elimina y procede a enviar http 200 + datos del registro eliminado.
            }
        }
        return new ResponseEntity<Persona>(eliminada,HttpStatus.OK);
    }
}
