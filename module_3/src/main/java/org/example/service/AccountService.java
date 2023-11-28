package org.example.service;

import org.example.dao.AccountCrudDaoImpl;
import org.example.entity.Account;

import java.util.Collection;
import java.util.UUID;

public class AccountService  implements BaseCrudService<Account> {
    private AccountCrudDaoImpl accountDao = new AccountCrudDaoImpl();

    @Override
    public void create(Account entity) {
        accountDao.create(entity);
    }

    @Override
    public Account read(UUID id) {
        return accountDao.read(id, Account.class);
    }

    @Override
    public void update(Account entity) {
        accountDao.update(entity);
    }

    @Override
    public void delete(UUID id) {
        accountDao.delete(id, Account.class);
    }

    public Collection<Account> findByUserId(UUID userId) {
        return accountDao.findByUserId(userId);
    }
}