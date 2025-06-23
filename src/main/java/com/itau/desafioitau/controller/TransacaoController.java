package com.itau.desafioitau.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itau.desafioitau.model.Transacao;
import com.itau.desafioitau.service.TransacaoService;

@RestController
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService){
        this.transacaoService = transacaoService;
    }

    @PostMapping("/transacao")
    @ResponseStatus(HttpStatus.CREATED)
    public void postTransacao(@RequestBody Transacao transacao){
        transacaoService.adicionar(transacao);
    }

    @DeleteMapping("/transacao")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTransacoes(){
        transacaoService.limpar();
    }
}
