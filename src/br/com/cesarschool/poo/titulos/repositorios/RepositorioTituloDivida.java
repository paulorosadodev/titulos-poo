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

public class RepositorioTituloDivida extends RepositorioGeral{
	DAOSerializadorObjetos dao = getDao();
	Class<?> classeEntidade = getClasseEntidade();
	public RepositorioTituloDivida() {
		super();
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