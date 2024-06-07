package Menu;
import Utilizadores.Utilizador;
import Utilizadores.UtilizadorAmador;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class NewMenu  {

    public interface Handler{
        public void execute();
    }

    public interface Checker{
        public boolean validate();
    }
    private static Scanner input = new Scanner(System.in);

    private List<String> opcoes;
    private List<NewMenu.Handler> handlers;
    private List<NewMenu.Checker> checkers;

    /**
     * Cria um novo menu utilizando as seguintes options
     * @param options opções
     */
    public NewMenu(String[] options){
        this.opcoes = Arrays.asList(options);
        this.checkers = new ArrayList<>();
        this.handlers = new ArrayList<>();
        this.opcoes.forEach(s -> {
            this.checkers.add(()->true);
            this.handlers.add(()->System.out.println("Não foi possível atribuir a opção"));
        });
    }


    /**
     * Corre o menu
     */
    public void run(){
        int op;
        do {
            this.show();
            op = readOption();
            if(op > 0 && !this.checkers.get(op-1).validate()){
                System.out.println("Opção indisponível");
            } else if (op > 0) {
                this.handlers.get(op-1).execute();
            }
        }while (op != 0);
    }

    /**
     * Corre o menu sem ter a opção de sair
     */
    public void run_autoExit() {
        int op;
        this.showNoExit();
        boolean verify = false;
        while (!verify) {
            op = readOption();
            if (op > 0 && op <= this.checkers.size() && !this.checkers.get(op-1).validate()) {
                System.out.println("Opção indisponível");
            } else if (op > 0 && op <= this.handlers.size()-1) {
                this.handlers.get(op-1).execute();
                verify = true;
            } else if (op == 0 || op == this.handlers.size()) {
                System.out.println("Opção Inválida!");
            }
        }
    }


    public void setCheckers(int i,NewMenu.Checker c){
        this.checkers.set(i-1,c);
    }

    public void setHandlers(int i,NewMenu.Handler h){
        this.handlers.set(i-1,h);
    }

    /**
     * Mostra as opcoes do menu
     */
    private void show(){
        for (int i = 1; i < this.opcoes.size(); i++){
            System.out.printf("\n%d -> %s",i,this.opcoes.get(i));
        }
        System.out.printf("\n\n 0 -> Sair\n");
    }

    /**
     * Mostra as opcoes do menu sem exit
     */
    private void showNoExit() {
        for (int i = 1; i < this.opcoes.size(); i++){
            System.out.printf("\n%d -> %s",i,this.opcoes.get(i));
        }
    }

    /**
     * lê a opcao do utilizador
     * @return opcao que corresponde ao seguinte menu
     */
    private int readOption(){
        int op;
        System.out.print("\nSelecione uma opção: ");
        try {
            String line = input.nextLine();
            op = Integer.parseInt(line);
        }catch (NumberFormatException e){
            op = -1;
        }
        if(op < 0 || op > this.opcoes.size()){
            System.out.println("Opção Inválida!");
            op = -1;
        }
        return op;
    }



}
