package com.nttdata.transferservice.infrastructura.rest.service;

import com.nttdata.transferservice.application.model.TransferRepository;
import com.nttdata.transferservice.domain.Transfer;
import com.nttdata.transferservice.infrastructura.model.dao.TransferDao;
import com.nttdata.transferservice.infrastructura.model.dto.AccountDto;
import com.nttdata.transferservice.infrastructura.rest.repository.TransferCrudRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

public class TransferCrudService implements TransferRepository {


    @Autowired
    private WebClientService webClientService;
    @Autowired
    private TransferCrudRepository transferCrudRepository;

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
        //Realizar transferencias entre cuentas
        return transferCrudRepository.save(this.toTransferDAO(transfer))
                .flatMap(transferDao ->
                        webClientService.getAccount(transfer.getIdAccountOrigin())
                                .flatMap(accountDto1 -> webClientService.updateAccount(transferDao.getIdAccountOrigin(), updateAccountOrigin(accountDto1, transfer)))
                                .flatMap(accountDto -> webClientService.getAccount(transferDao.getIdAccountDestiny())
                                        .flatMap(accountDto2 -> webClientService.updateAccount(transferDao.getIdAccountOrigin(), updateAccountDestiny(accountDto2, transfer))))
                                .map(accountDto -> toTransfer(transferDao))
                );
    }


    @Override
    public Mono<Transfer> update(String id, Transfer transfer) {


        Mono<Transfer>transferMono = Mono.empty();
        return transferMono.switchIfEmpty(Mono.error(new RuntimeException("Erro: la trnsferencia no se puede actualizar:")));

        /*return transferCrudRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Error: No existe la transferencia a actualizar")))
                .flatMap(transferDao -> {
                    transferDao = toTransferDAO(transfer);
                    return transferCrudRepository.save(transferDao).map(this::toTransfer);
                });*/
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

    public AccountDto updateAccountOrigin(AccountDto accountDto, Transfer transfer) {
        AccountDto a = new AccountDto();
        a.setId(accountDto.getId());
        a.setNumberAccount(accountDto.getNumberAccount());
        a.setBalance(accountDto.getBalance() - transfer.getAmount());
        a.setCoin(accountDto.getCoin());
        a.setIdcustomer(accountDto.getIdcustomer());
        a.setTypeAccount(accountDto.getTypeAccount());
        a.setDate(accountDto.getDate());
        a.setTypeCard(accountDto.getTypeCard());
        a.setNumberCard(accountDto.getNumberCard());
        a.setCcv(accountDto.getCcv());
        a.setExipirationCard(accountDto.getExipirationCard());
        a.setMaxTransac(accountDto.getMaxTransac());
        a.setMaxLimitMov(accountDto.getMaxLimitMov());
        a.setMaintenance(accountDto.getMaintenance());
        a.setCommissionMaintenance(accountDto.getCommissionMaintenance());
        a.setMinAmount(accountDto.getMinAmount());
        return a;
    }

    public AccountDto updateAccountDestiny(AccountDto accountDto, Transfer transfer) {
        AccountDto a = new AccountDto();
        a.setId(accountDto.getId());
        a.setNumberAccount(accountDto.getNumberAccount());
        a.setBalance(accountDto.getBalance() + transfer.getAmount());
        a.setCoin(accountDto.getCoin());
        a.setIdcustomer(accountDto.getIdcustomer());
        a.setTypeAccount(accountDto.getTypeAccount());
        a.setDate(accountDto.getDate());
        a.setTypeCard(accountDto.getTypeCard());
        a.setNumberCard(accountDto.getNumberCard());
        a.setCcv(accountDto.getCcv());
        a.setExipirationCard(accountDto.getExipirationCard());
        a.setMaxTransac(accountDto.getMaxTransac());
        a.setMaxLimitMov(accountDto.getMaxLimitMov());
        a.setMaintenance(accountDto.getMaintenance());
        a.setCommissionMaintenance(accountDto.getCommissionMaintenance());
        a.setMinAmount(accountDto.getMinAmount());
        return a;
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
