package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
public class RepositorioTituloDivida {

	public boolean incluir(TituloDivida tituloDivida) throws IOException {

		File arquivo = new File("arquivos/TituloDivida.txt");

		if (!arquivo.exists()) {
			arquivo.getParentFile().mkdirs();
			arquivo.createNewFile();
		}

		boolean achouId = false;

		try (BufferedReader br = new BufferedReader(new FileReader("arquivos/TituloDivida.txt"))) {
			String linha;

			while ((linha = br.readLine()) != null) {
				if (linha.startsWith(String.valueOf(tituloDivida.getIdentificador()))) {
					achouId = true;
					break;
				}

			}
		}

		if (achouId) {
			return false;
		} else {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
				bw.write(tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" + tituloDivida.getDataDeValidade() + ";"
						+ tituloDivida.getTaxaJuros());
				bw.newLine();
			}
			return true;
		}
	}

	public boolean alterar(TituloDivida tituloDivida) throws IOException {
		List<String> linhas = new ArrayList<>();

		File arquivo = new File("arquivos/TituloDivida.txt");

		if (!arquivo.exists()) {
			arquivo.getParentFile().mkdirs();
			arquivo.createNewFile();
		}

		boolean achouId = false;

		try (BufferedReader br = new BufferedReader(new FileReader("arquivos/TituloDivida.txt"))) {
			String linha;

			while ((linha = br.readLine()) != null) {
				if (Integer.valueOf(linha.split(";")[0]) == tituloDivida.getIdentificador()) {
					linhas.add(tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" + tituloDivida.getDataDeValidade() + ";"
							+ tituloDivida.getTaxaJuros());
					achouId = true;
				} else {
					linhas.add(linha);
				}

			}
		}

		if (achouId) {
			Files.write(Paths.get("arquivos/TituloDivida.txt"), linhas);
			return true;
		}
		return false;
	}

	public boolean excluir(int identificador) throws IOException {

		List<String> linhas = new ArrayList<>();

		File arquivo = new File("arquivos/TituloDivida.txt");

		if (!arquivo.exists()) {
			arquivo.getParentFile().mkdirs();
			arquivo.createNewFile();
		}

		boolean achouId = false;

		try (BufferedReader br = new BufferedReader(new FileReader("arquivos/TituloDivida.txt"))) {
			String linha;

			while ((linha = br.readLine()) != null) {
				if (!linha.startsWith(String.valueOf(identificador))) {
					linhas.add(linha);
				} else {
					achouId = true;
				}

			}
		}

		if (achouId) {
			Files.write(Paths.get("arquivos/TituloDivida.txt"), linhas);
			return true;
		}
		return false;
	}

	public TituloDivida buscar(int identificador) throws IOException {

		File arquivo = new File("arquivos/TituloDivida.txt");

		if (!arquivo.exists()) {
			arquivo.getParentFile().mkdirs();
			arquivo.createNewFile();
		}

		TituloDivida objeto = null;

		try (BufferedReader br = new BufferedReader(new FileReader("arquivos/TituloDivida.txt"))) {
			String linha;

			while ((linha = br.readLine()) != null) {
				if (linha.startsWith(String.valueOf(identificador))) {
					String[] array = linha.split(";");
					objeto = new TituloDivida(Integer.valueOf(array[0]), array[1], LocalDate.parse(array[2]),
							Double.valueOf(array[3]));
					return objeto;
				}
			}
		}

		return null;
	}

}
