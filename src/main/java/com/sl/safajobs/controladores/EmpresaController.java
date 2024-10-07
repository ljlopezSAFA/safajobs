package com.sl.safajobs.controladores;

import com.sl.safajobs.dtos.EmpresaDTO;
import com.sl.safajobs.modelos.Empresa;
import com.sl.safajobs.repositorios.EmpresaRepository;
import com.sl.safajobs.servicios.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/empresas")
@AllArgsConstructor
public class EmpresaController {

    private EmpresaService empresaService;


    @GetMapping
    public List<EmpresaDTO> getEmpresas(){
        return empresaService.getAll();
    }

}
