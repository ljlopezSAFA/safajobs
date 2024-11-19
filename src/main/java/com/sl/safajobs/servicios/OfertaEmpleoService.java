package com.sl.safajobs.servicios;

import com.sl.safajobs.dto.EmpresaDTO;
import com.sl.safajobs.dto.OfertaEmpleoCrearDTO;
import com.sl.safajobs.dto.OfertaEmpleoDTO;
import com.sl.safajobs.dto.OfertaEmpleoMostrarDTO;
import com.sl.safajobs.modelos.Empresa;
import com.sl.safajobs.modelos.OfertaEmpleo;
import com.sl.safajobs.repositorios.OfertaEmpleoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OfertaEmpleoService {

    private OfertaEmpleoRepository ofertaEmpleoRepository;
    private EmpresaService empresaService;


    public List<OfertaEmpleoMostrarDTO> getAll(){

        List<OfertaEmpleoMostrarDTO> ofertaEmpleoDTOS = new ArrayList<>();
        List<OfertaEmpleo> ofertaEmpleos = ofertaEmpleoRepository.findAll();

        for(OfertaEmpleo o : ofertaEmpleos){
            OfertaEmpleoMostrarDTO dto = new OfertaEmpleoMostrarDTO();
            dto.setId(o.getId());
            dto.setTitular(o.getTitular());
            dto.setRemuneracion(o.getRemuneracion());
            dto.setPuesto(o.getPuesto());
            dto.setRequisitos(o.getRequisitos());
            dto.setRemuneracionMinima(o.getRemuneracionMinima());
            dto.setRemuneracionMaxima(o.getRemuneracionMaxima());

            EmpresaDTO dtoe = new EmpresaDTO();
            dtoe.setId(o.getEmpresa().getId());
            dtoe.setCif(o.getEmpresa().getCif());
            dtoe.setNombre(o.getEmpresa().getNombre());
            dtoe.setFechaFundacion(o.getEmpresa().getFechaFundacion().toString());
            dtoe.setEsTecnologica(o.getEmpresa().getEsTecnologica());
            dtoe.setFoto(o.getEmpresa().getFoto());

            dto.setEmpresa(dtoe);
            ofertaEmpleoDTOS.add(dto);

        }


        return ofertaEmpleoDTOS;


//        return ofertaEmpleoRepository.findAll();
    }

    /**
     * Crea una nueva oferta de empleo
     *
     * @param dto
     * @return
     */
    public OfertaEmpleo crearNueva(OfertaEmpleoCrearDTO dto){

        OfertaEmpleo entity = new OfertaEmpleo();

        entity.setPuesto(dto.getPuesto());
        entity.setTitular(dto.getTitular());
        entity.setRequisitos(dto.getRequisitos_minimos());
        entity.setRemuneracion(dto.getRemuneracion());
        entity.setRemuneracionMinima(dto.getRemuneracionMinima());
        entity.setRemuneracionMaxima(dto.getRemuneracionMaxima());

        Empresa empresa = empresaService.getById(dto.getIdEmpresa());
        entity.setEmpresa(empresa);

        return ofertaEmpleoRepository.save(entity);

    }

    public OfertaEmpleo editar(OfertaEmpleoCrearDTO dto, Integer id){

        OfertaEmpleo entity = ofertaEmpleoRepository.getReferenceById(id);

        entity.setPuesto(dto.getPuesto());
        entity.setTitular(dto.getTitular());
        entity.setRequisitos(dto.getRequisitos_minimos());
        entity.setRemuneracion(dto.getRemuneracion());
        entity.setRemuneracionMinima(dto.getRemuneracionMinima());
        entity.setRemuneracionMaxima(dto.getRemuneracionMaxima());


        return ofertaEmpleoRepository.save(entity);

    }

    public OfertaEmpleo getById(Integer id){
        return ofertaEmpleoRepository.findById(id).orElse(null);
    }



}
