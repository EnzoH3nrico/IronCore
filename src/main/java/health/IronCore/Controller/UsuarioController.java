package health.IronCore.Controller;

import health.IronCore.dto.usuario.DadosTokenJWT;
import health.IronCore.dto.usuario.DadosAutenticacao;
import health.IronCore.service.CadastroService;
import jakarta.validation.Valid;
import health.IronCore.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import health.IronCore.security.token.TokenService;
import tools.jackson.databind.annotation.JsonAppend;

@RestController
@RequestMapping("/ironCore")
public class UsuarioController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CadastroService cadastro;

    @PostMapping("/cadastro")
    public ResponseEntity cadastro(@RequestBody @Valid DadosAutenticacao dadosAutenticacao){
        return cadastro.cadastrarUsuario(dadosAutenticacao);
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados){

        //System.out.println(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("123456"));


        var autenticadorToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        System.out.println("autenticadorToken passou");

        var autenticacao = manager.authenticate(autenticadorToken);

        System.out.println("autenticacao passou");

        var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        System.out.println("Token passou");

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
