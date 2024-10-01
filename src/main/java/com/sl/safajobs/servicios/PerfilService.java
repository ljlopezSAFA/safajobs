package com.sl.safajobs.servicios;

import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.repositorios.PerfilRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PerfilService {


    private PerfilRepository perfilRepository;


    /**
     * Este método extrae todos los perfiles de base de datos
     *
     * @return
     */
    public List<Perfil> getAll(){
        return perfilRepository.findAll();
    }


    /**
     * Este método busca un perfil a partir de su id
     *
     * @param id
     * @return
     */
    public Perfil getById(Integer id){
        return perfilRepository.findById(id).orElse(null);
    }


    /**
     * Este método guarda un perfil nuevo o modifica uno existente
     *
     * @param perfil
     * @return
     */
    public Perfil guardar(Perfil perfil){
        return perfilRepository.save(perfil);
    }


    /**
     * Elimina un perfil a traves de su id
     *
     * @param id
     */
    public void eliminar(Integer id){
        perfilRepository.deleteById(id);
    }


    public void eliminar(Perfil perfil){
        perfilRepository.delete(perfil);
    }



}
