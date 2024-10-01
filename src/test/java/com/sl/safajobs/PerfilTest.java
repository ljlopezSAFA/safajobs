package com.sl.safajobs;

import com.sl.safajobs.modelos.Aptitud;
import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.servicios.AptitudService;
import com.sl.safajobs.servicios.PerfilService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PerfilTest {

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private AptitudService aptitudService;


    @Test
    @Transactional
    void testFindALL(){
        List<Perfil> perfils = perfilService.getAll();
        for(Perfil p : perfils){
            System.out.println(p.toString());
        }
    }


    @Test
    void testGuardar(){
        Perfil perfil = new Perfil();
        perfil.setNombre("Julio");
        perfil.setApellidos("Maldonado");
        perfil.setDni("12345678P");
        perfil.setFechaNacimiento(LocalDate.now());
        perfil.setMail("jmaldonado@safareyes.es");

        Perfil perfilguardado = perfilService.guardar(perfil);
        System.out.println(perfilguardado.toString());

    }


    @Test
    @Transactional
    void testEditar(){
        Perfil perfil = perfilService.getById(2);
        perfil.setNombre("Julio");
        perfil.setApellidos("Maldonado Maldini");
        perfil.setDni("12345678O");
        perfil.setFechaNacimiento(LocalDate.now());
        perfil.setMail("jmaldini@safareyes.es");

        Aptitud aptitud = aptitudService.getById(1);
        perfil.getAptitudes().add(aptitud);


        Perfil perfilguardado = perfilService.guardar(perfil);
        System.out.println(perfilguardado.toString());

    }


    @Test
    @Transactional
    void testEliminar(){
        Perfil perfil = perfilService.getById(2) ;
        perfilService.eliminar(perfil);
    }



}
