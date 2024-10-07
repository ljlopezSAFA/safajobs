package com.sl.safajobs.servicios;

import com.sl.safajobs.modelos.Aptitud;
import com.sl.safajobs.repositorios.AptitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AptitudService {

    @Autowired
    private AptitudRepository aptitudRepository;


    /**
     * Busca una aptitud por id
     *
     * @param id
     * @return
     */
    public Aptitud getById(Integer id){
        return aptitudRepository.findById(id).orElse(null);
    }


    /**
     * Devuelve todas las aptitudes de base de datos
     *
     * @return
     */
    public List<Aptitud> getAllAptitudes(){
        return aptitudRepository.findAll();
    }

    /**
     * Crea o modifica una aptitud
     *
     * @param aptitud
     * @return
     */
    public Aptitud guardar(Aptitud aptitud){
        return aptitudRepository.save(aptitud);
    }


    /**
     * Elimina una aptitud por id
     *
     * @param id
     */
    public void eliminar(Integer id){
        aptitudRepository.deleteById(id);
    }


}
