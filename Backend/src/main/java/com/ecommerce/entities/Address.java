package com.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String houseNo;

    private String street;

    private String city;

    private String state;

    private String country;

    private String pincode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}