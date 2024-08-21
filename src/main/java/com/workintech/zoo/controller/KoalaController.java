package com.workintech.zoo.controller;


import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    Map<Integer, Koala> koalas;

    @PostConstruct
    public void init(){
        koalas = new HashMap<>();
    }

    @GetMapping
    public List<Koala> getKoala(){
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getKoala(@PathVariable int id){
        if (id <= 0) {
            throw new ZooException("Id must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if (!koalas.containsKey(id)) {
            throw new ZooException("This koala with given id: " + id + " is not exist",
                    HttpStatus.NOT_FOUND);
        }
        return koalas.get(id);
    }

    @PostMapping
    public Koala postKoala(@RequestBody Koala koala){
        if (koala.getId() == null) {
            throw new ZooException(
                    koala.getName() + " could not saved without id", HttpStatus.BAD_REQUEST
            );
        } else {
            koalas.put(koala.getId(), koala);
        }
        return koalas.get(koala.getId());
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable int id,@RequestBody Koala koala){
         koalas.put(id,koala);
        return koalas.get(id);
    }

    @DeleteMapping("/{id}")
    public Koala deleteKoala(@PathVariable int id){
        return koalas.remove(id);
    }
}
