package Menu;

import Aplicacao.Controller;


/**
 * Representa o checker de um user
 */
public class UsersChecker implements NewMenu.Checker{
    private Controller control;

    public UsersChecker(Controller control){
        this.control = control;
    }

    @Override
    public boolean validate() {
        return this.control.getTotalUsers() > 0;
    }
}
