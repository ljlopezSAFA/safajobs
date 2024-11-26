package com.sl.safajobs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilCrearDTO {
    private  String nombre;
    private String apellidos;
    private String mail;
    private String puesto;
    private String dni;
    private String fechaNacimiento;
    private List<Integer>  aptitudes;
}
