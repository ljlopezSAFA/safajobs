package com.sl.safajobs.controladores;


import com.sl.safajobs.dto.PerfilCrearDTO;
import com.sl.safajobs.dto.PerfilDTO;
import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.servicios.PerfilService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil")
@AllArgsConstructor
public class PerfilController {


    private PerfilService perfilService;


    @GetMapping("/all")
    public List<PerfilDTO> getAllPerfiles(){
        List<PerfilDTO> perfiles = perfilService.getAll();
        return perfiles;
    }


    @GetMapping()
    public Perfil getById(@RequestParam Integer id){
        Perfil perfil = perfilService.getById(id);
        return perfil;
    }

    @GetMapping("/id/{id}")
    public PerfilDTO getByIdPath(@PathVariable Integer id){
        PerfilDTO dto = perfilService.getByIdDTO(id);
        return dto;
    }


    @PostMapping()
    public Perfil guardar(@RequestBody  PerfilCrearDTO perfil) throws Exception {
        Perfil perfilGuardado = perfilService.guardar(perfil);
        return perfilGuardado;
    }


    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return perfilService.eliminar(id);
    }



    @GetMapping("/buscar")
    public List<PerfilDTO> buscar(@RequestParam String busqueda){
        List<PerfilDTO> dtos= perfilService.buscar(busqueda);
        return dtos;
    }




}
