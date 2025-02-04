package com.sl.safajobs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeDTO {

    private Integer id;
    private String mensaje;
    private Integer idEmisor;
    private Integer idReceptor;
    private String fecha;

}
