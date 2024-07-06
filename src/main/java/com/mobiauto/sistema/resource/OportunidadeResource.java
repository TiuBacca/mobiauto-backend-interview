package com.mobiauto.sistema.resource;

import com.mobiauto.sistema.request.ClienteRequest;
import com.mobiauto.sistema.request.OportunidadeRequest;
import com.mobiauto.sistema.service.OportunidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("oportunidade")
@RestController
@RequiredArgsConstructor
public class OportunidadeResource {

    private final OportunidadeService oportunidadeService;

    @PostMapping("/nova")
    public ResponseEntity<?> criarOportunidade(@RequestBody OportunidadeRequest request){
        try {
            oportunidadeService.criarOportunidade(request);
            return ResponseEntity.status(HttpStatus.OK).body("Oportunidade criada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/atribuir-responsavel")
    public ResponseEntity<?> redirecionarResponsavelOportunidade(@RequestBody OportunidadeRequest request){
        try {
            oportunidadeService.redirecionarResponsavelOportunidade(request);
            return ResponseEntity.status(HttpStatus.OK).body("Oportunidade direcionada para responsável com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
