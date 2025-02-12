package com.sl.safajobs.servicios;

import com.sl.safajobs.modelos.ExperienciaEducativa;
import com.sl.safajobs.repositorios.ExperienciaEducativaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExperienciaEducativaService {

    private ExperienciaEducativaRepository repository;


    public List<ExperienciaEducativa> getAllByPerfilId(Integer id){
        return repository.findAllByPerfilId(id);
    }

    public ExperienciaEducativa findById(Integer id){
        return repository.findById(id).orElse(null);

    }

}
