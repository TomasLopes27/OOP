package Atividades;

import Utilizadores.Utilizador;

import java.time.LocalDate;
import java.time.LocalTime;

public class LegPress extends Atividade implements AtividadePesos, AtividadeRepeticoes{

    private int reps;
    private double peso;
    private double calorias_gastas;

    /**
     * Contrutor vazio
     */
    public LegPress() {
        this.reps = 0;
        this.peso= 0.0;
        this.calorias_gastas = -1.0;
    }

    /**
     * Construtor parametrizado
     * @param codigo
     * @param duracao
     * @param data
     * @param frequencia_cardiaca
     * @param reps
     * @param peso
     * @param horas
     */
    public LegPress(int codigo, int duracao, LocalDate data, double frequencia_cardiaca,
                    int reps, double peso, LocalTime horas) {
        super(codigo, duracao, data, frequencia_cardiaca,horas);
        this.calorias_gastas = 0.0;
        this.reps = reps;
        this.peso = peso;
    }

    /**
     * Construtor de copia
     * @param lp
     */
    public LegPress (LegPress lp) {
        super(lp);
        this.calorias_gastas = lp.getCalorias_gastas();
        this.peso = lp.getPeso();
        this.reps = lp.getNumReps();
    }

    @Override
    public double getCalorias_gastas() {
        return calorias_gastas;
    }

    @Override
    public double getPeso() {
        return peso;
    }

    @Override
    public int getNumReps() {
        return this.reps;
    }

    @Override
    public void setCalorias_gastas(double calorias_gastas) {
        this.calorias_gastas = calorias_gastas;
    }

    @Override
    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public void setNumReps(int num) {
        this.reps = num;
    }

    /**
     * Calcula as calorias da atividade tendo em conta o utilizador
     * @param user utilizador
     * @return calorias
     */
    @Override
    public double calc_calorias(Utilizador user) {
        double cal = 0.0;

        if (this.getFrequenciaCardiaca() / 80 < 1) {
            cal = reps * (peso / 95) * user.getFator_multiplicativo();
        } else {
            cal = this.getFrequenciaCardiaca() / 70 * reps * (peso / 100) * user.getFator_multiplicativo();
        }

        return cal;
    }

    @Override
    public LegPress clone() {
        return new LegPress(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LegPress lp = (LegPress) o;
        return this.reps == lp.getNumReps()
                && this.peso == lp.getPeso()
                && this.calorias_gastas == lp.getCalorias_gastas();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n***** Leg Press *****\n");
        sb.append(super.toString())
                .append("\nReps ->").append(this.reps)
                .append("\nPeso por rep -> ").append(this.peso)
                .append("\nCalorias gastas -> ").append(this.calorias_gastas);
        return sb.toString();
    }


}
