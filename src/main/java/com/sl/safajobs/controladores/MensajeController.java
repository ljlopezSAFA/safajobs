package com.sl.safajobs.controladores;

import com.sl.safajobs.dto.ConversacionDTO;
import com.sl.safajobs.servicios.MensajeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mensaje")
@AllArgsConstructor
public class MensajeController {

    private MensajeService service;


    @GetMapping("/conversaciones")
    public List<ConversacionDTO> getConversacionesByperfil(@RequestParam Integer idPerfil){
        return service.getConverdacionesByIdPerfil(idPerfil);
    }




}
