package com.gdp.demo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Userdtos {
    private String userId;

    @Size(min =3, max=15, message= "invalid Name")
    private String name;
   
    
    //@Email(message = "Invalid email")
   
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
    		+ "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Invalid email !!")
    private String email;
    @NotBlank(message = "password required")
    private String password;

    @Size(min =3, max=15, message= "invalid Name")
    private String gender;
    @Size(min =3, max=150, message= "invalid Name")
    private String about;


    private String imageName;


	


	


}
