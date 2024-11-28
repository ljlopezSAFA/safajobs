package com.sl.safajobs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilCrearDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private  String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellidos;

    @Email(message = "El email introducido no es válido")
    private String mail;

    private String puesto;
    private String dni;

//    @Past(message = "La fecha de nacimiento debe de ser pasada")
    private String fechaNacimiento;
    private List<Integer>  aptitudes;
}
