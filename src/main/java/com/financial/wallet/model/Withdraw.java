package com.financial.wallet.model;

import lombok.Getter;
import lombok.Setter;

public class Withdraw extends Transaction{

    @Getter
    @Setter
    private String destination;
}
