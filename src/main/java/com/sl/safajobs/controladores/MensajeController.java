package com.sl.safajobs.controladores;

import com.sl.safajobs.dto.*;
import com.sl.safajobs.modelos.Mensaje;
import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.security.JWTService;
import com.sl.safajobs.servicios.MensajeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensaje")
@AllArgsConstructor
public class MensajeController {

    private MensajeService service;
    private JWTService jwtService;


    @GetMapping("/conversaciones")
    public List<ConversacionDTO> getConversacionesByperfil(@RequestParam Integer idPerfil){
        return service.getConverdacionesByIdPerfil(idPerfil);
    }

    @GetMapping("/chats")
    public List<ChaDBDTO> getConversacionesByperfil(@RequestHeader("Authorization") String token){
        Perfil perfil = jwtService.extraerPerfilToken(token);
        return service.getConversaciones(perfil);
    }

    @GetMapping("/chat/{idContacto}")
    public List<MensajeDTO> getConversacionesByperfil(@RequestHeader("Authorization") String token,
                                                      @PathVariable Integer idContacto){
        Perfil perfil = jwtService.extraerPerfilToken(token);
        return service.getByEmisorReceptor(perfil.getId(), idContacto);
    }

    @PostMapping("/enviar")
    public RespuestaDTO enviarMensaje(@RequestHeader("Authorization") String token, @RequestBody EnviarMensajeDTO enviarMensajeDTO ){
        Perfil perfil = jwtService.extraerPerfilToken(token);
        return service.enviarMensaje(perfil, enviarMensajeDTO);
    }





}
