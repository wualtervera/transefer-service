package com.nttdata.transferservice.application.impl.TransferOperations;

import com.nttdata.transferservice.application.model.TransferRepository;
import com.nttdata.transferservice.application.operations.TransferOperations;
import com.nttdata.transferservice.domain.Transfer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TransferOperationsImpl implements TransferOperations {

    @Autowired
    private TransferRepository transferRepository;

    @Override
    public Flux<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    public Mono<Transfer> findById(String id) {
        return transferRepository.findById(id);
    }

    @Override
    public Mono<Transfer> save(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    @Override
    public Mono<Transfer> update(String id, Transfer transfer) {
        return transferRepository.update(id, transfer);
    }

    @Override
    public Mono<Void> delete(String id) {
        return transferRepository.delete(id);
    }
}
