package com.sl.safajobs.security;

import com.sl.safajobs.dto.LoginDTO;
import com.sl.safajobs.dto.RespuestaDTO;
import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.modelos.Usuario;
import com.sl.safajobs.repositorios.UsuarioRepository;
import com.sl.safajobs.servicios.PerfilService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private JWTService jwtService;
    private PerfilService perfilService;

    public ResponseEntity<RespuestaDTO> login(LoginDTO dto){

        // Buscar usuario por nombre de usuario
        Optional<Usuario> usuarioOpcional = usuarioRepository.findTopByUsername(dto.getUsername());

        if (usuarioOpcional.isPresent()) {
            Usuario usuario = usuarioOpcional.get();

            // Verificar la contraseña
            if (passwordEncoder.matches(dto.getPassword(), usuario.getPassword())) {

                Perfil perfil = perfilService.buscarPorUsuario(usuario);

                // Contraseña válida, devolver token de acceso
                String token = jwtService.generateToken(usuario);
                return ResponseEntity
                        .ok(RespuestaDTO
                                .builder()
                                .estado(HttpStatus.OK.value())
                                .avatar(perfil.getFoto())
                                .token(token).build());
            } else {
                throw new BadCredentialsException("Contraseña incorrecta");
            }
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

    }
}
