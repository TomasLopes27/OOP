package Atividades;

import Utilizadores.Utilizador;

import java.sql.BatchUpdateException;
import java.time.LocalDate;
import java.time.LocalTime;

public class BicicletaMontanha extends  Atividade implements AtividadeAltimetria, AtividadeDistancia, Hard {

    private double altimetria;
    private double distancia;
    private String percurso;
    private double calorias_gastas;


    /**
     * Construtor vazio
     */
    public BicicletaMontanha(){
        super();
        this.altimetria = 0;
        this.distancia = 0;
        this.percurso = null;
        this.calorias_gastas = -1.0;
    }

    /**
     * Construtor parametrizado
     * @param codigo
     * @param duracao
     * @param data
     * @param frequencia_cardiaca
     * @param altimetria
     * @param distancia
     * @param percurso
     * @param horas
     */
    public BicicletaMontanha(int codigo, int duracao, LocalDate data, double frequencia_cardiaca, double altimetria,
                             double distancia, String percurso, LocalTime horas)
    {
        super(codigo,duracao,data,frequencia_cardiaca,horas);
        this.altimetria = altimetria;
        this.distancia = distancia;
        this.percurso = percurso;
        this.calorias_gastas = 0.0;    // Faz o calculo das calorias e coloca no this.calorias
    }

    /**
     * Construtor de copia
     * @param bike
     */
    public BicicletaMontanha(BicicletaMontanha bike){
        super(bike);
        this.altimetria = bike.getAltimetria();
        this.distancia = bike.getDistancia();
        this.percurso = bike.getPercurso();
        this.calorias_gastas = bike.getCalorias_gastas();
    }



    @Override
    public double getAltimetria() {return this.altimetria;}

    @Override
    public double getDistancia() {return this.distancia;}

    @Override
    public String getPercurso() {return this.percurso;}

    @Override
    public double getCalorias_gastas(){return this.calorias_gastas;}


    @Override
    public void setAltimetria(double altimetria) {this.altimetria = altimetria;}

    @Override
    public void setDistancia(double dist) {this.distancia = dist;}

    @Override
    public void setPercurso(String percurso) {this.percurso = percurso;}

    @Override
    public void setCalorias_gastas(double calorias_gastas) {this.calorias_gastas = calorias_gastas;}

    /**
     * Calcula as calorias da atividade tendo em conta o utilizador
     * @param user utilizador
     * @return calorias
     */
    @Override
    public double calc_calorias(Utilizador user) {
        double calorias = 0.0;

        if (this.getFrequenciaCardiaca() / 80 < 1) {
            calorias = (this.getDistancia() / 1400) * (this.getAltimetria() / 10) * 7.594 * user.getFator_multiplicativo();
        } else {
            calorias = (this.getFrequenciaCardiaca() / 80) * (this.getDistancia() / 1400) * (this.getAltimetria() / 10) * 7.594 * user.getFator_multiplicativo();
        }

        
        return calorias;
    }

    @Override
    public boolean equals(Object o){
        if(o == this) {return true;}
        if(o == null || o.getClass() != this.getClass()){return false;}
        if(!super.equals(o)) {return false;}
        BicicletaMontanha bike = (BicicletaMontanha) o;
        return (bike.getAltimetria() == this.altimetria && bike.getDistancia() == this.distancia &&
                bike.getCalorias_gastas() == this.calorias_gastas && bike.percurso.equals(this.percurso));

    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n***** Ciclismo na Montanha *****\n");
        sb.append(super.toString())
                .append("\nDistância: ")
                .append(this.getDistancia())
                .append("\nAltimetria: ")
                .append(this.getAltimetria())
                .append("\nPercurso: ")
                .append(this.getPercurso())
                .append("\nCalorias Gastas: ")
                .append(this.getCalorias_gastas());

        return sb.toString();
    }


    public BicicletaMontanha clone(){return new BicicletaMontanha(this);}

}
