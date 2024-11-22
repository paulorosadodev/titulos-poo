package br.com.cesarschool.poo.titulos.utils;

import br.com.cesarschool.poo.titulos.entidades.Transacao;

public class ComparadorTransacaoPorNomeCredora extends ComparadorPadrao
        implements Comparador {

    @Override
    public int comparar(Comparavel c1, Comparavel c2) {
        if (!(c1 instanceof Transacao) || !(c2 instanceof Transacao)){
            throw new IllegalArgumentException("Os objetos devem ser do tipo Transacao.");
        }
        Transacao transacao1 = (Transacao) c1;
        Transacao transacao2 = (Transacao) c2;
        return transacao1.getEntidadeCredito().getNome()
                .compareTo(transacao2.getEntidadeCredito().getNome());
    }
}