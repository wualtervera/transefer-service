package com.nttdata.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String id;
    private String numberAccount; //Numero de cuenta
    private double balance;     //Cuanto dinero tiene
    private String typeAccount;  // Tipo de cuenta (CCORIENTE, CAHORRO,CPLAZOFIJO)
    private String typeCard;
    private String numberCard;
    private String ccv;
    private LocalDate exipirationCard; //Fecha de expiración
    private  double minAmount; //Monto mínimo cada mes para personas vip
    private  double maxTransac; //Máximo de trnsacciones
    private int maxLimitMov;  //Límite máximo de movimientos
    private String coin;      // Tipo de moneda
    private String idcustomer; //Client
    private double maintenance; //Comisión de mantenimiento - total recaudado
    private double commissionMaintenance; //Comisión de mantenimiento a aplicar mensual
    private LocalDate date;     //Fecha de creacion

}
