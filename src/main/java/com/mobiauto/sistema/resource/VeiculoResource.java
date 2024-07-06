package com.mobiauto.sistema.resource;

import com.mobiauto.sistema.request.OportunidadeRequest;
import com.mobiauto.sistema.request.UsuarioRequest;
import com.mobiauto.sistema.request.VeiculoRequest;
import com.mobiauto.sistema.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("veiculo")
@RestController
@RequiredArgsConstructor
public class VeiculoResource {

    private final VeiculoService veiculoService;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvarVeiculo(@RequestBody VeiculoRequest request){
        try {
            veiculoService.salvarVeiculo(request);
            return ResponseEntity.status(HttpStatus.OK).body("Veículo salvo com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/vender")
    public ResponseEntity<?> venderVeiculo(@RequestBody OportunidadeRequest request){
        try {
            veiculoService.venderVeiculo(request);
            return ResponseEntity.status(HttpStatus.OK).body("Veículo vendido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
