package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private UUID id;
    private String city;
    private String zip;

    @Override
    public String toString() {
        return "Address{" +
            "id=" + id +
            ", city='" + city + '\'' +
            ", zip='" + zip + '\'' +
            '}';
    }
}
