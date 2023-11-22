package sarrussys.main.arvoreABB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Arvore {
    private No raiz;

    public Arvore() {
        this.raiz = null;
    }

    public void inserir(String cpf, String agencia, String numero, double saldo) {
        raiz = inserirRec(raiz, cpf, agencia, numero, saldo);
    }

    private No inserirRec(No no, String cpf, String agencia, String numero, double saldo) {
        if (no == null) {
            return new No(cpf, agencia, numero, saldo);
        }

        if (cpf.compareTo(no.cpf) < 0) {
            no.esquerda = inserirRec(no.esquerda, cpf, agencia, numero, saldo);
        } else if (cpf.compareTo(no.cpf) > 0) {
            no.direita = inserirRec(no.direita, cpf, agencia, numero, saldo);
        } else {
            // CPF já existe, pode lidar com isso conforme necessário
        }

        return no;
    }


    private int altura(No no) {
        if (no == null) {
            return 0;
        }
        return 1 + Math.max(altura(no.esquerda), altura(no.direita));
    }

    private int fatorBalanceamento(No no) {
        if (no == null) {
            return 0;
        }
        return altura(no.esquerda) - altura(no.direita);
    }

    private No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        return y;
    }

    private No balancearRec(No no) {
        if (no == null) {
            return null;
        }

        int fator = fatorBalanceamento(no);

        if (fator > 1) {
            // Caso de desequilíbrio à esquerda
            if (fatorBalanceamento(no.esquerda) < 0) {
                no.esquerda = rotacaoEsquerda(no.esquerda);
            }
            return rotacaoDireita(no);
        } else if (fator < -1) {
            // Caso de desequilíbrio à direita
            if (fatorBalanceamento(no.direita) > 0) {
                no.direita = rotacaoDireita(no.direita);
            }
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public void balancear() {
        raiz = balancearRec(raiz);
    }




    private double pesquisarCpfRec(No no, String cpf, FileWriter resultadoFile) throws IOException {
        if (no == null) {
            resultadoFile.write("CPF " + cpf + ":\nINEXISTENTE\n\n");
            return 0.0;  // Retorna 0.0 se o CPF não for encontrado
        }

        double saldoTotal = 0.0;

        if (cpf.compareTo(no.cpf) < 0) {
            saldoTotal += pesquisarCpfRec(no.esquerda, cpf, resultadoFile);
        } else if (cpf.compareTo(no.cpf) > 0) {
            saldoTotal += pesquisarCpfRec(no.direita, cpf, resultadoFile);
        } else {
            // CPF encontrado
            resultadoFile.write("CPF " + cpf + ":\n");
            saldoTotal += escreverInformacoes(no, resultadoFile);
            resultadoFile.write("Saldo Total: " + saldoTotal + "\n\n");
        }

        return saldoTotal;
    }

    public void pesquisarCpf(String cpf, FileWriter resultadoFile) throws IOException {
        pesquisarCpfRec(raiz, cpf, resultadoFile);
    }

    private double escreverInformacoes(No no, FileWriter resultadoFile) throws IOException {
        double saldoTotal = no.saldo;

        resultadoFile.write("agencia " + no.agencia + " Conta " + no.numero + " Saldo: " + no.saldo + "\n");

        if (no.esquerda != null) {
            saldoTotal += escreverInformacoes(no.esquerda, resultadoFile);
        }
        if (no.direita != null) {
            saldoTotal += escreverInformacoes(no.direita, resultadoFile);
        }

        return saldoTotal;
    }

}