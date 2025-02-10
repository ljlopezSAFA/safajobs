package com.sl.safajobs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienciaLaboralDTO {

    private String puesto;
    private String fechaInicio;
    private String fechaFin;
    private String empresa;
    private String logoEmpresa;

}
