package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTituloDivida;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * Deve ser um singleton.
 *
 * Deve ter um atributo repositorioTituloDivida, do tipo RepositorioTituloDivida, que deve
 * ser inicializado na sua declara��o. Este atributo ser� usado exclusivamente
 * pela classe, n�o tendo, portanto, m�todos set e get.
 *
 * M�todos:
 *
 * pivate String validar(TituloDivida titulo): deve validar os dados do objeto recebido nos seguintes termos:
 * identificador: deve ser maior que zero e menor que 100000 (1)
 * nome: deve ser preenchido, diferente de branco e de null (2). deve ter entre 10 e 100 caracteres (3).
 * data de validade: deve ser maior do que a data atual mais 180 dias (4).
 * valorUnitario: deve ser maior que zero (5).
 * O m�todo validar deve retornar null se o objeto estiver v�lido, e uma mensagem pertinente (ver abaixo)
 * se algum valor de atributo estiver inv�lido.
 *
 * (1) - Identificador deve estar entre 1 e 99999.
 * (2) - Nome deve ser preenchido.
 * (3) - Nome deve ter entre 10 e 100 caracteres.
 * (4) - Data de validade deve ter pelo menos 180 dias na frente da data atual.
 * (5) - Taxa de juros deve ser maior ou igual a zero.
 *
 * public String incluir(TituloDivida titulo): deve chamar o m�todo validar. Se ele
 * retornar null, deve incluir titulo no reposit�rio. Retornos poss�veis:
 * (1) null, se o retorno do validar for null e o retorno do incluir do
 * reposit�rio for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "T�tulo j� existente", se o retorno do validar for null
 * e o retorno do reposit�rio for false.
 *
 * public String alterar(TituloDivida titulo): deve chamar o m�todo validar. Se ele
 * retornar null, deve alterar titulo no reposit�rio. Retornos poss�veis:
 * (1) null, se o retorno do validar for null e o retorno do alterar do
 * reposit�rio for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "T�tulo inexistente", se o retorno do validar for null
 * e o retorno do reposit�rio for false.
 *
 * public String excluir(int identificador): deve validar o identificador.
 * Se este for v�lido, deve chamar o excluir do reposit�rio. Retornos poss�veis:
 * (1) null, se o retorno do excluir do reposit�rio for true.
 * (2) A mensagem "T�tulo inexistente", se o retorno do reposit�rio for false
 * ou se o identificador for inv�lido.
 *
 * public TituloDivida buscar(int identificador): deve validar o identificador.
 * Se este for v�lido, deve chamar o buscar do reposit�rio, retornando o
 * que ele retornar. Se o identificador for inv�lido, retornar null.
 */
public class MediatorTituloDivida {
    private static MediatorTituloDivida instance;
    private RepositorioTituloDivida repositorioTituloDivida = new RepositorioTituloDivida();

    private MediatorTituloDivida() {
    }

    public static MediatorTituloDivida getInstance() {
        if (instance == null) {
            instance = new MediatorTituloDivida();
        }
        return instance;

    }
    public List<TituloDivida> obterTitulosDivida() {
        try {
            return List.of((TituloDivida[]) repositorioTituloDivida.obterTodosTitulosDivida());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private String validar(TituloDivida titulo) {
        boolean isValid = true;
        if (titulo.getIdentificador() <= 0 || titulo.getIdentificador() >= 100000) {
            return "Identificador deve estar entre 1 e 99999";
        }
        if (titulo.getNome()==null || titulo.getNome().trim().isBlank()) {
            return "Nome deve ser preenchido.";
        }
        if (titulo.getNome().length() < 10 || titulo.getNome().length() > 100) {

            return "Nome deve ter entre 10 e 100 caracteres.";
        }
        if (titulo.getDataValidade().isBefore(LocalDate.now().plusDays(180))) {
            return "Data de validade deve ter pelo menos 180 dias na frente da data atual.";

        }
        if (titulo.getTaxaJuros() < 0) {
            return "Taxa de juros deve ser maior ou igual a zero.";
        }
        return null;
    }


    public String incluir(TituloDivida titulo) throws IOException {
        String result = validar(titulo);
        if (result == null) {
            boolean deuCerto = repositorioTituloDivida.incluir(titulo);
            if (deuCerto) {
                return null;
            } else {
                return "T�tulo j� existente";
            }
        } else {
            return result;
        }
    }


    public String alterar(TituloDivida titulo) throws IOException {
        String result = validar(titulo);
        if (result == null) {
            boolean deuCerto = repositorioTituloDivida.alterar(titulo);
            if (deuCerto) {
                return null;
            } else {
                return "T�tulo inexistente";
            }
        } else {
            return result;
        }
    }


    public String excluir(int identificador) throws IOException {
        if (identificador <= 0 || identificador >= 100000) {
            return "T�tulo inexistente";
        }
        boolean deuCerto = repositorioTituloDivida.excluir(identificador);
        if (deuCerto) {
            return null;
        } else {
            return "T�tulo inexistente";

        }
    }


    public TituloDivida buscar(int identificador) throws IOException {
        if (identificador <= 0 || identificador >= 100000) {
            return null;
        }
        TituloDivida tituloDivida= repositorioTituloDivida.buscar(identificador);
        return tituloDivida;

    }
}