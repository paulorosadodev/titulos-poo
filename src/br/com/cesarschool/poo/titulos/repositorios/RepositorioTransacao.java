package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.daogenerico.Entidade;

import br.com.cesarschool.poo.titulos.entidades.Transacao;

import java.util.ArrayList;
import java.util.List;


public class RepositorioTransacao extends RepositorioGeral{
	private final DAOSerializadorObjetos<Transacao> dao;

	public RepositorioTransacao() {
		super(Transacao.class);
		this.dao = new DAOSerializadorObjetos<>(Transacao.class);
	}

	public void incluir(Transacao transacao) {
		if (buscar(String.valueOf(transacao.getIdUnico())) == null) {
			dao.incluir(transacao);
		}
	}

	public Transacao[] buscarPorEntidadeCredora(long identificadorEntidadeCredito) {
		List<Transacao> resultado = new ArrayList<>();
		for (Transacao transacao : buscarTodos()) {
			if (transacao.getEntidadeCredito() != null
					&& transacao.getEntidadeCredito().getIdentificador() == identificadorEntidadeCredito) {
				resultado.add(transacao);
			}
		}
		return resultado.toArray(new Transacao[0]);
	}

	public Transacao[] buscarPorEntidadeDevedora(long identificadorEntidadeDebito) {
		List<Transacao> resultado = new ArrayList<>();
		for (Transacao transacao : buscarTodos()) {
			if (transacao.getEntidadeDebito() != null
					&& transacao.getEntidadeDebito().getIdentificador() == identificadorEntidadeDebito) {
				resultado.add(transacao);
			}
		}
		return resultado.toArray(new Transacao[0]);
	}

	@Override
	public Class<?> getClasseEntidade() {
		return Transacao.class;
	}

	@Override
	public Transacao[] buscarTodos() {
		List<Transacao> todasTransacoes = new ArrayList<>();
		for (Entidade entidade : dao.buscarTodos()) {
			if (entidade instanceof Transacao) {
				todasTransacoes.add((Transacao) entidade);
			}
		}
		return todasTransacoes.toArray(new Transacao[0]);
	}
}