package org.example.service;

import org.example.dao.TransactionCreateDao;
import org.example.entity.Transaction;

import java.util.UUID;


public class TransactionService {
    private TransactionCreateDao transactionDao = new TransactionCreateDao();

    public void create(Transaction entity, UUID accountIdFrom, UUID accountIdTo) {
        AccountService accountService = new AccountService();
        transactionDao.create(entity, accountService.read(accountIdFrom), accountService.read(accountIdTo));
    }
}
