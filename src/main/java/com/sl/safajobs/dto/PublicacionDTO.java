package com.sl.safajobs.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionDTO {
    private String texto;
    private String fecha;
    private String imageUrl;
    private String nombrePerfil;
    private String fotoPerfil;
    private String nombreEmpresa;
    private String fotoEmpresa;
}
