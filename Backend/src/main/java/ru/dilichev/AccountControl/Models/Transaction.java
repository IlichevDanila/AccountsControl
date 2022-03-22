package ru.dilichev.AccountControl.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Transactions")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private long id;

    @Column(nullable = false, name = "debit_account_id")
    @NonNull
    private String debit_account_id;

    @Column(nullable = false, name = "credit_account_id")
    @NonNull
    private String credit_account_id;

    @Column(nullable = false, name = "tran_time")
    @NonNull
    private String tran_time;

    @Column(nullable = false, name = "amount")
    @NonNull
    private double amount;
}
