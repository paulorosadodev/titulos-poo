package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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