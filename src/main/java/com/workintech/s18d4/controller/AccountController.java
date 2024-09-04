package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")

public class AccountController {
    private final AccountService accountService;
    private final CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService,CustomerService customerService){
        this.accountService=accountService;
        this.customerService=customerService;
    }

    @GetMapping
    public List<Account> getAll(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account get(@PathVariable long id){
        return accountService.find(id);
    }

//    [POST]/workintech/accounts/{customerId} => Parametre olarak gelen id ile ilgili customeri bulur
//    ve Account objesini Customer ile ilişkilendirip veri tabanına kaydeder.

    @PostMapping("/{customerId}")
    public AccountResponse save(@PathVariable long customerId,@RequestBody Account account){
        Customer customer = customerService.find(customerId);
        account.setCustomer(customer);
        customer.addAccount(account);
        customerService.save(customer);
        CustomerResponse cRes=new CustomerResponse(customer.getId(),customer.getEmail(),customer.getSalary());
        return new AccountResponse(account.getId(),account.getAccountName(),account.getMoneyAmount(),cRes);
    }

//    [PUT]/workintech/accounts/{customerId} =>Parametre olarak gelen id ile ilgili customeri bulur
//    ve Account objesinin yeni bilgilerini güncelleyip Customer ile ilişkilendirip veri tabanına kaydeder.

    @PutMapping("/{customerId}")
    public AccountResponse update(@PathVariable long customerId,@RequestBody Account account){
        Customer customer = customerService.find(customerId);
        account.setCustomer(customer);
        customer.updateAccount(account);
        customerService.save(customer);
        CustomerResponse cRes=new CustomerResponse(customer.getId(),customer.getEmail(),customer.getSalary());
        return new AccountResponse(account.getId(),account.getAccountName(),account.getMoneyAmount(),cRes);
    }

    @DeleteMapping("/{id}")
    public Account delete(@PathVariable long id){
        return accountService.delete(id);
    }
}
