package br.gov.cesarschool.poo.daogenerico;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Entidade implements Serializable {
    private String idUnico;
    private LocalDateTime dataHoraInclusao;
    private LocalDateTime dataHoraUltimaAlteracao;

    public Entidade(String idUnico) {
        this();
        this.idUnico = idUnico;
    }

    protected Entidade() {
        this.dataHoraInclusao = LocalDateTime.now();
        this.dataHoraUltimaAlteracao = LocalDateTime.now();
    }

    public abstract String getIdUnico();

    public LocalDateTime getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    public LocalDateTime getDataHoraUltimaAlteracao() {
        return dataHoraUltimaAlteracao;
    }

    public void setDataHoraUltimaAlteracao(LocalDateTime dataHoraUltimaAlteracao) {
        this.dataHoraUltimaAlteracao = dataHoraUltimaAlteracao;
    }
}