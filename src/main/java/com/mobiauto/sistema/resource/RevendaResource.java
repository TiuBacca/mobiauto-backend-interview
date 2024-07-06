package com.mobiauto.sistema.resource;

import com.mobiauto.sistema.request.RevendaRequest;
import com.mobiauto.sistema.request.UsuarioRequest;
import com.mobiauto.sistema.service.RevendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("revenda")
@RestController
@RequiredArgsConstructor
public class RevendaResource {

    private final RevendaService revendaService;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarRevenda(@RequestBody RevendaRequest request){
        try {
            revendaService.salvarRevenda(request);
            return ResponseEntity.status(HttpStatus.OK).body("Revenda salva com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
