package sarrussys.main.virus;

class NoABB {
    private String cpf;
    private int agencia;
    private int numero;
    private double saldo;
    private NoABB esquerda;
    private NoABB direita;

    public NoABB(String cpf, int agencia, int numero, double saldo) {
        this.cpf = cpf;
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.esquerda = this.direita = null;
    }

    public String getCpf() {
        return cpf;
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public NoABB getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoABB esquerda) {
        this.esquerda = esquerda;
    }

    public NoABB getDireita() {
        return direita;
    }

    public void setDireita(NoABB direita) {
        this.direita = direita;
    }
}