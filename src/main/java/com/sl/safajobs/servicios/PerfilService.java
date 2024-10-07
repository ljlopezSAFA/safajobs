package com.sl.safajobs.servicios;

import com.sl.safajobs.dto.PerfilCrearDTO;
import com.sl.safajobs.dto.PerfilDTO;
import com.sl.safajobs.modelos.Aptitud;
import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.repositorios.PerfilRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class PerfilService {


    private PerfilRepository perfilRepository;

    private AptitudService aptitudService;


    /**
     * Este método extrae todos los perfiles de base de datos
     *
     * @return
     */
    public List<PerfilDTO> getAll(){

        List<Perfil> perfiles = perfilRepository.findAll();
        List<PerfilDTO> perfilDTOS = new ArrayList<>();

        for(Perfil p : perfiles){
            PerfilDTO dto = new PerfilDTO();
            dto.setNombre(p.getNombre());
            dto.setApellidos(p.getApellidos());
            dto.setMail(p.getMail());
            perfilDTOS.add(dto);
        }

        return perfilDTOS;
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
     * @param dto
     * @return
     */
    public Perfil guardar(PerfilCrearDTO dto){

        Perfil perfilGuardar = new Perfil();
        perfilGuardar.setNombre(dto.getNombre());
        perfilGuardar.setApellidos(dto.getApellidos());
        perfilGuardar.setDni(dto.getDni());
        perfilGuardar.setMail(dto.getMail());

        //FECHA NACIMIENTO (STRING) -> LOCALTADE
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimiento = LocalDate.parse(dto.getFechaNacimiento(), formatter);
        perfilGuardar.setFechaNacimiento(fechaNacimiento);


        //APTITUDES
        Set<Aptitud> aptituds = new HashSet<>();
        for(Integer idAptitud : dto.getAptitudes()){
            Aptitud aptitud = aptitudService.getById(idAptitud);
            aptituds.add(aptitud);
        }
        perfilGuardar.setAptitudes(aptituds);

        return perfilRepository.save(perfilGuardar);
    }


    /**
     * Elimina un perfil a traves de su id
     *
     * @param id
     */
    public String eliminar(Integer id){
        String mensaje;
        Perfil perfil = getById(id);

        if(perfil == null){
            return  "El perfil con el id indicado no exite";
        }

        try {
            perfilRepository.deleteById(id);
            perfil = getById(id);
            if(perfil != null){
                mensaje =  "No se ha podido eliminar el perfil";
            }else{
                mensaje = "Perfil eliminado correctamente";
            }
        } catch (Exception e) {
            mensaje =  "No se ha podido eliminar el perfil";
        }

       return mensaje;
    }


    public void eliminar(Perfil perfil){
        perfilRepository.delete(perfil);
    }



}
