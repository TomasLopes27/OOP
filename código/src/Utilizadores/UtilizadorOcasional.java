
package Utilizadores;

import Atividades.Atividade;
import PlanoTreino.PlanoTreino;

import java.time.LocalDate;
import java.util.Map;

public class UtilizadorOcasional extends Utilizador {

    private static double multiplicador = 1.37;

    /**
     * Construtor vazior
     */
    public UtilizadorOcasional() {
        super();
    }

    /**
     * Construtor parametrizado
     * @param codigo_utilizador id do utilizador
     * @param nome nome do utilizador
     * @param morada morada do utilizador
     * @param email email do utilizador
     * @param frequencia_cardiaca frequencia cardiaca do utilizador
     * @param genero genero do utilizador
     * @param peso peso do utilizador
     * @param altura altura do utilizador
     * @param data_de_nascimento data de nascimento do utilizador
     * @param calorias_total calorias gastas pelo utilizador
     * @param atividades atividades
     * @param planos_treino planos de treino
     */
    public UtilizadorOcasional(int codigo_utilizador, String nome, String morada, String email, int frequencia_cardiaca,
                               Genero genero, double peso, int altura, LocalDate data_de_nascimento, double calorias_total, Map<Integer, Atividade> atividades,
                               Map<Integer, PlanoTreino> planos_treino) {
        super(codigo_utilizador, nome, morada, email, frequencia_cardiaca, genero, peso, altura, data_de_nascimento, calorias_total, atividades, planos_treino);
        setFator_multiplicativo(calculo_multiplicador());
    }

    /**
     * Construtor parametrizado
     * @param codigo_utilizador id do utilizador
     * @param nome nome do utilizador
     * @param morada morada do utilizador
     * @param email email do utilizador
     * @param frequencia_cardiaca frequencia cardiaca do utilizador
     * @param genero genero do utilizador
     * @param peso peso do utilizador
     * @param altura altura do utilizador
     * @param data_de_nascimento data de nascimento do utilizador
     */
    public UtilizadorOcasional(int codigo_utilizador, String nome, String morada, String email, int frequencia_cardiaca,
                               Genero genero, double peso, int altura, LocalDate data_de_nascimento) {
        super(codigo_utilizador, nome, morada, email, frequencia_cardiaca, genero, peso, altura, data_de_nascimento);
        setFator_multiplicativo(calculo_multiplicador());
    }

    /**
     * Construtor de copia
     * @param o1 utilizador a copiar
     */
    public UtilizadorOcasional(UtilizadorOcasional o1) {
        super(o1);
        setFator_multiplicativo(calculo_multiplicador());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        return true;
    }

    /**
     * Calculo do multiplicador
     * @return multiplicador
     */
    @Override
    public double calculo_multiplicador() {
        double altura_multi = 0.0;
        double peso_multi = 0.0;

        if (this.getAltura() > 160) {
            altura_multi = (this.getAltura() - 160.0) / 1200.0;
        }

        if (this.getPeso() > 80.0) {
            peso_multi = (this.getPeso() - 80.0) / 220.0;
        }

        return multiplicador + ((peso_multi + altura_multi) * 0.5);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        return sb.toString();
    }

    @Override
    public UtilizadorOcasional clone() {
        return new UtilizadorOcasional(this);
    }

}
