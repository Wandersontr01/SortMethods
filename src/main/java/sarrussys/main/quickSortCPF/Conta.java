package sarrussys.main.quickSortCPF;

class Conta implements Comparable<Conta> {
    String agencia;
    String numero;
    String cpf;
    double saldo;

    public Conta(String agencia, String numero, String cpf, double saldo) {
        this.agencia = agencia;
        this.numero = numero;
        this.cpf = cpf;
        this.saldo = saldo;
    }

    @Override
    public int compareTo(Conta other) {
        int cpfCompare = this.cpf.compareTo(other.cpf);
        if (cpfCompare != 0) {
            return cpfCompare;
        } else {
            int agenciaCompare = this.agencia.compareTo(other.agencia);
            if (agenciaCompare != 0) {
                return agenciaCompare;
            } else {
                return this.numero.compareTo(other.numero);
            }
        }
    }
}