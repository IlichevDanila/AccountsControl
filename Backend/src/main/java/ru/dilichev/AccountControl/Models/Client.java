package ru.dilichev.AccountControl.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Clients")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private long id;

    @Column(nullable = false, name = "type")
    @NonNull
    private String type;

    @Column(nullable = false, name = "phone")
    @NonNull
    private String phone;

    @Column(nullable = false, name = "address")
    @NonNull
    private String address;

    public Client(Long id, String type, String phone, String address)
    {
        this.id = id == null? Long.MAX_VALUE : id.longValue();
        this.type = type;
        this.phone = phone;
        this.address = address;
    }
}
