package com.sl.safajobs.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienciaEducativaDTO {
    private String centroEducativo;
    private String curso;
    private String fechaInicio;
    private String fechaFin;
}
