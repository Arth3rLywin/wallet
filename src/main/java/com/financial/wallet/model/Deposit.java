package com.financial.wallet.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Deposit extends Transaction {

    private String source;

    public String getSource (){
        return source;
    }
    public void setSource (String source) {
        this.source = source;
    }
}
