package com.sl.safajobs.servicios;

import com.sl.safajobs.dto.*;
import com.sl.safajobs.mappers.PerfilMapper;
import com.sl.safajobs.modelos.*;
import com.sl.safajobs.repositorios.PerfilRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class PerfilService {


    private PerfilRepository perfilRepository;

    private AptitudService aptitudService;

    private ExperienciaEducativaService experienciaEducativaService;

    private ExperienciaLaboralService experienciaLaboralService;

    private PerfilMapper perfilMapper;

    private EmpresaService empresaService;


    /**
     * Este método extrae todos los perfiles de base de datos
     *
     * @return
     */
    public List<PerfilDTO> getAll() {

        List<Perfil> perfiles = perfilRepository.findAll();
        List<PerfilDTO> perfilDTOS = new ArrayList<>();

        for (Perfil p : perfiles) {
            PerfilDTO dto = new PerfilDTO();
            dto.setNombre(p.getNombre());
            dto.setApellidos(p.getApellidos());
            dto.setMail(p.getMail());
            dto.setFoto(p.getFoto());
            perfilDTOS.add(dto);
        }

        return perfilDTOS;
    }


    /**
     * Busca perfiles por coincidencia en nombre, apellidos o mail
     *
     * @param busqueda
     * @return
     */
    public List<PerfilDTO> buscar(String busqueda) {
        return perfilMapper.toDTO(perfilRepository.buscar(busqueda));
    }


    /**
     * Este método busca un perfil a partir de su id
     *
     * @param id
     * @return
     */
    public Perfil getById(Integer id) {
        return perfilRepository.findById(id).orElse(null);
    }

    public PerfilDTO getByIdDTO(Integer id) {
        Perfil p = perfilRepository.findById(id).orElse(null);
        PerfilDTO dto = new PerfilDTO();
        if (p != null) {
            dto.setNombre(p.getNombre());
            dto.setApellidos(p.getApellidos());
            dto.setMail(p.getMail());
            dto.setFoto(p.getFoto());
        }
        return dto;
    }


    /**
     * Este método guarda un perfil nuevo o modifica uno existente
     *
     * @param dto
     * @return
     */
    public Perfil guardar(@Valid PerfilCrearDTO dto) throws Exception {

        Perfil perfilGuardar = new Perfil();
        perfilGuardar.setNombre(dto.getNombre());
        perfilGuardar.setApellidos(dto.getApellidos());
        perfilGuardar.setPuesto(dto.getPuesto());

        if (dto.getMail().contains("@")) {
            perfilGuardar.setMail(dto.getMail());
        } else {
            throw new Exception("El mail introducido no es válido");
        }

        if (dto.getDni().length() == 9) {
            perfilGuardar.setDni(dto.getDni());
        } else {
            throw new Exception("El dni introducido no es válido");
        }

        //FECHA NACIMIENTO (STRING) -> LOCALTADE
        LocalDate fechaNacimiento = getLocalDate(dto.getFechaNacimiento());
        perfilGuardar.setFechaNacimiento(fechaNacimiento);

        //APTITUDES
        Set<Aptitud> aptituds = new HashSet<>();
        for (Integer idAptitud : dto.getAptitudes()) {
            Aptitud aptitud = aptitudService.getById(idAptitud);
            aptituds.add(aptitud);
        }
        perfilGuardar.setAptitudes(aptituds);
        return perfilRepository.save(perfilGuardar);
    }

    private static LocalDate getLocalDate(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaFormateada = LocalDate.parse(fecha);
        return fechaFormateada;
    }


    /**
     * Elimina un perfil a traves de su id
     *
     * @param id
     */
    public String eliminar(Integer id) {
        String mensaje;
        Perfil perfil = getById(id);

        if (perfil == null) {
            return "El perfil con el id indicado no exite";
        }

        try {
            perfilRepository.deleteById(id);
            perfil = getById(id);
            if (perfil != null) {
                mensaje = "No se ha podido eliminar el perfil";
            } else {
                mensaje = "Perfil eliminado correctamente";
            }
        } catch (Exception e) {
            mensaje = "No se ha podido eliminar el perfil";
        }

        return mensaje;
    }


    public void eliminar(Perfil perfil) {
        perfilRepository.delete(perfil);
    }


    public void anyadirParticipante(Integer idPerfil, Integer idAptitud) throws Exception {

        Perfil perfil = perfilRepository.findById(idPerfil).orElse(null);

        if (perfil != null) {


            Aptitud aptitud = aptitudService.getById(idAptitud);

            if (aptitud != null) {

                if (perfil.getAptitudes().contains(aptitud)) {
                    perfil.getAptitudes().add(aptitud);
                }

            }

        }

        perfilRepository.save(perfil);


    }


    public Perfil guardarPerfil(Perfil perfil) {
        return perfilRepository.save(perfil);
    }


    public List<Aptitud> getAmigos(Integer idPerfil) {
        Perfil p = perfilRepository.findById(idPerfil).orElse(null);

        if (p != null) {
            return new ArrayList<>(p.getAptitudes());
        } else {
            return new ArrayList<>();
        }
    }


    public Perfil buscarPorUsuario(Usuario usuario) {
        return perfilRepository.findTopByUsuario(usuario);
    }


    public void editarPerfil(PerfilDatosDTO dto, Perfil perfil) {

        if (dto.getNombre() != null && !dto.getNombre().isBlank()) {
            perfil.setNombre(dto.getNombre());
        }
        if (dto.getApellidos() != null && !dto.getApellidos().isBlank()) {
            perfil.setApellidos(dto.getApellidos());
        }
        if (dto.getPuesto() != null && !dto.getPuesto().isBlank()) {
            perfil.setPuesto(dto.getPuesto());
        }
        if (dto.getMail() != null && !dto.getMail().isBlank()) {
            perfil.setMail(dto.getMail());
        }
        if (dto.getFoto() != null && !dto.getFoto().isBlank()) {
            perfil.setFoto(dto.getFoto());
        }
        if (dto.getFechaNacimiento() != null && !dto.getFechaNacimiento().isBlank()) {
            perfil.setFechaNacimiento(LocalDate.parse(dto.getFechaNacimiento()));
        }
        if (dto.getAptitudes() != null && !dto.getAptitudes().isEmpty()) {
            perfil.setAptitudes(dto.getAptitudes().stream()
                    .map(a -> aptitudService.getById(a.getId()))
                    .collect(Collectors.toSet()));
        }
        if (dto.getEducacion() != null && !dto.getEducacion().isEmpty()) {
            perfil.setExperienciaEducativa(dto.getEducacion().stream()
                    .map(a -> a.getId() != null ? experienciaEducativaService.findById(a.getId()) :
                            new ExperienciaEducativa(null, a.getCentroEducativo(),
                                    a.getCurso(), getLocalDate(a.getFechaInicio()), getLocalDate(a.getFechaFin()), perfil)
                    ).collect(Collectors.toSet()));
        }
        if (dto.getExperienciaLaboral() != null && !dto.getExperienciaLaboral().isEmpty()) {
            perfil.setExperienciaLaboral(dto.getExperienciaLaboral().stream()
                    .map(a -> a.getId() != null ? experienciaLaboralService.findById(a.getId()) :
                            new ExperienciaLaboral(null, perfil.getPuesto(),
                                    getLocalDate(a.getFechaInicio()),
                                    getLocalDate(a.getFechaFin()),perfil,empresaService.getById(a.getIdEmpresa()))
                    ).collect(Collectors.toSet()));
        }

       perfilRepository.save(perfil);

    }


    public PerfilDatosDTO obtenerDatosPerfil(Perfil perfil) {
        PerfilDatosDTO dto = new PerfilDatosDTO();

        dto.setNombre(perfil.getNombre());
        dto.setApellidos(perfil.getApellidos());
        dto.setMail(perfil.getMail());
        dto.setFechaNacimiento(perfil.getFechaNacimiento().toString());
        dto.setFoto(perfil.getFoto());

        dto.setAptitudes(perfil.getAptitudes()
                .stream()
                .map(a -> new AptitudDTO(a.getId(), a.getTipoAptitud().toString(), a.getTitulo(), a.getDetalle()))
                .toList());


        dto.setEducacion(experienciaEducativaService.getAllByPerfilId(perfil.getId())
                .stream()
                .map(e -> new ExperienciaEducativaDTO(e.getId(), e.getCentroEducativo(), e.getCurso(), e.getFechaInicio().toString(), e.getFechaFin() != null ? e.getFechaFin().toString() : ""))
                .toList());


        dto.setExperienciaLaboral(experienciaLaboralService.getByIdPerfil(perfil.getId())
                .stream()
                .map(e -> new ExperienciaLaboralDTO(e.getId(), e.getPuesto(), e.getFechaInicio().toString(), e.getFechaFin() != null ? e.getFechaFin().toString() : "",e.getEmpresa().getId(), e.getEmpresa().getNombre(), e.getEmpresa().getFoto()))
                .toList());
        return dto;

    }


}
