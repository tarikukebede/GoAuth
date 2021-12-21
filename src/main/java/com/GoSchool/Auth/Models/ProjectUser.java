package com.GoSchool.Auth.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "UserName can not be empty")
    @Min(value = 6, message = "Username should be less than 6 characters")
    @Max(value = 20, message = "username should not be greater than 20 characters")
    private String username;

    @NotEmpty(message = "Password can not be empty")
    @Min(value = 6, message = "Password should be less than 6 characters")
    @Max(value = 20, message = "Password should not be greater than 20 characters")
    private String password;
}
