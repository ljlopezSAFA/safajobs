package com.sl.safajobs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfertaEmpleoCrearDTO {
    private String titular;
    private String puesto;
    private String requisitos;
    private Double remuneracionMaxima;
    private Double remuneracionMinima;
    private Double remuneracion;
    private Integer idEmpresa;

}
