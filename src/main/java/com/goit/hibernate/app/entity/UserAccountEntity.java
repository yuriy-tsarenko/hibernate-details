package com.goit.hibernate.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_account")
public class UserAccountEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Id
    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
