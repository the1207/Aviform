package com.gestNutri.dto.resquest;

import com.gestNutri.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UtilisateurResquest(
        @NotBlank @Email String email,
        @NotBlank String motDePasse,
        @NotNull Role role
) {
}
