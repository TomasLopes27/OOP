package Aplicacao;

import Atividades.*;
import Exceptions.AtividadeNExisteException;
import Exceptions.PlanoTreinoNExisteException;
import Exceptions.UtilizadorNExisteException;
import PlanoTreino.PlanoTreino;
import Utilizadores.*;
import jdk.jshell.execution.Util;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model implements Serializable {
    private Map<Integer, Utilizador> utilizadores;
    private Map<Integer, Atividade> atividades;
    private Map<Integer, PlanoTreino> planoTreinos;
    private LocalDate data_atual;

    /**
     * construtor vazio
     */
    public Model() {
        this.utilizadores = new HashMap<>();
        this.atividades = new HashMap<>();
        this.planoTreinos = new HashMap<>();
        this.data_atual = LocalDate.now();
    }

    /**
     * construtor parametrizado
     * @param utilizadores
     * @param atividades
     * @param planoTreinos
     * @param data_atual
     */
    public Model(Map<Integer, Utilizador> utilizadores, Map<Integer, Atividade> atividades, Map<Integer, PlanoTreino> planoTreinos, LocalDate data_atual) {
        this.utilizadores = new HashMap<>();

        for (Utilizador user: utilizadores.values()) {
            this.utilizadores.put(user.getCodigo_utilizador(), user.clone());
        }

        this.atividades = new HashMap<>();
        for (Atividade atividade: atividades.values()) {
            this.atividades.put(atividade.getCodigo(), atividade.clone());
        }

        this.planoTreinos = new HashMap<>();
        for (PlanoTreino treino: planoTreinos.values()) {
            this.planoTreinos.put(treino.getCod(), treino.clone());
        }

        this.data_atual = data_atual;
    }

    /**
     * construtor de copia
     * @param m1
     */
    public Model(Model m1) {
        this.utilizadores = m1.getUtilizadores();
        this.atividades = m1.getAtividades();
        this.planoTreinos = m1.getPlanoTreinos();
        this.data_atual = m1.getData_atual();
    }

    /**
     * utilizadores do programa
     * @return
     */
    public Map<Integer, Utilizador> getUtilizadores() {
        Map<Integer, Utilizador> utilizadores = new HashMap<>();

        for (Utilizador user: this.utilizadores.values()) {
            utilizadores.put(user.getCodigo_utilizador(), user.clone());
        }

        return utilizadores;
    }

    /**
     * atividades do programa (não considera as dos utilizadores)
     * @return
     */
    public Map<Integer, Atividade> getAtividades() {
        Map<Integer, Atividade> atividades = new HashMap<>();

        for (Atividade atividade: this.atividades.values()) {
            atividades.put(atividade.getCodigo(), atividade.clone());
        }

        return atividades;
    }

    /**
     * planos de treino do programa (não considera os dos utilizadores)
     * @return
     */
    public Map<Integer, PlanoTreino> getPlanoTreinos() {
        Map<Integer, PlanoTreino> planoTreinos = new HashMap<>();
        for (PlanoTreino treino: this.planoTreinos.values()) {
            planoTreinos.put(treino.getCod(), treino.clone());
        }
        return planoTreinos;
    }

    /**
     * data atual do programa
     * @return
     */
    public LocalDate getData_atual() {
        return data_atual;
    }

    /**
     * dá set à data atual do programa com a data dada
     * @param data_atual
     */
    public void setData_atual(LocalDate data_atual) {
        this.data_atual = data_atual;
    }

    /**
     * da set aos utilizadores que sao recebidos
     * @param utilizadores
     */
    public void setUtilizadores(Map<Integer, Utilizador> utilizadores) {
        Map<Integer, Utilizador> copia = new HashMap<>();

        for (Utilizador user: utilizadores.values()) {
            copia.put(user.getCodigo_utilizador(), user.clone());
        }

        this.utilizadores = copia;
    }

    /**
     * da set às atividades que sao recebidas
     * @param atividades
     */
    public void setAtividades(Map<Integer, Atividade> atividades) {
        Map<Integer, Atividade> copia = new HashMap<>();

        for (Atividade atividade: atividades.values()) {
            copia.put(atividade.getCodigo(), atividade.clone());
        }

        this.atividades = copia;
    }

    /**
     * da set aos planos de treino que sao recebidos
     * @param planoTreinos
     */
    public void setPlanoTreinos(Map<Integer, PlanoTreino> planoTreinos) {
        Map<Integer, PlanoTreino> copia = new HashMap<>();

        for (PlanoTreino treino: planoTreinos.values()) {
            copia.put(treino.getCod(), treino.clone());
        }

        this.planoTreinos = copia;
    }

    public Model clone() {
        return new Model(this);
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Model other = (Model) o;
        return this.utilizadores.equals(other.getUtilizadores())
                && this.atividades.equals(other.getAtividades())
                && this.planoTreinos.equals(other.getPlanoTreinos())
                && this.data_atual.equals(other.getData_atual());
    }

    /**
     * mostra os utilizadores da aplicacao
     * @return String
     */
    public String showUsers() {
        StringBuilder sb = new StringBuilder();

        for (Utilizador user: this.utilizadores.values()) {
            sb.append(user.toString()).append("\n\n");
        }

        return sb.toString();
    }

    /**
     * mostra um utilizador dado o seu id
     * @param id
     * @return
     */
    public String showSingleUser(int id) {
        StringBuilder string = new StringBuilder();
        Utilizador user = null;

        try {
            user = getUtilizador(id);
        } catch (UtilizadorNExisteException e) {}

        assert user != null; //considera isto como diferente de null, nsei se isto tem que ser clone
        return user.showMe();
    }

    /**
     * mostra as atividades do programa (nao considera as dos utilizadores)
     * @return string
     */
    public String showAtividades() {
        StringBuilder sb = new StringBuilder();

        for (Atividade atividade: this.atividades.values()) {
            sb.append(atividade.clone().toString()).append("\n\n");
        }

        return sb.toString();
    }

    /**
     * mostra os pt do programa (nao considera os dos utilizadores)
     * @return
     */
    public String showPlanoTreinos() {
        StringBuilder sb = new StringBuilder();

        for (PlanoTreino treino: this.planoTreinos.values()) {
            sb.append(treino.clone().toString()).append("\n");

        }
        return sb.toString();
    }

    /**
     * adiciona um dado user ao programa
     * @param user
     */
    public void addUser(Utilizador user) {
        this.utilizadores.put(user.getCodigo_utilizador(), user.clone());
    }

    /**
     * remove um utilizador
     * @param id
     */
    public void remUser(int id) { this.utilizadores.remove(id); }

    /**
     * adiciona um utilizador professional com os dados parametros
     * @param nome
     * @param morada
     * @param email
     * @param frequencia
     * @param genero
     * @param peso
     * @param altura
     * @param data_nascimento
     */
    public void addUserProfissional(String nome, String morada, String email, int frequencia, Genero genero, double peso, int altura, LocalDate data_nascimento) {
        UtilizadorProfissional user = new UtilizadorProfissional(this.genCodUser(),nome, morada, email, frequencia, genero, peso, altura, data_nascimento);
        addUser(user);
    }

    /**
     * adiciona um utilizador ocasional com os dados parametros
     * @param nome
     * @param morada
     * @param email
     * @param frequencia
     * @param genero
     * @param peso
     * @param altura
     * @param data_nascimento
     */
    public void addUserOcasional(String nome, String morada, String email, int frequencia, Genero genero, double peso, int altura, LocalDate data_nascimento) {
        UtilizadorOcasional user = new UtilizadorOcasional(this.genCodUser(),nome, morada, email, frequencia, genero, peso, altura, data_nascimento);
        addUser(user);
    }

    /**
     * adiciona um utilizador amador com os dados parametros
     * @param nome
     * @param morada
     * @param email
     * @param frequencia
     * @param genero
     * @param peso
     * @param altura
     * @param data_nascimento
     */
    public void addUserAmador(String nome, String morada, String email, int frequencia, Genero genero, double peso, int altura, LocalDate data_nascimento) {
        UtilizadorAmador user = new UtilizadorAmador(this.genCodUser(),nome, morada, email, frequencia, genero, peso, altura, data_nascimento);
        addUser(user);
    }

    /**
     * verifica se existe um utilizador com o id dado
     * @param id
     * @return
     */
    public boolean existUser(int id) {
        return this.utilizadores.containsKey(id);
    }

    /**
     * cria uma atividade bicicleta montanha com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param altimetria
     * @param distancia
     * @param percurso
     * @param horas
     */
    public void newBicicletaMontanha(int duracao, LocalDate data, int fq, double altimetria, int distancia, String percurso, LocalTime horas) {
        BicicletaMontanha atividade = new BicicletaMontanha(genCodAtividade(), duracao, data, fq, altimetria, distancia, percurso, horas);
        atividades.put(atividade.getCodigo(), atividade);
    }

    /**
     * cria uma atividade supino com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param reps
     * @param peso
     * @param horas
     */
    public void newSupino(int duracao, LocalDate data, int fq, int reps, double peso, LocalTime horas) {
        Supino atividade = new Supino(genCodAtividade(), duracao, data, fq, reps, peso, horas);
        atividades.put(atividade.getCodigo(), atividade);
    }

    /**
     * cria uma atividade canoagem com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param distancia
     * @param percurso
     * @param horas
     */
    public void newRemo(int duracao, LocalDate data, int fq, int distancia, String percurso, LocalTime horas) {
        Remo atividade = new Remo(genCodAtividade(), duracao, data, fq, distancia, percurso, horas);
        atividades.put(atividade.getCodigo(), atividade);
    }

    /**
     * cria uma atividade abdominais com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param reps
     * @param horas
     */
    public void newAbdominais(int duracao, LocalDate data, int fq, int reps, LocalTime horas) {
        Abdominais atividade = new Abdominais(genCodAtividade(), duracao, data, fq, reps, horas);
        atividades.put(atividade.getCodigo(), atividade);
    }

    /**
     * cria uma atividade corrida na estrada com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param altimetria
     * @param distancia
     * @param percurso
     * @param horas
     */
    public void newCorridaEstrada(int duracao, LocalDate data, int fq, double altimetria, int distancia, String percurso, LocalTime horas) {
        CorridaEstrada atividade = new CorridaEstrada(genCodAtividade(), duracao, data, fq, altimetria, distancia, percurso, horas);
        atividades.put(atividade.getCodigo(), atividade);
    }

    /**
     * cria uma atividade corrida na pista com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param distancia
     * @param percurso
     * @param horas
     */
    public void newCorridaPistaDeAtletismo(int duracao, LocalDate data, int fq, int distancia, String percurso, LocalTime horas) {
        CorridaPistaAtletismo atividade = new CorridaPistaAtletismo(genCodAtividade(), duracao, data, fq, distancia, percurso, horas);
        atividades.put(atividade.getCodigo(), atividade);
    }

    /**
     * cria uma atividade elevacoes com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param reps
     * @param horas
     */
    public void newElevacoes(int duracao, LocalDate data, int fq, int reps, LocalTime horas) {
        Elevacoes atividade = new Elevacoes(genCodAtividade(), duracao, data, fq, reps, horas);
        atividades.put(atividade.getCodigo(), atividade);
    }

    /**
     * cria uma atividade passadeira na escada com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param altimetria
     * @param distancia
     * @param percurso
     * @param horas
     */
    public void newPassadeiraEscada(int duracao, LocalDate data, int fq, double altimetria, int distancia, String percurso, LocalTime horas) {
        PassadeiraEscada atividade = new PassadeiraEscada(genCodAtividade(), duracao, data, fq, altimetria, distancia, percurso, horas);
        atividades.put(atividade.getCodigo(), atividade);
    }

    /**
     * cria uma atividade flexoes com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param reps
     * @param horas
     */
    public void newFlexoes(int duracao, LocalDate data, int fq, int reps, LocalTime horas) {
        Flexoes atividade = new Flexoes(genCodAtividade(), duracao, data, fq, reps, horas);
        atividades.put(atividade.getCodigo(), atividade);
    }

    /**
     * cria uma atividade leg press com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param reps
     * @param peso
     * @param horas
     */
    public void newLegPress(int duracao, LocalDate data, int fq, int reps, double peso, LocalTime horas) {
        LegPress atividade = new LegPress(genCodAtividade(), duracao, data, fq, reps, peso, horas);
        atividades.put(atividade.getCodigo(), atividade);
    }

    /**
     * cria uma atividade natacao com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param distancia
     * @param percurso
     * @param horas
     */
    public void newNadar(int duracao, LocalDate data, int fq, int distancia, String percurso, LocalTime horas) {
        Nadar atividade = new Nadar(genCodAtividade(), duracao, data, fq, distancia, percurso, horas);
        atividades.put(atividade.getCodigo(), atividade);
    }

    /**
     * cria uma atividade shoulder press com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param reps
     * @param peso
     * @param horas
     */
    public void newShoulderPress(int duracao, LocalDate data, int fq, int reps, double peso, LocalTime horas) {
        ShoulderPress atividade = new ShoulderPress(genCodAtividade(), duracao, data, fq, reps, peso, horas);
        atividades.put(atividade.getCodigo(), atividade);
    }

    /**
     * verifica se uma dada data é valida para o utilizador
     * @param user
     * @param data
     * @return boolean
     * @throws UtilizadorNExisteException
     */
    public boolean validDataForUser (int user, LocalDate data) throws UtilizadorNExisteException {
        Utilizador usuario = getUtilizador(user);

        if (usuario == null) throw new UtilizadorNExisteException();

        return usuario.getData_nascimento().isBefore(data);
    }

    /**
     * cria um plano de treino dada uma data
     * @param data
     */
    public void newPlanoDeTreino(LocalDate data){
        PlanoTreino plano = new PlanoTreino(data, this.genCodPlanoTreino());
        this.planoTreinos.put(plano.getCod(), plano);
    }

    /**
     * adiciona uma dada atividade a um utilizador
     * @param atividade
     * @param utilizador
     * @param data
     * @param horas
     */
    public void addAtividadeUtilizador (int atividade, int utilizador, LocalDate data, LocalTime horas) {
        Utilizador user = null;
        try {
            user = getUtilizador(utilizador);
        } catch (UtilizadorNExisteException e) {}

        Atividade ativ = null;
        try {
            ativ = getAtividade(atividade);
        } catch (AtividadeNExisteException e) {}

        if (user != null) {
            user.addAtividade(ativ,data_atual,data,horas);
            updateUser(user);
        }

    }

    /**
     * adiciona uma atividade a um plano de treino
     * @param atividade
     * @param planoDeTreino
     */
    public void addAtividadePlanoDeTreino(int atividade, int planoDeTreino){
        PlanoTreino planoTreino = null;
        try {
            planoTreino = this.getPlanoTreino(planoDeTreino);
        }catch(PlanoTreinoNExisteException e){
            System.out.println("Plano de treino não existe");
        }

        Atividade ativ = null;
        try {
            ativ = getAtividade(atividade);
        } catch (AtividadeNExisteException e) {
            System.out.println("Atividade não existe");
        }

        assert planoTreino != null;
        assert ativ != null;
        ativ.setData(planoTreino.getData());
        planoTreino.addAtividade(ativ);
        updatePlanoTreino(planoTreino);

    }

    /**
     * atribui um plano de treino a um utilizador tendo em conta as datas dadas e os dias da semana
     * @param plano
     * @param utilizador
     * @param data_inicio
     * @param data_final
     * @param dias
     * @param horas
     */
    public void atribuirPlanoTreinoUser(int plano, int utilizador, LocalDate data_inicio, LocalDate data_final, List<DayOfWeek> dias, LocalTime horas) {
        PlanoTreino planoToInsert = null;
        try {
            planoToInsert = this.getPlanoTreino(plano);
        } catch (PlanoTreinoNExisteException e) {
            System.out.println("O plano de treino não existe");
        }

        Utilizador utilizadorToInsert = null;
        try {
            utilizadorToInsert = this.getUtilizador(utilizador);
        } catch (UtilizadorNExisteException e) {
            System.out.println("O utilizador não existe");
        }

        assert utilizadorToInsert != null;
        assert planoToInsert != null;
        utilizadorToInsert.addPlano(planoToInsert, data_atual, data_inicio, data_final, dias, horas);
        updateUser(utilizadorToInsert);
    }

    /**
     * avanca o tempo da aplicacao
     * @param dias
     */
    public void AvancarTempo(long dias){
        this.data_atual = this.data_atual.plusDays(dias);
    }

    /**
     * recalcula as calorias dos utilizadores
     */
    public void RecalcularCalorias(){
        for (Utilizador user: this.utilizadores.values()){
            user.RecalcularCalorias(this.data_atual);
        }
    }

    /**
     * retorna a copia de um utilizador com codigo id
     * @param id
     * @return
     * @throws UtilizadorNExisteException
     */
    public Utilizador getUtilizador (int id) throws UtilizadorNExisteException {
        Utilizador userToReturn = null;

        for (Utilizador user: this.utilizadores.values()) {
            if (user.getCodigo_utilizador() == id) {
                userToReturn = user;
                break;
            }
        }

        if (userToReturn == null) {
            throw new UtilizadorNExisteException();
        }

        return userToReturn.clone();
    }

    /**
     * retorna a copia de um plano de treino com codigo id
     * @param id
     * @return
     * @throws PlanoTreinoNExisteException
     */
    public PlanoTreino getPlanoTreino(int id) throws PlanoTreinoNExisteException{
        PlanoTreino planoTreinoReturn = null;

        for(PlanoTreino plano: this.planoTreinos.values()){
            if(plano.getCod() == id){
                planoTreinoReturn = plano;
                break;
            }
        }
        if(planoTreinoReturn == null){
            throw new PlanoTreinoNExisteException();
        }

        return planoTreinoReturn.clone();
    }

    /**
     * retorna a copia de uma atividade com codigo id
     * @param id
     * @return
     * @throws AtividadeNExisteException
     */
    public Atividade getAtividade (int id) throws AtividadeNExisteException {
        Atividade atividadeToReturn = null;

        for (Atividade atividade: this.atividades.values()) {
            if (atividade.getCodigo() == id) {
                atividadeToReturn = atividade;
                break;
            }
        }

        if (atividadeToReturn == null) {
            throw new AtividadeNExisteException();
        }

        return atividadeToReturn.clone();
    }

    /**
     * gera o codigo de um utilizador
     * @return
     */
    public int genCodUser () {
        return this.utilizadores.size() + 1;
    }

    /**
     * gera o codigo de uma atividade
     * @return
     */
    public int genCodAtividade () {
        return this.atividades.size() + 1;
    }

    /**
     * gera o codigo de um plano de treino
     * @return
     */
    public int genCodPlanoTreino () {
        return this.planoTreinos.size() + 1;
    }

    /**
     * quantos utilizadores existem no programa
     * @return
     */
    public int getTotalUsers () { return this.utilizadores.size(); }

    /**
     * quantas atividades existem no programa (exclui as que estao associadas aos utilizadores)
     * @return
     */
    public int getTotalAtividades () { return this.atividades.size(); }

    /**
     * quantos planos de treino existem no programa (exclui os que estao associados aos utilizadores)
     * @return
     */
    public int getTotalPlanoTreinos () { return this.planoTreinos.size(); }

    /**
     * guarda o estado do programa
     * @param fileName
     * @throws IOException
     */
    public void guardaEstado(String fileName) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    /**
     * carrega o estado do programa
     * @param fileName
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void carregaEstado(String fileName) throws IOException, ClassNotFoundException {
        Model m = guardaAux(fileName);
        this.atividades = m.atividades;
        this.utilizadores = m.utilizadores;
        this.planoTreinos = m.planoTreinos;
        this.data_atual = m.data_atual;
    }

    /**
     * metodo auxiliar para guardar o estado
     * @param fileName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Model guardaAux(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        Model m = (Model) ois.readObject();
        ois.close();
        return m;
    }

    /**
     * query 1 no periodo entre inicio e fim
     * @param inicio
     * @param fim
     * @return
     * @throws UtilizadorNExisteException
     */
    public Utilizador Query1Periodo (LocalDate inicio, LocalDate fim) throws UtilizadorNExisteException {
        Utilizador mostSpent = null;
        double calorias_spent = 0.0;

        for (Utilizador user: this.utilizadores.values()) {
            double cal_user = user.CaloriasBetweenTwoDates(inicio, fim);
            if (cal_user > calorias_spent) {
                mostSpent = user;
                calorias_spent = cal_user;
            }
        }

        if (mostSpent == null) throw new UtilizadorNExisteException("Não existe nenhum utilizador");

        return mostSpent.clone();
    }

    /**
     * query 1 com os dados relevantes e existentes no programa
     * @return
     * @throws UtilizadorNExisteException
     */
    public Utilizador Query1Sempre () throws UtilizadorNExisteException {
        Utilizador mostSpent = null;
        double calorias_spent = 0.0;
        for (Utilizador user: this.utilizadores.values()) {
            if (user.getCalorias_total() > calorias_spent) {
                mostSpent = user;
                calorias_spent = user.getCalorias_total();
            }
        }

        if (mostSpent == null) throw new UtilizadorNExisteException("Não existe nenhum utilizador");

        return mostSpent.clone();
    }

    /**
     * query 2 no periodo entre inicio e fim
     * @param inicio
     * @param fim
     * @return
     * @throws UtilizadorNExisteException
     */
    public Utilizador Query2Periodo (LocalDate inicio, LocalDate fim) throws UtilizadorNExisteException {
        Utilizador mostSpent = null;
        int maior = 0;

        for (Utilizador user: this.utilizadores.values()) {
            int total = user.AtividadesBetweenTwoDates(inicio, fim, this.data_atual);
            if (total > maior) {
                mostSpent = user;
                maior = total;
            }
        }

        if (mostSpent == null) throw new UtilizadorNExisteException("Não existe nenhum utilizador");

        return mostSpent.clone();
    }

    /**
     * query 2 com os dados relevantes e existentes no programa
     * @return
     * @throws UtilizadorNExisteException
     */
    public Utilizador Query2Sempre () throws UtilizadorNExisteException {
        Utilizador mostSpent = null;
        int maior = 0;
        for (Utilizador user: this.utilizadores.values()) {
            int tamanho = user.AtividadesTotal(this.data_atual);
            if (tamanho > maior) {
                mostSpent = user;
                maior = tamanho;
            }
        }

        if (mostSpent == null) throw new UtilizadorNExisteException("Não existe nenhum utilizador");

        return mostSpent.clone();
    }

    /**
     * query 3
     * @return
     */
    public String Query3 () {
        StringBuilder builder = new StringBuilder();
        int altimetria = 0;
        int distancia = 0;
        int pesos = 0;
        int repeticoes = 0;

        for (Utilizador user: this.utilizadores.values()) {
            Map<String, Integer> atividades = user.getTotalTipoAtividades(this.data_atual);

            altimetria += atividades.get("altimetria");
            distancia += atividades.get("distancia");
            pesos += atividades.get("pesos");
            repeticoes += atividades.get("repeticoes");
        }

        if (altimetria > distancia && altimetria > pesos && altimetria > repeticoes) {
            builder.append("\nAtividades de Altimetria");
        } else if (distancia > altimetria && distancia > pesos && distancia > repeticoes) {
            builder.append("\nAtividades de Distância");
        } else if (pesos > altimetria && pesos > repeticoes && pesos > distancia) {
            builder.append("\nAtividades de Pesos");
        } else if (repeticoes > altimetria && repeticoes > pesos && repeticoes > distancia) {
            builder.append("\nAtividades de Repetições");
        } else {
            builder.append("\nNão existe uma atividade mais realizada");
        }

        return builder.toString();
    }

    /**
     * query 4 no periodo entre o inicio e fim
     * @param data_inicio
     * @param data_limite
     * @param user
     * @return
     * @throws UtilizadorNExisteException
     */
    public double Query4Periodo (LocalDate data_inicio, LocalDate data_limite, int user) throws UtilizadorNExisteException {
        Utilizador usuario = getUtilizador(user);

        if (usuario == null) throw new UtilizadorNExisteException("Utilizador não existe");

        return usuario.totalKmsPeriodo(data_inicio, data_limite, this.data_atual);
    }

    /**
     * query 4 com os dados relevantes existentes no programa
     * @param user
     * @return
     * @throws UtilizadorNExisteException
     */
    public double Query4Sempre(int user) throws UtilizadorNExisteException {
        Utilizador usuario = getUtilizador(user);

        if (usuario == null) throw new UtilizadorNExisteException("Utilizador não existe");

        return usuario.totalKms(this.data_atual);
    }

    /**
     * query 5 no periodo entre o inicio e fim
     * @param data_inicio
     * @param data_limite
     * @param user
     * @return
     * @throws UtilizadorNExisteException
     */
    public double Query5Periodo (LocalDate data_inicio, LocalDate data_limite, int user) throws UtilizadorNExisteException {
        Utilizador usuario = getUtilizador(user);

        if (usuario == null) throw new UtilizadorNExisteException("Utilizador não existe");

        return usuario.totalMetrosAltimetriaPeriodo(data_inicio, data_limite, this.data_atual);
    }

    /**
     * query 5 com os dados relevantes existentes no programa
     * @param user
     * @return
     * @throws UtilizadorNExisteException
     */
    public double Query5Sempre (int user) throws UtilizadorNExisteException {
        Utilizador usuario = getUtilizador(user);

        if (usuario == null) throw new UtilizadorNExisteException("Utilizador não existe");

        return usuario.totalMetrosAltimetria(this.data_atual);
    }

    /**
     * query 6
     * @return
     * @throws PlanoTreinoNExisteException
     */
    public PlanoTreino Query6 () throws PlanoTreinoNExisteException {
        PlanoTreino maisExigente = null;
        double max = 0.0;

        for (Utilizador user: this.utilizadores.values()) {
            try {
                PlanoTreino plano = user.PlanoMaisExigente(this.data_atual);
                if (plano.getCalorias() > max) {
                    maisExigente = plano;
                    max = plano.getCalorias();
                }
            } catch (PlanoTreinoNExisteException e) {System.out.println("Não existe nenhum plano de treino");}
        }

        if (maisExigente == null) throw new PlanoTreinoNExisteException("Não existe nenhum plano de treino");

        return maisExigente.clone();
    }

    /**
     * query 7
     * @param id
     * @return
     * @throws UtilizadorNExisteException
     */
    public String Query7 (int id) throws UtilizadorNExisteException {
        Utilizador usuario = getUtilizador(id);

        if (usuario == null) throw new UtilizadorNExisteException("Não existe nenhum utilizador");

        return usuario.showAtividades();
    }

    /**
     * query 8
     * @return
     */
    public String Query8 () {
        StringBuilder builder = new StringBuilder();

        builder.append("**** Lista de atividades Hard ****\n");

        for (Atividade atividade: this.atividades.values()) {
            if (atividade instanceof Hard) {
                builder.append(atividade.clone().toString()).append("\n");
            }
        }

        return builder.toString();
    }

    /**
     * atualiza um utilizador
     * @param user
     */
    public void updateUser(Utilizador user) {
        this.utilizadores.put(user.getCodigo_utilizador(), user);
    }

    /**
     * atualiza um plano de treino
     * @param planoTreino
     */
    public void updatePlanoTreino(PlanoTreino planoTreino) {
        this.planoTreinos.put(planoTreino.getCod(), planoTreino);
    }

}
