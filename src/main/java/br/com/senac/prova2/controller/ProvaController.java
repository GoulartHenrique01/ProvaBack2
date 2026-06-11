package br.com.senac.prova2.controller;

import br.com.senac.prova2.dtos.ProvaFiltroDto;
import br.com.senac.prova2.dtos.ProvaRequestDto;
import br.com.senac.prova2.entidades.Prova;
import br.com.senac.prova2.services.ProvaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/prova")
public class ProvaController {
    private ProvaService provaService;

    public ProvaController(ProvaService provaService) {
        this.provaService = provaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Prova>> listar(ProvaFiltroDto filtro) {
        return ResponseEntity.ok(provaService.listar(filtro));
    }

    @PostMapping("/criar")
    public ResponseEntity<Prova> criar(@RequestBody ProvaRequestDto prova) {
        try{
            return ResponseEntity.ok(provaService.criar(prova));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Prova>  atualizar(@RequestBody ProvaRequestDto prova,
                                            @PathVariable Long id){
        try{
            return ResponseEntity.ok(provaService.atualizar(id, prova));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Prova> deletar(@PathVariable Long id){
        try{
            provaService.deletar(id);
            return ResponseEntity.ok(null);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
