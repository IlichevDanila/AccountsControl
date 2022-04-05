package ru.dilichev.AccountControl.Models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Accounts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(nullable = false, name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    @NonNull
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type")
    @ToString.Exclude
    @NonNull
    private AccountType type;

    @Column(nullable = false, name = "status")
    @NonNull
    private String status;

    @Column(nullable = false, name = "balance")
    private double balance;

    @Column(nullable = false, name = "creating_time")
    @NonNull
    private Timestamp creating_time;

    @Column(nullable = true, name = "response_account")
    private String response_account;

    @Column(nullable = true, name = "loan_account")
    private String loan_account;
}
