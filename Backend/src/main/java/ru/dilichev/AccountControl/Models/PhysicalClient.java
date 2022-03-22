package ru.dilichev.AccountControl.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Physical_clients")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalClient implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @ToString.Exclude
    @NonNull
    private Client client;

    @Column(nullable = false, name = "fullname")
    @NonNull
    private String fullName;

    @Column(nullable = false, name = "passport")
    @NonNull
    private String passport;

    @Column(nullable = false, name = "tin")
    @NonNull
    private String tin;
}
