package com.sl.safajobs.servicios;

import com.sl.safajobs.enumerados.TipoAptitud;
import com.sl.safajobs.modelos.Aptitud;
import com.sl.safajobs.repositorios.AptitudRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class AptitudServiceTest {

    @Autowired
    private AptitudService service;

    @Autowired
    private AptitudRepository repository;



    @BeforeEach
    public void  inicializarDatos(){

        Aptitud a1 = new Aptitud();
        a1.setTitulo("PHP");
        a1.setDetalle("Conocimientos en PHP");
        a1.setTipoAptitud(TipoAptitud.TECNOLOGICAS);

        Aptitud a2 = new Aptitud();
        a2.setTitulo("JAVA");
        a2.setDetalle("Conocimientos en JAVA");
        a2.setTipoAptitud(TipoAptitud.TECNOLOGICAS);

        repository.save(a1);
        repository.save(a2);
    }





    @Test
    public void testFindAll(){

        //GIVEN

        //WHEN
        List<Aptitud> aptitudes = service.getAllAptitudes();

        //THEN
        assertEquals(2,aptitudes.size());
    }


    @Test
    public void testFindById() throws Exception {

        //GIVEN

        //WHEN
        Aptitud aptitud = service.getById(3);

        //THEN
        assertNotNull(aptitud);
        assertEquals("DJANGO", aptitud.getTitulo());

    }


    @Test
    public void testFindByIdNegatigo() throws Exception {

        //GIVEN

        //WHEN
        //THEN
        Exception exception = assertThrows(Exception.class, ()-> service.getById(10));
        assertEquals("No existe ninguna aptitud con el id indicado", exception.getMessage());

    }


    @Test
    public  void testGuardarAptitud(){

        //GIVEN
        Aptitud aptitud = new Aptitud();
        aptitud.setTitulo("PHP");
        aptitud.setDetalle("Conocimientos en PHP");
        aptitud.setTipoAptitud(TipoAptitud.TECNOLOGICAS);

        //WHEN
        Aptitud aptitudGuardada = service.guardar(aptitud);

        //THEN
        assertNotNull(aptitudGuardada);
        assertNotNull(aptitudGuardada.getId());

    }


    @Test
    public  void testGuardarAptitudNegativo(){

        //GIVEN
        Aptitud aptitud = new Aptitud();
        aptitud.setTitulo("");
        aptitud.setDetalle("Conocimientos en PHP");
        aptitud.setTipoAptitud(TipoAptitud.TECNOLOGICAS);

        //WHEN
        Aptitud aptitudGuardada = service.guardar(aptitud);

        // THEN
       assertNull(aptitudGuardada);

    }











}
