package com.GoSchool.Auth.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Persistence Model that represent a user
 * @author Tariku Lemma
 *
 **/

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "UserName can not be empty")
    @Min(value = 4, message = "Username should not be less than 6 characters")
    @Max(value = 20, message = "username should not be greater than 20 characters")
    private String username;

    @NotEmpty(message = "Password can not be empty")
    @Min(value = 4, message = "Password should not be less than 6 characters")
    @Max(value = 20, message = "Password should not be greater than 20 characters")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();


}
