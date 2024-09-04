package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }


    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account find(long id) {
        return accountRepository.findById(id).orElseThrow(()->new RuntimeException());
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account delete(long id) {
        Account acc=find(id);
        accountRepository.deleteById(id);
        return acc;
    }
}
