package com.sl.safajobs.controladores;

import com.sl.safajobs.modelos.Aptitud;
import com.sl.safajobs.servicios.AptitudService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aptitud")
@AllArgsConstructor
public class AptitudController {

    private AptitudService aptitudService;

    @GetMapping("/all")
    public List<Aptitud> getAll(){
        return aptitudService.getAllAptitudes();
    }

    @GetMapping
    public Aptitud getById(@RequestParam Integer id){
        return aptitudService.getById(id);
    }


    @PostMapping
    public Aptitud guardar(@RequestBody Aptitud aptitud){
        return aptitudService.guardar(aptitud);
    }


    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id){
        return aptitudService.eliminar(id);
    }






}
