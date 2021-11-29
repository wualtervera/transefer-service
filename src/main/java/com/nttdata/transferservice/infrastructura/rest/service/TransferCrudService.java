package com.nttdata.transferservice.infrastructura.rest.service;

import com.nttdata.transferservice.application.model.TransferRepository;
import com.nttdata.transferservice.domain.Transfer;
import com.nttdata.transferservice.infrastructura.model.dao.TransferDao;
import com.nttdata.transferservice.infrastructura.rest.repository.TransferCrudRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TransferCrudService implements TransferRepository {


    @Autowired
    TransferCrudRepository transferCrudRepository;

    @Override
    public Flux<Transfer> findAll() {
        return transferCrudRepository.findAll().map(this::toTransfer);
    }

    @Override
    public Mono<Transfer> findById(String id) {
        return transferCrudRepository.findById(id).map(this::toTransfer);
    }

    @Override
    public Mono<Transfer> save(Transfer transfer) {
        return transferCrudRepository.save(this.toTransferDAO(transfer)).map(this::toTransfer);
    }

    @Override
    public Mono<Transfer> update(String id, Transfer transfer) {
        return transferCrudRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Error: No existe la transferencia a actualizar")))
                .flatMap(transferDao -> {
                    transferDao = toTransferDAO(transfer);
                    return transferCrudRepository.save(transferDao).map(this::toTransfer);
                });
    }

    @Override
    public Mono<Void> delete(String id) {
        return transferCrudRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Error: No existe la transferencia a eliminar.").fillInStackTrace()))
                .flatMap(transferDao -> {
                    transferDao.setId(id);
                    return transferCrudRepository.deleteById(transferDao.getId());
                });
    }

    //Mapper
    public TransferDao toTransferDAO(Transfer transfer) {
        ModelMapper modelMapper = new ModelMapper();
        TransferDao transferDao = modelMapper.map(transfer, TransferDao.class);
        return transferDao;
    }

    public Transfer toTransfer(TransferDao transferDao) {
        ModelMapper modelMapper = new ModelMapper();
        Transfer transfer = modelMapper.map(transferDao, Transfer.class);
        return transfer;
    }
}
