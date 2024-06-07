package Atividades;

import Utilizadores.Utilizador;

import java.time.LocalDate;
import java.time.LocalTime;

public class Elevacoes extends Atividade implements AtividadeRepeticoes {

    private int numReps;
    private double calorias_gastas;

    /**
     * Construtor vazio
     */
    public Elevacoes(){
        super();
        this.numReps = 0;
        this.calorias_gastas = -1.0;
    }

    /**
     * Construtor parametrizado
     * @param codigo
     * @param duracao
     * @param data
     * @param frequencia_cardiaca
     * @param numReps
     * @param horas
     */
    public Elevacoes(int codigo, int duracao, LocalDate data, double frequencia_cardiaca, int numReps, LocalTime horas){
        super(codigo, duracao, data, frequencia_cardiaca,horas);
        this.numReps = numReps;
        this.calorias_gastas = 0.0;
    }

    /**
     * Construtor de copia
     * @param elevacoes
     */
    public Elevacoes(Elevacoes elevacoes){
        super(elevacoes);
        this.numReps = elevacoes.getNumReps();
        this.calorias_gastas = elevacoes.getCalorias_gastas();
    }

    @Override
    public int getNumReps() {return this.numReps;}

    @Override
    public double getCalorias_gastas(){return this.calorias_gastas;}

    @Override
    public void setNumReps(int num) {this.numReps = num;}

    @Override
    public void setCalorias_gastas(double calorias_gastas){this.calorias_gastas = calorias_gastas;}

    /**
     * Calcula as calorias da atividade tendo em conta o utilizador
     * @param user utilizador
     * @return calorias
     */
    @Override
    public double calc_calorias(Utilizador user) {
        double calorias;

        if (this.getFrequenciaCardiaca() / 80 < 1) {
            calorias = this.numReps * 0.245 * user.getFator_multiplicativo();
        } else {
            calorias = this.getFrequenciaCardiaca() / 67.45 * this.numReps * 0.145 * user.getFator_multiplicativo();
        }

        return calorias;
    }

    @Override
    public boolean equals(Object o){
        if(o == this) {return true;}
        if(o == null || o.getClass() != this.getClass()){return false;}
        if(!super.equals(o)) {return false;}
        Elevacoes elevacoes = (Elevacoes) o;
        return (this.numReps == elevacoes.getNumReps() && this.calorias_gastas == elevacoes.getCalorias_gastas());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n**** Elevacoes ****\n");
        sb.append(super.toString())
                .append("\nNúmero de repetições: ")
                .append(this.getNumReps())
                .append("\nCalorias Gastas: ")
                .append(this.getCalorias_gastas());
        return sb.toString();
    }

    @Override
    public Elevacoes clone() {
        return new Elevacoes(this);
    }


}
