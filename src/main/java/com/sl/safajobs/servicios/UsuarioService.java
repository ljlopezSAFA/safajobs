package com.sl.safajobs.servicios;

import com.sl.safajobs.dto.LoginDTO;
import com.sl.safajobs.dto.RegistroDTO;
import com.sl.safajobs.dto.RespuestaDTO;
import com.sl.safajobs.enumerados.Rol;
import com.sl.safajobs.modelos.Usuario;
import com.sl.safajobs.repositorios.UsuarioRepository;
import com.sl.safajobs.security.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {


    private UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private JWTService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findTopByUsername(username).orElse(null);
    }


    public Usuario registrarUsuario(RegistroDTO dto){

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(dto.getUsername());
        nuevoUsuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        nuevoUsuario.setRol(Rol.PERFIL);

        return usuarioRepository.save(nuevoUsuario);
    }


    public ResponseEntity<RespuestaDTO> login(LoginDTO dto){

        // Buscar usuario por nombre de usuario
        Optional<Usuario> usuarioOpcional = usuarioRepository.findTopByUsername(dto.getUsername());

        if (usuarioOpcional.isPresent()) {
            Usuario usuario = usuarioOpcional.get();

            // Verificar la contraseña
            if (passwordEncoder.matches(dto.getPassword(), usuario.getPassword())) {

                // Contraseña válida, devolver token de acceso
                String token = jwtService.generateToken(usuario);
                return ResponseEntity
                        .ok(RespuestaDTO
                                .builder()
                                .estado(HttpStatus.OK.value())
                                .token(token).build());
            } else {
                throw new BadCredentialsException("Contraseña incorrecta");
            }
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

    }











//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
//
//    public Usuario buscarUsuarioPorNombre(String username){
//        return usuarioRepository.findTopByUsername(username).orElse(null);
//    }
//
//
//
//    public Usuario guardarUsuario(UsuarioDTO dto){
//
//        Usuario usuario = new Usuario();
//        usuario.setUsername(dto.getUsername());
//        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
//        usuario.setRol(Rol.PERFIL);
//        return usuarioRepository.save(usuario);
//
//    }


}
