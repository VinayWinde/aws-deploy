package com.rdb_aws.rdbCheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceBook {


    public Repo repo;
    @Autowired
    public ServiceBook(Repo repo){
        this.repo=repo;
    }


    public List<Enty> getall() {
  return repo.findAll();
    }


    public Optional<Enty> getId(String id) {
        return repo.findById(id);
    }


    public void addBook(Enty enty) {
        repo.save(enty);
    }
}
