package com.sl.safajobs.servicios;

import com.sl.safajobs.dto.InscripcionOfertaEmpleoDTO;
import com.sl.safajobs.dto.OfertaEmpleoDTO;
import com.sl.safajobs.enumerados.Estado;
import com.sl.safajobs.modelos.InscripcionOfertaEmpleo;
import com.sl.safajobs.modelos.OfertaEmpleo;
import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.repositorios.InscripcionOfertaEmpleoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class InscripcionOfertaEmpleoService {

    private InscripcionOfertaEmpleoRepository inscripcionOfertaEmpleoRepository;
    private OfertaEmpleoService ofertaEmpleoService;
    private PerfilService perfilService;



    public OfertaEmpleoDTO inscribirse(InscripcionOfertaEmpleoDTO dto){

        InscripcionOfertaEmpleo entity = new InscripcionOfertaEmpleo();
        Perfil perfil = perfilService.getById(dto.getIdPerfil());
        OfertaEmpleo ofertaEmpleo = ofertaEmpleoService.getById(dto.getIdOfertaEmpleo());

        if(perfil!= null && ofertaEmpleo!= null && !ofertaEmpleo.getInscritos().contains(perfil)){
            entity.setOfertaEmpleo(ofertaEmpleo);
            entity.setPerfil(perfil);
            entity.setEstado(Estado.ENVIADA);
            entity.setFechaIncripcion(LocalDate.now());
            InscripcionOfertaEmpleo inscripcion = inscripcionOfertaEmpleoRepository.save(entity);
            return getOfertaEmpleoDTO(inscripcion);
        } else{
            InscripcionOfertaEmpleo inscripcion = inscripcionOfertaEmpleoRepository.findByPerfilIdAndOfertaEmpleoId(dto.getIdPerfil(), dto.getIdOfertaEmpleo()).orElse(null);
            return getOfertaEmpleoDTO(inscripcion);
        }
    }

    private OfertaEmpleoDTO getOfertaEmpleoDTO(InscripcionOfertaEmpleo inscripcion) {
        OfertaEmpleoDTO dtofinal = new OfertaEmpleoDTO();
        dtofinal.setEmpresa(inscripcion.getOfertaEmpleo().getEmpresa().getNombre());
        dtofinal.setTitular(inscripcion.getOfertaEmpleo().getTitular());
        dtofinal.setPuesto(inscripcion.getOfertaEmpleo().getPuesto());
        dtofinal.setInscritos(inscripcionOfertaEmpleoRepository.getInscritos(inscripcion.getOfertaEmpleo().getId()));
        dtofinal.setNumInscritos(inscripcionOfertaEmpleoRepository.getNumeroInscritos(inscripcion.getOfertaEmpleo().getId()));
        return dtofinal;
    }


}
