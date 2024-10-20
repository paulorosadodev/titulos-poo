package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        super("Gerenciamento de Títulos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        JLabel lblTitulo = new JLabel("Gerenciamento de Títulos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo);

        JButton btnAcoes = new JButton("Gerenciar Ações");
        JButton btnTitulosDivida = new JButton("Gerenciar Títulos de Dívida");
        JButton btnEntidadesOperadoras = new JButton("Gerenciar Entidades Operadoras");
        JButton btnOperacoes = new JButton("Realizar Operações");
        JButton btnSair = new JButton("Sair");

        add(btnAcoes);
        add(btnTitulosDivida);
        add(btnEntidadesOperadoras);
        add(btnOperacoes);
        add(btnSair);

        btnAcoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaAcao();
            }
        });

        btnTitulosDivida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaTituloDivida();
            }
        });

        btnEntidadesOperadoras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaEntidadeOperadora();
            }
        });

        btnOperacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaOperacao();
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MenuPrincipal();
    }
}