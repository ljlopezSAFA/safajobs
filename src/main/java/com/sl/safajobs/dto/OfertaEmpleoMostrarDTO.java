package com.sl.safajobs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfertaEmpleoMostrarDTO {

    private Integer id;
    private String titular;
    private String puesto;
    private String requisitos;
    private Double remuneracion;
    private Double remuneracionMinima;
    private Double remuneracionMaxima;
    private EmpresaDTO empresa;


}
