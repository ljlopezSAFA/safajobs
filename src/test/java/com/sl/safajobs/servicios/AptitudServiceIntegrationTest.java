package com.sl.safajobs.servicios;

import com.sl.safajobs.enumerados.TipoAptitud;
import com.sl.safajobs.modelos.Aptitud;
import com.sl.safajobs.repositorios.AptitudRepository;
import com.sl.safajobs.repositorios.PerfilRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AptitudServiceIntegrationTest {

    @InjectMocks
    private AptitudService service; //REAL

    @Mock
    private AptitudRepository repository; //SIMULADO



    @Test
    public void testFindAllIntegracion(){

        //GIVEN
        List<Aptitud> aptitudesPorDefecto = new ArrayList<>();
        Aptitud a1 = new Aptitud();
        a1.setTitulo("PHP");
        a1.setDetalle("Conocimientos en PHP");
        a1.setTipoAptitud(TipoAptitud.TECNOLOGICAS);

        Aptitud a2 = new Aptitud();
        a2.setTitulo("JAVA");
        a2.setDetalle("Conocimientos en JAVA");
        a2.setTipoAptitud(TipoAptitud.TECNOLOGICAS);

        aptitudesPorDefecto.add(a1);
        aptitudesPorDefecto.add(a2);

        Mockito.when(repository.findAll()).thenReturn(aptitudesPorDefecto);


        //WHEN
        List<Aptitud> aptituds = service.getAllAptitudes();

        //THEN
        assertEquals(2, aptituds.size());
        Mockito.verify(repository, Mockito.times(1)).findAll();

    }


    @Test
    public void testBuscarPorIdIntegracion() throws Exception {

        //GIVEN
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));

        //THEN && WHEN
        assertThrows( Exception.class, ()-> service.getById(3));
        Mockito.verify(repository, Mockito.times(1)).findById(3);


    }





}
