package sarrussys.main.hashing;

import java.util.Arrays;

public class Tabela {
    private static final int tamanho_tabela = 557;
    private NoHash[] tabela;

    public Tabela() {
        tabela = new NoHash[tamanho_tabela];
    }

    private int hash(String chave) {
        int valorHash = 0;
        for (int i = 0; i < chave.length(); i++) {
            valorHash = 31 * valorHash + chave.charAt(i);
        }
        return Math.abs(valorHash) % tamanho_tabela;
    }

    public void inserir(String cpf, String agencia, String conta, double saldo) {
        int indice = hash(cpf);
        NoHash novoNo = new NoHash(cpf, agencia, conta, saldo);

        if (tabela[indice] == null) {
            tabela[indice] = novoNo;
        } else {
            NoHash atual = tabela[indice];
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novoNo;
        }
    }

    public NoHash buscar(String cpf) {
        int indice = hash(cpf);
        NoHash atual = tabela[indice];

        while (atual != null) {
            if (atual.cpf.equals(cpf)) {
                return atual;
            }
            atual = atual.proximo;
        }

        return null;
    }

    public int getTamanho_tabela(){
        return this.getTamanho_tabela();
    }
}
