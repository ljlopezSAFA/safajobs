package com.sl.safajobs.servicios;


import com.sl.safajobs.dto.PerfilCrearDTO;
import com.sl.safajobs.dto.PerfilDTO;
import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.repositorios.PerfilRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class PerfilServiceTest {


    @Autowired
    private PerfilService service;

    @Autowired
    private PerfilRepository repository;


    @BeforeEach
    public void inicializarBaseDatos() throws Exception {
        Perfil dto = new Perfil();
        dto.setNombre("Antonio");
        dto.setApellidos("López");
        dto.setFechaNacimiento(LocalDate.of(1980,1,1));
        dto.setMail("antonio@es");
        dto.setDni("12345678A");
        dto.setPuesto("Empleado");
        dto.setAptitudes(new HashSet<>());

        repository.save(dto);

        PerfilCrearDTO dto1 = new PerfilCrearDTO();
        dto1.setNombre("Enrique");
        dto1.setApellidos("Martínez");
        dto1.setFechaNacimiento("1980-05-05");
        dto1.setMail("enrique@es");
        dto1.setDni("12345678A");
        dto1.setPuesto("Empleado");
        dto1.setAptitudes(new ArrayList<>());

        PerfilCrearDTO dto2 = new PerfilCrearDTO();
        dto2.setNombre("Pepito");
        dto2.setApellidos("Ordóñez");
        dto2.setFechaNacimiento("1980-05-05");
        dto2.setMail("pordonez@es");
        dto2.setDni("12345678A");
        dto2.setPuesto("Empleado");
        dto2.setAptitudes(new ArrayList<>());
        service.guardar(dto1);
        service.guardar(dto2);

    }







    @Test
    @DisplayName("Test 1 -> Crear perfil con datos incorrectos")
    @Tag("Perfil")
    public void testCrearPerfilMailNegativo() {

        //GIVEN
        PerfilCrearDTO dto = new PerfilCrearDTO();
        dto.setNombre("Pepito");
        dto.setApellidos("Martínez");
        dto.setFechaNacimiento("1980-05-05");
        dto.setMail("mailinvalido.es");
        dto.setDni("12345678A");
        dto.setAptitudes(new ArrayList<>());

        //WHEN && THEN
        Exception exception = assertThrows(Exception.class, () -> service.guardar(dto));
    }


    @Test
    @DisplayName("Test 2 -> Crear perfil con datos correctos")
    @Tag("Perfil")
    public void testCrearPerfilPositivo() throws Exception {

        //GIVEN
        PerfilCrearDTO dto = new PerfilCrearDTO();
        dto.setNombre("Juan");
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




    @Test
    @DisplayName("Test 3 -> Probar a buscar perfil inexistente")
    public void testPerfilInexistente() throws Exception {

        //GIVEN

        //WHEN
        List<PerfilDTO> perfilesEncontrados = service.buscar("Clara");

        //THEN
        assertTrue(perfilesEncontrados.isEmpty());


    }


    @Test
    @DisplayName("Test 4 -> Probar a buscar perfil por Nombre")
    public void testBucarPerfilNombre(){

        //GIVEN

        //WHEN
        List<PerfilDTO> perfilesEncontrados = service.buscar("Antonio");


        //THEN
        assertFalse(perfilesEncontrados.isEmpty());
        assertEquals(1, perfilesEncontrados.size());
        assertEquals("Antonio", perfilesEncontrados.get(0).getNombre());
    }



    @Test
    @DisplayName("Test 5 -> Crear perfil con nombre en blanco")
    public void testCrearPerfilSinNombreNegativo() {

        //GIVEN
        PerfilCrearDTO dto = new PerfilCrearDTO();
        dto.setNombre("Luis");
        dto.setApellidos("");
        dto.setFechaNacimiento("1980-05-05");
        dto.setMail("mailinvalido@hola.es");
        dto.setDni("12345678A");
        dto.setAptitudes(new ArrayList<>());

        //WHEN
        //THEN
        assertThrows(ConstraintViolationException.class, () -> service.guardar(dto));

    }













}
