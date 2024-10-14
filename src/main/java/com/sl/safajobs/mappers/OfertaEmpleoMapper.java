package com.sl.safajobs.mappers;

import com.sl.safajobs.dto.OfertaEmpleoCrearDTO;
import com.sl.safajobs.modelos.Empresa;
import com.sl.safajobs.modelos.OfertaEmpleo;
import com.sl.safajobs.servicios.EmpresaService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class OfertaEmpleoMapper {

    @Autowired
    private EmpresaService empresaService;

    @Mapping(source = "idEmpresa",target = "empresa", qualifiedByName = "pasarEmpresa")
    @Mapping(source = "requisitos_minimos", target ="requisitos" )
    public abstract OfertaEmpleo toEntity(OfertaEmpleoCrearDTO dto);


    @Named("pasarEmpresa")
    public Empresa getEmpresaByID(Integer idEmpresa){
        return empresaService.getById(idEmpresa);
    }

}
