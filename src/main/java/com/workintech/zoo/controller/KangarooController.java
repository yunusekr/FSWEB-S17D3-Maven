package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public List<Kangaroo> getKangaroos(){
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getKangaroo(@PathVariable int id){
        if (id <= 0) {
            throw new ZooException("Id must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("This kangaroo with given id: " + id + " is not exist",
                    HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo postKangaroo(@RequestBody Kangaroo kangaroo) {
        if (kangaroo.getId() == null) {
            throw new ZooException(
                    kangaroo.getName() + " could not saved without id", HttpStatus.BAD_REQUEST
            );
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroos.get(kangaroo.getId());
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroos(@PathVariable int id,@RequestBody Kangaroo kangaroo){
         kangaroos.put(id,kangaroo);
         return kangaroos.get(id);
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroos(@PathVariable int id){
        return kangaroos.remove(id);
    }
}
