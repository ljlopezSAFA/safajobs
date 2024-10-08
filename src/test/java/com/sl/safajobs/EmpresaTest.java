package com.sl.safajobs;

import com.sl.safajobs.dto.EmpresaDTO;
import com.sl.safajobs.modelos.Empresa;
import com.sl.safajobs.servicios.EmpresaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class EmpresaTest {

    @Autowired
    private EmpresaService empresaService;



    @Test
    void testCrearEmpresa(){
        Empresa  empresa = new Empresa();
        empresa.setCif("B18754789");
        empresa.setNombre("DUO TAPAS");
        empresa.setMail("duotapas@gmail.com");
        empresa.setEsTecnologica(false);
        empresa.setFechaFundacion(LocalDate.of(1999,10,10));
        Empresa empresaGuardada = empresaService.guardar(empresa);
        System.out.println(empresaGuardada.toString());
    }

    @Test
    void testEditarEmpresa(){
        Empresa  empresa = empresaService.getById(3);
        empresa.setCif("B18754789");
        empresa.setNombre("DUO TAPAS ALAMEDA");
        empresa.setMail("duotapasalameda@gmail.com");
        empresa.setEsTecnologica(false);
        empresa.setFechaFundacion(LocalDate.of(1999,8,10));
        Empresa empresaGuardada = empresaService.guardar(empresa);
        System.out.println(empresaGuardada.toString());
    }


    @Test
    void testElimnarEmpresa(){
        empresaService.eliminar(3);
    }


    @Test
    void testBuscarTodasEmpresas(){
        List<EmpresaDTO> empresas = empresaService.getAll();
        for(EmpresaDTO e: empresas){
            System.out.println(e.getNombre());
        }
    }


}
