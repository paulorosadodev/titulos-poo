package br.com.cesarschool.poo.titulos.test;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EntidadeOperadoraTeste {

    @Test
    public void testeValidarErroIdentificadorLongo() throws IOException {
        EntidadeOperadora ec = new EntidadeOperadora(999999999, "Visa", true);
        ec.creditarSaldoAcao(20);
        Assertions.assertEquals("Identificador deve estar entre 100 e 1000000.", MediatorEntidadeOperadora.getInstancia().incluir(ec));
    }

    @Test
    public void testeValidarErroIdentificadorCurto() throws IOException {
        EntidadeOperadora ec = new EntidadeOperadora(99, "Visa", true);
        ec.creditarSaldoAcao(20);
        Assertions.assertEquals("Identificador deve estar entre 100 e 1000000.", MediatorEntidadeOperadora.getInstancia().incluir(ec));
    }

    @Test
    public void testeValidarErroNomeInvalido() throws IOException {
        EntidadeOperadora ec = new EntidadeOperadora(101, "     ", true);
        ec.creditarSaldoAcao(20);
        Assertions.assertEquals("Nome deve ser preenchido.", MediatorEntidadeOperadora.getInstancia().incluir(ec));
    }

    @Test
    public void testeValidarErroNomeNulo() throws IOException {
        EntidadeOperadora ec = new EntidadeOperadora(101, null, true);
        ec.creditarSaldoAcao(20);
        Assertions.assertEquals("Nome deve ser preenchido.", MediatorEntidadeOperadora.getInstancia().incluir(ec));
    }

    @Test
    public void testeValidarErroNomeCurto() throws IOException {
        EntidadeOperadora ec = new EntidadeOperadora(101, "aa", true);
        ec.creditarSaldoAcao(20);
        Assertions.assertEquals("Nome deve ter entre 10 e 100 caracteres.", MediatorEntidadeOperadora.getInstancia().incluir(ec));
    }

    @Test
    public void testeValidarErroNomeLongo() throws IOException {
        EntidadeOperadora ec = new EntidadeOperadora(101, "aa", true);
        ec.creditarSaldoAcao(20);
        Assertions.assertEquals("Nome deve ter entre 10 e 100 caracteres.", MediatorEntidadeOperadora.getInstancia().incluir(ec));
    }

    @Test
    public void testeValidarCerto() throws IOException {
        EntidadeOperadora ec = new EntidadeOperadora(101, "BANCO CENTRAL", true);
        ec.creditarSaldoAcao(20);
        Assertions.assertNull(MediatorEntidadeOperadora.getInstancia().incluir(ec));
    }

}
