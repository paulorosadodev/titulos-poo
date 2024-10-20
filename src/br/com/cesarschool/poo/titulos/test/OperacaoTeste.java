package br.com.cesarschool.poo.titulos.test;

import br.com.cesarschool.poo.titulos.mediators.MediatorOperacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class OperacaoTeste {
    boolean ehAcao;
    int entidadeCredito;
    int idEntidadeDebito;
    int idAcaoOuTitulo;
    double valor;
    MediatorOperacao op = MediatorOperacao.getInstancia();
    private String realizarOperacao() throws IOException {
        return MediatorOperacao.getInstancia().realizarOperacao(
                ehAcao,
                entidadeCredito,
                idEntidadeDebito,
                idAcaoOuTitulo,
                valor);

    }

    public OperacaoTeste() throws IOException {
    }

    @Test
    public void TesteRealizarOperacaoValorErrado() throws IOException {
        ehAcao = true;
        entidadeCredito = 101;
        idEntidadeDebito = 101;
        idAcaoOuTitulo = 1;
        valor = 0;

        Assertions.assertEquals("Valor Inválido", realizarOperacao());
    }

    @Test
    public void TesteRealizarOperacaoEntidadeCreditoInvalida() throws IOException {
        ehAcao = true;
        entidadeCredito = 1;
        idEntidadeDebito = 101;
        idAcaoOuTitulo = 1;
        valor = 10;

        Assertions.assertEquals("Entidade crédito inexistente", realizarOperacao());
    }
    @Test
    public void TesteRealizarOperacaoEntidadeDebitoInvalida() throws IOException {
        ehAcao = true;
        entidadeCredito = 101;
        idEntidadeDebito = 1;
        idAcaoOuTitulo = 1;
        valor = 10;
        Assertions.assertEquals("Entidade debito inexistente", realizarOperacao());
    }
    @Test
    public void TesteRealizarOperacaoEntidadeCreditoAcaoNaoAutorizada() throws IOException {
        ehAcao = true;
        entidadeCredito = 101;
        idEntidadeDebito = 102;
        idAcaoOuTitulo = 1;
        valor = 10;
        Assertions.assertEquals("Entidade de crédito não autorizada para ação", realizarOperacao());
    }
    @Test
    public void TesteRealizarOperacaoEntidadeDebitoAcaoNaoAutorizada() throws IOException {
        ehAcao = true;
        entidadeCredito = 102;
        idEntidadeDebito = 101;
        idAcaoOuTitulo = 1;
        valor = 10;
        Assertions.assertEquals("Entidade de débito não foi autorizada para ação", realizarOperacao());
    }
    @Test
    public void TesteRealizarOperacaoBuscarAcaoInexistente() throws IOException {
        ehAcao = true;
        entidadeCredito = 102;
        idEntidadeDebito = 102;
        idAcaoOuTitulo = 9;
        valor = 10;
        Assertions.assertEquals("Ação inexistente", realizarOperacao());
    }
    @Test
    public void TesteRealizarOperacaoBuscarTituloDividaInexistente() throws IOException {
        ehAcao = false;
        entidadeCredito = 102;
        idEntidadeDebito = 102;
        idAcaoOuTitulo = 9;
        valor = 10;
        Assertions.assertEquals("Titulo inexistente", realizarOperacao());
    }
    @Test
    public void TesteRealizarOperacaoSaldoInssuficienteDebitoAcao() throws IOException {
        ehAcao = true;
        entidadeCredito = 102;
        idEntidadeDebito = 102;
        idAcaoOuTitulo = 1;
        valor = 1000000;
        Assertions.assertEquals("Saldo da entidade débito insuficiente", realizarOperacao());
    }
    @Test
    public void TesteRealizarOperacaoSaldoInssuficienteDebitoTitulo() throws IOException {
        ehAcao = false;
        entidadeCredito = 102;
        idEntidadeDebito = 102;
        idAcaoOuTitulo = 1;
        valor = 1000000;
        Assertions.assertEquals("Saldo da entidade débito insuficiente", realizarOperacao());
    }
    @Test
    public void TesteRealizarOperacaoValorUltrapassaValorUnitario() throws IOException {
        ehAcao = true;
        entidadeCredito = 102;
        idEntidadeDebito = 103;
        idAcaoOuTitulo = 1;
        valor = 20;
        Assertions.assertEquals("Valor da operação e menor do que o valor unitário da ação", realizarOperacao());
    }
    @Test
    public void TesteRealizarOperacaoErroEntidadeDebito() throws IOException {
        ehAcao = true;
        entidadeCredito = 102;
        idEntidadeDebito = 103;
        idAcaoOuTitulo = 1;
        valor = 20;
        Assertions.assertEquals("Valor da operação e menor do que o valor unitário da ação", realizarOperacao());
    }
    @Test
    public void TesteRealizarOperacaoCertaAcao() throws IOException {
        ehAcao = true;
        entidadeCredito = 102;
        idEntidadeDebito = 103;
        idAcaoOuTitulo = 10;
        valor = 25;
        Assertions.assertNull(realizarOperacao());
    }
    @Test
    public void TesteRealizarOperacaoCertaTitulo() throws IOException {
        ehAcao = false;
        entidadeCredito = 102;
        idEntidadeDebito = 103;
        idAcaoOuTitulo = 1;
        valor = 25;
        Assertions.assertNull(realizarOperacao());
    }
}