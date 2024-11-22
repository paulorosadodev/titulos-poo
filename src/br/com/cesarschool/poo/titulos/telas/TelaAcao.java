package br.com.cesarschool.poo.titulos.telas;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TelaAcao extends JFrame {

    private final MediatorAcao mediatorAcao = MediatorAcao.getInstancia();
    private final JTextField txtIdentificador;
    private final JTextField txtNome;
    private final JTextField txtDataValidade;
    private final JTextField txtValorUnitario;
    private final JLabel lblMensagem;

    public TelaAcao() {
        super("CRUD de Ações");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));

        txtIdentificador = new JTextField();
        txtNome = new JTextField();
        txtDataValidade = new JTextField();
        txtValorUnitario = new JTextField();
        JButton btnIncluir = new JButton("Incluir");
        JButton btnAlterar = new JButton("Alterar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnSair = new JButton("Sair");
        lblMensagem = new JLabel("");

        add(new JLabel("Identificador:"));
        add(txtIdentificador);
        add(new JLabel("Nome:"));
        add(txtNome);
        add(new JLabel("Data Validade (dd/MM/yyyy):"));
        add(txtDataValidade);
        add(new JLabel("Valor Unitário:"));
        add(txtValorUnitario);
        add(btnIncluir);
        add(btnAlterar);
        add(btnExcluir);
        add(btnBuscar);
        add(btnSair);
        add(lblMensagem);

        btnIncluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incluirAcao();
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarAcao();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirAcao();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAcao();
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

    private void incluirAcao() {
        try {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            String nome = txtNome.getText();
            LocalDate dataValidade = LocalDate.parse(txtDataValidade.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            double valorUnitario = Double.parseDouble(txtValorUnitario.getText());

            Acao acao = new Acao(identificador, nome, dataValidade, valorUnitario);
            String retorno = mediatorAcao.incluir(acao);
            if (retorno == null) {
                lblMensagem.setText("Ação incluída com sucesso!");
                lblMensagem.setForeground(Color.GREEN); // Mensagem de sucesso em verde
                limparCampos();
            } else {
                lblMensagem.setText(retorno);
                lblMensagem.setForeground(Color.RED); // Mensagem de erro em vermelho
            }
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Identificador ou valor unitário inválido!");
            lblMensagem.setForeground(Color.RED); // Mensagem de erro em vermelho
        } catch (DateTimeParseException ex) {
            lblMensagem.setText("Data inválida!");
            lblMensagem.setForeground(Color.RED); // Mensagem de erro em vermelho
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao incluir ação!");
            lblMensagem.setForeground(Color.RED); // Mensagem de erro em vermelho
        }
    }

    private void alterarAcao() {
        try {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            String nome = txtNome.getText();
            LocalDate dataValidade = LocalDate.parse(txtDataValidade.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            double valorUnitario = Double.parseDouble(txtValorUnitario.getText());

            Acao acao = new Acao(identificador, nome, dataValidade, valorUnitario);
            String retorno = mediatorAcao.alterar(acao);
            if (retorno == null) {
                lblMensagem.setText("Ação alterada com sucesso!");
                lblMensagem.setForeground(Color.GREEN); // Mensagem de sucesso em verde
                limparCampos();
            } else {
                lblMensagem.setText(retorno);
                lblMensagem.setForeground(Color.RED); // Mensagem de erro em vermelho
            }
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Identificador ou valor unitário inválido!");
            lblMensagem.setForeground(Color.RED); // Mensagem de erro em vermelho
        } catch (DateTimeParseException ex) {
            lblMensagem.setText("Data inválida!");
            lblMensagem.setForeground(Color.RED); // Mensagem de erro em vermelho
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao alterar ação!");
            lblMensagem.setForeground(Color.RED); // Mensagem de erro em vermelho
        }
    }

    private void excluirAcao() {
        try {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            String retorno = mediatorAcao.excluir(identificador);
            if (retorno == null) {
                lblMensagem.setText("Ação excluída com sucesso!");
                lblMensagem.setForeground(Color.GREEN); // Mensagem de sucesso em verde
                limparCampos();
            } else {
                lblMensagem.setText(retorno);
                lblMensagem.setForeground(Color.RED); // Mensagem de erro em vermelho
            }
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Identificador inválido!");
            lblMensagem.setForeground(Color.RED); // Mensagem de erro em vermelho
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao excluir ação!");
            lblMensagem.setForeground(Color.RED); // Mensagem de erro em vermelho
        }
    }

    private void buscarAcao() {
        try {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            Acao acao = mediatorAcao.buscar(identificador);
            if (acao != null) {
                txtNome.setText(acao.getNome());
                txtDataValidade.setText(acao.getDataValidade().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                txtValorUnitario.setText(Double.toString(acao.getValorUnitario()));
                lblMensagem.setText("Ação encontrada!");
                lblMensagem.setForeground(Color.GREEN); // Mensagem de sucesso em verde
            } else {
                lblMensagem.setText("Ação não encontrada!");
                lblMensagem.setForeground(Color.RED); // Mensagem de erro em vermelho
            }
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Identificador inválido!");
            lblMensagem.setForeground(Color.RED); // Mensagem de erro em vermelho
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao buscar ação!");
            lblMensagem.setForeground(Color.RED); // Mensagem de erro em vermelho
        }
    }

    private void limparCampos() {
        txtIdentificador.setText("");
        txtNome.setText("");
        txtDataValidade.setText("");
        txtValorUnitario.setText("");
    }
}