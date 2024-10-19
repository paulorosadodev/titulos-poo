package br.com.cesarschool.poo.titulos.test;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

public class AcaoTeste {

    @Test
    public void testeValidarErroNomeCurto() throws IOException {
        Acao acao = new Acao(10,"CESAR", LocalDate.of(2025, 10, 7), 30.332);
        Assertions.assertEquals("Nome deve ter entre 10 e 100 caracteres.", MediatorAcao.getInstancia().incluir(acao));
    }

    @Test
    public void testeValidarErroNomeLongo() throws IOException {
        Acao acao = new Acao(10,
                "CESARRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                LocalDate.of(2025, 10, 7), 30.332);
        Assertions.assertEquals("Nome deve ter entre 10 e 100 caracteres.", MediatorAcao.getInstancia().incluir(acao));
    }

    @Test
    public void testeValidarErroNomeVazio() throws IOException {
        Acao acao = new Acao(10, "", LocalDate.of(2025, 10, 7), 30.332);
        Assertions.assertEquals("Nome deve ser preenchido.", MediatorAcao.getInstancia().incluir(acao));
    }

    @Test
    public void testeValidarErroNomeNulo() throws IOException {
        Acao acao = new Acao(10, null, LocalDate.of(2025, 10, 7), 30.332);
        Assertions.assertEquals("Nome deve ser preenchido.", MediatorAcao.getInstancia().incluir(acao));
    }

    @Test
    public void testeValidarErroIdentificadorMenor() throws IOException {
        Acao acao = new Acao(0, "CESAR SCHOOL", LocalDate.of(2025, 10, 7), 30.332);
        Assertions.assertEquals("Identificador deve estar entre 1 e 99999.", MediatorAcao.getInstancia().incluir(acao));
    }

    @Test
    public void testeValidarErroIdentificadorMaior() throws IOException {
        Acao acao = new Acao(999999999, "CESAR SCHOOL", LocalDate.of(2025, 10, 7), 30.332);
        Assertions.assertEquals("Identificador deve estar entre 1 e 99999.", MediatorAcao.getInstancia().incluir(acao));
    }

    @Test
    public void testeValidarErroDataInvalida() throws IOException {
        Acao acao = new Acao(10, "CESAR SCHOOL", LocalDate.of(2024, 1, 7), 30.332);
        Assertions.assertEquals("Data de validade deve ter pelo menos 30 dias na frente da data atual.", MediatorAcao.getInstancia().incluir(acao));
    }

    @Test
    public void testeValidarErroValorInvalido() throws IOException {
        Acao acao = new Acao(10, "CESAR SCHOOL", LocalDate.of(2025, 1, 7), -30.332);
        Assertions.assertEquals("Valor unitário deve ser maior que zero.", MediatorAcao.getInstancia().incluir(acao));
    }

    @Test
    public void testeValidarCerto() throws IOException {
        Acao acao = new Acao(10, "CESARSCHOOL", LocalDate.of(2025, 10, 7), 30.332);
        Assertions.assertNull(MediatorAcao.getInstancia().incluir(acao));
    }
}
