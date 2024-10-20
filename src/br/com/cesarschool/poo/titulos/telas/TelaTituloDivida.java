package br.com.cesarschool.poo.titulos.telas;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TelaTituloDivida extends JFrame {

    private final MediatorTituloDivida mediatorTituloDivida = MediatorTituloDivida.getInstancia();
    private final JTextField txtIdentificador;
    private final JTextField txtNome;
    private final JTextField txtDataValidade;
    private final JTextField txtTaxaJuros;
    private final JLabel lblMensagem;

    public TelaTituloDivida() {
        super("CRUD de Títulos de Dívida");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10)); // Correção do layout

        txtIdentificador = new JTextField();
        txtNome = new JTextField();
        txtDataValidade = new JTextField();
        txtTaxaJuros = new JTextField();
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
        add(new JLabel("Taxa de Juros (%):"));
        add(txtTaxaJuros);
        add(btnIncluir);
        add(btnAlterar);
        add(btnExcluir);
        add(btnBuscar);
        add(btnSair);
        add(lblMensagem);

        btnIncluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incluirTituloDivida();
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarTituloDivida();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirTituloDivida();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarTituloDivida();
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

    private void incluirTituloDivida() {
        try {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            String nome = txtNome.getText();
            LocalDate dataValidade = LocalDate.parse(txtDataValidade.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            double taxaJuros = Double.parseDouble(txtTaxaJuros.getText());

            TituloDivida tituloDivida = new TituloDivida(identificador, nome, dataValidade, taxaJuros);
            String retorno = mediatorTituloDivida.incluir(tituloDivida);
            if (retorno == null) {
                lblMensagem.setText("Título de Dívida incluído com sucesso!");
                limparCampos();
            } else {
                lblMensagem.setText(retorno);
            }
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Identificador ou taxa de juros inválido!");
        } catch (DateTimeParseException ex) {
            lblMensagem.setText("Data inválida!");
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao incluir título de dívida!");
        }
    }

    private void alterarTituloDivida() {
        try {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            String nome = txtNome.getText();
            LocalDate dataValidade = LocalDate.parse(txtDataValidade.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            double taxaJuros = Double.parseDouble(txtTaxaJuros.getText());

            TituloDivida tituloDivida = new TituloDivida(identificador, nome, dataValidade, taxaJuros);
            String retorno = mediatorTituloDivida.alterar(tituloDivida);
            if (retorno == null) {
                lblMensagem.setText("Título de Dívida alterado com sucesso!");
                limparCampos();
            } else {
                lblMensagem.setText(retorno);
            }
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Identificador ou taxa de juros inválido!");
        } catch (DateTimeParseException ex) {
            lblMensagem.setText("Data inválida!");
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao alterar título de dívida!");
        }
    }

    private void excluirTituloDivida() {
        try {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            String retorno = mediatorTituloDivida.excluir(identificador);
            if (retorno == null) {
                lblMensagem.setText("Título de Dívida excluído com sucesso!");
                limparCampos();
            } else {
                lblMensagem.setText(retorno);
            }
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Identificador inválido!");
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao excluir título de dívida!");
        }
    }

    private void buscarTituloDivida() {
        try {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            TituloDivida tituloDivida = mediatorTituloDivida.buscar(identificador);
            if (tituloDivida != null) {
                txtNome.setText(tituloDivida.getNome());
                txtDataValidade.setText(tituloDivida.getDataDeValidade().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                txtTaxaJuros.setText(Double.toString(tituloDivida.getTaxaJuros()));
                lblMensagem.setText("Título de Dívida encontrado!");
            } else {
                lblMensagem.setText("Título de Dívida não encontrado!");
            }
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Identificador inválido!");
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao buscar título de dívida!");
        }
    }

    private void limparCampos() {
        txtIdentificador.setText("");
        txtNome.setText("");
        txtDataValidade.setText("");
        txtTaxaJuros.setText("");
    }
}