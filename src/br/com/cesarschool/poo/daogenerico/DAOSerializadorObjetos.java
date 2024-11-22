package br.com.cesarschool.poo.daogenerico;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DAOSerializadorObjetos <T extends Entidade & Serializable> {
    private String nomeDiretorio;

    public DAOSerializadorObjetos(Class<?> tipoEntidade) {
        this.nomeDiretorio = "." + File.separator + tipoEntidade.getSimpleName();
        File dir = new File(nomeDiretorio);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public boolean incluir(Entidade entidade) {
        if (entidade == null) {
            return false;
        }

        File file = new File(nomeDiretorio + File.separator + entidade.getIdUnico());
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else if (file.exists()) {
                return false;
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(entidade);
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Entidade entidade) {
        File file = new File(nomeDiretorio + File.separator + entidade.getIdUnico());
        if (!file.exists()) {
            return false;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Entidade entidadeExistente = (Entidade) ois.readObject();
            if (entidadeExistente.getIdUnico().equals(entidade.getIdUnico())) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    oos.writeObject(entidade);
                }
                return true;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean excluir(String idUnico) {
        File file = new File(nomeDiretorio + File.separator + idUnico);
        try {
            return file.delete();
        } catch (SecurityException e) {
            return false;
        }
    }

    public Entidade buscar(String idUnico) {
        File file = new File(nomeDiretorio + File.separator + idUnico);
        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Entidade) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Entidade[] buscarTodos() {
        File directory = new File(nomeDiretorio);
        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            return new Entidade[0];
        }

        List<Entidade> entidades = new ArrayList<>();
        for (File file : files) {
            if (file.isFile()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    Entidade entidade = (Entidade) ois.readObject();
                    entidades.add(entidade);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return entidades.toArray(new Entidade[0]);
    }
}