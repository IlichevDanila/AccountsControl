package ru.dilichev.AccountControl.Models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Offices")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private long id;

    @Column(nullable = false, name = "phone")
    @NonNull
    private String phone;

    @Column(nullable = false, name = "address")
    @NonNull
    private String address;
}
