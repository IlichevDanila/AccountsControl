package ru.dilichev.AccountControl.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Account_types")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private long id;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "profitability_percent")
    private Double profitability_percent;

    @Column(nullable = false, name = "profitability_fixed")
    private Double profitability_fixed;

    @Column(nullable = false, name = "debiting")
    private boolean debiting;

    @Column(nullable = false, name = "accrual")
    private boolean accrual;

    @Column(nullable = false, name = "period")
    @NonNull
    private String period;

    @Column(nullable = false, name = "valid")
    private boolean valid;
}
