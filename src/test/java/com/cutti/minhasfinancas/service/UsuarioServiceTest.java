package com.cutti.minhasfinancas.service;


import com.cutti.minhasfinancas.exceptions.RegraNegocioException;
import com.cutti.minhasfinancas.model.entity.Usuario;
import com.cutti.minhasfinancas.model.repository.UsuarioRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
    @Autowired
    UsuarioService service;

    @Autowired
    UsuarioRepository repository;

    @Test
    public void deveValidarEmail() {

        UsuarioRepository usuarioRepositoryMock = Mockito.mock(UsuarioRepository.class);

        repository.deleteAll();

        service.validarEmail("email");
    }

    @Test
    public void deveLancarErroQuandoExistirEmailCadastrado() {
        Assertions.assertThrows(RegraNegocioException.class, () -> {
            Usuario usuario = Usuario.builder().nome("nome").email("email").build();
            repository.save(usuario);

            service.validarEmail("email");
        });
    }
}
