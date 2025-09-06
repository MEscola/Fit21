package com.desafio.fit21.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafio.fit21.model.Progress;
import com.desafio.fit21.service.ProgressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/progress")
public class ProgressController {

    
    private final ProgressService progressService;

    // Retorna todos os dias de progresso do usuário (cria registros se não existirem)
    @GetMapping("/{userId}")
    public ResponseEntity<List<Progress>> getProgress(@PathVariable Long userId) {
        List<Progress> progresso = progressService.getProgress(userId);
        return ResponseEntity.ok(progresso);
    }

    // Marca um dia como concluído
    @PostMapping("/{userId}/mark/{day}")
    public ResponseEntity<Progress> markDay(@PathVariable Long userId, @PathVariable int day) {
        return ResponseEntity.ok(progressService.markDay(userId, day));
    }
}
