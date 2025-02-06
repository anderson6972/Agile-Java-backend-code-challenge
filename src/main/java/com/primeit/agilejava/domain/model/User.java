package com.primeit.agilejava.domain.model;

import com.primeit.agilejava.domain.common.CustomGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa el modelo de dominio de un usuario.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;  // Identificador único del usuario
    private String name;
    private String email;
    private CustomGender gender;  // Enum común
    private String photo;
    private String country;
    private String state;
    private String city;
}
