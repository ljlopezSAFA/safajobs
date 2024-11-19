package com.sl.safajobs.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpresaDTO {
    private Integer id;
    private String nombre;
    private String cif;
    private String fechaFundacion;
    private Boolean esTecnologica;
    private String foto;
}
