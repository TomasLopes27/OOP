package Utilizadores;

import Atividades.Atividade;
import PlanoTreino.PlanoTreino;

import java.time.LocalDate;
import java.util.Map;

public class UtilizadorProfissional extends Utilizador {

    private static double multiplicador = 1.9;

    /**
     * Construtor vazio
     */
    public UtilizadorProfissional() {
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
    public UtilizadorProfissional (int codigo_utilizador, String nome, String morada, String email, int frequencia_cardiaca,
                                   Genero genero, double peso, int altura, LocalDate data_de_nascimento, double calorias_total, Map<Integer, Atividade> atividades,
                                   Map<Integer, PlanoTreino> planos_treino) {
        super(codigo_utilizador, nome, morada, email, frequencia_cardiaca, genero, peso, altura, data_de_nascimento, calorias_total, atividades, planos_treino);
        this.setFator_multiplicativo(calculo_multiplicador());
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
    public UtilizadorProfissional(int codigo_utilizador, String nome, String morada, String email, int frequencia_cardiaca,
                                  Genero genero, double peso, int altura, LocalDate data_de_nascimento) {
        super(codigo_utilizador, nome, morada, email, frequencia_cardiaca, genero, peso, altura, data_de_nascimento);
        this.setFator_multiplicativo(calculo_multiplicador());
    }

    /**
     * Construtor de cópia
     * @param u1 utilizador a copiar
     */
    public UtilizadorProfissional (UtilizadorProfissional u1) {
        super(u1);
        this.setFator_multiplicativo(calculo_multiplicador());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        return true;
    }

    /**
     * Calculador do multiplicador
     * @return multiplicador
     */
    @Override
    public double calculo_multiplicador() {
        double altura_multi = 0.0;
        double peso_multi = 0.0;

        if (this.getAltura() > 150) {
            altura_multi = (this.getAltura() - 150.0) / 1000.0;
        }

        if (this.getPeso() > 70) {
            peso_multi = (this.getPeso() - 70.0) / 180.0;
        }

        return multiplicador + ((peso_multi + altura_multi) * 0.55);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        return sb.toString();
    }

    @Override
    public UtilizadorProfissional clone() {
        return new UtilizadorProfissional(this);
    }

}
