package com.sl.safajobs;

import com.sl.safajobs.modelos.Publicacion;
import com.sl.safajobs.repositorios.PublicacionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SafajobsApplicationTests {

    @Autowired
    private PublicacionRepository publicacionRepository;



    @Test
    void testFindAll(){

        List<Publicacion> publicaciones = publicacionRepository.findAll();

        for(Publicacion p : publicaciones){
            System.out.println(p.getId());
            System.out.println(p.getFecha());
            System.out.println(p.getTexto());
        }



    }







}
