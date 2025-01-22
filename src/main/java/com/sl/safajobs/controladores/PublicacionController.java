package com.sl.safajobs.controladores;

import com.sl.safajobs.dto.PublicacionCrearDTO;
import com.sl.safajobs.dto.PublicacionDTO;
import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.security.JWTService;
import com.sl.safajobs.servicios.PublicacionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publicacion")
@AllArgsConstructor
public class PublicacionController {

    private PublicacionService publicacionService;
    private JWTService jwtService;


    @GetMapping("/all")
    public List<PublicacionDTO> obtenerPublicaciones(@RequestHeader("Authorization") String token){
        return publicacionService.getAll();
    }


    @GetMapping("/all/me")
    public List<PublicacionDTO> obtenerMisPublicaciones(@RequestHeader("Authorization") String token){
        Perfil perfilLogueado = jwtService.extraerPerfilToken(token);
        return publicacionService.getByPerfil(perfilLogueado);

    }

    @GetMapping("/{id}")
    public PublicacionDTO getById(@PathVariable Integer id){
        return publicacionService.getByID(id);
    }

    @DeleteMapping
    public void eliminar(@RequestParam Integer id){
        publicacionService.eliminar(id);
    }



    @PostMapping()
    public PublicacionDTO guardar(@RequestBody PublicacionCrearDTO dto){
        return publicacionService.guardarPublicacion(dto);
    }




}
