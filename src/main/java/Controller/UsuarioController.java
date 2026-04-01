package Controller;

import dto.usuario.DadosTokenJWT;
import dto.usuario.DadosAutenticacao;
import model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class Controller {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity cadastro(@RequestBody @Validated DadosAutenticacao dados){
        var autenticadorToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        var autenticacao = manager.authenticate(autenticadorToken);

        var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());


        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
