package com.workintech.s18d4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="customer",schema = "fsweb")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "salary")
    private double salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<Account> accounts;

    public void addAccount(Account account){
        if(account==null){
            accounts =new ArrayList<>();
        }
    accounts.add(account);
    }

    public void updateAccount(Account account){
        for(Account a :accounts){
            if(a.getId()== account.getId()){
                a.setCustomer(account.getCustomer());
                a.setMoneyAmount(account.getMoneyAmount());
                a.setAccountName(account.getAccountName());
            }
        }
    }
}
