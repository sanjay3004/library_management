package com.example.library_management.dto;

import com.example.library_management.enums.Department;
import com.example.library_management.enums.Gender;
import com.example.library_management.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequestDTO {

    @NotBlank @NotNull
    String name;
    @Email
    String email;
    @NotBlank @NotNull
    String password;
    @NotBlank @NotNull
    String registerNO;
    Department dept;
    @NotBlank @NotNull @Min(1) @Max(4)
    int year;
}
