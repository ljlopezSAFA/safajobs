package com.sl.safajobs.servicios;


import com.sl.safajobs.dto.PerfilCrearDTO;
import com.sl.safajobs.enumerados.TipoAptitud;
import com.sl.safajobs.modelos.Aptitud;
import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.repositorios.PerfilRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PerfilServiceIntegrationTest {

    @InjectMocks
    private PerfilService perfilService; // Servicio real que estamos probando

    @Mock
    private PerfilRepository perfilRepository; // Mock del repositorio

    @Mock
    private AptitudService aptitudService; // Mock del servicio de aptitudes


    @Test
    @DisplayName("Crear perfil con email inválido lanza excepción")
    void testGuardarPerfilEmailInvalido() {
        // GIVEN: Un DTO con email inválido
        PerfilCrearDTO dto = new PerfilCrearDTO();
        dto.setNombre("Juan");
        dto.setApellidos("Pérez");
        dto.setFechaNacimiento("1990-01-01");
        dto.setMail("email_invalido.es"); // Email incorrecto
        dto.setDni("12345678A");
        dto.setAptitudes(List.of());

        // WHEN & THEN: Lanza excepción
        Exception exception = assertThrows(Exception.class, () -> perfilService.guardar(dto));
        assertEquals("El mail introducido no es válido", exception.getMessage());

        // Verificamos que no se llamó al repositorio
        verifyNoInteractions(perfilRepository);
    }

    @Test
    @DisplayName("Crear perfil con datos válidos se guarda correctamente")
    void testGuardarPerfilValido() throws Exception {
        // GIVEN
        //Un DTO con datos válidos
        PerfilCrearDTO dto = new PerfilCrearDTO();
        dto.setNombre("Juan");
        dto.setApellidos("Pérez");
        dto.setFechaNacimiento("1990-01-01");
        dto.setMail("juan@example.com"); // Email válido
        dto.setDni("12345678A");
        dto.setAptitudes(List.of(1, 2)); // IDs de aptitudes
        // Simulamos las aptitudes que devuelve aptitudService
        Aptitud aptitud1 = new Aptitud(1, TipoAptitud.TECNOLOGICAS ,"Java", "Conocimientos en Java");
        Aptitud aptitud2 = new Aptitud(2, TipoAptitud.TECNOLOGICAS,"Spring", "Conocimientos en Spring");
        when(aptitudService.getById(1)).thenReturn(aptitud1);
        when(aptitudService.getById(2)).thenReturn(aptitud2);
        // Simulamos el repositorio devolviendo un perfil guardado
        Perfil perfilGuardado = new Perfil();
        perfilGuardado.setId(1);
        perfilGuardado.setNombre("Juan");
        perfilGuardado.setMail("juan@example.com");
        perfilGuardado.setAptitudes(Set.of(aptitud1, aptitud2));
//        when(perfilRepository.save(any(Perfil.class))).thenReturn(perfilGuardado);

        // WHEN: Guardamos el perfil
        Perfil resultado = perfilService.guardar(dto);

        // THEN
        // Verificamos los resultados
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        assertEquals("juan@example.com", resultado.getMail());
        assertEquals(2, resultado.getAptitudes().size());
        // Verificamos que se llamaron las dependencias
        verify(aptitudService).getById(1);
        verify(aptitudService).getById(2);
        verify(perfilRepository).save(any(Perfil.class));
    }



}
