package com.desafio.fit21.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.fit21.service.ReceitaService;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping("/random")
    public ResponseEntity<String> getReceitaAleatoria() {
        try {
            String response = receitaService.getReceitaAleatoria();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar receita: " + e.getMessage());
        }
    }

    @GetMapping("/categoria/{tipo}")
    public ResponseEntity<String> getReceitaPorCategoria(@PathVariable String tipo) {
        try {
            String response = receitaService.getReceitaPorCategoria(tipo);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar receita: " + e.getMessage());
        }
    }
}
