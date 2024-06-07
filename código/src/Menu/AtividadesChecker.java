package Menu;

import Aplicacao.Controller;

/**
 * Representa o checker de atividades
 */
public class AtividadesChecker implements NewMenu.Checker {
    private Controller control;

    public AtividadesChecker (Controller control) {
        this.control = control;
    }


    @Override
    public boolean validate() {
        return this.control.getTotalAtividades() > 0;
    }
}
