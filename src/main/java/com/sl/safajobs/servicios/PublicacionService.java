package com.sl.safajobs.servicios;


import com.sl.safajobs.dto.PublicacionCrearDTO;
import com.sl.safajobs.dto.PublicacionDTO;
import com.sl.safajobs.modelos.Empresa;
import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.modelos.Publicacion;
import com.sl.safajobs.repositorios.PublicacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PublicacionService {

    private PublicacionRepository publicacionRepository;

    private PerfilService perfilService;
    private EmpresaService empresaService;


    /**
     * Busca todas las publicaciones de base de datos
     *
     * @return
     */
    public List<PublicacionDTO> getAll() {

        List<Publicacion> publicaciones = publicacionRepository.findAll();
        List<PublicacionDTO> dtos = new ArrayList<>();


        for (Publicacion p : publicaciones) {
            PublicacionDTO dto = getPublicacionDTO(p);
            dtos.add(dto);

        }

        return dtos;
    }


    /**
     * Busca una publicacion a traves del id
     *
     * @param id
     * @return
     */
    public PublicacionDTO getByID(Integer id) {
        Publicacion p = publicacionRepository.findById(id).orElse(null);

        if (p != null) {
            PublicacionDTO dto = getPublicacionDTO(p);
            return dto;


        } else {
            return null;
        }


    }


    /**
     * Elimina una publicación por ID
     *
     * @param id
     */
    public void eliminar(Integer id) {
        publicacionRepository.deleteById(id);
    }


    public PublicacionDTO guardarPublicacion(PublicacionCrearDTO dto) {

        Publicacion publicacion = null;

        if (dto.getId() != null) {
            publicacion = publicacionRepository.findById(dto.getId()).orElse(null);
        }

        if (publicacion == null) {
            publicacion = new Publicacion();
            publicacion.setFecha(LocalDateTime.now());
        }

        //GUARDAR -> Entidad
        publicacion.setTexto(dto.getTexto());
        publicacion.setImagenUrl(dto.getImageUrl());


        //PERFIL
        if (dto.getId_perfil() != null) {
            Perfil perfil = perfilService.getById(dto.getId_perfil());
            publicacion.setPerfil(perfil);
        }

        if (dto.getId_empresa() != null) {
            Empresa empresa = empresaService.getById(dto.getId_perfil());
            publicacion.setEmpresa(empresa);
        }

        Publicacion p = publicacionRepository.save(publicacion);
        PublicacionDTO dtonuevo = getPublicacionDTO(p);

        return dtonuevo;

    }

    private static PublicacionDTO getPublicacionDTO(Publicacion p) {
        PublicacionDTO dtonuevo = new PublicacionDTO();

        dtonuevo.setTexto(p.getTexto());
        dtonuevo.setImageUrl(p.getImagenUrl());
        dtonuevo.setFecha(p.getFecha().toString());

        if (p.getPerfil() != null) {
            dtonuevo.setNombrePerfil(p.getPerfil().getNombre().concat(",").concat(p.getPerfil().getApellidos()));
            dtonuevo.setFotoPerfil(p.getPerfil().getFoto());
        }

        if (p.getEmpresa() != null) {
            dtonuevo.setNombreEmpresa(p.getEmpresa().getNombre());
            dtonuevo.setFotoEmpresa(p.getEmpresa().getFoto());
        }
        return dtonuevo;
    }

}
