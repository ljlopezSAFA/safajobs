package com.sl.safajobs.controladores;

import com.sl.safajobs.dto.LoginDTO;
import com.sl.safajobs.dto.RegistroDTO;
import com.sl.safajobs.dto.RespuestaDTO;
import com.sl.safajobs.modelos.Usuario;
import com.sl.safajobs.security.TokenDataDTO;
import com.sl.safajobs.servicios.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private UsuarioService service;


    @PostMapping("/registro/perfil")
    public Usuario registro(@RequestBody RegistroDTO registroDTO){
        return service.registrarUsuario(registroDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<RespuestaDTO> registro(@RequestBody LoginDTO dto){
        return service.login(dto);
    }


}
