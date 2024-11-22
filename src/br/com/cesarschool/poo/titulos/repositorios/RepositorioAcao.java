package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.daogenerico.Entidade;
import br.com.cesarschool.poo.titulos.entidades.Acao;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, valorUnitario)
 *
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12
 *
 * A inclusão deve adicionar uma nova linha ao arquivo. Não é permitido incluir
 * identificador repetido. Neste caso, o método deve retornar false. Inclusão com
 * sucesso, retorno true.
 *
 * A alteração deve substituir a linha atual por uma nova linha. A linha deve ser
 * localizada por identificador que, quando não encontrado, enseja retorno false.
 * Alteração com sucesso, retorno true.
 *
 * A exclusão deve apagar a linha atual do arquivo. A linha deve ser
 * localizada por identificador que, quando não encontrado, enseja retorno false.
 * Exclusão com sucesso, retorno true.
 *
 * A busca deve localizar uma linha por identificador, materializar e retornar um
 * objeto. Caso o identificador não seja encontrado no arquivo, retornar null.
 */
public class RepositorioAcao extends RepositorioGeral{
    DAOSerializadorObjetos dao = getDao();
    Class<?> classeEntidade = getClasseEntidade();
    public RepositorioAcao() {
        super();
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