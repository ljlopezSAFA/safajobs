package com.sl.safajobs.mappers;


import com.sl.safajobs.dto.PerfilDTO;
import com.sl.safajobs.modelos.Perfil;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PerfilMapper {


    /**
     * Este método sirve para pasar a Entity un DTO de Perfil
     * @param dto
     * @return
     */
    Perfil toEntity(PerfilDTO dto);


    /**
     * Este método sirve para pasar a DTO un Entity de Perfil
     * @param entity
     * @return
     */
    PerfilDTO toDTO(Perfil entity);



    List<Perfil> toEntity(List<PerfilDTO> dtos);

    List<PerfilDTO> toDTO(List<Perfil> entities);




}
