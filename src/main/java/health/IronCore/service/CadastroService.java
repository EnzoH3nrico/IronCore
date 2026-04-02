package health.IronCore.service;

import health.IronCore.dto.usuario.DadosAutenticacao;
import health.IronCore.model.Usuario;
import health.IronCore.repository.UsuarioRepository;
import health.IronCore.security.exception.TratadorDeErros;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.annotation.JsonAppend;

@Service
public class CadastroService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity cadastrarUsuario(DadosAutenticacao dados){

        if(repository.findByLogin(dados.login()) != null){
            return ResponseEntity.badRequest().body("Usuário já existe");
        }


        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(dados.login());


        novoUsuario.setSenha(passwordEncoder.encode(dados.senha()));

        repository.save(novoUsuario);

        return ResponseEntity.ok("Usuário cadastrado com sucesso");
    }
}
