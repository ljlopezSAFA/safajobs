//package com.sl.safajobs.servicios;
//
//import com.sl.safajobs.dto.PerfilCrearDTO;
//import com.sl.safajobs.dto.PerfilDTO;
//import com.sl.safajobs.enumerados.TipoAptitud;
//import com.sl.safajobs.mappers.PerfilMapper;
//import com.sl.safajobs.modelos.Aptitud;
//import com.sl.safajobs.modelos.Perfil;
//import com.sl.safajobs.repositorios.PerfilRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class PerfilServiceIntegracionTest {
//
//
//    @InjectMocks
//    private PerfilService perfilService;
//
//    @Mock
//    private PerfilRepository perfilRepository;
//
//    @Mock
//    private AptitudService aptitudService;
//
//    @Mock
//    private PerfilMapper perfilMapper;
//
//
//
//    @Test
//    @DisplayName("Test de Integracion Perfil Service 1 -> Test crear perfil inválido")
//    public void testCrearPerfilInvalidoIntegracion(){
//
//        //GIVEN
//        PerfilCrearDTO dto = new PerfilCrearDTO();
//        dto.setNombre("Pepito");
//        dto.setApellidos("Martínez");
//        dto.setFechaNacimiento("1980-05-05");
//        dto.setMail("mailinvalido.es");
//        dto.setDni("12345678A");
//        dto.setAptitudes(new ArrayList<>());
//
//
//        //WHEN && THEN
//        Exception exception = assertThrows(Exception.class, () -> perfilService.guardar(dto));
//        assertEquals("El mail introducido no es válido", exception.getMessage());
//        verifyNoInteractions(perfilRepository, aptitudService);
//    }
//
//
//
//    @Test
//    @DisplayName("Test de Integracion Perfil Service 2 -> Test crear perfil válido")
//    public void testCrearPerfilPositivoIntegracion() throws Exception {
//
//
//        //GIVEN
//        PerfilCrearDTO dto = new PerfilCrearDTO();
//        dto.setNombre("Pepito");
//        dto.setApellidos("Martínez");
//        dto.setPuesto("Profesor");
//        dto.setFechaNacimiento("1980-05-05");
//        dto.setMail("mailinvalido@gmail.es");
//        dto.setDni("12345678A");
//        dto.setAptitudes(List.of(1));
//
//        Aptitud aptitud1 = new Aptitud(1, TipoAptitud.TECNOLOGICAS, "Java", "Conocimientos en Java");
//        when(aptitudService.getById(1)).thenReturn(aptitud1);
//
//        Perfil perfilGuardado = new Perfil();
//        perfilGuardado.setNombre("Pepito");
//        when(perfilRepository.save(any(Perfil.class))).thenReturn(perfilGuardado);
//
//        //WHEN
//        Perfil perfil = perfilService.guardar(dto);
//
//        //THEN
//        assertEquals(perfilGuardado, perfil);
//        verify(aptitudService).getById(1);
//        verify(perfilRepository).save(any(Perfil.class));
//    }
//
//
//
//
//    @Test
//    @DisplayName("Test de Integracion Perfil Service 3 -> Test buscar perfiles por nombre positivo")
//    public void testBuscarPerfilesConNombrePositivoIntegracion(){
//
//        //GIVEN
//
//        List<Perfil> perfiles = new ArrayList<>();
//
//        Perfil perfilEsperado1 = new Perfil();
//        perfilEsperado1.setId(1);
//        perfilEsperado1.setNombre("Antonio");
//        Perfil perfilEsperado2 = new Perfil();
//        perfilEsperado2.setId(2);
//        perfilEsperado2.setNombre("Antonia");
//
//        perfiles.add(perfilEsperado1);
//        perfiles.add(perfilEsperado2);
//        when(perfilRepository.buscar(any(String.class))).thenReturn(perfiles);
//        when(perfilMapper.toDTO(any(List.class))).thenReturn(List.of(new PerfilDTO(), new PerfilDTO()));
//
//
//        //WHEN
//        List<PerfilDTO> perfilesObtenidos = perfilService.buscar("ejemploBusqueda");
//
//        //THEN
//        assertEquals(2, perfilesObtenidos.size());
//        verify(perfilRepository).buscar(any(String.class));
//        verify(perfilMapper).toDTO(perfiles);
//
//
//
//    }
//
//
//
//
//
//
//
//
//}
