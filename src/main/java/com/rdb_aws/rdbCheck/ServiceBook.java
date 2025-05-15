package com.rdb_aws.rdbCheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceBook {

   private final List<Enty> enty=new ArrayList<>();


    public List<Enty> getall() {
  return enty;

    }


    public Optional<Enty> getId(String id) {
       return enty.stream().filter(x->x.getId() == id).findFirst();
    }


    public void addBook(Enty ent) {
        enty.add(ent);
    }

    public void delBook(String id){
        enty.removeIf(x->x.getId()  == id);
    }

}
