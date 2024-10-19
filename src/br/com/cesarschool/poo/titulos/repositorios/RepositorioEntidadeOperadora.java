package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * Deve gravar em e ler de um arquivo texto chamado EntidadeOperadora.txt os dados dos objetos do tipo
 * EntidadeOperadora. Seguem abaixo exemplos de linhas.
 *
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12 
 * 
 * A inclus�o deve adicionar uma nova linha ao arquivo. Não é permitido incluir
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
public class RepositorioEntidadeOperadora {

    public boolean incluir(EntidadeOperadora entidadeOperadora) throws IOException {
        File arquivo = new File("arquivos/EntidadeOperadora.txt");

        if (!arquivo.exists()) {
            arquivo.getParentFile().mkdirs();
            arquivo.createNewFile();
        }

        boolean achouId = false;

        try (BufferedReader br = new BufferedReader(new FileReader("arquivos/EntidadeOperadora.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith(String.valueOf(entidadeOperadora.getIdentificador()))) {
                    achouId = true;
                    break;
                }

            }
        }

        if (achouId) {
            return false;
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
                bw.write(entidadeOperadora.getIdentificador() + ";" + entidadeOperadora.getNome() + ";" + entidadeOperadora.isAutorizadoAcao() + ";"
                        + entidadeOperadora.getSaldoAcao() + ";" + entidadeOperadora.getSaldoTituloDivida());
                bw.newLine();
            }
            return true;
        }

    }

    public boolean alterar(EntidadeOperadora entidadeOperadora) throws IOException {
        List<String> linhas = new ArrayList<>();

        File arquivo = new File("arquivos/EntidadeOperadora.txt");

        if (!arquivo.exists()) {
            arquivo.getParentFile().mkdirs();
            arquivo.createNewFile();
        }

        boolean achouId = false;

        try (BufferedReader br = new BufferedReader(new FileReader("arquivos/EntidadeOperadora.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (Integer.valueOf(linha.split(";")[0]) == entidadeOperadora.getIdentificador()) {
                    linhas.add(entidadeOperadora.getIdentificador() + ";" + entidadeOperadora.getNome() + ";" + entidadeOperadora.isAutorizadoAcao() + ";"
                            + entidadeOperadora.getSaldoAcao() + ";" + entidadeOperadora.getSaldoTituloDivida());
                    achouId = true;
                } else {
                    linhas.add(linha);
                }

            }
        }

        if (achouId) {
            Files.write(Paths.get("arquivos/EntidadeOperadora.txt"), linhas);
            return true;
        }
        return false;
    }

    public boolean excluir(int identificador) throws IOException {
        List<String> linhas = new ArrayList<>();

        File arquivo = new File("arquivos/EntidadeOperadora.txt");

        if (!arquivo.exists()) {
            arquivo.getParentFile().mkdirs();
            arquivo.createNewFile();
        }

        boolean achouId = false;

        try (BufferedReader br = new BufferedReader(new FileReader("arquivos/EntidadeOperadora.txt"))) {
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
            Files.write(Paths.get("arquivos/EntidadeOperadora.txt"), linhas);
            return true;
        }
        return false;
    }

    public EntidadeOperadora buscar(int identificador) throws IOException {

        File arquivo = new File("arquivos/EntidadeOperadora.txt");

        if (!arquivo.exists()) {
            arquivo.getParentFile().mkdirs();
            arquivo.createNewFile();
        }

        EntidadeOperadora objeto = null;

        try (BufferedReader br = new BufferedReader(new FileReader("arquivos/EntidadeOperadora.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith(String.valueOf(identificador))) {
                    String[] array = linha.split(";");
                    objeto = new EntidadeOperadora(Integer.valueOf(array[0]), array[1],
                            Boolean.parseBoolean(array[2]));
                    objeto.creditarSaldoAcao(Double.parseDouble(array[3]));
                    objeto.creditarSaldoTituloDivida(Double.parseDouble(array[4]));
                    return objeto;
                }
            }
        }

        return null;
    }

}
