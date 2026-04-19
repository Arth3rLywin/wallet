package com.financial.wallet.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Withdraw extends Transaction{

    private String destination;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

}
