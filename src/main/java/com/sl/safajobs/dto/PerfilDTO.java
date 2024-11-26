package com.sl.safajobs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilDTO {
    private  String nombre;
    private String apellidos;
    private String puesto;
    private String mail;
    private String foto;
}
