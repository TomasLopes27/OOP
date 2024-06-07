package Exceptions;

/**
 * Exception de um utilizador
 */
public class UtilizadorNExisteException extends Exception{

    public UtilizadorNExisteException(){
        super();
    }

    public UtilizadorNExisteException(String msg){
        super(msg);
    }
}
