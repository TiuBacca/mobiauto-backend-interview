package com.mobiauto.sistema.resource;

import com.mobiauto.sistema.request.ClienteRequest;
import com.mobiauto.sistema.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("cliente")
@RestController
@RequiredArgsConstructor
public class ClienteResource {

    private final ClienteService clienteService;

    @PostMapping("/salvar")
    public ResponseEntity<?>salvarCliente(@RequestBody ClienteRequest request){
        try {
            clienteService.salvarCliente(request);
            return ResponseEntity.status(HttpStatus.OK).body("Cliente salvo com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
