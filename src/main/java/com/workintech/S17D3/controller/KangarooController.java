package com.workintech.S17D3.controller;

import com.workintech.S17D3.entity.Kangaroo;
import com.workintech.S17D3.validation.ZooKangarooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
private Map<Integer, Kangaroo> kangaroos;

@PostConstruct
    public void init(){
    kangaroos = new HashMap<>();
}
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Kangaroo> findAll(){
        return this.kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo find(@PathVariable("id") Integer id){
        ZooKangarooValidation.isValid(id);
    return kangaroos.get(id);
    }
    @PostMapping
    public  Kangaroo save(@RequestBody Kangaroo kangaroo){
    kangaroos.put(kangaroo.getId(), kangaroo);
    return kangaroos.get(kangaroo.getId());
    }
    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable("id") Integer id, @RequestBody Kangaroo kangaroo){
    kangaroo.setId(id);
    if(kangaroos.containsKey(id)){
        kangaroos.put(id, kangaroo);
        return kangaroos.get(id);
    } else {
        return save(kangaroo);
    }
    }
    @DeleteMapping
    public Kangaroo delete(@PathVariable("id") Integer id){
        return kangaroos.remove(id);
    }

}
