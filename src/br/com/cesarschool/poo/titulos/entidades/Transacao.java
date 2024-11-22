package br.com.cesarschool.poo.titulos.entidades;

import br.com.cesarschool.poo.daogenerico.Entidade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Esta classe deve ter os seguintes atributos:
 * entidadeCredito, do tipo EntidadeOperadora
 * entidadeDebito, do tipo EntidadeOperadora
 * acao, do tipo Acao
 * tituloDivida, do tipo TituloDivida
 * valorOperacao, do tipo double
 * dataHoraOperacao, do tipo LocalDateTime
 *
 * Deve ter um construtor p�blico que inicializa os atributos.
 * Deve ter m�todos get/set p�blicos para todos os atributos, que
 * s�o read-only fora da classe.
 *
 *
 */
public class Transacao extends Entidade {
    private EntidadeOperadora entidadeCredito;
    private EntidadeOperadora entidadeDebito;
    private Acao acao;
    private TituloDivida tituloDivida;
    private double valorOperacao;
    private LocalDateTime dataHoraOperacao;

    public Transacao(EntidadeOperadora entidadeCredito, EntidadeOperadora entidadeDebito, Acao acao, TituloDivida tituloDivida, double valorOperacao, LocalDateTime dataHoraOperacao) {
        this.entidadeCredito = entidadeCredito;
        this.entidadeDebito = entidadeDebito;
        this.acao = acao;
        this.tituloDivida = tituloDivida;
        this.valorOperacao = valorOperacao;
        this.dataHoraOperacao = dataHoraOperacao;
    }

    public EntidadeOperadora getEntidadeCredito() {
        return entidadeCredito;
    }

    public EntidadeOperadora getEntidadeDebito() {
        return entidadeDebito;
    }

    public Acao getAcao() {
        return acao;
    }

    public TituloDivida getTituloDivida() {
        return tituloDivida;
    }

    public double getValorOperacao() {
        return valorOperacao;
    }

    public LocalDateTime getDataHoraOperacao() {
        return dataHoraOperacao;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "\nEntidade Cr�dito: " + entidadeCredito +
                "\nEntidade D�bito: " + entidadeDebito +
                "\nA��o: " + acao +
                "\nT�tulo de d�vida: " + tituloDivida +
                "\nValor da Opera��o: " + valorOperacao +
                "\nData da opera��o: " + dataHoraOperacao;
    }

    @Override
    public String getIdUnico() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = now.format(formatter);

        if (getTituloDivida() == null){
            return getEntidadeCredito().getIdUnico() + "_" + getEntidadeDebito().getIdUnico() + "_" + getAcao().getIdUnico() + formattedDateTime;
        } else {
            return getEntidadeCredito().getIdUnico() + "_" + getEntidadeDebito().getIdUnico() + "_" + getTituloDivida().getIdUnico() + formattedDateTime;
        }
    }
}