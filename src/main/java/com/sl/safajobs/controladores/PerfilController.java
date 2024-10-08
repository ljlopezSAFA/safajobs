package com.sl.safajobs.controladores;


import com.sl.safajobs.dto.PerfilCrearDTO;
import com.sl.safajobs.dto.PerfilDTO;
import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.servicios.PerfilService;
import lombok.AllArgsConstructor;
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
    public Perfil getByIdPath(@PathVariable Integer id){
        Perfil perfil = perfilService.getById(id);
        return perfil;
    }


    @PostMapping()
    public Perfil guardar(@RequestBody PerfilCrearDTO perfil){
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
