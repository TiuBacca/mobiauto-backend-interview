package com.mobiauto.sistema.resource;

import com.mobiauto.sistema.request.UsuarioRequest;
import com.mobiauto.sistema.request.UsuarioRevendaRequest;
import com.mobiauto.sistema.service.UsuarioRevendaService;
import com.mobiauto.sistema.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("usuario")
@RestController
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioService usuarioService;
    private final UsuarioRevendaService usuarioRevendaService;

    @PostMapping("/salvar")
    public ResponseEntity<?> login(@RequestBody UsuarioRequest request){
        try {
            usuarioService.salvarUsuario(request);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário salvo com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarUsuario(@RequestBody UsuarioRequest request){
        try {
            usuarioService.salvarUsuario(request);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário salvo com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/associa-cargo-revenda")
    public ResponseEntity<?> salvarUsuarioRevenda(@RequestBody UsuarioRevendaRequest request){
        try {
            usuarioRevendaService.salvarUsuarioRevenda(request);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário relacionado com revenda com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/atualiza-cargo")
    public ResponseEntity<?> alterarCargoUsuario(@RequestBody UsuarioRevendaRequest request){
        try {
            usuarioRevendaService.alterarCargoUsuario(request);
            return ResponseEntity.status(HttpStatus.OK).body("Cargo do usuário alterado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
