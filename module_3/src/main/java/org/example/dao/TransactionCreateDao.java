package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entity.Account;
import org.example.entity.Operation;
import org.example.entity.Transaction;
import org.example.entity.Transfer;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TransactionCreateDao {
    private final EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("jpa-hibernate-postgresql");
    private final EntityManager entityManager = emf.createEntityManager();

    public void create(Transaction entity, Account accountFrom, Account accountTo) {
        entity.setDate(Timestamp.valueOf(LocalDateTime.now()));
        entityManager.getTransaction().begin();
        entityManager.persist(entity);

        Transfer transferOutlay =
                createTransfer(entity, Operation.PROFIT, accountTo);
        updateAccountFrom(entity, transferOutlay, accountFrom);

        Transfer transferProfit =
                createTransfer(entity, Operation.OUTLAY, accountFrom);
        updateAccountTo(entity, transferProfit, accountTo);

        entityManager.getTransaction().commit();
    }

    private Transfer createTransfer(Transaction transaction, Operation operation, Account account) {
        Transfer transfer = new Transfer();
        transfer.setTransaction(transaction);
        transfer.setOperation(operation.name());
        transfer.setAccount(account);
        transfer.setAccount(transfer.getAccount());
        entityManager.persist(transfer);
        return transfer;
    }

    private void updateAccountFrom(Transaction entity, Transfer transferOutlay, Account account) {
        Account accountFrom = account;
        accountFrom.getTransfers().add(transferOutlay);
        accountFrom.setAmount(accountFrom.getAmount() - entity.getTransactionAmount());
        entityManager.merge(accountFrom);
    }

    private void updateAccountTo(Transaction entity, Transfer transferProfit, Account account) {
        Account accountTo = account;
        accountTo.getTransfers().add(transferProfit);
        accountTo.setAmount(accountTo.getAmount() + entity.getTransactionAmount());
        entityManager.merge(accountTo);
    }
}
