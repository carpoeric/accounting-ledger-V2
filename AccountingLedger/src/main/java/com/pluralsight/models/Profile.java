package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Profile {
    @Id
    private int userId;
    private String firstName = "";
    private String lastName = "";
    private String phone = "";
    private String email = "";
    private String address = "";
    private String city = "";
    private String state = "";
    private String zip = "";


}
