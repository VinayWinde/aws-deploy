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
    @Autowired
    private ServiceBook serviceBook;


    @GetMapping("/all")
    public ResponseEntity<List<Enty>> getAll() {
        List<Enty> li=serviceBook.getall();
        return  new ResponseEntity<>(li,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Enty>> getId(@PathVariable String id) {
        Optional<Enty> op= serviceBook.getId(id);
        return  new ResponseEntity<>(op,HttpStatus.OK);
    }

    @PostMapping("/addbook")
    public ResponseEntity<String> add(@RequestBody Enty enty){
    serviceBook.addBook(enty);
    return new ResponseEntity<>("ADDED",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/del")
    public ResponseEntity<String> delB(@PathVariable String id){
    serviceBook.delBook(id);
    return new ResponseEntity<>("DELETED",HttpStatus.OK);
    }






}
