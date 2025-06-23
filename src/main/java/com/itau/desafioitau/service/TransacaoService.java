package com.itau.desafioitau.service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import com.itau.desafioitau.dto.EstatisticaDTO;
import com.itau.desafioitau.model.Transacao;

@Service
public class TransacaoService {
    
    private final List<Transacao> transacoes = new CopyOnWriteArrayList<>();

    public void adicionar(Transacao t) {
        if(t.getValor() < 0) throw new IllegalArgumentException("Valor negativo");
        if(t.getDataHora().isAfter(OffsetDateTime.now())) throw new IllegalArgumentException("Futuro");

        transacoes.add(t);
    }

    public void limpar(){
        transacoes.clear();
    }

    public EstatisticaDTO calcularEstatisticas(){
        var agora = OffsetDateTime.now();
        var ultimos60s = transacoes.stream()
            .filter(t -> t.getDataHora().isAfter(agora.minusSeconds(60)))
            .toList();

        DoubleSummaryStatistics stats = ultimos60s.stream()
            .mapToDouble(Transacao::getValor)
            .summaryStatistics();

        return new EstatisticaDTO(
            stats.getCount(),
            stats.getSum(),
            stats.getAverage(),
            stats.getCount() == 0 ? 0.0 : stats.getMin(),
            stats.getCount() == 0 ? 0.0 : stats.getMax()
        );
    }
}
