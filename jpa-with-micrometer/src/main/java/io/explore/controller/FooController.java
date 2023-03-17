package io.explore.controller;

import io.explore.model.Foo;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/foos")
class FooController {

    @GetMapping
    public List<Foo> findAll() {
        log.info("Finding all foo's");
        List<Foo> foos = new ArrayList<>();
        foos.add(new Foo(1l));
        foos.add(new Foo(2l));
        return foos;
    }

    @GetMapping(value = "/{id}")
    public Foo findById(@PathVariable("id") Long id) {
        log.info("Found foo");
        return new Foo(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Foo resource) {
        log.error("Error while creating foo");
        return -1l;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Foo resource) {
        log.warn("Foo may or may not be updated");
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        log.error("Error while deleting foo");
    }

}