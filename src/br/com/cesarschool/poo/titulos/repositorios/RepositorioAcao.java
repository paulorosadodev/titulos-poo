package br.com.cesarschool.poo.titulos.repositorios;

import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.gov.cesarschool.poo.daogenerico.Entidade;
import br.com.cesarschool.poo.titulos.entidades.Acao;

import java.io.*;

/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, valorUnitario)
 *
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12
 *
 * A inclus�o deve adicionar uma nova linha ao arquivo. N�o � permitido incluir
 * identificador repetido. Neste caso, o m�todo deve retornar false. Inclus�o com
 * sucesso, retorno true.
 *
 * A altera��o deve substituir a linha atual por uma nova linha. A linha deve ser
 * localizada por identificador que, quando n�o encontrado, enseja retorno false.
 * Altera��o com sucesso, retorno true.
 *
 * A exclus�o deve apagar a linha atual do arquivo. A linha deve ser
 * localizada por identificador que, quando n�o encontrado, enseja retorno false.
 * Exclus�o com sucesso, retorno true.
 *
 * A busca deve localizar uma linha por identificador, materializar e retornar um
 * objeto. Caso o identificador n�o seja encontrado no arquivo, retornar null.
 */
public class RepositorioAcao extends RepositorioGeral{
    private final DAOSerializadorObjetos<Acao> dao;
    public RepositorioAcao() {
        super(Acao.class);
        this.dao = new DAOSerializadorObjetos<>(Acao.class);
    }

    public boolean incluir(Acao acao) throws IOException {
        return dao.incluir(acao);
    }

    public boolean alterar(Acao acao) throws IOException {
        return dao.alterar(acao);
    }

    public boolean excluir(int identificador) throws IOException {
        return dao.excluir(String.valueOf(identificador));
    }

    @Override
    public String toString() {
        return "RepositorioAcao{}";
    }

    public Acao buscar(int identificador) throws IOException {
        return (Acao) dao.buscar(String.valueOf(identificador));
    }

    public Entidade[] obterTodasAcoes() throws IOException {
        return dao.buscarTodos();
    }


    @Override
    public Class<?> getClasseEntidade() {
        return Acao.class;
    }
}