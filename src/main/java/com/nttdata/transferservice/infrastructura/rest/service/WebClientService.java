package com.nttdata.account.infrestructure.rest.service;

import com.nttdata.account.infrestructure.model.dto.CreditDto;
import com.nttdata.account.infrestructure.model.dto.CustomerDTO;
import com.nttdata.account.infrestructure.model.dto.ProfileDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class WebClientService {
    WebClient webClient = WebClient.create("http://localhost:8080/api/v1");


    //obtiene el perfil del cliente
    public Mono<ProfileDto> getProfile(String idProfile) {
        return webClient.get()
                .uri("/profile/{idProfile}", idProfile)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ProfileDto.class);
    }
    //obtiene el cliente
    public Mono<CustomerDTO> getCustommer(String idCustomer) {
        return webClient.get()
                .uri("/customers/{idCustomer}", idCustomer)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CustomerDTO.class);
    }

    //obtiene el cliente
    public Flux<CreditDto> getCredit(String idCustomer) {
        return webClient.get()
                .uri("/credit/customer/{idCustomer}", idCustomer)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(CreditDto.class);
    }


}
