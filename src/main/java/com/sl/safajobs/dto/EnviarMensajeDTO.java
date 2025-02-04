package com.sl.safajobs.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnviarMensajeDTO {


    private String texto;
    private Integer idReceptor;
}
