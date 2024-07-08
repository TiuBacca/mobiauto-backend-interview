package com.mobiauto.sistema;

import br.com.caelum.stella.validation.CNPJValidator;
import com.mobiauto.sistema.domain.Usuario;
import com.mobiauto.sistema.exceptions.RegistroNaoEncontradoException;
import com.mobiauto.sistema.repository.UsuarioRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Util {

    public static PrivateKey chave;

    private  final UsuarioRepository usuarioRepository;

    public static void validaCNPJ(String cnpj) {
        CNPJValidator validator = new CNPJValidator();
        validator.assertValid(cnpj);
    }

    public static String gerarToken(String texto) throws Exception {
        String token;
        if (chave == null) {
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
            KeyPair kp = keyGenerator.genKeyPair();
            chave = kp.getPrivate();
        }
        token = Jwts.builder().setSubject(texto).setIssuer("localhost:8090").setIssuedAt(new Date())
                .setExpiration(
                        Date.from(LocalDateTime.now().plusMinutes(1440L).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(chave, SignatureAlgorithm.RS256).compact();
        return token;
    }

    public static String getEmailUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String nome;
        if (principal instanceof UserDetails) {
            nome = ((UserDetails) principal).getUsername();
        } else {
            nome = principal.toString();
        }

        nome = nome.replace("{\"email\":\"", "");
        nome = nome.substring(0, nome.indexOf(",") - 1);

        return nome;
    }

    public Usuario getUsuarioLogado() throws Exception {
        String emailLogado = Util.getEmailUsuarioLogado();
        return usuarioRepository.findByEmail(emailLogado)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Usuário não encontrado para o email: " + emailLogado));

    }
}


