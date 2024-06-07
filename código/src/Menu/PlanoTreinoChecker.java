package Menu;

import Aplicacao.Controller;


/**
 * Representa o checker de um plano de treino
 */
public class PlanoTreinoChecker implements NewMenu.Checker{
    private Controller control;

    public PlanoTreinoChecker(Controller control){
        this.control = control;
    }

    @Override
    public boolean validate() {
        return this.control.getTotalPlanoTreinos() > 0;
    }

}
