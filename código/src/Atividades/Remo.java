package Atividades;

import Utilizadores.Utilizador;

import java.time.LocalDate;
import java.time.LocalTime;

public class Remo extends Atividade implements AtividadeDistancia {
    private double calorias_gastas;
    private double distancia;
    private String percurso;

    /**
     * Contrutor vazio
     */
    public Remo(){
        super();
        this.distancia = 0;
        this.percurso = null;
        this.calorias_gastas = -1.0;
    }

    /**
     * Construtor parametrizado
     * @param codigo codigo da atividade
     * @param duracao duracao da atividade
     * @param data data da atividade
     * @param frequencia_cardiaca frequencia cardiaca da atividade
     * @param distancia distancia da atividade
     * @param percurso percurso da atividade
     * @param horas
     */
    public Remo(int codigo, int duracao, LocalDate data, double frequencia_cardiaca,double distancia, String percurso, LocalTime horas){
        super(codigo, duracao, data, frequencia_cardiaca, horas);
        this.calorias_gastas = 0.0;
        this.distancia = distancia;
        this.percurso = percurso;
    }

    /**
     * Construtor de copia
     * @param activ a ser copiado
     */
    public Remo(Remo activ){
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

    //----------------------------------FUNÇÕES DA CLASSE----------------------------------//

    /**
     * Calcula as calorias da atividade tendo em conta o utilizador
     * @param user utilizador
     * @return calorias
     */
    @Override
    public double calc_calorias (Utilizador user){
        double cal = 0.0;

        if (this.getFrequenciaCardiaca() / 80 < 1) {
            cal = this.getDistancia() * 0.05 * user.getFator_multiplicativo();
        } else {
            cal = (this.getFrequenciaCardiaca() / 80) * this.getDistancia() * 0.05 * user.getFator_multiplicativo();
        }

        return cal;
    }


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || o.getClass() != this.getClass()) return  false;
        if (!super.equals(o)) return false;
        Remo remo = (Remo) o;
        return (this.calorias_gastas == remo.getCalorias_gastas() && this.distancia == remo.getDistancia() && this.percurso.equals(remo.getPercurso()));
    }

    @Override
    public Remo clone(){
        return new Remo(this);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n***** Canoagem *****\n");
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
