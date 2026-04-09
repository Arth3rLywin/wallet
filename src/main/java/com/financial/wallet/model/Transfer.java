package com.financial.wallet.model;

import lombok.Getter;
import lombok.Setter;

public class Transfer extends Transaction {
    @Getter
    @Setter
    private Account receiverAccount;
}
