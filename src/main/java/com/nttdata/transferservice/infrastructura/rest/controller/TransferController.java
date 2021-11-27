package com.nttdata.transferservice.infrastructura.rest.controller;

import com.nttdata.transferservice.application.operations.TransferOperations;
import com.nttdata.transferservice.domain.Transfer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = "api/v1/transfer")
public class TransferController {
    @Autowired
    private TransferOperations transferOperations;

    @GetMapping("list")
    public Flux<Transfer> getAll(){
        return transferOperations.findAll();
    }

    @GetMapping("list/{id}")
    public Mono<Transfer> getOne(@PathVariable String id){
        return transferOperations.findById(id);
    }

    @PostMapping("add")
    public Mono<Transfer> save(@RequestBody Transfer profile){
        return transferOperations.save(profile);
    }

    @PutMapping("update/{id}")
    public Mono<Transfer> update(@PathVariable String id, @RequestBody Transfer profile){
        return transferOperations.update(id, profile);
    }

    @DeleteMapping("remove/{id}")
    public Mono<Void> remove(@PathVariable String id){
        return transferOperations.delete(id);
    }


}
