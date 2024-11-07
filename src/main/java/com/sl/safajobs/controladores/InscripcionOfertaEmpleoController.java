package com.sl.safajobs.controladores;

import com.sl.safajobs.dto.InscripcionOfertaEmpleoDTO;
import com.sl.safajobs.dto.OfertaEmpleoDTO;
import com.sl.safajobs.modelos.InscripcionOfertaEmpleo;
import com.sl.safajobs.servicios.InscripcionOfertaEmpleoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/inscripcion/oferta/empleo")
public class InscripcionOfertaEmpleoController {


    private InscripcionOfertaEmpleoService inscripcionOfertaEmpleoService;

    @PostMapping("/join")
    public OfertaEmpleoDTO inscribirse(@RequestBody InscripcionOfertaEmpleoDTO dto){
        return inscripcionOfertaEmpleoService.inscribirse(dto);

    }



}
