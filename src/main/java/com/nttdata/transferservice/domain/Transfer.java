package com.nttdata.transferservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {
    private String id;
    private String typeTran;    //Tipo de transferencia- (INTERNA, EXTERNA)
    private String idAccountOrigin; //Cuenta origen
    private String idAccountDestiny;    //Cuenta destino
    private double amount;  //montor de la trnsferencia
    private LocalDate date;
    String idAccount;  //cuenta de tranferencia
}
