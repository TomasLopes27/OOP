package Atividades;

import Utilizadores.Utilizador;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flexoes extends Atividade implements AtividadeRepeticoes{

    private int numReps;
    private double calorias_gastas;

    /**
     * Construtor por omissao
     */
    public Flexoes(){
        super();
        this.numReps = 0;
        this.calorias_gastas = -1.0;
    }

    /**
     * Contrutor parametrizado
     * @param codigo
     * @param duracao
     * @param data
     * @param frequencia_cardiaca
     * @param numReps
     * @param horas
     */
    public Flexoes(int codigo, int duracao, LocalDate data, double frequencia_cardiaca, int numReps, LocalTime horas){
        super(codigo, duracao, data, frequencia_cardiaca,horas);
        this.numReps = numReps;
        this.calorias_gastas = 0.0;
    }

    /**
     * Construtor de copia
     * @param flexoes
     */
    public Flexoes(Flexoes flexoes){
        super(flexoes);
        this.numReps = flexoes.getNumReps();
        this.calorias_gastas = flexoes.getCalorias_gastas();
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
            calorias = this.numReps/10 * user.getFator_multiplicativo();
        } else {
            calorias = this.getFrequenciaCardiaca() / 80 * this.numReps/10 * 0.3 * user.getFator_multiplicativo();
        }

        return calorias;
    }

    @Override
    public boolean equals(Object o){
        if(o == this) {return true;}
        if(o == null || o.getClass() != this.getClass()){return false;}
        if(!super.equals(o)) {return false;}
        Flexoes flexoes = (Flexoes) o;
        return (this.numReps == flexoes.getNumReps() && this.calorias_gastas == flexoes.getCalorias_gastas());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n**** Flexoes ****\n");
        sb.append(super.toString())
                .append("\nNúmero de repetições: ")
                .append(this.getNumReps())
                .append("\nCalorias Gastas: ")
                .append(this.getCalorias_gastas());
        return sb.toString();
    }
    @Override
    public Flexoes clone() {
        return new Flexoes(this);
    }


}
