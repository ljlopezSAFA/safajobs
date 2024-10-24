package com.sl.safajobs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionCrearDTO {
    private Integer id;
    private String texto;
    private String imageUrl;
    private Integer id_perfil;
    private Integer id_empresa;
}
