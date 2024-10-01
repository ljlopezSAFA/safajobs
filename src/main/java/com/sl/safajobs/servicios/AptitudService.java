package com.sl.safajobs.servicios;

import com.sl.safajobs.modelos.Aptitud;
import com.sl.safajobs.repositorios.AptitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AptitudService {

    @Autowired
    private AptitudRepository aptitudRepository;


    public Aptitud getById(Integer id){
        return aptitudRepository.findById(id).orElse(null);
    }


}
