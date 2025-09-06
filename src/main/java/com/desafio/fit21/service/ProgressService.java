package com.desafio.fit21.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.desafio.fit21.model.Progress;
import com.desafio.fit21.model.User;
import com.desafio.fit21.repository.ProgressRepository;
import com.desafio.fit21.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final UsuarioRepository usuarioRepository;
        
    // Se não existir 21 dias, criar automaticamente

    public List<Progress> getProgress(Long userId) {
        List<Progress> progresso = progressRepository.findByUserId(userId);

        if(progresso.size() < 21) {
            List<Integer> diasExistentes = new ArrayList<>();
            progresso.forEach(p -> diasExistentes.add(p.getDayNumber()));
            for(int i = 1; i <= 21; i++) {
                if(!diasExistentes.contains(i)) {
                    Progress novoDia = new Progress();
                    novoDia.setUserId(userId);
                    novoDia.setDayNumber(i);
                    novoDia.setCompleted(false);
                    progresso.add(progressRepository.save(novoDia));
                }
            }
        }

        return progresso;
    }
    
    public Progress markDay(Long userId, int day) {
        Progress progress = progressRepository.findByUserIdAndDayNumber(userId, day);

        if(progress == null) {
            // Criar se não existir
            progress = new Progress();
            progress.setUserId(userId);
            progress.setDayNumber(day);
        }

        progress.setCompleted(true);

        User user = progress.getUsuario();
        if(user == null) {
            user = usuarioRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário nao encontrado"));
            progress.setUsuario(user);
        }

        user.setPontuacao(user.getPontuacao() + 10); //10 pontos por dia concluido;
        progressRepository.save(progress);
        return progress;

    }
}
