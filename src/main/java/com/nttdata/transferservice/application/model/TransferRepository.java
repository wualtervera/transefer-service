package com.nttdata.transferservice.application.model;

import com.nttdata.transferservice.domain.Transfer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransferRepository {
    public Flux<Transfer> findAll();

    public Mono<Transfer> findById(String id);

    public Mono<Transfer> save(Transfer profile);

    public Mono<Transfer> update(String id,  Transfer profile);

    public Mono<Void> delete(String id);
}
