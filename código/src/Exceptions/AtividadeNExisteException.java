package Exceptions;

/**
 * Exception se nao existe uma atividade
 */
public class AtividadeNExisteException extends Exception {

    public AtividadeNExisteException(){
        super();
    }

    public AtividadeNExisteException(String msg){
        super(msg);
    }
}
