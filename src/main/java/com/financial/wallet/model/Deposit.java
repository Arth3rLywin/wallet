package com.financial.wallet.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Deposit extends Transaction {

    @Getter
    @Setter
    private String source;

}
