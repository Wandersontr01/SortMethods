package sarrussys.main.hashing;

public class NoHash {
    String cpf;
    String agencia;
    String conta;
    double saldo;
    NoHash proximo;

    public NoHash(String cpf, String agencia, String conta, double saldo) {
        this.cpf = cpf;
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.proximo = null;
    }
}
