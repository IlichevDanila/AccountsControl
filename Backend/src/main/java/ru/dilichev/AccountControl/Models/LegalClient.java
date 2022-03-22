package ru.dilichev.AccountControl.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Legal_clients")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LegalClient implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @ToString.Exclude
    @NonNull
    private Client client;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "form")
    @NonNull
    private String form;

    @Column(nullable = false, name = "tin")
    @NonNull
    private String tin;
}
