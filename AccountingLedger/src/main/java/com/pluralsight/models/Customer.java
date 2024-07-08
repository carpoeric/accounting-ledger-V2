package com.pluralsight.models;

import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Customer {
    @Id
    private int id;
    private String name;
    private String email;
    private String phone;

}