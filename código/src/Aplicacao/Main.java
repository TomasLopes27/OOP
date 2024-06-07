package Aplicacao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Model app = new Model();
        Controller controller = new Controller(app);
        Scanner sc = new Scanner(System.in);
        View view = new View(controller, sc);
        view.run();
    }
}
