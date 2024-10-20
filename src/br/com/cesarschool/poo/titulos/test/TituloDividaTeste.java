package br.com.cesarschool.poo.titulos.test;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

public class TituloDividaTeste {

    //    @Test
//    public void testeValidarCerto() throws IOException {
//        TituloDivida td = new TituloDivida(5, "BRASILIANO", LocalDate.of(2025, 10, 7), 20.0);
//        Assertions.assertNull(MediatorTituloDivida.getInstancia());
//    }
    @Test
    public void testeValidarIdentificadorMenor() throws IOException {
        TituloDivida td = new TituloDivida(0, "BRASILIANO", LocalDate.of(2025, 10, 7), 20.0);
        Assertions.assertEquals("Identificador deve estar entre 1 e 99999.",MediatorTituloDivida.getInstancia().incluir(td));
    }
    @Test
    public void testeValidarIdentificadorMaior() throws IOException {
        TituloDivida td = new TituloDivida(1999999999, "BRASILIANO", LocalDate.of(2025, 10, 7), 20.0);
        Assertions.assertEquals("Identificador deve estar entre 1 e 99999.",MediatorTituloDivida.getInstancia().incluir(td));
    }
    @Test
    public void testeValidarNomeInvalido() throws IOException {
        TituloDivida td = new TituloDivida(1, "                         ", LocalDate.of(2025, 10, 7), 20.0);
        Assertions.assertEquals("Nome deve ser preenchido.",MediatorTituloDivida.getInstancia().incluir(td));
    }
    @Test
    public void testeValidarNomeNulo() throws IOException {
        TituloDivida td = new TituloDivida(1, null, LocalDate.of(2025, 10, 7), 20.0);
        Assertions.assertEquals("Nome deve ser preenchido.",MediatorTituloDivida.getInstancia().incluir(td));
    }
    @Test
    public void testeValidarNomeMenor() throws IOException {
        TituloDivida td = new TituloDivida(1, "null", LocalDate.of(2025, 10, 7), 20.0);
        Assertions.assertEquals("Nome deve ter entre 10 e 100 caracteres.",MediatorTituloDivida.getInstancia().incluir(td));
    }

    @Test
    public void testeValidarNomeMaior() throws IOException {
        TituloDivida td = new TituloDivida(1, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                LocalDate.of(2025, 10, 7), 20.0);
        Assertions.assertEquals("Nome deve ter entre 10 e 100 caracteres.",
                MediatorTituloDivida.getInstancia().incluir(td));
    }
    @Test
    public void testeValidarDataInvalida() throws IOException {
        TituloDivida td = new TituloDivida(1, "Brasiliano", LocalDate.now(), 20.0);
        Assertions.assertEquals("Data de validade deve ter pelo menos 180 dias na frente da data atual.",MediatorTituloDivida.getInstancia().incluir(td));
    }
    @Test
    public void testeValidarDataAnterior() throws IOException {
        TituloDivida td = new TituloDivida(1, "Brasiliano", LocalDate.of(2024, 1, 7), 20.0);
        Assertions.assertEquals("Data de validade deve ter pelo menos 180 dias na frente da data atual.",MediatorTituloDivida.getInstancia().incluir(td));
    }
    @Test
    public void testeValidarTaxaJuros() throws IOException {
        TituloDivida td = new TituloDivida(1, "Brasiliano", LocalDate.of(2025, 1, 7), -0.1);
        Assertions.assertEquals("Taxa de juros deve ser maior ou igual a zero.",MediatorTituloDivida.getInstancia().incluir(td));
    }



}