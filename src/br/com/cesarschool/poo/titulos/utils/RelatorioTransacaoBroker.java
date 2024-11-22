package br.com.cesarschool.poo.titulos.utils;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;

public class RelatorioTransacaoBroker {

    public Transacao[] relatorioTransacaoPorDataHora(){
        RepositorioTransacao repositorioTransacao = new RepositorioTransacao();
        Transacao[] transacoes = repositorioTransacao.buscarTodos();

        Ordenador.ordenar(transacoes);

        return transacoes;
    }

    public Transacao[] relatorioTransacaoPorNomeEntidadeCredora(){
        RepositorioTransacao repositorioTransacao = new RepositorioTransacao();
        Transacao[] transacoes = repositorioTransacao.buscarTodos();
        ComparadorTransacaoPorNomeCredora comparador = new ComparadorTransacaoPorNomeCredora();

        Ordenador.ordenar(transacoes, comparador);

        return transacoes;
    }
}