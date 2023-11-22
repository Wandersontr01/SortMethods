package sarrussys.main.models;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Item {
    private Long cpf;
    private String agencia;
    private String numero;
    private double saldo;

    public Item(String cpf, String agencia, String numero, String saldo){
        this.cpf = parseLong(cpf);
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = parseDouble(saldo);
    }

    public Long getChave() {
        return cpf;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
