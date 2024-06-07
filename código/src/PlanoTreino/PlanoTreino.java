package PlanoTreino;

import Atividades.Atividade;
import Exceptions.AtividadeNExisteException;
import Utilizadores.Utilizador;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PlanoTreino implements Serializable {
    private int cod;
    private LocalDate data;
    private List<Atividade> atividades;
    private double calorias;

    /**
     * Construtor vazio
     */
    public PlanoTreino() {
        this.cod = -1;
        this.data = LocalDate.now();
        this.atividades = new ArrayList<>();
        this.calorias = 0.0;
    }

    /**
     * Construtor parametrizado
     * @param data data do plano
     * @param cod id do plano
     */
    public PlanoTreino(LocalDate data, int cod){
        this.cod = cod;
        this.data = data;
        this.atividades = new ArrayList<>();
        this.calorias = 0.0;
    }

    /**
     * Construtor parametrizado
     * @param id id do plano
     * @param data data do plano
     * @param atividades atividades do plano
     */
    public PlanoTreino(int id, LocalDate data, List<Atividade> atividades) {
        this.cod = id;
        this.data = data;
        this.atividades = new ArrayList<>();
        for(Atividade atividade: atividades) {
            this.atividades.add(atividade.clone());
        }
        this.calorias = 0;
    }

    /**
     * Construtor de cópia
     * @param p plano de treino a ser copiado
     */
    public PlanoTreino(PlanoTreino p) {
        this.cod = p.getCod();
        this.data = p.getData();
        this.atividades = p.getAtividades();
        this.calorias = p.getCalorias();
    }

    public int getCod() {
        return cod;
    }

    public double getCalorias() {
        return this.calorias;
    }

    public void setCalorias (double calorias) {
        this.calorias = calorias;
    }

    public void setCod(int id) {
        this.cod = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Atividade> getAtividades() {
        List<Atividade> activs = new ArrayList<>();

        for(Atividade atividade: this.atividades) {
            activs.add(atividade.clone());
        }

        return activs;
    }

    public void setAtividades(List<Atividade> atividades) {
        List<Atividade> activs = new ArrayList<>();

        for(Atividade atividade: atividades) {
            activs.add(atividade.clone());
        }

        this.atividades = activs;
    }

    public boolean equals(PlanoTreino p) {
        if (p == this) return true;
        if (p == null || p.getClass() != this.getClass()) return false;
        PlanoTreino p2 = (PlanoTreino) p;
        return this.data.equals(p2.getData()) && this.atividades.equals(p2.getAtividades());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("***** Plano de treino *****\n")
                .append("Código -> ").append(this.cod).append("\n")
                .append("Data -> ").append(this.data).append("\n")
                .append("Calorias totais -> ").append(this.calorias).append("\n")
                .append("\n----- Atividades -----");
        for (Atividade atividade: this.atividades) {
            sb.append("\n").append(atividade.toString()).append("\n");
        }

        return sb.toString();
    }

    public PlanoTreino clone() {
        return new PlanoTreino(this);
    }

    /**
     * Adiciona uma atividade a um plano de treino
     * @param atividade atividade a adicionar
     */
    public void addAtividade(Atividade atividade) {
        this.atividades.add(atividade);
    }

    /**
     * calcula as calorias totais do plano de treino
     * @return das calorias
     */
    public double calcularCalorias() {
        double calorias = 0.0;
        for (Atividade atividade: this.atividades) {
            calorias += atividade.getCalorias_gastas();
        }
        return calorias;
    }

    /**
     * mete as datas das atividades com a data do plano de treino
     * @param horas
     */
    public void setDatasAtividades(LocalTime horas) {
        for (Atividade atividade: this.atividades) {
            atividade.setData(this.data);
            atividade.setHora(horas);
        }
    }

    /**
     * Coloca o id das atividades tendo em conta o utilizador e calcula as calorias
     * @param primeiro_codigo primeiro codigo da atividade no utilizador
     * @param user utilizador a inserir
     */
    public void setStats(int primeiro_codigo, Utilizador user) {
        for (Atividade atividade: this.atividades) {
            atividade.setCodigo(primeiro_codigo);
            double calorias = atividade.calc_calorias(user);
            atividade.setCalorias_gastas(calorias);
            user.insertAtividadeFromPlano(atividade);
            primeiro_codigo++;
        }

        this.calorias = calcularCalorias();
    }

    /**
     * Coloca apenas o id da atividade tendo em conta o utilizador
     * @param primeiro_codigo primeiro codigo da atividade
     * @param user utilizador a inserir
     */
    public void setOnlyId(int primeiro_codigo, Utilizador user) {
        for (Atividade atividade: this.atividades) {
            atividade.setCodigo(primeiro_codigo);
            user.insertAtividadeFromPlano(atividade);
        }
    }

    public void updateCalorias(Utilizador user) {
        for (Atividade atividade: this.atividades) {
            atividade.calc_calorias(user);
        }
    }
}
