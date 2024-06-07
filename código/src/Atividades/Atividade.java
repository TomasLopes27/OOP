package Atividades;

import Utilizadores.Utilizador;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Atividades
 */
public abstract class Atividade implements Serializable {
    private int codigo;
    private double duracao; // em minutos
    private LocalDate data;
    private LocalTime hora;
    private double frequencia_cardiaca;

    /**
     * Construtor vazio
     */
    public Atividade (){
        this.codigo = -1;
        this.duracao = 0;
        this.frequencia_cardiaca = -1.0;
        this.data = null;
        this.hora = null;
    }

    /**
     * Construtor parametrizado
     * @param codigo
     * @param duracao
     * @param data
     * @param frequencia_cardiaca
     * @param hora
     */
    public Atividade (int codigo, int duracao, LocalDate data, double frequencia_cardiaca, LocalTime hora){
        this.codigo = codigo;
        this.duracao = duracao;
        this.data = data;
        this.frequencia_cardiaca = frequencia_cardiaca;
        this.hora = hora;
    }

    /**
     * Construtor por copia
     * @param atividade
     */
    public Atividade(Atividade atividade){
        this.codigo = atividade.getCodigo();
        this.duracao = atividade.getDuracao();
        this.data = atividade.getData();
        this.frequencia_cardiaca = atividade.getFrequenciaCardiaca();
        this.hora = atividade.getHora();
    }

    //----------------------------------Getters----------------------------------//
    public int getCodigo(){return this.codigo;}
    public double getDuracao(){return this.duracao;}
    public LocalDate getData(){return this.data;}
    public double getFrequenciaCardiaca(){return this.frequencia_cardiaca;}
    public LocalTime getHora(){return this.hora;}

    //----------------------------------Setters----------------------------------//
    public void setCodigo(int codigo) {this.codigo = codigo;}
    public void setDuracao(double duracao) {this.duracao = duracao;}
    public void setData(LocalDate data) {this.data = data;}
    public void setFrequencia_cardiaca(double frequencia_cardiaca) {this.frequencia_cardiaca = frequencia_cardiaca;}
    public void setHora(LocalTime hora) {this.hora = hora;}


    //----------------------------------FUNÇÕES BASICAS--------------------------//

    /**
     * Calcula as calorias da atividade tendo em conta o utilizador
     * @param user utilizador
     * @return calorias
     */
    public abstract double calc_calorias(Utilizador user); //Obriga a implementação deste método
    public abstract void setCalorias_gastas(double calorias_gastas);
    public abstract double getCalorias_gastas();

    @Override
     public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Código -> ").append(this.getCodigo()).append("\n")
                .append("Duração -> ").append(this.getDuracao()).append("\n")
                .append("Data -> ").append(this.getData()).append("\n")
                .append("Horas -> ").append(this.getHora()).append("\n")
                .append("Frequência Cardíaca -> ").append(this.getFrequenciaCardiaca());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Atividade ativ = (Atividade) o;
        return (this.data.isEqual(ativ.getData())
                && this.codigo == ativ.getCodigo()
                && this.duracao == ativ.getDuracao()
                && this.frequencia_cardiaca == ativ.getFrequenciaCardiaca());
    }

    @Override
    public abstract Atividade clone();



}
