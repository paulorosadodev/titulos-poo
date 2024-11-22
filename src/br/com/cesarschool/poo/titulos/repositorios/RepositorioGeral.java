package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.daogenerico.Entidade;

public abstract class RepositorioGeral <T extends Entidade>{
    private DAOSerializadorObjetos dao;
    public abstract Class<T> getClasseEntidade();

//    public RepositorioGeral(Class<T> classeEntidade) {
//        this.classeEntidade = classeEntidade;
//        this.dao = new DAOSerializadorObjetos(classeEntidade);
//    }


    public RepositorioGeral() {
        this.dao = new DAOSerializadorObjetos(getClasseEntidade());
    }

    public DAOSerializadorObjetos getDao() {
        return dao;
    }

    public boolean incluir(T entidade){
        return dao.incluir(entidade);
    }

    public boolean alterar(T entidade){
        return dao.alterar(entidade);
    }

    public boolean excluir(String id){
        return dao.excluir(id);
    }

    public Entidade buscar(String id){
        return dao.buscar(id);
    }

    public Entidade[] buscarTodos(){
        return dao.buscarTodos();
    }
}