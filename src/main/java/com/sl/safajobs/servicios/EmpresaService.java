package com.sl.safajobs.servicios;

import com.sl.safajobs.dto.EmpresaDTO;
import com.sl.safajobs.modelos.Empresa;
import com.sl.safajobs.repositorios.EmpresaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmpresaService {


    private EmpresaRepository empresaRepository;


    /**
     * Busca empresas por cif
     *
     * @param cif
     * @return
     */
    public List<Empresa> getEmpresasPorCIF(String cif, String nombre){
        List<Empresa> empresas = empresaRepository.findAllByCifEqualsAndNombreLike(cif, nombre);
        return empresas;
    }


    /**
     * Obtener todas las empresas
     *
     * @return
     */
    public List<EmpresaDTO> getAll(){

        List<EmpresaDTO> empresaDTOS = new ArrayList<>();
        List<Empresa> empresas =  empresaRepository.findAll();
        for(Empresa empresa: empresas){
            EmpresaDTO dto = new EmpresaDTO();
            dto.setId(empresa.getId());
            dto.setNombre(empresa.getNombre());
            dto.setCif(empresa.getCif());
            dto.setEsTecnologica(empresa.getEsTecnologica());
            dto.setFechaFundacion(empresa.getFechaFundacion().toString());
            empresaDTOS.add(dto);
        }

        return empresaDTOS;
    }


    /**
     * Busca una empresa por id
     *
     * @param id
     * @return
     */
    public Empresa getById(Integer id){
        return empresaRepository.findById(id).orElse(null);
    }


    /**
     * Crea una empresa nueva o modifica una existente
     *
     * @param empresa
     * @return
     */
    public Empresa guardar(Empresa empresa){
        return empresaRepository.save(empresa);
    }


    /**
     * Elimina una empresa por id
     *
     * @param id
     */
    public void eliminar(Integer id){
        empresaRepository.deleteById(id);
    }


    /**
     * Elimina una empresa
     *
     * @param empresa
     */
    public void eliminar(Empresa empresa){
        empresaRepository.delete(empresa);
    }














}
