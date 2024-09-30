package com.sl.safajobs;

import com.sl.safajobs.modelos.Aptitud;
import com.sl.safajobs.modelos.Empresa;
import com.sl.safajobs.repositorios.AptitudRepository;
import com.sl.safajobs.servicios.EmpresaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SafajobsApplicationTests {

    @Autowired
    private EmpresaService empresaService;

    @Test
    void testFindAllAptitudes() {
//        List<Empresa> empresas = empresaService.getEmpresasPorCIF("B32453219");
//        for(Empresa a :empresas){
//            System.out.println(a.toString());
//        }
    }

}
