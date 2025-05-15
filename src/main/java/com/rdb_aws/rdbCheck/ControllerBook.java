package com.rdb_aws.rdbCheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("book")
public class ControllerBook {

    public ServiceBook serviceBook;
@Autowired
    public ControllerBook(ServiceBook serviceBook){
        this.serviceBook=serviceBook;
    }


    @GetMapping("/all")
    public List<Enty> getAll() {
    return  serviceBook.getall();
    }

    @GetMapping("/{id}")
    public Optional<Enty> getId(@PathVariable String id) {
        return serviceBook.getId(id);
    }

    @PostMapping("/addbook")
    public ResponseEntity<String> add(@RequestBody Enty enty){
    serviceBook.addBook(enty);
    return new ResponseEntity<>("ADDED",HttpStatus.ACCEPTED);
    }







}
