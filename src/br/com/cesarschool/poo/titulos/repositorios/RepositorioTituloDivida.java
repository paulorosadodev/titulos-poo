package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.daogenerico.Entidade;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

import java.io.*;

/*
 * Deve gravar em e ler de um arquivo texto chamado TituloDivida.txt os dados dos objetos do tipo
 * TituloDivida. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, taxaJuros).
 *
    1;BRASIL;2024-12-12;10.5
    2;EUA;2026-01-01;1.5
    3;FRANCA;2027-11-11;2.5
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

public class RepositorioTituloDivida extends RepositorioGeral{
	private final DAOSerializadorObjetos<TituloDivida> dao;

	public RepositorioTituloDivida() {
		super(TituloDivida.class);
		this.dao = new DAOSerializadorObjetos<>(TituloDivida.class);
	}

	public boolean incluir(TituloDivida tituloDivida) throws IOException {
		return dao.incluir(tituloDivida);
	}

	public boolean alterar(TituloDivida tituloDivida) throws IOException {
		return dao.alterar(tituloDivida);
	}


	public boolean excluir(int identificador) throws IOException {
		return dao.excluir(String.valueOf(identificador));
	}

	public TituloDivida buscar(int identificador) throws IOException {
		return (TituloDivida) dao.buscar(String.valueOf(identificador));
	}
	public Entidade[] obterTodosTitulosDivida() throws IOException {
		return dao.buscarTodos();
	}

	@Override
	public Class<?> getClasseEntidade() {
		return TituloDivida.class;
	}
}