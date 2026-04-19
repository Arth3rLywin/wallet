package com.financial.wallet.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Transfer extends Transaction {
    private Account receiverAccount;

    public Account getReceiverAccount() {
        return receiverAccount;
    }
    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }
}
