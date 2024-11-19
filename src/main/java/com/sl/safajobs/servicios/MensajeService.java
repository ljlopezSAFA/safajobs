package com.sl.safajobs.servicios;

import com.sl.safajobs.dto.ConversacionDTO;
import com.sl.safajobs.modelos.Mensaje;
import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.repositorios.MensajeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MensajeService {

    private MensajeRepository mensajeRepository;
    private PerfilService perfilService;



    public List<ConversacionDTO> getConverdacionesByIdPerfil(Integer idPerfil){


        List<Integer> perfilesConversaciones = mensajeRepository.getConversacionesActivas(idPerfil);
        List<ConversacionDTO> conversacionDTOS = new ArrayList<>();

        for(Integer id: perfilesConversaciones) {

            Perfil p = perfilService.getById(id);
            Mensaje ultimoMensaje = mensajeRepository.getUltimoMensaje(idPerfil,p.getId());

            ConversacionDTO dto = new ConversacionDTO();
            dto.setNombrePerfil(p.getNombre().concat(" ").concat(p.getApellidos()) );
            dto.setFotoPerfil(p.getFoto());
            dto.setUltimoMensaje(ultimoMensaje.getMensaje());
            dto.setFechaUltimoMensaje(ultimoMensaje.getFecha());
            conversacionDTOS.add(dto);
        }


        return conversacionDTOS;

    }



}
