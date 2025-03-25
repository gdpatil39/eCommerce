package com.gdp.demo.dtos;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Userdtos {
    private String userId;


    private String name;

    private String email;

    private String password;


    private String gender;

    private String about;


    private String imageName;


}
