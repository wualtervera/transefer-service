package com.nttdata.transferservice.infrastructura.rest.service;

import com.nttdata.transferservice.infrastructura.model.dto.AccountDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class WebClientService {
    final WebClient webClient = WebClient.create("http://localhost:8080/api/v1");
    //Actualiza balance de la cuenta
    public Mono<AccountDto> updateAccount(String idAccount, AccountDto accountDto) {
        return webClient.put()
                .uri("/account/{idAccount}", idAccount)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(accountDto), AccountDto.class)
                .retrieve()
                .bodyToMono(AccountDto.class);
    }

    public Mono<AccountDto> getAccount(String idAccoun) {
        return webClient.get()
                .uri("/account/{idAccoun}", idAccoun)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AccountDto.class);
    }

}
