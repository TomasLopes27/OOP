package Aplicacao;

import Atividades.Atividade;
import Atividades.AtividadeAltimetria;
import Atividades.BicicletaMontanha;
import Exceptions.PlanoTreinoNExisteException;
import Exceptions.UtilizadorNExisteException;
import Menu.AtividadesChecker;
import Menu.NewMenu;
import Menu.PlanoTreinoChecker;
import Menu.UsersChecker;
import Utilizadores.Genero;
import Utilizadores.Utilizador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class View {
    private Controller control;
    private Scanner sc;

    /**
     * Construtor parametrizado
     * @param control
     * @param scanner
     */
    public View(Controller control, Scanner scanner) {
        this.control = control;
        this.sc = scanner;
    }

    /**
     * executar o programa
     */
    public void run() {
        MenuPrincipal();
    }

    /**
     * Menu principal
     */
    public void MenuPrincipal() {
        String[] options = {"Selecione uma opção\n","Menu utilizadores","Menu atividades","Menu planos de treino","Menu Simulação","Guardar Estado"
                           ,"Carregar Estado", "Menu queries", "Avançar tempo"};
        System.out.println("\n***** Menu principal *****\n");
        NewMenu principal = new NewMenu(options);
        principal.setHandlers(1,this::MenuUtilizadores);
        principal.setHandlers(2,this::MenuAtividades);
        principal.setHandlers(3,this::MenuPlanosDeTreino);
        principal.setHandlers(4,this::MenuSimulacao1);
        principal.setHandlers(5,this::GuardaEstado);
        principal.setHandlers(6,this::carregaEstado);
        principal.setHandlers(7,this::MenuQueries);
        principal.setHandlers(8, this::MenuAvancarTempo);
        principal.run();
    }

    /**
     * Menu dos planos de treino
     */
    public void MenuPlanosDeTreino(){
        System.out.println("\n***** Plano de Treino *****\n");
        String[] options = {"Escolha uma opção","Criar Plano de Treino","Associar Plano de Treino a um Utilizador","Listar Planos de Treino"};
        NewMenu menuPlanoDeTreino = new NewMenu(options);

        UsersChecker userChecker = new UsersChecker(this.control);
        PlanoTreinoChecker planoChecker = new PlanoTreinoChecker(this.control);

        menuPlanoDeTreino.setCheckers(2, planoChecker);

        menuPlanoDeTreino.setHandlers(1,this::criarPlanoDeTreino);
        menuPlanoDeTreino.setHandlers(2, this::MenuInserirIdUserPlano);
        menuPlanoDeTreino.setHandlers(3,this::ListarPlanoDeTreino);

        menuPlanoDeTreino.run();
    }

    /**
     * Menu de avancar tempo
     */
    public void MenuAvancarTempo () {
        System.out.println(this.control.getData());
        System.out.print("Introduza os dias que pretende avançar: ");

        long choice = 0;
        boolean valid_choice = false;
        while (!valid_choice) {
            try {
                String act = sc.nextLine();
                choice = Long.parseLong(act);
                if (choice > 0) {
                    valid_choice = true;
                }
            } catch (NumberFormatException e) {}
            if (!valid_choice) {System.out.println("Valor inválido");}
        }

        this.control.AvancarTempo(choice);
    }

    /**
     * Menu de simualacao
     */
    public void MenuSimulacao1(){
        System.out.println(this.control.getData());
        System.out.print("Introduza os dias que pretende avançar: ");

        long choice = 0;
        boolean valid_choice = false;
        while (!valid_choice) {
            try {
                String act = sc.nextLine();
                choice = Long.parseLong(act);
                if (choice > 0) {
                    valid_choice = true;
                }
            } catch (NumberFormatException e) {}
            if (!valid_choice) {System.out.println("Valor inválido");}
        }

        Model antigo = this.control.getModelo().clone();    //clone do antigo

        this.control.AvancarTempo(choice);

        this.MenuSimulacao();

        //POE O MODEL ANTIGO COMO ATUAL
        this.control.colocaModelo(antigo);
        System.out.println(this.control.getData());
    }

    /**
     * Menu dos utilizadores
     */
    public void MenuUtilizadores() {
            System.out.println("***** Menu Utilizadores *****");
            String [] options = {"Selecione uma opção\n","Criar utilizador","Remover utilizador","Ver utilizador por id","Listar utilizadores"};

            NewMenu users = new NewMenu(options);

            UsersChecker userChecker = new UsersChecker(this.control);

            users.setCheckers(2, userChecker);
            users.setCheckers(3, userChecker);

            users.setHandlers(1,this::AddUtilizador);
            users.setHandlers(2,this::RemoveUtilizador);
            users.setHandlers(3,this::ListarSingleUser);
            users.setHandlers(4,this::ListarUtilizadores);
            users.run();


    }

    /**
     * Menu das atividades
     */
    public void MenuAtividades() {
        System.out.println("\n***** Menu atividades *****\n");
        String [] options = {"Selecione uma opção\n","Criar uma atividade","Consultar atividades","Atribuir atividade a um utilizador"};

        NewMenu activ = new NewMenu(options);

        AtividadesChecker atividadesChecker = new AtividadesChecker(this.control);

        activ.setCheckers(3, atividadesChecker);

        activ.setHandlers(1,this::AddAtividade);
        activ.setHandlers(2,this::ListarAtividades);
        activ.setHandlers(3,this::MenuInserirIdUtilizador);

        activ.run();
    }

    /**
     * Menu de inserir o id de um utilizador
     */
    public void MenuInserirIdUserPlano() {
        System.out.println("\n***** Inserir id do utilizador que pretende associar a um plano de treino *****\n");
        String[] options = {"Selecione uma opção", "Inserir id do utilizador"};
        NewMenu users = new NewMenu(options);

        UsersChecker userChecker = new UsersChecker(this.control);

        users.setCheckers(1, userChecker);
        users.setHandlers(1, this::AtribuirPlanoTreinoUtilizador);

        users.run();
    }

    /**
     * Menu de atribuir plano de treino a um utilizador
     */
    public void AtribuirPlanoTreinoUtilizador() {
        int plano = 0;
        while (plano <= 0 || plano > this.control.getTotalPlanoTreinos()) {
            System.out.print("Qual o plano que pretende inserir (1-"+ this.control.getTotalPlanoTreinos()+"): ");
            try {
                plano = this.sc.nextInt();
            } catch (NumberFormatException e) {System.out.println("Valor inválido");}
        }

        int user = 0;
        while (user <= 0 || user > this.control.getTotalUsers()) {
            System.out.print("Qual o id do utilizador que pretende inserir (1-"+ this.control.getTotalUsers()+"): ");
            try {
                user = this.sc.nextInt();
            } catch (NumberFormatException e) {System.out.println("Valor inválido");}
        }

        List<DayOfWeek> dias = askDias();

        String dataPlanodeTreino;
        LocalDate data_inicio = LocalDate.now();
        boolean idade_valido = false;
        while(!idade_valido) {
            System.out.print("Data de início do plano de treino: ");
            dataPlanodeTreino = sc.nextLine();
            try {
                data_inicio = LocalDate.parse(dataPlanodeTreino);
                if (this.control.validData(user, data_inicio)) {
                    idade_valido = true;
                }
            } catch (DateTimeParseException | UtilizadorNExisteException e) {e.getMessage();}
            if (!idade_valido) {System.out.println("Valor inválido");}
        }

        String horaPlano;
        LocalTime hora_inicio = LocalTime.now();
        boolean hora_valido = false;
        while(!hora_valido) {
            System.out.print("Hora do plano de treino: ");
            horaPlano = sc.nextLine();
            try {
                hora_inicio = LocalTime.parse(horaPlano);
                hora_valido = true;
            } catch (DateTimeParseException e) {System.out.println("Hora inválida");}
        }

        String dataLimite_parse;
        LocalDate data_limite = LocalDate.now();
        boolean datalimite_valido = false;
        while(!datalimite_valido) {
            System.out.print("Data limite do plano de treino: ");
            dataLimite_parse = sc.nextLine();
            try {
                data_limite = LocalDate.parse(dataLimite_parse);
                if (data_limite.isAfter(data_inicio)) {
                    datalimite_valido = true;
                }
            } catch (DateTimeParseException e) {}
            if (!datalimite_valido) {System.out.println("Valor inválido");}
        }

        this.control.atribuirPlanoTreinoUser(plano, user, data_inicio, data_limite, dias, hora_inicio);

    }

    /**
     * Menu de adicionar atividade a um plano de treino
     */
    public void AddAtividadeforPlanoTreino() {
        System.out.println("***** Adicionar atividade *****");
        String [] option = {"Qual o tipo de atividade que pretende inserir\n","Atividade distância","Atividade altimetria e distância",
                "Atividade repetições sem pesos","Atividade repetições com pesos"};
        NewMenu addActiv = new NewMenu(option);
        addActiv.setHandlers(1,this::MenuAtividadeDistanciaNoExit);
        addActiv.setHandlers(2,this::MenuAtividadeAltimetriaNoExit);
        addActiv.setHandlers(3,this::MenuAtividadeSemPesoNoExit);
        addActiv.setHandlers(4,this::MenuAtividadeComPesoNoExit);

        addActiv.run_autoExit();
    }

    /**
     * Menu das queries
     */
    public void MenuQueries() {
        String[] options = {"Selecione uma opção","Utilizador que mais calorias dispendeu num período ou desde sempre",
                            "Utilizador que mais actividades realizou num período ou desde sempre",
                            "Tipo de actividade mais realizada",
                            "Km's que um utilizador realizou num período ou desde sempre",
                            "Metros de altimetria é que um utilizador totalizou num período ou desde sempre",
                            "Plano de treino mais exigente em função do dispêndio de calorias proposto",
                            "Listar as actividades de um utilizador", "Lista as atividades do tipo Hard"};

        NewMenu menu = new NewMenu(options);

        UsersChecker usersChecker = new UsersChecker(this.control);

        menu.setCheckers(1, usersChecker);
        menu.setCheckers(2, usersChecker);
        menu.setCheckers(4, usersChecker);
        menu.setCheckers(5, usersChecker);
        menu.setCheckers(6, usersChecker);
        menu.setCheckers(7, usersChecker);

        menu.setHandlers(1,this::Query1);
        menu.setHandlers(2,this::Query2);
        menu.setHandlers(3,this::Query3);
        menu.setHandlers(4,this::Query4);
        menu.setHandlers(5,this::Query5);
        menu.setHandlers(6,this::Query6);
        menu.setHandlers(7,this::Query7);
        menu.setHandlers(8,this::Query8);

        menu.run();

    }

    /**
     * Menu query 1
     */
    public void Query1() {
        System.out.println("\n***** Utilizador que gastou mais calorias *****");
        System.out.println("1 - Num período");
        System.out.println("2 - Desde sempre");

        boolean valid = false;
        int choice = 0;
        while(!valid) {
            try {
                String ch = sc.nextLine();
                choice = Integer.parseInt(ch);
                if (choice == 1 || choice == 2) {
                    valid = true;
                }
            } catch (NumberFormatException e) {System.out.println("Valor inválido");}
        }

        switch (choice) {
            case 1:
                LocalDate inicio;
                LocalDate fim;
                boolean valid_dates = false;
                do {
                    inicio = askData(0);
                    fim = askData(1);
                    if (fim.isAfter(inicio)) valid_dates = true;
                } while (!valid_dates);

                try {
                    System.out.println(this.control.Query1Periodo(inicio, fim));
                } catch (UtilizadorNExisteException e) {System.out.println("\n" + e.getMessage());}

                break;
            case 2:
                try {
                    System.out.println(this.control.Query1Sempre());
                } catch (UtilizadorNExisteException e) {System.out.println("\n" + e.getMessage());}
                break;

        }
    }

    /**
     * Menu query 2
     */
    public void Query2() {
        System.out.println("Utilizador que mais atividades realizou");
        System.out.println("1 - Num período");
        System.out.println("2 - Desde sempre");

        boolean valid = false;
        int choice = 0;
        while(!valid) {
            try {
                String ch = sc.nextLine();
                choice = Integer.parseInt(ch);
                if (choice == 1 || choice == 2) {
                    valid = true;
                }
            } catch (NumberFormatException e) {System.out.println("Valor inválido");}
        }

        switch (choice) {
            case 1:
                LocalDate inicio;
                LocalDate fim;
                boolean valid_dates = false;
                do {
                    inicio = askData(0);
                    fim = askData(1);
                    if (fim.isAfter(inicio)) valid_dates = true;
                } while (!valid_dates);

                try {
                    System.out.println(this.control.Query2Periodo(inicio, fim));
                } catch (UtilizadorNExisteException e) {System.out.println("\n" + e.getMessage());}

                break;

            case 2:
                try {
                    System.out.println(this.control.Query2Sempre());
                } catch (UtilizadorNExisteException e) {System.out.println("\n" + e.getMessage());}
                break;
        }

    }

    /**
     * Menu query 3
     */
    public void Query3() {
        System.out.println("\nA atividade mais realizada é");
        System.out.println(this.control.Query3());
    }

    /**
     * Menu query 4
     */
    public void Query4() {
        System.out.println("***** Km's que um utilizador realizou *****");
        int user = 0;

        boolean valid_choice = false;
        while (user <= 0 || user > this.control.getTotalUsers()) {
            System.out.print("Qual o utilizador que pretende verificar os km's (1-"+ this.control.getTotalUsers()+"): ");
            String teste = sc.nextLine();
            try {
                user = Integer.parseInt(teste);
                valid_choice = true;
            } catch (NumberFormatException e) {System.out.println("Valor inválido");}
            if (valid_choice) System.out.println("Valor inválido");
        }

        System.out.println("1 - Num período");
        System.out.println("2 - Desde sempre");

        boolean valid = false;
        int choice = 0;
        while(!valid) {
            System.out.print("Selecione uma opção: ");
            try {
                String ch = sc.nextLine();
                choice = Integer.parseInt(ch);
                if (choice == 1 || choice == 2) {
                    valid = true;
                }
            } catch (NumberFormatException e) {}
            if (!valid) {System.out.println("Valor inválido");}
        }

        System.out.println("O número de km's é:");

        switch (choice) {
            case 1:
                LocalDate inicio;
                LocalDate fim;
                boolean valid_dates = false;
                do {
                    inicio = askData(0);
                    fim = askData(1);
                    if (fim.isAfter(inicio)) valid_dates = true;
                } while (!valid_dates);

                try {
                    System.out.println(this.control.Query4Periodo(inicio, fim, user));
                } catch (UtilizadorNExisteException e) {System.out.println(e.getMessage());}
                break;

            case 2:
                try {
                    System.out.println(this.control.Query4Sempre(user));
                } catch (UtilizadorNExisteException e) {System.out.println(e.getMessage());}
                break;
        }

    }

    /**
     * Menu query 5
     */
    public void Query5() {
        System.out.println("\n***** Metros de altimetria que um utilizador realizou *****");
        int user = 0;

        boolean valid_choice = false;
        while (user <= 0 || user > this.control.getTotalUsers()) {
            System.out.print("Qual o utilizador que pretende verificar os metros de altimetria (1-"+ this.control.getTotalUsers()+"): ");
            try {
                user = this.sc.nextInt();
                valid_choice = true;
            } catch (NumberFormatException e) {System.out.println("Valor inválido");}
            if (valid_choice) System.out.println("Valor inválido");
        }

        System.out.println("1 - Num período");
        System.out.println("2 - Desde sempre");

        boolean valid = false;
        int choice = 0;
        while(!valid) {
            try {
                String ch = sc.nextLine();
                choice = Integer.parseInt(ch);
                if (choice == 1 || choice == 2) {
                    valid = true;
                }
            } catch (NumberFormatException e) {System.out.println("Valor inválido");}
        }

        System.out.println("O número de metros de altimetria é:");

        switch (choice) {
            case 1:
                LocalDate inicio;
                LocalDate fim;
                boolean valid_dates = false;
                do {
                    inicio = askData(0);
                    fim = askData(1);
                    if (fim.isAfter(inicio)) valid_dates = true;
                } while (!valid_dates);

                try {
                    System.out.println(this.control.Query5Periodo(inicio, fim, user));
                } catch (UtilizadorNExisteException e) {System.out.println(e.getMessage());}
                break;

            case 2:
                try {
                    System.out.println(this.control.Query5Sempre(user));
                } catch (UtilizadorNExisteException e) {System.out.println(e.getMessage());}
                break;
        }
    }

    /**
     * Menu query 6
     */
    public void Query6() {
        try {
            System.out.println(this.control.Query6());
        } catch (PlanoTreinoNExisteException e) {e.getMessage();}
    }

    /**
     * Menu query 7
     */
    public void Query7() {
        int user = 0;
        while (user <= 0 || user > this.control.getTotalUsers()) {
            System.out.print("Qual o utilizador que ver as atividades (1-"+ this.control.getTotalUsers()+"): ");
            try {
                user = this.sc.nextInt();
            } catch (NumberFormatException e) {System.out.println("Valor inválido");}
        }

        try {
            System.out.println(this.control.Query7(user));
        } catch (UtilizadorNExisteException e) {System.out.println(e.getMessage());}
    }

    /**
     * Query adicional que lista as atividades do tipo Hard
     */
    public void Query8() {
        System.out.println(this.control.Query8());
    }

    /**
     * Menu de criar uma atividade
     */
    public void AddAtividade() {
        System.out.println("\n***** Adicionar atividade *****\n");
        String [] option = {"Qual o tipo de atividade que pretende inserir\n","Atividade distância","Atividade altimetria e distância",
                "Atividade repetições sem pesos","Atividade repetições com pesos"};
        NewMenu addActiv = new NewMenu(option);
        addActiv.setHandlers(1,this::MenuAtividadeDistancia);
        addActiv.setHandlers(2,this::MenuAtividadeAltimetria);
        addActiv.setHandlers(3,this::MenuAtividadeSemPeso);
        addActiv.setHandlers(4,this::MenuAtividadeComPeso);
        addActiv.run();

    }

    /**
     * Menu da atividade de distancia
     */
    public void MenuAtividadeDistancia() {
        System.out.println("\n***** Atividade distância *****\n");
        String [] options = {"Qual o tipo de atividade que pretende inserir\n","Canoagem","Corrida na pista de Atletismo","Natação"};
        NewMenu activDist = new NewMenu(options);
        activDist.setHandlers(1,this::newRemo);
        activDist.setHandlers(2,this::newCorridaPistaDeAtletismo);
        activDist.setHandlers(3,this::newNadar);
        activDist.run();
    }

    /**
     * Menu da atividade de distancia sem opcao de sair
     */
    public void MenuAtividadeDistanciaNoExit() {
        System.out.println("\n***** Atividade distância *****\n");
        String [] options = {"Qual o tipo de atividade que pretende inserir\n","Canoagem","Corrida na pista de Atletismo","Natação"};
        NewMenu activDist = new NewMenu(options);
        activDist.setHandlers(1,this::newRemo);
        activDist.setHandlers(2,this::newCorridaPistaDeAtletismo);
        activDist.setHandlers(3,this::newNadar);
        activDist.run_autoExit();
    }

    /**
     * Menu da atividade de altimetria
     */
    public void MenuAtividadeAltimetria() {
        System.out.println("\n***** Atividade altimetria *****\n");
        String[] options = {"Qual o tipo de atividade que pretende inserir\n","Ciclismo na montanha","Corrida de estrada","Passadeira em escada"};
        NewMenu activAlt = new NewMenu(options);
        activAlt.setHandlers(1,this::newBicicletaMontanha);
        activAlt.setHandlers(2,this::newCorridaDeEstrada);
        activAlt.setHandlers(3,this::newPassadeiraEscada);
        activAlt.run();
    }

    /**
     * Menu da atividade de altimetria sem opcao de sair
     */
    public void MenuAtividadeAltimetriaNoExit() {
        System.out.println("\n***** Atividade altimetria *****\n");
        String[] options = {"Qual o tipo de atividade que pretende inserir\n","Ciclismo na montanha","Corrida de estrada","Passadeira em escada"};
        NewMenu activAlt = new NewMenu(options);
        activAlt.setHandlers(1,this::newBicicletaMontanha);
        activAlt.setHandlers(2,this::newCorridaDeEstrada);
        activAlt.setHandlers(3,this::newPassadeiraEscada);
        activAlt.run_autoExit();
    }

    /**
     * Menu da atividade de repeticoes
     */
    public void MenuAtividadeSemPeso() {
        System.out.println("\n***** Atividade sem pesos *****\n");
        String[] options = {"Qual o tipo de atividade que pretende inserir\n","Abdominais","Flexões","Elevações"};
        NewMenu activSemPeso = new NewMenu(options);
        activSemPeso.setHandlers(1,this::newAbdominais);
        activSemPeso.setHandlers(2,this::newFlexoes);
        activSemPeso.setHandlers(3,this::newElevacoes);
        activSemPeso.run();
    }

    /**
     * Menu da atividade de repeticoes sem opcao de sair
     */
    public void MenuAtividadeSemPesoNoExit() {
        System.out.println("\n***** Atividade sem pesos *****\n");
        String[] options = {"Qual o tipo de atividade que pretende inserir\n","Abdominais","Flexões","Elevações"};
        NewMenu activSemPeso = new NewMenu(options);
        activSemPeso.setHandlers(1,this::newAbdominais);
        activSemPeso.setHandlers(2,this::newFlexoes);
        activSemPeso.setHandlers(3,this::newElevacoes);
        activSemPeso.run_autoExit();
    }

    /**
     * Menu da atividade com peso
     */
    public void MenuAtividadeComPeso() {
        System.out.println("\n***** Atividade com pesos *****\n");
        String[] options = {"Qual o tipo de atividade que pretende inserir\n","Supino","Leg Press","Shoulder Press"};
        NewMenu activComPeso = new NewMenu(options);
        activComPeso.setHandlers(1,this::newSupino);
        activComPeso.setHandlers(2,this::newLegPress);
        activComPeso.setHandlers(3,this::newShoudlerPress);
        activComPeso.run();
    }

    /**
     * Menu de atividades com peso sem a opcao de sair
     */
    public void MenuAtividadeComPesoNoExit() {
        System.out.println("\n***** Atividade com pesos *****\n");
        String[] options = {"Qual o tipo de atividade que pretende inserir\n","Supino","Leg Press","Shoulder Press"};
        NewMenu activComPeso = new NewMenu(options);
        activComPeso.setHandlers(1,this::newSupino);
        activComPeso.setHandlers(2,this::newLegPress);
        activComPeso.setHandlers(3,this::newShoudlerPress);
        activComPeso.run_autoExit();
    }

    /**
     * Menu da parte de simulacao
     */
    public void MenuSimulacao(){
        System.out.println("\n***** Simular avanço do tempo *****\n");
        String[] options = {"Selecione uma opção\n","Menu Queries"};
        NewMenu simulacao = new NewMenu(options);
        simulacao.setHandlers(1,this::MenuQueries);
        simulacao.run();
    }

    /**
     * Menu que cria um plano de treino
     */
    public void criarPlanoDeTreino(){
        System.out.println("\n***** Criar Plano de Treino *****\n");
        int numeroDeAtividades = 0;
        boolean valid_choice = false;
        LocalDate data = LocalDate.now();

        while (!valid_choice) {
            System.out.print("Número de atividades: ");
            try {
                String act = sc.nextLine();
                numeroDeAtividades = Integer.parseInt(act);
                if (numeroDeAtividades >= 1 ) {
                    valid_choice = true;
                }
            } catch (NumberFormatException e) {}
            if (!valid_choice) {System.out.println("Valor inválido");}
        }

        this.control.newPlanoDeTreino(data);

        int indicePlanodeTreino = this.control.getTotalPlanoTreinos();
        int indiceUltimo = this.control.getTotalAtividades();

        for(int i = 0; i < numeroDeAtividades; i++){
            AddAtividadeforPlanoTreino();
            int atividadetoAdd = indiceUltimo+1;
            this.control.addAtividadePlanoDeTreino(atividadetoAdd, indicePlanodeTreino);
            indiceUltimo++;
        }

    }

    /**
     * Menu que adiciona um utilizador ao programa
     */
    public void AddUtilizador() {
        System.out.println("\n***** Adicionar utilizador*****");
        System.out.println("Que tipo de utilizador pretende criar");
        System.out.println("1 - Utilizador Profissional");
        System.out.println("2 - Utilizador Amador");
        System.out.println("3 - Utilizador Ocasional");
        System.out.println("4 - Voltar");
        System.out.print("\nSelecione uma opção: ");

        //sc.nextLine();

        int choice = 0;
        boolean valid_choice = false;
        while (!valid_choice) {
            try {
                String act = sc.nextLine();
                choice = Integer.parseInt(act);
                if (choice >= 1 && choice <= 4) {
                    valid_choice = true;
                }
            } catch (NumberFormatException e) {}
            if (!valid_choice) {System.out.println("Valor inválido");}
        }


        if (choice == 4) return;

        System.out.println("***** CRIAR UTILIZADOR *****");
        System.out.println("Nome: ");

        String nome = sc.nextLine();

        System.out.println("Morada: ");
        String morada = sc.nextLine();

        System.out.println("Email: ");
        String email = sc.nextLine();

        int fq = -1;
        boolean fq_valida= false;
        while (!fq_valida) {
            System.out.println("Frequência cardiaca média: ");
            String fq_s = sc.nextLine();
            try {
                fq = Integer.parseInt(fq_s);
                if (fq > 0) {
                    fq_valida = true;
                }
            } catch (NumberFormatException e) {}
            if (!fq_valida) System.out.println("Valor inválido");
        }


        String gen;
        Genero genero;
        do {
            System.out.println("Genero: (M ou F)");
            gen = sc.nextLine();
            if (!gen.equals("M") && !gen.equals("F")) {
                System.out.println("Valor inválido");
            }
        }while (!gen.equals("M") && !gen.equals("F"));
        if(gen.equals("M")){
            genero = Genero.MASCULINO;
        }else {
            genero = Genero.FEMININO;
        }

        String peso_s;
        double peso = 0.0;
        boolean peso_valido = false;
        while (!peso_valido) {
            System.out.println("Peso: ");
            peso_s = sc.nextLine();
            try {
                peso = Double.parseDouble(peso_s);
                if (peso > 0) {
                    peso_valido = true;
                }
            } catch (NumberFormatException e) {

            }
            if (!peso_valido) {
                System.out.println("Valor inválido");
            }
        }

        String altura_s;
        int altura = 0;
        boolean altura_valido = false;
        while (!altura_valido) {
            System.out.println("Altura (cm): ");
            altura_s = sc.nextLine();
            try {
                altura = Integer.parseInt(altura_s);
                if (altura > 0) {
                    altura_valido = true;
                }
            } catch (NumberFormatException e) {}
            if (!altura_valido) {System.out.println("Valor inválido");}
        }

        String idade_s;
        LocalDate data_nascimento = LocalDate.now();
        boolean idade_valido = false;
        while(!idade_valido) {
            System.out.println("Data de Nascimento: ");
            idade_s = sc.nextLine();
            try {
                data_nascimento = LocalDate.parse(idade_s);
                if (data_nascimento.isBefore(LocalDate.now())) {
                    idade_valido = true;
                }
            } catch (DateTimeParseException e) {}
            if (!idade_valido) {System.out.println("Valor inválido");}
        }

        this.control.addUser(nome, morada, email, fq, genero, peso, altura, data_nascimento, choice);
    }

    /**
     * Menu que remove um utilizador
     */
    public void RemoveUtilizador() {
        System.out.println("\n***** Remover utilizador *****");
        System.out.println("Insira o id do utilizador que pretende remover");

        sc.nextLine();

        int id = 0;
        boolean valid_id = false;
        while (!valid_id) {
            String act = sc.nextLine();
            try {
                id = Integer.parseInt(act);
                if (id > 0 && this.control.existUser(id)) {
                    valid_id = true;
                }
            } catch (NumberFormatException e) {}
            if (!valid_id) {System.out.println("Valor inválido");}
        }

        this.control.remUser(id);

    }

    /**
     * Menu de inserir o id do utilizador
     */
    public void MenuInserirIdUtilizador () {
        System.out.println("\n***** Menu inserir atividades em utilizador *****");
        String[] options = {"Selecione uma opção","Escolher o id do utilizador"};

        NewMenu insert = new NewMenu(options);

        UsersChecker usersChecker = new UsersChecker(this.control);
        insert.setCheckers(1, usersChecker);
        insert.setHandlers(1, this::AddAtividadeUtilizador);

        insert.run();
    }

    /**
     * Adiciona uma atividade a um utilizador
     */
    public void AddAtividadeUtilizador() {
        System.out.println("\n***** Adicionar atividade a um utilizador *****\n");
        String dataAtividade;

        int atividade = 0;
        while (atividade <= 0 || atividade > this.control.getTotalAtividades()) {
            System.out.print("Qual a atividade que pretende inserir (1-"+ this.control.getTotalAtividades()+") \n");
            try {
                atividade = this.sc.nextInt();
            } catch (NumberFormatException e) {System.out.println("Valor inválido");}
        }

        int user = 0;
        while (user <= 0 || user > this.control.getTotalUsers()) {
            System.out.print("Qual o utilizador que pretende inserir (1-"+ this.control.getTotalUsers()+") \n");
            try {
                user = this.sc.nextInt();
            } catch (NumberFormatException e) {System.out.println("Valor inválido");}
        }

        LocalDate data = LocalDate.now();
        boolean idade_valido = false;
        sc.nextLine();
        while(!idade_valido) {
            System.out.print("Data de Atividade: ");
            dataAtividade = sc.nextLine();
            try {
                data = LocalDate.parse(dataAtividade);
                if (this.control.validData(user, data)) {
                    idade_valido = true;
                }
            } catch (DateTimeParseException | UtilizadorNExisteException d) {d.getMessage();}
            if (!idade_valido) {System.out.println("Valor inválido");}
        }

        String horas_str;
        LocalTime horas = LocalTime.now();
        boolean horas_valido = false;
        while (!horas_valido) {
            System.out.print("Horas: ");
            horas_str = sc.nextLine();
            try {
                horas = LocalTime.parse(horas_str);
                horas_valido = true;
            } catch (DateTimeParseException e) {System.out.println("Valor inválido");}
        }

        this.control.addAtividadeUtilizador(atividade, user, data, horas);
    }

    /**
     * Cria uma atividade de ciclismo na montanha
     */
    public void newBicicletaMontanha() {
        int duracao = askDuracao();
        //LocalDate data = askData();
        int fq = askFrequenciaCardiaca();
        double altimetria = askAltimetria();
        int distancia = askDistancia();
        String percurso = askPercurso();

        this.control.newBicicletaMontanha(duracao, LocalDate.now(), fq, altimetria, distancia, percurso, LocalTime.now());
    }

    /**
     * Cria uma atividade de supino
     */
    public void newSupino() {
        int duracao = askDuracao();
        //LocalDate data = askData();
        int fq = askFrequenciaCardiaca();
        int reps = askReps();
        double peso = askPeso();

        this.control.newSupino(duracao, LocalDate.now(), fq, reps, peso, LocalTime.now());
    }

    /**
     * Cria uma atividade de remo
     */
    public void newRemo() {
        int duracao = askDuracao();
       // LocalDate data = askData();
        int fq = askFrequenciaCardiaca();
        int distancia = askDistancia();
        String percurso = askPercurso();



        this.control.newRemo(duracao, LocalDate.now(), fq, distancia, percurso, LocalTime.now());
    }

    /**
     * Cria uma atividade de abdominais
     */
    public void newAbdominais() {
        int duracao = askDuracao();
       // LocalDate data = askData();
        int fq = askFrequenciaCardiaca();
        int reps = askReps();

        this.control.newAbdominais(duracao, LocalDate.now(), fq, reps, LocalTime.now());
    }

    /**
     * Cria uma atividade de corrida de estrada
     */
    public void newCorridaDeEstrada() {
        int duracao = askDuracao();
        //LocalDate data = askData();
        int fq = askFrequenciaCardiaca();
        double altimetria = askAltimetria();
        int distancia = askDistancia();
        String percurso = askPercurso();

        this.control.newCorridaEstrada(duracao, LocalDate.now(), fq, altimetria, distancia, percurso, LocalTime.now());
    }

    /**
     * Cria uma atividade de atletismo na pista
     */
    public void newCorridaPistaDeAtletismo() {
        int duracao = askDuracao();
        // LocalDate data = askData();
        int fq = askFrequenciaCardiaca();
        int distancia = askDistancia();
        String percurso = askPercurso();



        this.control.newCorridaPistaDeAtletismo(duracao, LocalDate.now(), fq, distancia, percurso, LocalTime.now());
    }

    /**
     * Cria uma atividade de elevacoes
     */
    public void newElevacoes() {
        int duracao = askDuracao();
        // LocalDate data = askData();
        int fq = askFrequenciaCardiaca();
        int reps = askReps();

        this.control.newElevacoes(duracao, LocalDate.now(), fq, reps, LocalTime.now());
    }

    /**
     * Cria uma atividade de passadeira escada
     */
    public void newPassadeiraEscada() {
        int duracao = askDuracao();
        //LocalDate data = askData();
        int fq = askFrequenciaCardiaca();
        double altimetria = askAltimetria();
        int distancia = askDistancia();
        String percurso = askPercurso();

        this.control.newPassadeiraEscada(duracao, LocalDate.now(), fq, altimetria, distancia, percurso, LocalTime.now());
    }

    /**
     * Cria uma atividade de felxões
     */
    public void newFlexoes() {
        int duracao = askDuracao();
        // LocalDate data = askData();
        int fq = askFrequenciaCardiaca();
        int reps = askReps();

        this.control.newFlexoes(duracao, LocalDate.now(), fq, reps, LocalTime.now());
    }

    /**
     * Cria uma atividade de leg press
     */
    public void newLegPress() {
        int duracao = askDuracao();
        //LocalDate data = askData();
        int fq = askFrequenciaCardiaca();
        int reps = askReps();
        double peso = askPeso();

        this.control.newLegPress(duracao, LocalDate.now(), fq, reps, peso, LocalTime.now());
    }

    /**
     * Cria uma atividade de natação
     */
    public void newNadar() {
        int duracao = askDuracao();
        // LocalDate data = askData();
        int fq = askFrequenciaCardiaca();
        int distancia = askDistancia();
        String percurso = askPercurso();



        this.control.newNadar(duracao, LocalDate.now(), fq, distancia, percurso, LocalTime.now());
    }

    /**
     * Cria uma atividade shoulder press
     */
    public void newShoudlerPress() {
        int duracao = askDuracao();
        //LocalDate data = askData();
        int fq = askFrequenciaCardiaca();
        int reps = askReps();
        double peso = askPeso();

        this.control.newShoulderPress(duracao, LocalDate.now(), fq, reps, peso, LocalTime.now());
    }

    /**
     * Pergunta a duracao de uma atividade
     * @return atividadde
     */
    private int askDuracao() { //tem que ficar sempre em primeiro
        int duracao = 0;
        boolean valid_duration = false;
        while (!valid_duration) {
            System.out.print("Duração: ");
            String act = sc.nextLine();
            try {
                duracao = Integer.parseInt(act);
                if (duracao > 0) {
                    valid_duration = true;
                }
            } catch (NumberFormatException e) {}
            if (!valid_duration) {System.out.println("Valor inválido");}
        }
        return duracao;
    }

    /**
     * pergunta uma data
     * @param val 0, data de inicio; 1, data do final
     * @return
     */
    private LocalDate askData(int val) {
        LocalDate data = null;
        boolean data_valida = false;
        while(!data_valida) {
            if (val == 0) {
                System.out.print("Data do início: ");
            } else {
                System.out.print("Data do fim: ");
            }
            String idade_s = sc.nextLine();
            try {
                data = LocalDate.parse(idade_s);
                data_valida = true;
            } catch (DateTimeParseException e) {}
            if (!data_valida) {System.out.println("Valor inválido");}
        }
        return data;
    }

    /**
     * Pergunta a frequencia caradiaca
     * @return frequencia caradiaca
     */
    private int askFrequenciaCardiaca() {
        int fq = -1;
        boolean fq_valida= false;
        while (!fq_valida) {
            System.out.print("Frequência cardiaca média: ");
            String fq_s = sc.nextLine();
            try {
                fq = Integer.parseInt(fq_s);
                if (fq > 0) {
                    fq_valida = true;
                }
            } catch (NumberFormatException e) {}
            if (!fq_valida) System.out.println("Valor inválido");
        }
        return fq;
    }

    /**
     * Pergunta a distancia de uma atividade
     * @return distancia
     */
    private int askDistancia() {
        int distancia = -1;

        boolean distancia_valida= false;
        while (!distancia_valida) {
            System.out.print("Distância (m): ");
            String fq_s = sc.nextLine();
            try {
                distancia = Integer.parseInt(fq_s);
                if (distancia > 0) {
                    distancia_valida = true;
                }
            } catch (NumberFormatException e) {}
            if (!distancia_valida) System.out.println("Valor inválido");
        }
        return distancia;
    }

    /**
     * Pergunta a altimetria de uma atividade
     * @return altimetria
     */
    private double askAltimetria() {
        double altimetria = 0;
        boolean altimetria_valida = false;
        while(!altimetria_valida) {
            System.out.print("Altimetria (m): ");
            String str = sc.nextLine();
            try {
                altimetria = Double.parseDouble(str);
                if (altimetria >= 0) {
                    altimetria_valida = true;
                }
            } catch (NumberFormatException e) {}
            if (!altimetria_valida) { System.out.println("Valor inválido");}
        }
        return altimetria;
    }

    /**
     * Pergunta o percurso de uma atividade
     * @return atividade
     */
    private String askPercurso() {
        System.out.print("Indique o percurso: ");
        String fq;
        fq = sc.nextLine();
        return fq;
    }

    /**
     * Pergunta o numero de repeticoes
     * @return repeticoes
     */
    private int askReps() {
        int repeticoes = -1;

        boolean repeticoes_valida= false;
        while (!repeticoes_valida) {
            System.out.print("Repetições: ");
            String fq_s = sc.nextLine();
            try {
                repeticoes = Integer.parseInt(fq_s);
                if (repeticoes > 0) {
                    repeticoes_valida = true;
                }
            } catch (NumberFormatException e) {}
            if (!repeticoes_valida) System.out.println("Valor inválido");
        }
        return repeticoes;
    }

    /**
     * Pergunta o peso
     * @return peso
     */
    private double askPeso() {
        int peso = -1;

        boolean peso_valido= false;
        while (!peso_valido) {
            System.out.print("Peso: ");
            String fq_s = sc.nextLine();
            try {
                peso = Integer.parseInt(fq_s);
                if (peso > 0) {
                    peso_valido = true;
                }
            } catch (NumberFormatException e) {}
            if (!peso_valido) System.out.println("Valor inválido");
        }
        return peso;
    }

    /**
     * Pergunta os dias da semana para um plano de treino
     * @return lista dos dias da semana
     */
    private List<DayOfWeek> askDias() {
        System.out.println("Quais são os dias que pretende realizar a atividade");
        System.out.println("1 - Segunda-Feira");
        System.out.println("2 - Terça-Feira");
        System.out.println("3 - Quarta-Feira");
        System.out.println("4 - Quinta-Feira");
        System.out.println("5 - Sexta-Feira");
        System.out.println("6 - Sábado");
        System.out.println("7 - Domingo");
        System.out.print("Insira os dias que pretende, separando-os por vírgulas (ex:1,2,6): ");
        sc.nextLine();
        String dias = sc.nextLine();

        String[] diasArray = dias.split(",");
        List<DayOfWeek> diasEscolhidos = new ArrayList<>();

        for (String dia : diasArray) {
            int diaInt = 0;
            try{
                diaInt = Integer.parseInt(dia.trim());
            } catch (NumberFormatException e) {System.out.println("Valor inválido");}

            switch (diaInt) {
                case 1:
                    diasEscolhidos.add(DayOfWeek.MONDAY);
                    break;
                case 2:
                    diasEscolhidos.add(DayOfWeek.TUESDAY);
                    break;
                case 3:
                    diasEscolhidos.add(DayOfWeek.WEDNESDAY);
                    break;
                case 4:
                    diasEscolhidos.add(DayOfWeek.THURSDAY);
                    break;
                case 5:
                    diasEscolhidos.add(DayOfWeek.FRIDAY);
                    break;
                case 6:
                    diasEscolhidos.add(DayOfWeek.SATURDAY);
                    break;
                case 7:
                    diasEscolhidos.add(DayOfWeek.SUNDAY);
                    break;
                default:
                    System.out.println("Dia inválido: " + dia);
            }
        }

        return diasEscolhidos;
    }

    /**
     * Lista um unico utilizador dado um id
     */
    public void ListarSingleUser() {
        System.out.println("\n***** Listar utilizador *****");
        int user = 0;
        while (user <= 0 || user > this.control.getTotalUsers()) {
            System.out.print("Qual o utilizador que pretende inserir (1-"+ this.control.getTotalUsers()+") \n");
            try {
                user = this.sc.nextInt();
            } catch (NumberFormatException e) {System.out.println("Valor inválido");}
        }

        System.out.println("\n");
        System.out.println(this.control.showSingleUser(user));

    }

    /**
     * Lista os utilizador existentes
     */
    public void ListarUtilizadores() {
        System.out.println("\n***** Listar Utilizadores *****\n");
        System.out.println(this.control.showUsers());
    }

    /**
     * Lista os planos de treino existentes
     */
    public void ListarPlanoDeTreino(){
        System.out.println("\n***** Lista de Planos de Treino *****\n");
        System.out.println(this.control.showPlanoTreinos());
    }

    /**
     * Lista as atividade existentes
     */
    public void ListarAtividades() {
        System.out.println("\n***** Listar Atividades *****\n");
        System.out.println(this.control.showAtividades());
    }

    /**
     * Carrega o estado do programa
     */
    public void carregaEstado(){
            System.out.print("Nome do ficheiro: ");
            String file = sc.nextLine();
            try {
                this.control.CarregarEstado(file);
            }catch (IOException | ClassNotFoundException e){}
        }

    /**
     * Guarda o estado do programa
     */
    public void GuardaEstado() {
        System.out.print("Nome do ficheiro: ");
        String file = sc.nextLine();
        try {
            this.control.GuardarEstado(file);
        } catch (IOException e){}

    }
}
