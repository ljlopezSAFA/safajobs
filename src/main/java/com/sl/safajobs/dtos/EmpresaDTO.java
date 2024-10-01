package com.sl.safajobs.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpresaDTO {
    private Integer id;
    private String nombre;
    private String cif;
    private LocalDate fechaFundacion;
    private Boolean esTecnologica;
}
