package Atividades;

import Utilizadores.Utilizador;
import Utilizadores.UtilizadorAmador;
import Utilizadores.UtilizadorOcasional;
import Utilizadores.UtilizadorProfissional;

import java.time.LocalDate;
import java.time.LocalTime;

public class Abdominais extends Atividade implements AtividadeRepeticoes {
    private int numReps;
    private double calorias_gastas;

    /**
     * Construtor por omissão
     */
    public Abdominais(){
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
    public Abdominais(int codigo, int duracao, LocalDate data, double frequencia_cardiaca, int numReps, LocalTime horas){
        super(codigo, duracao, data, frequencia_cardiaca, horas);
        this.numReps = numReps;
        this.calorias_gastas = 0.0;
    }

    /**
     * Construtor por copia
     * @param abdominais
     */
    public Abdominais(Abdominais abdominais){
        super(abdominais);
        this.numReps = abdominais.getNumReps();
        this.calorias_gastas = abdominais.getCalorias_gastas();
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
            calorias = this.numReps * 0.3 * user.getFator_multiplicativo();
        } else {
            calorias = this.getFrequenciaCardiaca() / 80 * this.numReps * 0.3 * user.getFator_multiplicativo();
        }

        return calorias;
    }

    @Override
    public boolean equals(Object o){
        if(o == this) {return true;}
        if(o == null || o.getClass() != this.getClass()){return false;}
        if(!super.equals(o)) {return false;}
        Abdominais abdominais = (Abdominais) o;
        return (this.numReps == abdominais.getNumReps() && this.calorias_gastas == abdominais.getCalorias_gastas());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n**** Abdominais ****\n");
        sb.append(super.toString())
                .append("\nNúmero de repetições: ")
                .append(this.getNumReps())
                .append("\nCalorias Gastas: ")
                .append(this.getCalorias_gastas());
        return sb.toString();
    }
    @Override
    public Abdominais clone() {
        return new Abdominais(this);
    }

}
