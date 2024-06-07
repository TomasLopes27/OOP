package Atividades;

import Utilizadores.Utilizador;

import java.time.LocalDate;
import java.time.LocalTime;

public class PassadeiraEscada extends  Atividade implements AtividadeAltimetria, AtividadeDistancia {

    private double altimetria;
    private double distancia;
    private String percurso;
    private double calorias_gastas;

    /**
     * Construtor vazio
     */
    public PassadeiraEscada(){
        this.altimetria = 0;
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
     * @param altimetria altimetria da atividade
     * @param distancia distancia da atividade
     * @param percurso percurso
     * @param horas horas da atividade
     */
    public PassadeiraEscada(int codigo, int duracao, LocalDate data, double frequencia_cardiaca, double altimetria,
                            double distancia, String percurso, LocalTime horas){
        super(codigo,duracao,data,frequencia_cardiaca,horas);
        this.altimetria = altimetria;
        this.distancia = distancia;
        this.percurso = percurso;
        this.calorias_gastas = 0.0;    // Faz o calculo das calorias e coloca no this.calorias
    }

    /**
     * Construtor de copia
     * @param passadeiraEscada a copiar
     */
    public PassadeiraEscada(PassadeiraEscada passadeiraEscada){
        super(passadeiraEscada);
        this.altimetria = passadeiraEscada.getAltimetria();
        this.distancia = passadeiraEscada.getDistancia();
        this.percurso = passadeiraEscada.getPercurso();
        this.calorias_gastas = passadeiraEscada.getCalorias_gastas();
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
            calorias = 70.19651 * this.getAltimetria()/(this.getDistancia()*1.56) * user.getFator_multiplicativo();
        } else {
            calorias = (1 + this.getFrequenciaCardiaca()) * this.getAltimetria()/(this.getDistancia()*1.56) * user.getFator_multiplicativo();
        }
        return calorias;
    }


    @Override
    public boolean equals(Object o){
        if(o == this) {return true;}
        if(o == null || o.getClass() != this.getClass()){return false;}
        if(!super.equals(o)) {return false;}
        PassadeiraEscada escada = (PassadeiraEscada) o;
        return (escada.getAltimetria() == this.altimetria && escada.getDistancia() == this.distancia &&
                escada.getCalorias_gastas() == this.calorias_gastas && escada.percurso.equals(this.percurso));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n***** Passadeira em escada *****\n");
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
    @Override
    public PassadeiraEscada clone() {
        return new PassadeiraEscada(this);
    }
}
