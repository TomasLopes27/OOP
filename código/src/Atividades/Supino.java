    package Atividades;

    import Utilizadores.Utilizador;

    import java.time.LocalDate;
    import java.time.LocalTime;

    /**
     * Classe do supino
     */
    public class Supino extends Atividade implements AtividadePesos, AtividadeRepeticoes{
    private int reps;
    private double peso;
    private double calorias_gastas;

    /**
     * Construtor vazio
     */
    public Supino() {
        this.reps = 0;
        this.peso= 0.0;
        this.calorias_gastas = -1.0;
    }

        /**
         * Construtor parametrizado
         * @param codigo codigo da atividade
         * @param duracao duracao da atividade
         * @param data data da atividade
         * @param frequencia_cardiaca frequencia cardiaca da atividade
         * @param reps numero de repeticoes
         * @param peso peso
         * @param horas horas da atividade
         */
    public Supino(int codigo, int duracao, LocalDate data, double frequencia_cardiaca,
                  int reps, double peso, LocalTime horas) {
        super(codigo, duracao, data, frequencia_cardiaca, horas);
        this.calorias_gastas = 0.0;
        this.reps = reps;
        this.peso = peso;
    }

        /**
         * Construtor de copia
         * @param s1 a ser copiado
         */
    public Supino (Supino s1) {
        super(s1);
        this.calorias_gastas = s1.getCalorias_gastas();
        this.peso = s1.getPeso();
        this.reps = s1.getNumReps();
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
            cal = reps * (peso / 100) * user.getFator_multiplicativo();
        } else {
            cal = this.getFrequenciaCardiaca() / 80 * reps * (peso / 100) * user.getFator_multiplicativo();
        }

        return cal;
    }

    @Override
    public Atividade clone() {
        return new Supino(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Supino sup1 = (Supino) o;
        return this.reps == sup1.getNumReps()
                && this.peso == sup1.getPeso()
                && this.calorias_gastas == sup1.getCalorias_gastas();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n***** Supino *****\n");
        sb.append(super.toString())
                .append("\nReps ->").append(this.reps)
                .append("\nPeso por rep -> ").append(this.peso)
                .append("\nCalorias gastas -> ").append(this.calorias_gastas);
        return sb.toString();
    }




}
