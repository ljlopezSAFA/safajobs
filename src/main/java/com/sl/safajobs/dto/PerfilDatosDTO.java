package com.sl.safajobs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilDatosDTO {

    private  String nombre;
    private String apellidos;
    private String puesto;
    private String mail;
    private String foto;
    private String fechaNacimiento;
    private List<ExperienciaEducativaDTO> educacion;
    private List<ExperienciaLaboralDTO> experienciaLaboral;
    private List<AptitudDTO> aptitudes;

}
