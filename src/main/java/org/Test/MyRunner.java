package org.Test;

import org.Test.domain.Class1;
import org.Test.repositories.GeodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Component
public class MyRunner implements CommandLineRunner {
  @Autowired
  private GeodeRepository repository;

  @Override
  public void run(String... args) throws Exception {

    //repository.deleteAll(); // doesn't work for partitioned regions as of 2020-11-02 https://jira.spring.io/browse/DATAGEODE-265
    repository.deleteAll(repository.findAll());

    ArrayList<Integer> l = new ArrayList<>();
    HashMap<Integer, String> m = new HashMap<>();
    Class1 obj;
    Optional<Class1> o;

    l.clear(); l.add(1); l.add(2); l.add(3); l.add(4);
    m.clear(); m.put(1, "a"); m.put(2, "b"); m.put(3, "c"); m.put(4, "d");
    obj = new Class1(1, l, m);
    repository.save(obj);

    l.clear(); l.add(2); l.add(3); l.add(4);
    m.clear(); m.put(2, "b"); m.put(3, "c"); m.put(4, "d");
    obj = new Class1(2, l, m);
    repository.save(obj);

    l.clear(); l.add(3); l.add(4);
    m.clear(); m.put(3, "c"); m.put(4, "d");
    obj = new Class1(3, l, m);
    repository.save(obj);

    m.clear(); m.put(4, "d");
    obj = new Class1(4, null, m);
    repository.save(obj);

    l.clear(); l.add(3); l.add(4);
    obj = new Class1(5, null, null);
    repository.save(obj);

    System.out.println("\n 1 -- findAll().foreach works ---------------------- ---------");

    repository.findAll().forEach((item) ->System.out.println(item.toString()));

    System.out.println("\n 2 -- issue with the array and map. displays the last entry --");

    o = repository.findById(1);
    o.ifPresent(ob -> System.out.println(ob.toString()));
    o = repository.findById(2);
    o.ifPresent(ob -> System.out.println(ob.toString()));
    o = repository.findById(3);
    o.ifPresent(ob -> System.out.println(ob.toString()));
    o = repository.findById(4);
    o.ifPresent(ob -> System.out.println(ob.toString()));
    o = repository.findById(5);
    o.ifPresent(ob -> System.out.println(ob.toString()));

    System.out.println("\n 3 -- findAllById().foreach does not work either -------------");

    ArrayList<Integer> il = new ArrayList<>();

    il.add(1); il.add(2); il.add(3); il.add(4); il.add(5);
    repository.findAllById(il).forEach((item) ->System.out.println(item.toString()));

    System.out.println("\n--------------------------------------------------------------");
  }
}
