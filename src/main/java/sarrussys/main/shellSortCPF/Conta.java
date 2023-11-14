package sarrussys.main.shellSortCPF;

import java.util.Comparator;

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

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public int compareTo(Conta other) {
        return Comparator.comparing(Conta::getCpf)
                .thenComparing(Conta::getAgencia)
                .thenComparing(Conta::getNumero)
                .compare(this, other);
    }
}