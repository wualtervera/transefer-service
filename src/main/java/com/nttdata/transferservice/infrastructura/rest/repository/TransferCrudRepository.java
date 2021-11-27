package com.nttdata.transferservice.infrastructura.rest.repository;

import com.nttdata.transferservice.infrastructura.model.dao.TransferDao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferCrudRepository extends ReactiveMongoRepository<TransferDao, String> {
}
