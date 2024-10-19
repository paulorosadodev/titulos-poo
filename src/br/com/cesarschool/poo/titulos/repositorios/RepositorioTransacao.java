package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Transacao;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Deve gravar em e ler de um arquivo texto chamado Transacao.txt os dados dos objetos do tipo
 * Transacao. Seguem abaixo exemplos de linhas 
 * De entidadeCredito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De entidadeDebito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De acao: identificador, nome, dataValidade, valorUnitario OU null
 * De tituloDivida: identificador, nome, dataValidade, taxaJuros OU null. 
 * valorOperacao, dataHoraOperacao
 * 
 *   002192;BCB;true;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;1;PETROBRAS;2024-12-12;30.33;null;100000.0;2024-01-01 12:22:21 
 *   002192;BCB;false;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;null;3;FRANCA;2027-11-11;2.5;100000.0;2024-01-01 12:22:21
 *
 * A inclusão deve adicionar uma nova linha ao arquivo.
 * 
 * A busca deve retornar um array de transações cuja entidadeCredito tenha identificador igual ao
 * recebido como parâmetro.
 */
public class RepositorioTransacao {
	public void incluir(Transacao transacao) throws IOException {
		File arquivo = new File("arquivos/Transacao.txt");

		if (!arquivo.exists()) {
			arquivo.getParentFile().mkdirs();
			arquivo.createNewFile();
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {

			bw.write(transacao.getEntidadeCredito().getIdentificador() + ";" +
					transacao.getEntidadeCredito().getNome() + ";" +
					transacao.getEntidadeCredito().isAutorizadoAcao() + ";" +
					transacao.getEntidadeCredito().getSaldoAcao() + ";" +
					transacao.getEntidadeCredito().getSaldoTituloDivida() + ";");


			bw.write(transacao.getEntidadeDebito().getIdentificador() + ";" +
					transacao.getEntidadeDebito().getNome() + ";" +
					transacao.getEntidadeDebito().isAutorizadoAcao() + ";" +
					transacao.getEntidadeDebito().getSaldoAcao() + ";" +
					transacao.getEntidadeDebito().getSaldoTituloDivida() + ";");


			if (transacao.getAcao() != null) {
				bw.write(transacao.getAcao().getIdentificador() + ";" +
						transacao.getAcao().getNome() + ";" +
						transacao.getAcao().getDataDeValidade() + ";" +
						transacao.getAcao().getValorUnitario() + ";");
			} else {
				bw.write("null;");
			}

			if (transacao.getTituloDivida() != null) {
				bw.write(transacao.getTituloDivida().getIdentificador() + ";" +
						transacao.getTituloDivida().getNome() + ";" +
						transacao.getTituloDivida().getDataDeValidade() + ";" +
						transacao.getTituloDivida().getTaxaJuros() + ";");
			} else {
				bw.write("null;");
			}

			bw.write(transacao.getValorOperacao() + ";" +
					transacao.getDataHoraOperacao().toString());

			bw.newLine();
		}
	}

	public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) throws IOException {
		List<Transacao> transacoes = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("arquivos/Transacao.txt"))) {
			String linha;

			while ((linha = br.readLine()) != null) {
				String[] array = linha.split(";");

				if (Integer.valueOf(array[0]).equals(identificadorEntidadeCredito)) {

					EntidadeOperadora entidadeCredito = new EntidadeOperadora(
							Long.parseLong(array[0]),
							array[1],
							Boolean.parseBoolean(array[2])
					);
					entidadeCredito.creditarSaldoAcao(Double.parseDouble(array[3]));
					entidadeCredito.creditarSaldoTituloDivida(Double.parseDouble(array[4]));

					EntidadeOperadora entidadeDebito = new EntidadeOperadora(
							Long.parseLong(array[5]),
							array[6],
							Boolean.parseBoolean(array[7])
					);
					entidadeDebito.creditarSaldoAcao(Double.parseDouble(array[8]));
					entidadeDebito.creditarSaldoTituloDivida(Double.parseDouble(array[9]));

					Acao acao = null;

					if (!"null".equals(array[10])) {
						acao = new Acao(
								Integer.parseInt(array[10]),
								array[11],
								LocalDate.parse(array[12]),
								Double.parseDouble(array[13])
						);
					}

					TituloDivida tituloDivida = null;

					if (!"null".equals(array[14])) {
						tituloDivida = new TituloDivida(
								Integer.parseInt(array[14]),
								array[15],
								LocalDate.parse(array[16]),
								Double.parseDouble(array[17])
						);
					}


					Transacao transacao = new Transacao(
							entidadeCredito,
							entidadeDebito,
							acao,
							tituloDivida,
							Double.parseDouble(array[array.length-2]),
							LocalDateTime.parse(array[array.length-1])
					);
					transacoes.add(transacao);
				}
			}
		}

        return transacoes.toArray(new Transacao[0]);
	}
}
