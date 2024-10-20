package br.com.cesarschool.poo.titulos.telas;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TelaEntidadeOperadora extends JFrame {

    private final MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstancia();
    private final JTextField txtIdentificador;
    private final JTextField txtNome;
    private final JCheckBox chkAutorizadoAcao;
    private final JLabel lblMensagem;

    public TelaEntidadeOperadora() {
        super("CRUD de Entidades Operadoras");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        txtIdentificador = new JTextField();
        txtNome = new JTextField();
        chkAutorizadoAcao = new JCheckBox("Autorizado para Ações");
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
        add(new JLabel("Autorizado para Ações:"));
        add(chkAutorizadoAcao);
        add(btnIncluir);
        add(btnAlterar);
        add(btnExcluir);
        add(btnBuscar);
        add(btnSair);
        add(lblMensagem);

        btnIncluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incluirEntidadeOperadora();
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarEntidadeOperadora();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirEntidadeOperadora();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEntidadeOperadora();
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

    private void incluirEntidadeOperadora() {
        try {
            long identificador = Long.parseLong(txtIdentificador.getText());
            String nome = txtNome.getText();
            boolean autorizadoAcao = chkAutorizadoAcao.isSelected();

            EntidadeOperadora entidadeOperadora = new EntidadeOperadora(identificador, nome, autorizadoAcao);
            String retorno = mediatorEntidadeOperadora.incluir(entidadeOperadora);
            if (retorno == null) {
                lblMensagem.setText("Entidade Operadora incluída com sucesso!");
                limparCampos();
            } else {
                lblMensagem.setText(retorno);
            }
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Identificador inválido!");
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao incluir Entidade Operadora!");
        }
    }

    private void alterarEntidadeOperadora() {
        try {
            long identificador = Long.parseLong(txtIdentificador.getText());
            String nome = txtNome.getText();
            boolean autorizadoAcao = chkAutorizadoAcao.isSelected();

            EntidadeOperadora entidadeOperadora = new EntidadeOperadora(identificador, nome, autorizadoAcao);
            String retorno = mediatorEntidadeOperadora.alterar(entidadeOperadora);
            if (retorno == null) {
                lblMensagem.setText("Entidade Operadora alterada com sucesso!");
                limparCampos();
            } else {
                lblMensagem.setText(retorno);
            }
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Identificador inválido!");
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao alterar Entidade Operadora!");
        }
    }

    private void excluirEntidadeOperadora() {
        try {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            String retorno = mediatorEntidadeOperadora.excluir(identificador);
            if (retorno == null) {
                lblMensagem.setText("Entidade Operadora excluída com sucesso!");
                limparCampos();
            } else {
                lblMensagem.setText(retorno);
            }
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Identificador inválido!");
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao excluir Entidade Operadora!");
        }
    }

    private void buscarEntidadeOperadora() {
        try {
            int identificador = Integer.parseInt(txtIdentificador.getText());
            EntidadeOperadora entidadeOperadora = mediatorEntidadeOperadora.buscar(identificador);
            if (entidadeOperadora != null) {
                txtNome.setText(entidadeOperadora.getNome());
                chkAutorizadoAcao.setSelected(entidadeOperadora.isAutorizadoAcao());
                lblMensagem.setText("Entidade Operadora encontrada!");
            } else {
                lblMensagem.setText("Entidade Operadora não encontrada!");
            }
        } catch (NumberFormatException ex) {
            lblMensagem.setText("Identificador inválido!");
        } catch (IOException ex) {
            lblMensagem.setText("Erro ao buscar Entidade Operadora!");
        }
    }

    private void limparCampos() {
        txtIdentificador.setText("");
        txtNome.setText("");
        chkAutorizadoAcao.setSelected(false);
    }
}