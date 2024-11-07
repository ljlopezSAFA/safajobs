package com.sl.safajobs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfertaEmpleoDTO {
    private String empresa;
    private String titular;
    private String puesto;
    private List<String> inscritos;
    private Integer numInscritos;
}
