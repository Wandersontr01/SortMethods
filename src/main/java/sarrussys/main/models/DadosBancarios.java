package sarrussys.main.models;

import static java.lang.Double.parseDouble;

public class DadosBancarios {
    private String agencia;
    private String numero;
    private String saldo;

    public DadosBancarios(String agencia, String numero, String saldo){
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNumero() {
        return numero;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

}
