package com.sl.safajobs.controladores;

import com.sl.safajobs.dto.PublicacionCrearDTO;
import com.sl.safajobs.dto.PublicacionDTO;
import com.sl.safajobs.servicios.PublicacionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publicacion")
@AllArgsConstructor
public class PublicacionController {

    private PublicacionService publicacionService;


    @GetMapping("/all")
    public List<PublicacionDTO> obtenerPublicaciones(){
        return publicacionService.getAll();
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
