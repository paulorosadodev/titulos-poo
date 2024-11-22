package br.com.cesarschool.poo.titulos.entidades;

import br.com.cesarschool.poo.daogenerico.Entidade;
import br.com.cesarschool.poo.titulos.utils.Comparavel;

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
 * Deve ter um construtor p?blico que inicializa os atributos.
 * Deve ter m?todos get/set p?blicos para todos os atributos, que
 * s?o read-only fora da classe.
 *
 *
 */
public class Transacao extends Entidade implements Comparavel {

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

    public LocalDateTime getDataHoraOperacao(){
        return dataHoraOperacao;
    }

    @Override
    public String getIdUnico() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = dataHoraOperacao.format(formatter);
        return entidadeCredito.getIdUnico() + "_"
                + entidadeDebito.getIdUnico() + "_"
                + ((acao!= null) ? acao.getIdUnico() : tituloDivida.getIdUnico())
                + "_" + formattedDateTime;
    }

    @Override
    public int comparar(Comparavel c) {
        if (!(c instanceof Transacao)) {
            throw new IllegalArgumentException("O objeto deve ser do tipo Transacao.");
        }
        Transacao outraTransacao = (Transacao) c;

        return outraTransacao.getDataHoraOperacao().compareTo(this.dataHoraOperacao);
    }
}

//OK