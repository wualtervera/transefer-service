package com.nttdata.transferservice.infrastructura.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transfer")
public class TransferDao {
    @Id
    private String id;
    private String typeTran;    //Tipo de transferencia- (INTERNA, EXTERNA)
    private String idAccountOrigin; //Cuenta origen
    private String idAccountDestiny;    //Cuenta destino
    private double amount;  //montor de la trnsferencia
    private LocalDate date;
    String idAccount;  //cuenta de tranferencia
}
