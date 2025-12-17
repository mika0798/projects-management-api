package com.project.company_projects_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    @Size(min=1,max=50,message="First name must be between 1 and 50 characters")
    @NotBlank(message="First name cannot be blank")
    private String firstName;

    @Size(min=1,max=50,message="Last name must be between 1 and 50 characters")
    @NotBlank(message="Last name cannot be blank")
    private String lastName;

    @NotBlank(message="Email cannot be blank")
    @Email(message="Invalid email address")
    private String email;
}
