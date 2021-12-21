package com.GoSchool.Auth.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Project can not be empty")
    @Min(value = 6, message = "Project name should be at least 6 characters long")
    @Max(value = 20, message = "Project name should not be more than 20 characters long")
    private String projectName;
    @NotEmpty(message = "Project type can't be empty")
    private String projectType;
    @Min(value = 10, message = "Project description can not be less than 10 characters")
    @Max(value = 500, message = "Project description can not be more than 500 characters")
    private String description;
    @NotEmpty(message = "Owner id can not be empty")
    private Long ownerId;
}
