package Utilizadores;

import Atividades.*;
import Exceptions.PlanoTreinoNExisteException;
import PlanoTreino.PlanoTreino;

import java.io.Serializable;
import java.time.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A classe utilizador representa os utilizadores da nossa app
 */
public abstract class Utilizador implements Serializable {
    private int codigo_utilizador;
    private String nome;
    private String morada;
    private String email;
    private int frequencia_cardiaca; //Média
    private Genero genero;
    private double peso;
    private int altura; //cm
    private LocalDate data_nascimento;
    private double calorias_total;
    private Map<Integer, Atividade> atividades;
    private Map<Integer, PlanoTreino> planos_treino;
    private double fator_multiplicativo;

    /**
     * Construtor vazio
     */
    public Utilizador() {
        this.codigo_utilizador = -1;
        this.nome = null;
        this.morada = null;
        this.email = null;
        this.frequencia_cardiaca = -1;
        this.genero = null;
        this.peso = -1.0;
        this.altura = -1;
        this.data_nascimento = LocalDate.now();
        this.calorias_total = 0.0;
        this.atividades = new HashMap<>();
        this.planos_treino = new HashMap<>();
        this.fator_multiplicativo = 0.0;
    }

    /**
     * Construtor parametrizado
     * @param codigo_utilizador id do utilizador
     * @param nome nome do utilizador
     * @param morada morada do utilizador
     * @param email email do utilizador
     * @param frequencia_cardiaca frequencia cardiaca do utilizador
     * @param genero genero do utilizador
     * @param peso peso do utilizador
     * @param altura altura do utilizador
     * @param data_nascimento data de nascimento do utilizador
     * @param calorias_total calorias totais do utilizador até ao momento
     * @param atividades map com atividades do utilizador
     * @param planos_treino map com os planos de treino do utilizador
     */
    public Utilizador(int codigo_utilizador, String nome, String morada,
                        String email, int frequencia_cardiaca, Genero genero, double peso, int altura, LocalDate data_nascimento, double calorias_total,
                        Map<Integer, Atividade> atividades, Map<Integer, PlanoTreino> planos_treino) {
        this.codigo_utilizador = codigo_utilizador;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.frequencia_cardiaca = frequencia_cardiaca;
        this.genero = genero;
        this.peso = peso;
        this.altura = altura;
        this.data_nascimento = data_nascimento;
        this.calorias_total = calorias_total;
        this.fator_multiplicativo = 0.0;

        this.atividades = new HashMap<>();

        for(Integer id_atividade: atividades.keySet()) {
            this.atividades.put(id_atividade, atividades.get(id_atividade).clone());
        }

        this.planos_treino = new HashMap<>();
        for(Integer id_plano: planos_treino.keySet()) {
            this.planos_treino.put(id_plano, planos_treino.get(id_plano).clone());
        }

    }

    /**
     * Construtor parametrizado
     * @param codigo_utilizador id do utilizador
     * @param nome nome do utilizador
     * @param morada morada do utilizador
     * @param email email do utilizador
     * @param frequencia_cardiaca frequencia cardíaca do utilizador
     * @param genero genero do utilizador
     * @param peso peso do utilizador
     * @param altura altura do utilizador
     * @param data_nascimento data de nascimento do utilizador
     */
    public Utilizador (int codigo_utilizador, String nome, String morada,
                       String email, int frequencia_cardiaca, Genero genero, double peso, int altura, LocalDate data_nascimento) {
        this.codigo_utilizador = codigo_utilizador;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.frequencia_cardiaca = frequencia_cardiaca;
        this.genero = genero;
        this.peso = peso;
        this.altura = altura;
        this.data_nascimento = data_nascimento;
        this.calorias_total = 0;
        this.atividades = new HashMap<>();
        this.planos_treino = new HashMap<>();
        this.fator_multiplicativo = 0.0;
    }

    /**
     * Construtor de copia
     * @param user utilizador que pretende copiar
     */
    public Utilizador(Utilizador user) {
        this.codigo_utilizador = user.getCodigo_utilizador();
        this.nome = user.getNome();
        this.morada = user.getMorada();
        this.email = user.getEmail();
        this.frequencia_cardiaca = user.getFrequencia_cardiaca();
        this.genero = user.getGenero();
        this.altura = user.getAltura();
        this.peso = user.getPeso();
        this.data_nascimento = user.getData_nascimento();
        this.calorias_total = user.getCalorias_total();
        this.atividades = user.getAtividades();
        this.planos_treino = user.getPlanos_treino();
        this.fator_multiplicativo = user.getFator_multiplicativo();
    }


    public int getCodigo_utilizador() {
        return this.codigo_utilizador;
    }

    public String getNome() {
        return this.nome;
    }

    public String getMorada() {
        return this.morada;
    }

    public String getEmail() {
        return this.email;
    }

    public int getFrequencia_cardiaca() {
        return this.frequencia_cardiaca;
    }

    public Genero getGenero() {
        return this.genero;
    }

    public double getPeso() {
        return this.peso;
    }

    public int getAltura() {
        return this.altura;
    }

    public LocalDate getData_nascimento() {
        return this.data_nascimento;
    }

    public double getCalorias_total() { return this.calorias_total; }

    public Map<Integer, Atividade> getAtividades() {
        Map<Integer, Atividade> atividades = new HashMap<>();

        for (Integer id_atividade: this.atividades.keySet()) {
            atividades.put(id_atividade, this.atividades.get(id_atividade).clone());
        }

        return atividades;
    }

    public Map<Integer, PlanoTreino> getPlanos_treino() {
        Map<Integer, PlanoTreino> planos_treino = new HashMap<>();

        for (Integer id_plano: this.planos_treino.keySet()) {
            planos_treino.put(id_plano, this.planos_treino.get(id_plano).clone());
        }

        return planos_treino;
    }

    public double getFator_multiplicativo() { return this.fator_multiplicativo; }

    /**
     * ----------------------------------------Setters----------------------------------------
     */
    public void setCodigo_utilizador(int codigo_utilizador){this.codigo_utilizador = codigo_utilizador;}
    public void setNome(String nome){this.nome = nome;}
    public void setMorada(String morada){this.morada = morada;}
    public void setEmail(String email){this.email = email;}
    public void setFrequencia_cardiaca(int frequencia_cardiaca) {this.frequencia_cardiaca = frequencia_cardiaca;}
    public void setGenero(Genero genero) {this.genero = genero;}
    public void setPeso(double peso) {this.peso = peso;}
    public void setAltura(int altura) {this.altura = altura;}
    public void setData_nascimento(LocalDate data_nascimento) {this.data_nascimento = data_nascimento;}
    public void setCalorias_total(double calorias_total) {this.calorias_total = calorias_total;}
    public void setFator_multiplicativo(double fator_multiplicativo) { this.fator_multiplicativo = fator_multiplicativo;}

    //DUAS MANEIRAS DISTINTAS

    public void setAtividades(Map<Integer, Atividade> atividades) {
        Map<Integer, Atividade> setAtiv = new HashMap<>();

        for(Integer id_atividade: atividades.keySet()){
            setAtiv.put(id_atividade, atividades.get(id_atividade).clone());
        }

        this.atividades = atividades;
    }

    public void setPlanos_treino(Map<Integer, PlanoTreino> planos_treino) {
        Map<Integer, PlanoTreino> setTreino = new HashMap<>();

        for(PlanoTreino pt: planos_treino.values()){
            setTreino.put(pt.getCod(), pt.clone());
        }

        this.planos_treino = setTreino;
    }

    /**
     * adiciona calorias ao user
     * @param calorias calorias que pretende adicionar
     */
    public void addCalorias(double calorias) {
        this.setCalorias_total(this.getCalorias_total() + calorias);
    }

    /**
     * adiciona uma atividade tendo em conta se possivel ou nao
     * @param ativ atividade a inserir
     * @param data_programa data que o programa se encontra, para auxiliar o calculo de calorias
     * @param data data da atividade
     * @param horas horas da atividade
     */
    public void addAtividade(Atividade ativ, LocalDate data_programa, LocalDate data, LocalTime horas) {
        Atividade toInsert = ativ;
        toInsert.setCodigo(getTotalAtividades()+1);
        toInsert.setData(data);
        toInsert.setHora(horas);

        if(toInsert.getData().isBefore(data_programa) && this.data_nascimento.isBefore(toInsert.getData())){
            toInsert.setCalorias_gastas(toInsert.calc_calorias(this));
        }

        this.atividades.put(toInsert.getCodigo(), toInsert);
        this.addCalorias(toInsert.getCalorias_gastas());
    }

    /**
     * adiciona um plano a um utilizador
     * @param plano plano a adicionar
     * @param data_programa data que o programa se encontra
     * @param data_inicio data do ínicio do plano de treino
     * @param data_limite data do final do plano de treino
     * @param dias dias da semana que faz o plano de treino
     */
    public void addPlano(PlanoTreino plano, LocalDate data_programa, LocalDate data_inicio, LocalDate data_limite, List<DayOfWeek> dias, LocalTime horas) {
        LocalDate data_plano = data_inicio;
        double calorias_totais = 0.0;

        while (!data_plano.isAfter(data_limite)) {
            PlanoTreino toInsert = plano.clone();
            toInsert.setCod(getTotalPlanos() + 1);
            toInsert.setData(data_plano);
            toInsert.setDatasAtividades(horas);

            if (dias.contains(data_plano.getDayOfWeek())) {
                if (toInsert.getData().isBefore(data_programa) && this.data_nascimento.isBefore(toInsert.getData())) {
                    toInsert.setStats(getTotalAtividades()+1, this);
                    toInsert.setCalorias(toInsert.calcularCalorias());
                } else {
                    toInsert.setOnlyId(getTotalAtividades()+1, this);
                }

                calorias_totais += toInsert.getCalorias();
                this.planos_treino.put(toInsert.getCod(), toInsert);
                this.addCalorias(calorias_totais);
            }
            data_plano = data_plano.plusDays(1);
        }

    }

    /**
     * insere uma atividade de um plano nas atividades de um utilizador
     * @param atividade atividade a inserir
     */
    public void insertAtividadeFromPlano(Atividade atividade) {
        this.atividades.put(atividade.getCodigo(), atividade);
    }

    /**
     * calcula as calorias entre duas datas
     * @param data_inicio data do inicio
     * @param data_limite data do final
     * @return
     */
    public double CaloriasBetweenTwoDates (LocalDate data_inicio, LocalDate data_limite) {
        double cal_total = 0.0;

        for (Atividade atividade: this.atividades.values()) {
            if ((atividade.getData().isBefore(data_limite) || atividade.getData().isEqual(data_limite)) &&
                    (atividade.getData().isAfter(data_inicio) || data_inicio.isEqual(atividade.getData()))) {
                cal_total += atividade.getCalorias_gastas();
            }
        }

        return cal_total;
    }

    /**
     * calcula as atividades entre duas datas
     * @param data_inicio data do inicio
     * @param data_limite data do final
     * @param data_programa data do programa
     * @return
     */
    public int AtividadesBetweenTwoDates (LocalDate data_inicio, LocalDate data_limite, LocalDate data_programa) {
        int n_atividades = 0;

        for (Atividade atividade: this.atividades.values()) {
            if ((atividade.getData().isBefore(data_limite) || atividade.getData().isEqual(data_limite)) &&
                    (atividade.getData().isAfter(data_inicio) || data_inicio.isEqual(atividade.getData())) &&
                    atividade.getData().isAfter(this.getData_nascimento()) && atividade.getData().isBefore(data_programa)) {
                n_atividades++;
            }
        }

        return n_atividades;
    }

    /**
     * numero de atividades que o utilizador já exerceu
     * @param data_programa data do programa
     * @return numero de atividades
     */
    public int AtividadesTotal (LocalDate data_programa) {
        int n_atividades = 0;

        for (Atividade atividade: this.atividades.values()) {
            if (atividade.getData().isBefore(data_programa) && atividade.getData().isAfter(this.getData_nascimento())) {
                n_atividades++;
            }
        }

        return n_atividades;
    }

    /**
     * recalcula as calorias tendo em conta a data do programa
     * @param data_programa data do programa
     */
    public void RecalcularCalorias(LocalDate data_programa) {
        double calorias_tot = 0.0;

        for(Atividade atividade: this.atividades.values()) {
            if (atividade.getData().isBefore(data_programa) && this.data_nascimento.isBefore(atividade.getData())) {
                atividade.setCalorias_gastas(atividade.calc_calorias(this));
                calorias_tot += atividade.getCalorias_gastas();
            }
        }

        for(PlanoTreino plano: this.planos_treino.values()) {
            if (plano.getData().isBefore(data_programa) && this.data_nascimento.isBefore(plano.getData())) {
                plano.updateCalorias(this);
                plano.setCalorias(plano.calcularCalorias());
            }
        }

        this.calorias_total = calorias_tot;
    }

    /**
     * string correspondente às informações do utilizador
     * @return String
     */
    public String showMe() {
        StringBuilder string = new StringBuilder();
        string.append(this.toString()).append("\n\n").append("***** ATIVIDADES *****\n\n");

        for (Atividade atividade: this.atividades.values()) {
            string.append(atividade.toString()).append("\n");
        }

        string.append("***** PLANOS TREINO *****\n\n");

        for (PlanoTreino plano: this.planos_treino.values()) {
            string.append(plano.toString()).append("\n");
        }

        return string.toString();
    }

    /**
     * total do tipo de atividades
     * @param data_programa data do programa
     * @return total do tipo de atividades
     */
    public Map<String, Integer> getTotalTipoAtividades (LocalDate data_programa) {
        Map<String, Integer> estatisticas = new HashMap<>();
        int altimetria = 0;
        int distancia = 0;
        int pesos = 0;
        int repeticoes = 0;

        for (Atividade atividade: this.atividades.values()) {
            if (atividade.getData().isBefore(data_programa)) {
                if (atividade instanceof AtividadeAltimetria) {
                    altimetria++;
                }
                if (atividade instanceof AtividadeDistancia) {
                    distancia++;
                }
                if (atividade instanceof AtividadePesos) {
                    pesos++;
                }
                if (atividade instanceof AtividadeRepeticoes) {
                    repeticoes++;
                }
            }
        }

        estatisticas.put("altimetria", altimetria);
        estatisticas.put("distancia", distancia);
        estatisticas.put("pesos", pesos);
        estatisticas.put("repeticoes", repeticoes);

        return estatisticas;
    }

    /**
     * total de km's exercidos durante um periodo
     * @param data_inicio data do inicio
     * @param data_limite data do final
     * @param data_programa data do programa
     * @return total dos km's
     */
    public double totalKmsPeriodo(LocalDate data_inicio, LocalDate data_limite, LocalDate data_programa) {
        double km = 0.0;

        for (Atividade atividade: this.atividades.values()) {
            if ((atividade instanceof AtividadeAltimetria || atividade instanceof AtividadeDistancia) &&
                    (atividade.getData().isBefore(data_limite) || atividade.getData().isEqual(data_limite)) &&
                    (atividade.getData().isAfter(data_inicio) || data_inicio.isEqual(atividade.getData())) &&
                    atividade.getData().isBefore(data_programa)) {

                if (atividade instanceof AtividadeAltimetria) {
                    km += ((AtividadeAltimetria) atividade).getDistancia();
                } else if (atividade instanceof AtividadeDistancia) {
                    km += ((AtividadeDistancia) atividade).getDistancia();
                }

            }
        }

        return km;
    }

    /**
     * total dos km's desde sempre até à data atual
     * @param data_programa data do programa
     * @return total dos km's
     */
    public double totalKms(LocalDate data_programa) {
        double km = 0.0;

        for (Atividade atividade: this.atividades.values()) {

            if (atividade.getData().isBefore(data_programa)) {
                if (atividade instanceof AtividadeAltimetria) {
                    km += ((AtividadeAltimetria) atividade).getDistancia();
                } else if (atividade instanceof AtividadeDistancia) {
                    km += ((AtividadeDistancia) atividade).getDistancia();
                }
            }

        }
        return km;
    }

    /**
     * total de metros de altimetria durante um periodo
     * @param data_inicio data do inicio
     * @param data_limite data do final
     * @param data_programa data do programa
     * @return do total de metros
     */
    public double totalMetrosAltimetriaPeriodo(LocalDate data_inicio, LocalDate data_limite, LocalDate data_programa) {
        double metros_altimetria = 0.0;

        for (Atividade atividade: this.atividades.values()) {
            if (atividade instanceof AtividadeAltimetria &&
                    (atividade.getData().isBefore(data_limite) || atividade.getData().isEqual(data_limite)) &&
                    (atividade.getData().isAfter(data_inicio) || data_inicio.isEqual(atividade.getData()))
                    && atividade.getData().isBefore(data_programa)) {

                metros_altimetria += ((AtividadeAltimetria) atividade).getAltimetria();

            }
        }

        return metros_altimetria;
    }

    /**
     * total de metros de altimetria desde sempre até à data do programa
     * @param data_programa data do programa
     * @return total de metros
     */
    public double totalMetrosAltimetria(LocalDate data_programa) {
        double metros_altimetria = 0.0;

        for (Atividade atividade: this.atividades.values()) {
            if (atividade.getData().isBefore(data_programa) && atividade instanceof AtividadeAltimetria) {
                metros_altimetria += ((AtividadeAltimetria) atividade).getAltimetria();
            }
        }

        return metros_altimetria;
    }

    /**
     * plano mais exigente (maior numero de calorias) do utilizador
     * @param data_programa data do programa
     * @return dum clone do plano
     * @throws PlanoTreinoNExisteException
     */
    public PlanoTreino PlanoMaisExigente (LocalDate data_programa) throws PlanoTreinoNExisteException {
        PlanoTreino planoMaisExigente = null;
        double calorias = 0.0;

        for (PlanoTreino plano: this.planos_treino.values()) {
            if (plano.getData().isBefore(data_programa)) {
                if (plano.getCalorias() > calorias) {
                    planoMaisExigente = plano;
                    calorias = plano.getCalorias();
                }
            }
        }

        if (planoMaisExigente == null) throw new PlanoTreinoNExisteException("Não existe nenhum plato de treino");

        return planoMaisExigente.clone();
    }

    /**
     * mostra as atividades do utilizador, independentemente se já foram realizadas ou não
     * @return
     */
    public String showAtividades () {
        StringBuilder atividades = new StringBuilder();

        atividades.append("\n**** Lista de atividades ****");

        for (Atividade atividade: this.atividades.values()) {
            atividades.append("\n").append(atividade.toString());
        }

        return atividades.toString();
    }


    /**
     * calculo das calorias totais
     * @return calorias
     */
    public double calc_calorico(){
        double calorias = 0;
        for(Atividade activ : this.atividades.values()){
            calorias += activ.getCalorias_gastas();
        }
        return calorias;
    }

    /**
     * cálculo do multiplicador de um utilizador
     * @return multiplicador
     */
    public abstract double calculo_multiplicador();

    public boolean equals(Object obj) {
        if(this == obj){return true;}
        if(obj == null || obj.getClass() != this.getClass()) {return false;}
        Utilizador user = (Utilizador) obj;
        return (this.codigo_utilizador == getCodigo_utilizador() && this.nome.equals(user.getNome()) && this.morada.equals(user.getMorada()) &&
                this.email.equals(user.getEmail()) && this.frequencia_cardiaca == user.getFrequencia_cardiaca());
    }

    @Override
    public String toString() {
        String generoString;
        if (genero == Genero.MASCULINO) {
            generoString = "Masculino";
        } else if (genero == Genero.FEMININO) {
            generoString = "Feminino";
        } else {
            generoString = "Indefinido"; // Handle other cases if necessary
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Código Utilizador -> ").append(codigo_utilizador)
                .append("\nNome -> ").append(nome)
                .append("\nMorada -> ").append(morada)
                .append("\nEmail -> ").append(email)
                .append("\nFrequência cardíaca -> ").append(frequencia_cardiaca)
                .append("\nGenero -> ").append(generoString)
                .append("\nPeso -> ").append(peso)
                .append("\nAltura -> ").append(altura)
                .append("\nData de Nascimento -> ").append(data_nascimento)
                .append("\nCalorias Gastas -> ").append(calorias_total)
                .append("\nFator multiplicativo -> ").append(fator_multiplicativo);

        return sb.toString();
    }

    @Override
    public abstract Utilizador clone();

    /**
     * total dos planos do utilizador
     * @return numero de planos
     */
    public int getTotalPlanos() {
        return this.planos_treino.size();
    }

    /**
     * total das atividades do utilizador
     * @return numero de atividades
     */
    public int getTotalAtividades() {
        return this.atividades.size();
    }


}
