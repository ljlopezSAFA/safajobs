package com.sl.safajobs.controladores;

import com.sl.safajobs.dto.OfertaEmpleoCrearDTO;
import com.sl.safajobs.modelos.OfertaEmpleo;
import com.sl.safajobs.servicios.OfertaEmpleoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ofertas-empleo")
@AllArgsConstructor
public class OfertaEmpleoController {


    private OfertaEmpleoService ofertaEmpleoService;

    @GetMapping("/all")
    public List<OfertaEmpleo> getAll(){
        return ofertaEmpleoService.getAll();
    }


    @PostMapping("/crear")
    public OfertaEmpleo crear(@RequestBody  OfertaEmpleoCrearDTO ofertaEmpleoCrearDTO){
        return ofertaEmpleoService.crearNueva(ofertaEmpleoCrearDTO);
    }


    @PutMapping("/editar/{id}")
    public OfertaEmpleo crear(@RequestBody  OfertaEmpleoCrearDTO ofertaEmpleoCrearDTO,
                              @PathVariable Integer id){
        return ofertaEmpleoService.editar(ofertaEmpleoCrearDTO, id);
    }





}