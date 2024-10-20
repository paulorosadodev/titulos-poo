package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Deve ser um singleton.
 *
 * Deve ter os seguites atributos:
 *
 * mediatorAcao, do tipo MediatorAcao
 * mediatorTituloDivida, do tipo MediatorTituloDivida
 * mediatorEntidadeOperadora, do tipo MediatorEntidadeOperadora
 * repositorioTransacao, do tipo RepositorioTransacao
 *
 * Estes atributos devem ser inicializados nas suas declarações.
 *
 * Estes atributos serão usados exclusivamente pela classe, não tendo, portanto, métodos set e get.
 *
 * Métodos:
 *
 * public String realizarOperacao(boolean ehAcao, int entidadeCredito,
 * int idEntidadeDebito, int idAcaoOuTitulo, double valor): segue o
 * passo a passo deste método.
 *
 * 1- Validar o valor, que deve ser maior que zero. Se o valor for inválido,
 * retornar a mensagem "Valor inválido".
 *
 * 2- Buscar a entidade de crédito no mediator de entidade operadora.
 * Se não encontrar, retornar a mensagem "Entidade crédito inexistente".
 *
 * 3- Buscar a entidade de débito no mediator de entidade operadora.
 * Se não encontrar, retornar a mensagem "Entidade débito inexistente".
 *
 * 4- Se ehAcao for true e a entidade de crédito não for autorizada para
 * ação, retornar a mensagem "Entidade de crédito não autorizada para ação"
 *
 * 5- Se ehAcao for true e a entidade de débito não for autorizada para
 * ação, retornar a mensagem "Entidade de débito não autorizada para ação"
 *
 * 6- Buscar a ação (se ehAcao for true) ou o título (se ehAcao for false)
 * no mediator de ação ou de título.
 *
 * 7- A depender de ehAcao, validar o saldoAcao ou o saldoTituloDivida da
 * entidade de débito. O saldo deve ser maior ou igual a valor. Se não for,
 * retornar a mensagem "Saldo da entidade débito insuficiente".
 *
 * 8- Se ehAcao for true, verificar se valorUnitario da ação é maior do que
 * valor. Se for, retornar a mensagem
 * "Valor da operação e menor do que o valor unitário da ação"
 *
 * 9- Calcular o valor da operação. Se ehAcao for true, o valor da operação
 * é igual a valor. Se ehAcao for false, o valor da operação é igual ao
 * retorno do método calcularPrecoTransacao, invocado a partir do título da
 * dívida buscado. valor deve ser passado como parâmetro na invocação deste
 * método.
 *
 *  10- Invocar o método creditarSaldoAcao ou creditarSaldoTituloDivida da
 *  entidade de crédito, passando o valor da operação.
 *
 *  11- Invocar o método debitarSaldoAcao ou debitarSaldoTituloDivida da
 *  entidade de débito, passando o valor da operação.
 *
 *  12- Alterar entidade de crédito no mediator de entidade operadora. Se
 *  o retorno do alterar for uma mensagem diferente de null, retornar a
 *  mensagem.
 *
 *  13- Alterar entidade de débito no mediator de entidade operadora. Se
 *  o retorno do alterar for uma mensagem diferente de null, retornar a
 *  mensagem.
 *
 *  14- Criar um objeto do tipo Transacao, com os seguintes valores de atributos:
 *
 * entidadeCredito - a entidade de crédito buscada
 * entidadeDebito - a entidade de débito buscada
 * acao - se ehAcao for true, a ação buscada, caso contrário, null
 * tituloDivida - se ehAcao for false, o título buscado, caso contrário, null
 * valorOperacao - o valor da operação calculado no item 9
 * dataHoraOperacao - a data e a hora atuais
 *
 * 15- Incluir a transação criada no repositório de transação.
 *
 * public Transacao gerarExtrato(int entidade):
 *
 * 1- para este método funcionar, deve-se acrescentar no repositório de
 * transação o método
 * public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeDebito)
 * A busca deve retornar um array de transações cuja entidadeDebito tenha identificador igual ao
 * recebido como parâmetro.
 *
 * 2- Buscar as transações onde entidade é credora.
 *
 * 3- Buscar as transações onde entidade é devedora.
 *
 * 4- Colocar as transações buscadas nos itens 2 e 3 em um único novo array.
 *
 * 5- Ordenar este novo array por dataHoraOperacao decrescente.
 *
 * 6- Retornar o novo array.
 **/
public class MediatorOperacao {

    private static MediatorOperacao instance;

    private MediatorAcao mediatorAcao = MediatorAcao.getInstancia();
    private MediatorTituloDivida mediatorTituloDivida = MediatorTituloDivida.getInstancia();
    private MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstancia();
    private RepositorioTransacao repositorioTransacao = new RepositorioTransacao();

    private MediatorOperacao() {

    }

    public static MediatorOperacao getInstancia() {
        if (instance == null) {
            instance = new MediatorOperacao();
        }
        return instance;
    }

    public String realizarOperacao(boolean ehAcao, int idEntidadeCredito, int idEntidadeDebito,
                                    int idAcaoOuTitulo, double valor) throws IOException {

        EntidadeOperadora entidadeCredito = mediatorEntidadeOperadora.buscar(idEntidadeCredito);
        EntidadeOperadora entidadeDebito = mediatorEntidadeOperadora.buscar(idEntidadeDebito);

        if (valor <= 0) {
            return "Valor inválido";
        }
        if (entidadeCredito == null) {
            return "Entidade crédito inexistente";
        }
        if (entidadeDebito == null) {
            return "Entidade débito inexistente";
        }
        if (ehAcao && !entidadeCredito.isAutorizadoAcao()) {
            return "Entidade de crédito não autorizada para ação";
        }
        if (ehAcao && !entidadeDebito.isAutorizadoAcao()) {
            return "Entidade de débito não autorizada para ação";
        }

        Acao acao = null;
        TituloDivida titulo = null;
        double valorOperacao = valor;

        if (ehAcao) {
            acao = mediatorAcao.buscar(idAcaoOuTitulo);

            if (entidadeDebito.getSaldoAcao() < valor) {
                return "Saldo da entidade débito insuficiente";
            }
            if (acao.getValorUnitario() > valor) {
                return "Valor da operação é menor do que o valor unitário da ação";
            }

            entidadeCredito.creditarSaldoAcao(valorOperacao);
            entidadeDebito.debitarSaldoAcao(valorOperacao);

        } else {
            titulo = mediatorTituloDivida.buscar(idAcaoOuTitulo);

            if (entidadeDebito.getSaldoTituloDivida() < valor) {
                return "Saldo da entidade débito insuficiente";
            }

            valorOperacao = titulo.calcularPrecoTransacao(valor);

            entidadeCredito.creditarSaldoTituloDivida(valorOperacao);
            entidadeDebito.debitarSaldoTituloDivida(valorOperacao);
        }

        String atualizacaoCredito = mediatorEntidadeOperadora.alterar(entidadeCredito);
        if (atualizacaoCredito != null) {
            return atualizacaoCredito;
        }

        String atualizacaoDebito = mediatorEntidadeOperadora.alterar(entidadeDebito);
        if (atualizacaoDebito != null) {
            return atualizacaoDebito;
        }

        Transacao transacao = new Transacao(entidadeCredito, entidadeDebito, acao, titulo, valorOperacao,
                LocalDateTime.now());

        repositorioTransacao.incluir(transacao);
        return null;
    }

    public Transacao[] gerarExtrato(int entidade) throws IOException {
        Transacao[] entidadeEhCredora = repositorioTransacao.buscarPorEntidadeCredora(entidade);
        Transacao[] entidadeEhDevedora = repositorioTransacao.buscarPorEntidadeDevedora(entidade);

        List<Transacao> transacoesCombinadas = new ArrayList<>();
        Collections.addAll(transacoesCombinadas, entidadeEhCredora);
        Collections.addAll(transacoesCombinadas, entidadeEhDevedora);

        transacoesCombinadas.sort((t1, t2) -> t2.getDataHoraOperacao().compareTo(t1.getDataHoraOperacao()));

        return transacoesCombinadas.toArray(new Transacao[0]);
    }
}