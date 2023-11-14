package sarrussys.main.arvoreABB;

public class No {
    String cpf;
    int agencia;
    int numero;
    double saldo;
    No esquerda, direita;

    public No(String cpf, int agencia, int numero, double saldo) {
        this.cpf = cpf;
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.esquerda = this.direita = null;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }
}