package com.sl.safajobs.servicios;

import com.sl.safajobs.dto.*;
import com.sl.safajobs.modelos.Mensaje;
import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.repositorios.MensajeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


    public List<ChaDBDTO> getConversaciones(Perfil perfil){
        List<Object> cosas =  mensajeRepository.getConversaciones(perfil.getId());
        List<ChaDBDTO> chats = new ArrayList<>();
        for(Object o : cosas) {
            Object[] oa = (Object[]) o;
            Timestamp tm = (Timestamp) oa[1];
            PerfilDTO perfilDTO = perfilService.getByIdDTO((Integer) oa[0]);
            chats.add(new ChaDBDTO((Integer) oa[0],tm.toLocalDateTime() , (String) oa[2],perfilDTO));
        }

        return chats;
    }


    public List<MensajeDTO> getByEmisorReceptor(Integer idPerfil1, Integer idPerfil2){

        List<MensajeDTO> dtos = new ArrayList<>();
        mensajeRepository.getByEmisorYReceptor(idPerfil1, idPerfil2).stream().forEach(
                m-> dtos.add(new MensajeDTO(m.getId(), m.getMensaje(),
                        m.getEmisor().getId(), m.getReceptor().getId(), m.getFecha().toString()))
        );


        return dtos;
    }


    public RespuestaDTO enviarMensaje(Perfil perfil, EnviarMensajeDTO enviarMensajeDTO) {


        Mensaje mensaje = new Mensaje();
        mensaje.setFecha(LocalDateTime.now());
        mensaje.setEmisor(perfil);
        mensaje.setReceptor(perfilService.getById(enviarMensajeDTO.getIdReceptor()));
        mensaje.setMensaje(enviarMensajeDTO.getTexto());

        mensajeRepository.save(mensaje);

        return RespuestaDTO.builder().estado(200).mensaje("Mensaje Enviado").build();
    }
}
