package com.desafio.fit21.service;

import org.springframework.stereotype.Service;

import com.desafio.fit21.model.User;
import com.desafio.fit21.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PontuacaoService {

    private final UsuarioRepository usuarioRepository;

    public User adicionarPontuacao(Long userId, int pontos) {
        User user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.setPontuacao(user.getPontuacao() + pontos);
        return usuarioRepository.save(user);
    }

    public String verificarConquista(User user) {
        int pontos = user.getPontuacao();
        if (pontos >= 21) return "🏆 Desafio 21 dias concluído!";
        if (pontos >= 14) return "🔥 14 dias de querência!";
        if (pontos >= 7) return "💪 7 dias concluídos!";
        return "Continue treinando!";
    }


}
