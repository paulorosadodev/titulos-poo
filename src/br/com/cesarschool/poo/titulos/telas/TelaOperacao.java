package br.com.cesarschool.poo.titulos.telas;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorOperacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelaOperacao extends JFrame {

    private final MediatorOperacao mediatorOperacao = MediatorOperacao.getInstance();
    private final MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstance();
    private final JComboBox<String> cbxEntidadeCredito;
    private final JComboBox<String> cbxEntidadeDebito;
    private final JComboBox<String> cbxTipoAtivo;
    private final JTextField txtIdentificadorAtivo;
    private final JTextField txtValorOperacao;
    private final JLabel lblMensagem;

    public TelaOperacao() {
        super("Tela de Operações");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));

        cbxEntidadeCredito = new JComboBox<>();
        cbxEntidadeDebito = new JComboBox<>();
        cbxTipoAtivo = new JComboBox<>(new String[]{"Ação", "Título de Dívida"});
        txtIdentificadorAtivo = new JTextField();
        txtValorOperacao = new JTextField();
        JButton btnRealizarOperacao = new JButton("Realizar Operação");
        JButton btnSair = new JButton("Sair");
        lblMensagem = new JLabel("");

        add(new JLabel("Entidade de Crédito:"));
        add(cbxEntidadeCredito);
        add(new JLabel("Entidade de Débito:"));
        add(cbxEntidadeDebito);
        add(new JLabel("Tipo de Ativo:"));
        add(cbxTipoAtivo);
        add(new JLabel("Identificador do Ativo:"));
        add(txtIdentificadorAtivo);
        add(new JLabel("Valor da Operação:"));
        add(txtValorOperacao);
        add(btnRealizarOperacao);
        add(btnSair);
        add(lblMensagem);

        popularComboboxesEntidades();

        btnRealizarOperacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarOperacao();
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void popularComboboxesEntidades() {
        try {
            List<EntidadeOperadora> entidades = obterEntidades();
            for (EntidadeOperadora entidade : entidades) {
                cbxEntidadeCredito.addItem(entidade.getNome() + " - " + entidade.getIdentificador());
                cbxEntidadeDebito.addItem(entidade.getNome() + " - " + entidade.getIdentificador());
            }
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao obter entidades operadoras!");
            lblMensagem.setForeground(Color.RED); // Cor vermelha para erro
        }
    }

    private List<EntidadeOperadora> obterEntidades() throws IOException {
        List<EntidadeOperadora> entidades = new ArrayList<>();
        for (int i = 100; i <= 100000; i++) {
            EntidadeOperadora entidade = mediatorEntidadeOperadora.buscar(i);
            if (entidade != null) {
                entidades.add(entidade);
            }
        }
        return entidades;
    }

    private void realizarOperacao() {
        try {
            String entidadeCreditoString = (String) cbxEntidadeCredito.getSelectedItem();
            String entidadeDebitoString = (String) cbxEntidadeDebito.getSelectedItem();
            if (entidadeCreditoString == null || entidadeDebitoString == null) {
                lblMensagem.setText("Selecione as entidades de crédito e débito!");
                lblMensagem.setForeground(Color.RED); // Cor vermelha para erro
                return;
            }

            long idEntidadeCredito = Long.parseLong(entidadeCreditoString.split(" - ")[1]);
            long idEntidadeDebito = Long.parseLong(entidadeDebitoString.split(" - ")[1]);
            boolean ehAcao = cbxTipoAtivo.getSelectedIndex() == 0;
            int idAtivo = Integer.parseInt(txtIdentificadorAtivo.getText());

            // Validação do campo txtValorOperacao
            if (txtValorOperacao.getText().isEmpty()) {
                lblMensagem.setText("O campo Valor da Operação é obrigatório!");
                lblMensagem.setForeground(Color.RED); // Cor vermelha para erro
                return;
            }

            double valor = Double.parseDouble(txtValorOperacao.getText());

            String retorno = mediatorOperacao.realizarOperacao(ehAcao, (int) idEntidadeCredito, (int) idEntidadeDebito, idAtivo, valor);
            if (retorno == null) {
                lblMensagem.setText("Operação realizada com sucesso!");
                lblMensagem.setForeground(Color.GREEN); // Cor verde para sucesso
                limparCampos();
            } else {
                lblMensagem.setText(retorno);
                lblMensagem.setForeground(Color.RED); // Cor vermelha para erro
            }
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Identificador do ativo ou valor da operação inválido!");
            lblMensagem.setForeground(Color.RED); // Cor vermelha para erro
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao realizar operação!");
            lblMensagem.setForeground(Color.RED); // Cor vermelha para erro
        }
    }

    private void limparCampos() {
        cbxEntidadeCredito.setSelectedIndex(0);
        cbxEntidadeDebito.setSelectedIndex(0);
        cbxTipoAtivo.setSelectedIndex(0);
        txtIdentificadorAtivo.setText("");
        txtValorOperacao.setText("");
    }
}