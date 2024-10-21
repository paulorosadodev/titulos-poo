package br.com.cesarschool.poo.titulos.telas;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorOperacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TelaExtrato extends JFrame {

    private final MediatorOperacao mediatorOperacao = MediatorOperacao.getInstancia();
    private final MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstancia();
    private final JComboBox<String> cbxEntidades;
    private final JTextArea txtAreaExtrato;
    private final JLabel lblMensagem;

    public TelaExtrato() {
        super("Gerar Extrato");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        cbxEntidades = new JComboBox<>();
        txtAreaExtrato = new JTextArea();
        txtAreaExtrato.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaExtrato);
        JButton btnGerarExtrato = new JButton("Gerar Extrato");
        JButton btnSair = new JButton("Sair");
        lblMensagem = new JLabel("");

        JPanel panelTop = new JPanel(new GridLayout(1, 2, 10, 10));
        panelTop.add(new JLabel("Entidade:"));
        panelTop.add(cbxEntidades);

        JPanel panelBottom = new JPanel(new FlowLayout());
        panelBottom.add(btnGerarExtrato);
        panelBottom.add(btnSair);
        panelBottom.add(lblMensagem);

        add(panelTop, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);

        popularComboboxEntidades();

        btnGerarExtrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarExtrato();
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

    private void popularComboboxEntidades() {
        try {
            List<EntidadeOperadora> entidades = obterEntidades();
            for (EntidadeOperadora entidade : entidades) {
                cbxEntidades.addItem(entidade.getNome() + " - " + entidade.getIdentificador());
            }
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao obter entidades operadoras!");
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

    private void gerarExtrato() {
        try {
            String entidadeString = (String) cbxEntidades.getSelectedItem();
            if (entidadeString == null) {
                lblMensagem.setText("Selecione uma entidade!");
                return;
            }

            int idEntidade = Integer.parseInt(entidadeString.split(" - ")[1]);
            Transacao[] transacoes = mediatorOperacao.gerarExtrato(idEntidade);

            if (transacoes.length == 0) { // Verificação se o array de transações está vazio
                txtAreaExtrato.setText("Não há transações registradas.");
                return;
            }

            StringBuilder extrato = new StringBuilder();
            for (Transacao transacao : transacoes) {
                extrato.append("Data/Hora: ")
                        .append(transacao.getDataHoraOperacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                        .append("\n");
                extrato.append("Tipo: ")
                        .append(transacao.getEntidadeCredito().getIdentificador() == idEntidade ? "Crédito" : "Débito")
                        .append("\n");
                extrato.append("Valor: ")
                        .append(transacao.getValorOperacao())
                        .append("\n");
                if (transacao.getAcao() != null) {
                    extrato.append("Ativo: ")
                            .append(transacao.getAcao().getNome())
                            .append(" (Ação)")
                            .append("\n");
                } else if (transacao.getTituloDivida() != null) {
                    extrato.append("Ativo: ")
                            .append(transacao.getTituloDivida().getNome())
                            .append(" (Título de Dívida)")
                            .append("\n");
                }
                extrato.append("--------------------\n");
            }

            txtAreaExtrato.setText(extrato.toString());
            lblMensagem.setText("Extrato gerado com sucesso!");
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Erro ao obter identificador da entidade!");
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao gerar extrato!");
        }
    }
}