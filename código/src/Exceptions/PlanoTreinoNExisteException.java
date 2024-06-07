package Exceptions;

/**
 * Exception se nao existe um plano de treino
 */
public class PlanoTreinoNExisteException extends Exception {
    public PlanoTreinoNExisteException(){
        super();
    }
    public PlanoTreinoNExisteException(String msg){
        super(msg);
    }
}
