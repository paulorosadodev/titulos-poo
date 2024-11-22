package br.gov.cesarschool.poo.daogenerico;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * Esta classe representa uma superclasse de todas as entidades.
 *
 * Deve ter os seguintes atributos (com respectivos set e get):
 *	LocalDateTime dataHoraInclusao,
 *  LocalDateTime dataHoraUltimaAlteracao,
 *	String usuarioInclusao e
 *	String usuarioUltimaAlteracao
 *
 * Deve ter um único construtor sem parâmetros.
 *
 * Deve ser abstrata.
 *
 * Deve ter um métod abstrato getIdUnico().
 *
 * Deve implementar a interface Serializable do JAVA
 */
public abstract class Entidade<T> implements Serializable {
    private LocalDateTime dataHoraInclusao;
    private LocalDateTime dataHoraUltimaAlteracao;
    private String usuarioInclusao;
    private String usuarioUltimaAlteracao;

    public String getUsuarioInclusao() {
        return usuarioInclusao;
    }

    public LocalDateTime getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    public LocalDateTime getDataHoraUltimaAlteracao() {
        return dataHoraUltimaAlteracao;
    }

    public String getUsuarioUltimaAlteracao() {
        return usuarioUltimaAlteracao;
    }

    public void setDataHoraInclusao(LocalDateTime dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }

    public void setDataHoraUltimaAlteracao(LocalDateTime dataHoraUltimaAlteracao) {
        this.dataHoraUltimaAlteracao = dataHoraUltimaAlteracao;
    }

    public void setUsuarioInclusao(String usuarioInclusao) {
        this.usuarioInclusao = usuarioInclusao;
    }

    public void setUsuarioUltimaAlteracao(String usuarioUltimaAlteracao) {
        this.usuarioUltimaAlteracao = usuarioUltimaAlteracao;
    }

    public abstract T getIdUnico();

    public Entidade() {
        dataHoraInclusao = LocalDateTime.now();
        dataHoraUltimaAlteracao = LocalDateTime.now();
    }

}