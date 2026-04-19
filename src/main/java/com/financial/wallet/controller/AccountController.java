package com.financial.wallet.controller;

import com.financial.wallet.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{id}/deposit")
    public String deposit(@PathVariable Long id,
                          @RequestParam BigDecimal amount,
                          @RequestParam String source) {
        accountService.deposit(id, amount, source);
        return "Deposit successful";
    }

    @PostMapping("/{id}/withdraw")
    public String withdraw(@PathVariable Long id,
                           @RequestParam BigDecimal amount,
                           @RequestParam String destination) {
        accountService.withdraw(id, amount, destination);
        return "Withdrawal successful";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long senderId,
                           @RequestParam Long receiverId,
                           @RequestParam BigDecimal amount) {
        accountService.transfer(senderId, receiverId, amount);
        return "Transfer successful";
    }
}
