package com.financial.wallet.service;

import com.financial.wallet.model.Account;
import com.financial.wallet.model.Transaction;
import com.financial.wallet.model.Deposit;
import com.financial.wallet.model.Withdraw;
import com.financial.wallet.model.Transfer;
import com.financial.wallet.model.TransactionStatus;
import com.financial.wallet.repository.AccountRepository;
import com.financial.wallet.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void deposit(Long accountId, BigDecimal amount, String source) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Correct balance update
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Deposit deposit = new Deposit();
        deposit.setAccount(account);
        deposit.setAmount(amount);
        deposit.setTimestamp(LocalDateTime.now());
        deposit.setSource(source);
        deposit.setStatus(TransactionStatus.SUCCESS);

        transactionRepository.save(deposit);
    }

    @Transactional
    public void withdraw(Long accountId, BigDecimal amount, String destination) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        Withdraw withdrawal = new Withdraw();
        withdrawal.setAccount(account);
        withdrawal.setAmount(amount);
        withdrawal.setTimestamp(LocalDateTime.now());
        withdrawal.setDestination(destination);
        withdrawal.setStatus(TransactionStatus.SUCCESS);

        transactionRepository.save(withdrawal);
    }

    @Transactional
    public void transfer(Long senderId, Long receiverId, BigDecimal amount) {
        Account sender = accountRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender account not found"));
        Account receiver = accountRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));
        accountRepository.save(sender);
        accountRepository.save(receiver);

        Transfer transfer = new Transfer();
        transfer.setAccount(sender);
        transfer.setReceiverAccount(receiver);
        transfer.setAmount(amount);
        transfer.setTimestamp(LocalDateTime.now());
        transfer.setStatus(TransactionStatus.SUCCESS);

        transactionRepository.save(transfer);
    }
}
