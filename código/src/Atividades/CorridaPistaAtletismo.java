package Atividades;

import Utilizadores.Utilizador;

import java.time.LocalDate;
import java.time.LocalTime;

public class CorridaPistaAtletismo extends Atividade implements AtividadeDistancia {
    private double calorias_gastas;
    private double distancia;
    private String percurso;

    /**
     * Construtor vazio
     */
    public CorridaPistaAtletismo (){
        super();
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
     * @param distancia
     * @param percurso
     * @param horas
     */
    public CorridaPistaAtletismo(int codigo, int duracao, LocalDate data, double frequencia_cardiaca, double distancia, String percurso, LocalTime horas){
        super(codigo, duracao, data, frequencia_cardiaca,horas);
        this.calorias_gastas = 0.0;
        this.distancia = distancia;
        this.percurso = percurso;
    }

    /**
     * Construtor por copia
     * @param activ
     */
    public CorridaPistaAtletismo(CorridaPistaAtletismo activ){
        super(activ);
        this.calorias_gastas = activ.getCalorias_gastas();
        this.distancia = activ.getDistancia();
        this.percurso = activ.getPercurso();
    }

    public double getCalorias_gastas(){return this.calorias_gastas;}

    @Override
    public double getDistancia(){return this.distancia;}

    @Override
    public String getPercurso(){return this.percurso;}

    @Override
    public void setCalorias_gastas(double calorias_gastas) {this.calorias_gastas = calorias_gastas;}
    @Override
    public void setDistancia(double dist){this.distancia = dist;}

    @Override
    public void setPercurso(String percurso){this.percurso = percurso;}

    /**
     * Calcula as calorias da atividade tendo em conta o utilizador
     * @param user utilizador
     * @return calorias
     */
    @Override
    public double calc_calorias(Utilizador user) {
        double calorias = 0.0;

        if (this.getFrequenciaCardiaca() / 75 < 1) {
            calorias = 53.14783/(this.getDistancia() / 1000) * (this.getDuracao()) * user.getFator_multiplicativo();
        } else {
            calorias = (this.getFrequenciaCardiaca() - 10.4859 / (this.getDistancia() / 1000) ) * (this.getDuracao()) * user.getFator_multiplicativo();
        }
        return calorias;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return  false;
        if (!super.equals(o)) return false;
        CorridaPistaAtletismo corrida = (CorridaPistaAtletismo) o;
        return (this.calorias_gastas == corrida.getCalorias_gastas() && this.distancia == corrida.getDistancia() && this.percurso.equals(corrida.getPercurso()));
    }

    @Override
    public CorridaPistaAtletismo clone(){
        return new CorridaPistaAtletismo(this);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n***** Corrida na Pista de Atletismo *****\n");
        sb.append(super.toString())
                .append("\nDistância: ")
                .append(this.getDistancia())
                .append("\nPercurso: ")
                .append(this.getPercurso())
                .append("\nCalorias Gastas: ")
                .append(this.getCalorias_gastas());
        return sb.toString();
    }




}
