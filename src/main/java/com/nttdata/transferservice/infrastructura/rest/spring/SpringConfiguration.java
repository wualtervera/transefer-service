package com.nttdata.transferservice.infrastructura.rest.spring;

import com.nttdata.transferservice.application.model.TransferRepository;
import com.nttdata.transferservice.infrastructura.rest.service.TransferCrudService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
    @Bean
    public TransferRepository transferRepository() {
        return new TransferCrudService();
    }
}
