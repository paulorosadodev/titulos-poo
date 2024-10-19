package br.com.cesarschool.poo.titulos.repositorios;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.cesarschool.poo.titulos.entidades.Acao;
/*

Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
Acao. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, valorUnitario)

  1;PETROBRAS;2024-12-12;30.33
  2;BANCO DO BRASIL;2026-01-01;21.21
  3;CORREIOS;2027-11-11;6.12
A inclus�o deve adicionar uma nova linha ao arquivo. Não é permitido incluir
identificador repetido. Neste caso, o método deve retornar false. Inclusão com
sucesso, retorno true.

A alteração deve substituir a linha atual por uma nova linha. A linha deve ser
localizada por identificador que, quando n�o encontrado, enseja retorno false.
Alteração com sucesso, retorno true.

A exclusão deve apagar a linha atual do arquivo. A linha deve ser
localizada por identificador que, quando não encontrado, enseja retorno false.
Exclusão com sucesso, retorno true.

A busca deve localizar uma linha por identificador, materializar e retornar um
objeto. Caso o identificador não seja encontrado no arquivo, retornar null.*/

public class RepositorioAcao {
    public boolean incluir(Acao acao) throws IOException {
        File arquivo = new File("arquivos/Acao.txt");

        if (!arquivo.exists()) {
            arquivo.getParentFile().mkdirs();
            arquivo.createNewFile();
        }

        boolean achouId = false;

        try (BufferedReader br = new BufferedReader(new FileReader("arquivos/Acao.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith(String.valueOf(acao.getIdentificador()))) {
                    achouId = true;
                    break;
                }

            }
        }

        if (achouId) {
            return false;
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
                bw.write(acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDataDeValidade() + ";"
                        + acao.getValorUnitario());
                bw.newLine();
            }
            return true;
        }

    }

    public boolean alterar(Acao acao) throws IOException {
        List<String> linhas = new ArrayList<>();

        File arquivo = new File("arquivos/Acao.txt");

        if (!arquivo.exists()) {
            arquivo.getParentFile().mkdirs();
            arquivo.createNewFile();
        }

        boolean achouId = false;

        try (BufferedReader br = new BufferedReader(new FileReader("arquivos/Acao.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (Integer.valueOf(linha.split(";")[0]) == acao.getIdentificador()) {
                    linhas.add(acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDataDeValidade() + ";"
                            + acao.getValorUnitario());
                    achouId = true;
                } else {
                    linhas.add(linha);
                }

            }
        }

        if (achouId) {
            Files.write(Paths.get("arquivos/Acao.txt"), linhas);
            return true;
        }
        return false;
    }

    public boolean excluir(int identificador) throws IOException {
        List<String> linhas = new ArrayList<>();

        File arquivo = new File("arquivos/Acao.txt");

        if (!arquivo.exists()) {
            arquivo.getParentFile().mkdirs();
            arquivo.createNewFile();
        }

        boolean achouId = false;

        try (BufferedReader br = new BufferedReader(new FileReader("arquivos/Acao.txt"))) {
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
            Files.write(Paths.get("arquivos/Acao.txt"), linhas);
            return true;
        }
        return false;
    }

    public Acao buscar(int identificador) throws IOException {

        File arquivo = new File("arquivos/Acao.txt");

        if (!arquivo.exists()) {
            arquivo.getParentFile().mkdirs();
            arquivo.createNewFile();
        }

        Acao objeto = null;

        try (BufferedReader br = new BufferedReader(new FileReader("arquivos/Acao.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith(String.valueOf(identificador))) {
                    String[] array = linha.split(";");
                    objeto = new Acao(Integer.valueOf(array[0]), array[1], LocalDate.parse(array[2]), Double.valueOf(array[3]));
                    return objeto;
                }
            }
        }

        return null;
    }
}