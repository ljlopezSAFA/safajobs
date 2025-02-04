package com.sl.safajobs.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespuestaDTO {
    private Integer estado;
    private String token;
    private String avatar;
    private String mensaje;
    private Object cuerpo;
}
