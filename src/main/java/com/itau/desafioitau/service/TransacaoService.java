package com.itau.desafioitau.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import com.itau.desafioitau.model.Transacao;

@Service
public class TransacaoService {
    
    private final List<Transacao> transacoes = new CopyOnWriteArrayList<>();

    public void adicionar(Transacao t) {
        if(t.getValor() < 0) throw new IllegalArgumentException("Valor negativo");
        if(t.getDataHora().isAfter(OffsetDateTime.now())) throw new IllegalArgumentException("Futuro");

        transacoes.add(t);
    }
}
