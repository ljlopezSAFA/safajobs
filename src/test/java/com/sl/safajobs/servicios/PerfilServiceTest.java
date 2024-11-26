package com.sl.safajobs.servicios;


import com.sl.safajobs.dto.PerfilCrearDTO;
import com.sl.safajobs.modelos.Perfil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PerfilServiceTest {


    @Autowired
    private PerfilService service;



    @Test
    @DisplayName("Test de crear perfil Negativo")
    @Tag("Perfil")
    public void testCrearPerfilMailNegativo() throws Exception {

        //GIVEN
        PerfilCrearDTO dto = new PerfilCrearDTO();
        dto.setNombre("Pepito");
        dto.setApellidos("Martínez");
        dto.setFechaNacimiento("1980-05-05");
        dto.setMail("mailinvalido.es");
        dto.setDni("12345678A");
        dto.setAptitudes(new ArrayList<>());

        //WHEN
        //THEN
        Exception exception = assertThrows(Exception.class, () -> service.guardar(dto));
        assertEquals("El mail introducido no es válido",exception.getMessage());
    }


    @Test
    @DisplayName("Test de crear perfil datos correctos")
    @Tag("Perfil")
    public void testCrearPerfilPositivo() throws Exception {

        //GIVEN
        PerfilCrearDTO dto = new PerfilCrearDTO();
        dto.setNombre("Pepito");
        dto.setApellidos("Martínez");
        dto.setFechaNacimiento("1980-05-05");
        dto.setMail("mailinvalido@es");
        dto.setDni("12345678A");
        dto.setPuesto("Empleado");
        dto.setAptitudes(new ArrayList<>());

        //WHEN
        Perfil p =  service.guardar(dto);


        //THEN
        assertNotNull(p);
        assertEquals(dto.getNombre(), p.getNombre());


    }






}
