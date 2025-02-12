package com.sl.safajobs.servicios;

import com.sl.safajobs.modelos.ExperienciaEducativa;
import com.sl.safajobs.modelos.ExperienciaLaboral;
import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.repositorios.ExperienciaLaboralRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExperienciaLaboralService {


    private ExperienciaLaboralRepository repository;

    public List<ExperienciaLaboral> getByIdPerfil(Integer idPerfil){
        return repository.findAllByPerfilId(idPerfil);

    }


    public ExperienciaLaboral findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
