package Aplicacao;

import Exceptions.PlanoTreinoNExisteException;
import Exceptions.UtilizadorNExisteException;
import PlanoTreino.PlanoTreino;
import Utilizadores.Genero;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Controller {
    private Model modelo;

    public Controller(Model modelo) {
        this.modelo = modelo;
    }

    public Controller(Controller c1) {
        this.modelo = c1.getModelo();
    }

    public Model getModelo() {
        return this.modelo.clone();
    }

    /**
     * print dos utilizadores do programa
     * @return users
     */
    public String showUsers () {
        return this.modelo.showUsers();
    }

    /**
     * print de um utilizador com codigo id
     * @param id
     * @return user
     */
    public String showSingleUser(int id) {
        return this.modelo.showSingleUser(id);
    }

    /**
     * print das atividades do programa
     * @return atividades
     */
    public String showAtividades () {
        return this.modelo.showAtividades();
    }

    /**
     * print dos planos de treino do programa
     * @return planos de treino
     */
    public String showPlanoTreinos () {
        return this.modelo.showPlanoTreinos();
    }

    /**
     * adiciona um utilizador no programa com os seguintes parametros
     * @param nome
     * @param morada
     * @param email
     * @param frequencia
     * @param genero
     * @param peso
     * @param altura
     * @param data_nascimento
     * @param type
     */
    public void addUser (String nome, String morada, String email, int frequencia, Genero genero,
                        double peso, int altura, LocalDate data_nascimento, int type) {
        switch (type) {
            case 1:
                this.modelo.addUserProfissional(nome, morada, email, frequencia, genero, peso, altura, data_nascimento);
                break;

            case 2:
                this.modelo.addUserAmador(nome, morada, email, frequencia, genero, peso, altura, data_nascimento);
                break;

            case 3:
                this.modelo.addUserOcasional(nome, morada, email, frequencia, genero, peso, altura, data_nascimento);
                break;
        }
    }

    /**
     * remove um utilizador com codigo id
     * @param id
     */
    public void remUser (int id) { this.modelo.remUser(id); }

    /**
     * verifica se existe um utilizador com codigo id
     * @param id
     * @return
     */
    public boolean existUser (int id) {
        return this.modelo.existUser(id);
    }

    /**
     * Cria uma ciclismo na montanha com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param altimetria
     * @param distancia
     * @param percurso
     * @param horas
     */
    public void newBicicletaMontanha(int duracao, LocalDate data, int fq, double altimetria, int distancia, String percurso, LocalTime horas) {
        this.modelo.newBicicletaMontanha(duracao, data, fq, altimetria, distancia, percurso, horas);
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
        this.modelo.newSupino(duracao, data, fq, reps, peso, horas);
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
        this.modelo.newRemo(duracao, data, fq, distancia, percurso, horas);
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
        this.modelo.newAbdominais(duracao, data, fq, reps, horas);
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
        this.modelo.newCorridaEstrada(duracao, data, fq, altimetria, distancia, percurso, horas);
    }

    /**
     * cria uma atividade corrida na pista de atletismo com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param distancia
     * @param percurso
     * @param horas
     */
    public void newCorridaPistaDeAtletismo(int duracao, LocalDate data, int fq, int distancia, String percurso, LocalTime horas) {
        this.modelo.newCorridaPistaDeAtletismo(duracao, data, fq, distancia, percurso, horas);
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
        this.modelo.newElevacoes(duracao, data, fq, reps, horas);
    }

    /**
     * cria uma atividade passadeira em escada com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param altimetria
     * @param distancia
     * @param percurso
     * @param horas
     */
    public void newPassadeiraEscada(int duracao, LocalDate data, int fq, double altimetria, int distancia, String percurso, LocalTime horas) {
        this.modelo.newPassadeiraEscada(duracao, data, fq, altimetria, distancia, percurso, horas);
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
        this.modelo.newFlexoes(duracao, data, fq, reps, horas);
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
        this.modelo.newLegPress(duracao, data, fq, reps, peso, horas);
    }

    /**
     * cria uma atividade natação com os seguintes parametros
     * @param duracao
     * @param data
     * @param fq
     * @param distancia
     * @param percurso
     * @param horas
     */
    public void newNadar(int duracao, LocalDate data, int fq, int distancia, String percurso, LocalTime horas) {
        this.modelo.newNadar(duracao, data, fq, distancia, percurso, horas);
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
        this.modelo.newShoulderPress(duracao, data, fq, reps, peso, horas);
    }

    /**
     * query 1 com periodo entre inicio e fim
     * @param inicio
     * @param fim
     * @return
     * @throws UtilizadorNExisteException
     */
    public String Query1Periodo(LocalDate inicio, LocalDate fim) throws UtilizadorNExisteException {
        return this.modelo.Query1Periodo(inicio, fim).toString();
    }

    /**
     * query 1 com valores desde o inicio
     * @return
     * @throws UtilizadorNExisteException
     */
    public String Query1Sempre() throws UtilizadorNExisteException {
        return this.modelo.Query1Sempre().toString();
    }

    /**
     * query 2 com periodo entre o inicio e fim
     * @param inicio
     * @param fim
     * @return
     * @throws UtilizadorNExisteException
     */
    public String Query2Periodo(LocalDate inicio, LocalDate fim) throws UtilizadorNExisteException {
        return this.modelo.Query2Periodo(inicio, fim).toString();
    }

    /**
     * query 2 desde o inicio do programa
     * @return
     * @throws UtilizadorNExisteException
     */
    public String Query2Sempre() throws UtilizadorNExisteException {
        return this.modelo.Query2Sempre().toString();
    }

    /**
     * query 3
     * @return string
     */
    public String Query3() {
        return this.modelo.Query3();
    }

    /**
     * query 4 com periodo entre o inicio e fim
     * @param inicio
     * @param fim
     * @param user
     * @return double
     * @throws UtilizadorNExisteException
     */
    public double Query4Periodo(LocalDate inicio, LocalDate fim, int user) throws UtilizadorNExisteException {
        return this.modelo.Query4Periodo(inicio, fim, user) / 1000;
    }

    /**
     * query 4 com os valores de sempre
     * @param user
     * @return double
     * @throws UtilizadorNExisteException
     */
    public double Query4Sempre (int user) throws UtilizadorNExisteException {
        return this.modelo.Query4Sempre(user) / 1000;
    }

    /**
     * query 5 entre o periodo inicio e fim
     * @param inicio
     * @param fim
     * @param user
     * @return double
     * @throws UtilizadorNExisteException
     */
    public double Query5Periodo(LocalDate inicio, LocalDate fim, int user) throws UtilizadorNExisteException {
        return this.modelo.Query5Periodo(inicio, fim, user);
    }

    /**
     * query 5 com os valores de sempre
     * @param user
     * @return double
     * @throws UtilizadorNExisteException
     */
    public double Query5Sempre (int user) throws UtilizadorNExisteException {
        return this.modelo.Query5Sempre(user);
    }

    /**
     * query 6
     * @return string
     * @throws PlanoTreinoNExisteException
     */
    public String Query6 () throws PlanoTreinoNExisteException {
        PlanoTreino plano = null;

        plano = this.modelo.Query6();

        if (plano == null) throw new PlanoTreinoNExisteException("Não existe nenhum plano de treino");

        return plano.toString();
    }

    /**
     * query 7
     * @param id
     * @return string
     * @throws UtilizadorNExisteException
     */
    public String Query7 (int id) throws UtilizadorNExisteException {
        return this.modelo.Query7(id);
    }

    /**
     * query 8
     * @return string
     */
    public String Query8 () {
        return this.modelo.Query8();
    }

    /**
     * adiciona uma atividade a um utilizador
     * @param atividade
     * @param utilizador
     * @param data
     * @param horas
     */
    public void addAtividadeUtilizador(int atividade, int utilizador, LocalDate data, LocalTime horas) {
        this.modelo.addAtividadeUtilizador(atividade, utilizador, data, horas);
    }

    /**
     * cria um novo plano de treino com uma x data
     * @param data
     */
    public void newPlanoDeTreino(LocalDate data){
        this.modelo.newPlanoDeTreino(data);
    }

    /**
     * adiciona uma atividade a um plano de treino
     * @param atividade
     * @param planoDeTreino
     */
    public void addAtividadePlanoDeTreino(int atividade, int planoDeTreino){
        this.modelo.addAtividadePlanoDeTreino(atividade,planoDeTreino);
    }

    /**
     * atribui um plano a um utilizador tendo em conta o inicio e o final do plano de treino, e os dias da semana
     * @param plano
     * @param utilizador
     * @param data_inicio
     * @param data_final
     * @param dias
     * @param horas
     */
    public void atribuirPlanoTreinoUser(int plano, int utilizador, LocalDate data_inicio, LocalDate data_final, List<DayOfWeek> dias, LocalTime horas) {
        this.modelo.atribuirPlanoTreinoUser(plano, utilizador, data_inicio, data_final, dias, horas);
    }

    /**
     * avança o tempo do programa, utilizado no menu simulacao e de avancar tempo
     * @param dia
     */
    public void AvancarTempo(long dia){
        this.modelo.AvancarTempo(dia);
        this.modelo.RecalcularCalorias();
    }

    /**
     * guarda o estado num ficheiro de nome fileName
     * @param fileName
     * @throws IOException
     */
    public void GuardarEstado(String fileName) throws IOException {
        this.modelo.guardaEstado(fileName);
    }

    /**
     * carrega o estado no programa de um ficheiro de nome fileName
     * @param fileName
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void CarregarEstado(String fileName) throws IOException, ClassNotFoundException {
        this.modelo.carregaEstado(fileName);
    }

    /**
     * verifica se uma data é valida para o dado utilizador
     * @param user
     * @param data
     * @return boolean
     * @throws UtilizadorNExisteException
     */
    public boolean validData(int user, LocalDate data) throws UtilizadorNExisteException {
        return this.modelo.validDataForUser(user, data);
    }

    /**
     * data atual do programa
     * @return localdate
     */
    public LocalDate getData(){return this.modelo.getData_atual();}

    /**
     * numero de utilizadores no programa
     * @return int
     */
    public int getTotalUsers () { return this.modelo.getTotalUsers(); }

    /**
     * numero de atividades gerais no programa (não conta as atividades dos utilizadores)
     * @return int
     */
    public int getTotalAtividades () { return this.modelo.getTotalAtividades(); }

    /**
     * numero de planos de treino no programa (não conta os pt dos utilizadores)
     * @return
     */
    public int getTotalPlanoTreinos () { return this.modelo.getTotalPlanoTreinos(); }

    /**
     * funcao que igual o modelo ao m1, server para guardar uma copia, no menu de simulação
     * @param m1
     */
    public void colocaModelo(Model m1){
        this.modelo = m1;
    }

}
