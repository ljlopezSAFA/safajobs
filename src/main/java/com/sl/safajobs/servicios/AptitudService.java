package com.sl.safajobs.servicios;

import com.sl.safajobs.modelos.Aptitud;
import com.sl.safajobs.repositorios.AptitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
    public Aptitud getById(Integer id)  {

        Aptitud aptitud  = aptitudRepository.findById(id).orElse(null);

        return aptitud;

    }

    /**
     * Devuelve todas las aptitudes de base de datos
     *
     * @return
     */
    public List<Aptitud> getAllAptitudes() {
        return aptitudRepository.findAll();
    }

    /**
     * Crea o modifica una aptitud
     *
     * @param aptitud
     * @return
     */
    public Aptitud guardar(Aptitud aptitud) {

        Aptitud aptitudGuardada;

        try{
            if(aptitud.getTitulo().isBlank()){
                throw new Exception("El título debe estar relleno");
            }

            aptitudGuardada = aptitudRepository.save(aptitud);
            return aptitudGuardada;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;


    }


    /**
     * Elimina una aptitud por id
     *
     * @param id
     */
    public String eliminar(Integer id) {
        try {
            aptitudRepository.deleteById(id);
            return "Aptitud eliminado";
        } catch (Exception e) {
            return "No se ha podido eliminar la aptitud";
        }

    }


}
