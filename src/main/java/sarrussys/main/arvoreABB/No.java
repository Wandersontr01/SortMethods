package sarrussys.main.arvoreABB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class No {
    String cpf;
    String agencia;
    String numero;
    double saldo;
    No esquerda, direita;

    public No(String cpf, String agencia, String numero, double saldo) {
        this.cpf = cpf;
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.esquerda = this.direita = null;
    }
}
