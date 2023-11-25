package sarrussys.main.models;

public class ItemParaOrdenacao {
    private String cpf;
    private String agencia;
    private String numero;
    private String saldo;

    public ItemParaOrdenacao(String cpf, String agencia, String numero, String saldo){
        this.cpf = cpf;
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getChave() {
        return cpf;
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

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
