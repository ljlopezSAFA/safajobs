package com.sl.safajobs.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AptitudDTO {

    private String tipoAptitud;
    private String titulo;
    private String detalle;
}
